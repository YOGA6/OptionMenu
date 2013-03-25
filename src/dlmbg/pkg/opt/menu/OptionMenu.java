package dlmbg.pkg.opt.menu;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class OptionMenu extends Activity {
	EditText etMessage1;
	EditText etMessage2;
	Integer[] arrayPointSize = {10, 20, 30, 40, 50};
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	etMessage1 = (EditText)findViewById(R.id.etMessage1);
	etMessage2 = (EditText)findViewById(R.id.etMessage2);
	// you may register an individual context menu for each view
	registerForContextMenu(etMessage1);
	registerForContextMenu(etMessage2);
	} //onCreate
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// only one Option menu per activity
		populateMyFirstMenu(menu);
		return super.onCreateOptionsMenu(menu);
		} 
		// detect what view is calling and create its context menu
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// decide what context menu needs to be made
		if (v.getId() == etMessage1.getId())
		// create a menu for etMessage1 box
		populateMyFirstMenu(menu);
		if (v.getId() == etMessage2.getId()){
		// create a menu for etMessage2 box
		populateMySecondMenu(menu);
		}
		} //onCreateContextMenu
		
		private void populateMyFirstMenu(Menu menu){
			int groupId = 0; int order= 0;
			//arguments: groupId, optionId, order, title
			menu.add(groupId, 1, 1, "10 px");
			menu.add(groupId, 2, 2, "20 px");
			menu.add(groupId, 3, 3, "30 px");
			menu.add(groupId, 4, 4, "40 px");
			menu.add(groupId, 5, 5, "50 px");
			menu.add(groupId, 6, 8, "Red text");
			menu.add(groupId, 7, 7, "Green Text");
			menu.add(groupId, 8, 6, "Blue text");
			} //populateMyMenu
			private void populateMySecondMenu(Menu menu){
			int groupId = 0; int order= 0;
			//arguments: groupId, optionId, order, title
			menu.add(groupId,  9, 1, "Bold");
			menu.add(groupId, 10, 2, "Italic");
			menu.add(groupId, 11, 3, "Normal");
			}//populateMySecondMenu

			@Override
			public boolean onContextItemSelected(MenuItem item) {
			return(applyMenuOption(item));
			}
			@Override
			public boolean onOptionsItemSelected(MenuItem item) {
			return(applyMenuOption(item));
			}
		
			private boolean applyMenuOption(MenuItem item){
				int menuItemId = item.getItemId();  // 1, 2, 3, ...11
				String strMsg2 = etMessage2.getText().toString();
				if (menuItemId <= 5) { 
				// first five option are for setting text size
				int newPointSize = arrayPointSize[menuItemId - 1];
				etMessage1.setTextSize(newPointSize);
				etMessage2.setTextSize(newPointSize);
				}
				else {
				// either change color on text1 or style on text2
				if (menuItemId == 6)
				etMessage1.setTextColor(0xffff0000); // red
				else if (menuItemId == 7)
				etMessage1.setTextColor(0xff00ff00); // green
				else if (menuItemId == 8)
				etMessage1.setTextColor(0xff0000ff); // blue
				else if (menuItemId == 9)
				etMessage2.setText(beautify(strMsg2, "BOLD")); //bold
				else if (menuItemId == 10)
				etMessage2.setText(beautify(strMsg2, "ITALIC")); //italic
				else if (menuItemId == 11)
				etMessage2.setText(beautify(strMsg2, "NORMAL")); //normal
				}
				return false;
				} //applyMenuOption
			
			// changing text style using HTML formatting
			// Spanned is text to which you could add formatting features  
			private Spanned beautify (String originalText, String selectedStyle){
			Spanned answer = null;
			if (selectedStyle.equals("BOLD"))
			answer = Html.fromHtml("<b>" + originalText +"</b");
			else if (selectedStyle.equals("ITALIC"))
			answer = Html.fromHtml("<i>" + originalText +"</i>");
			else if (selectedStyle.equals("NORMAL"))
			answer = Html.fromHtml("<normal>" + originalText +"</normal");
			return answer;
			} //beautify
}
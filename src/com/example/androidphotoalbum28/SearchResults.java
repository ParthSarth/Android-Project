package com.example.androidphotoalbum28;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

public class SearchResults extends Activity{

	GridView gv;
	TextView tv;
	public static ArrayList<String> results;
	PhotoAdapter pa;
	ArrayList<String> photos;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.searchresults);
	   // photos = new ArrayList<String>();
	   // photonums = new ArrayList<Integer>();
	    String name = "";
	    photos = new ArrayList<String>();
	    
	    for (int x = 0; x<results.size(); x++){
	    	 
	    	 name = results.get(x);
	    	int photonum = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
	    	
	    	
	    		photos.add(name);
	    	
	    }
	    
	    	pa = new PhotoAdapter(this, photos, 0);
	    
	    
	    tv = (TextView)findViewById(R.id.sresults);
	    gv =  (GridView) findViewById(R.id.sthumb);
	    tv.setText("Search Results:");
	    gv.setAdapter(pa);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchoptions, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem i){
		if (i.getItemId() == R.id.sback){
			finish();
		}
		return true;
	}
	
}
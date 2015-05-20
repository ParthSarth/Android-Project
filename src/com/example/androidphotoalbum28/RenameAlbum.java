package com.example.androidphotoalbum28;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.widget.*;

public class RenameAlbum extends Activity {
	
	
	public static final String OLDALBUM = "Old Album";
	public static final String NEWALBUM = "New Album";
	
	
	EditText oldalbumName, newalbumName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.renamealbum);
		
		oldalbumName = (EditText)findViewById(R.id.oldalbumName);
		newalbumName = (EditText)findViewById(R.id.newalbumName);
		
		
	}

	// called when the user taps the Save button
	public void save(View view) {
		// gather all data
		String oldalbum = oldalbumName.getText().toString();
		String newalbum = newalbumName.getText().toString();
		
		// name and artist are mandatory
		if ((oldalbum == null || oldalbum.length() == 0) || (newalbum==null || newalbum.length()==0)){
			Toast
			.makeText(this, "Album name is required for both", Toast.LENGTH_SHORT)
			.show();
			return;   // does not quit activity, just returns from method
		}
		
		// make Bundle
		Bundle bundle = new Bundle();
		bundle.putString(OLDALBUM, oldalbum);
		bundle.putString(NEWALBUM, newalbum);
		
		
		// send back to caller
		Intent intent = new Intent();
		intent.putExtras(bundle);
		
		setResult(3,intent);
		finish();  // IMPORTANT!! Otherwise, control won't go back to caller
	}
	
	// called when the user taps the Cancel button
	public void cancel(View view) {
		setResult(RESULT_CANCELED);
		finish();
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.add_song, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}


}

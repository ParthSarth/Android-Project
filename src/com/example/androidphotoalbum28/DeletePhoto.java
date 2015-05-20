package com.example.androidphotoalbum28;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.widget.*;

public class DeletePhoto extends Activity {
	
	
	public static final String PHOTO = "Photo";
	//public static final String CAPTION = "";
	
	
	EditText photoname;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletephoto);
		
		photoname = (EditText)findViewById(R.id.photoname);
		
		
	}

	// called when the user taps the Save button
	public void save(View view) {
		// gather all data
		String photo = photoname.getText().toString();
		
		// name and artist are mandatory
		if (photo == null || photo.length() == 0) {
			Toast
			.makeText(this, "Photo filename is required", Toast.LENGTH_SHORT)
			.show();
			return;   // does not quit activity, just returns from method
		}
		
		// make Bundle
		Bundle bundle = new Bundle();
		bundle.putString(PHOTO, photo);
		
		
		// send back to caller
		Intent intent = new Intent();
		intent.putExtras(bundle);
		
		setResult(2,intent);
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


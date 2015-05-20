package com.example.androidphotoalbum28;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;
import android.view.*;
import android.widget.*;

public class ViewPhoto extends Activity {
	
	ImageView iv;
	TextView tv;
	public static Controller c;
	public static Photo p;
	public static int photoid;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewphoto);
		
		iv = (ImageView)findViewById(R.id.viewphoto);
		tv = (TextView)findViewById(R.id.tags);
		Bitmap b = BitmapFactory.decodeFile(p.getPhotoName());
		iv.setImageBitmap(b);
		tv.setText("Tags:\n");
		for (int x = 0; x<p.getTags().size(); x++){
			tv.append(p.getTags().get(x).toString()+"\n");
		}
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tagoptions, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem mi){
		Intent i = null;
		if (mi.getItemId() == R.id.addtag){
			i = new Intent(this, AddTag.class);
			startActivityForResult(i, 0);
		}
		else if (mi.getItemId() == R.id.deletetag){
			i = new Intent(this, DeletePhoto.class);
			startActivityForResult(i, 0);
		}
		else if (mi.getItemId() == R.id.vback){
			finish();
		}
		return true;
	}
	
	protected void onActivityResult(int start, int end, Intent data){
		String type = null;
		String val = null;
		
		if (end == 1){
			type = data.getExtras().getString(AddTag.TT);
			val = data.getExtras().getString(AddTag.TV);
			p.addTag(type, val);
			tv.setText("");
			for (int x = 0; x<p.getTags().size(); x++){
				tv.append(p.getTags().get(x).toString()+"\n");
			}
			try {
				c.back.write(this);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (end == 2){
			type = data.getExtras().getString(AddTag.TT);
			val = data.getExtras().getString(AddTag.TV);
			p.deleteTag(type, val);
			tv.setText("");
			for (int x = 0; x<p.getTags().size(); x++){
				tv.append(p.getTags().get(x).toString()+"\n");
			}
			try {
				c.back.write(this);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

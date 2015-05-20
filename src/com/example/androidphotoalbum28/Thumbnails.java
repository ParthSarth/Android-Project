package com.example.androidphotoalbum28;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.*;
import android.app.ActionBar.LayoutParams;
import android.content.*;
import android.content.ClipData.Item;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;


public class Thumbnails extends Activity {

ArrayList<Integer> photonums;
ArrayList<String> photos;
GridView gv;
//public static ArrayList<Photo> photos;
public static Controller c = null;
public static Album a = null;
PhotoAdapter pa;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.thumbnails);
	    photos = new ArrayList<String>();
	    photonums = new ArrayList<Integer>();
	    String name = "";
	    
	    
	    for (int x = 0; x<a.getPhotos().size(); x++){
	    	 
	    	 name = a.getPhotos().get(x).getPhotoName();
	    	int photonum = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
	    	
	    		photos.add(name);
	    		photonums.add(photonum);
	    	
	    }
	    
	    	pa = new PhotoAdapter(this, photos, 0);
	    
	    
	  
	    gv =  (GridView) findViewById(R.id.thumb);
	    gv.setAdapter(pa);
	    gv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) { 
                viewphoto(position);
                 
                }    });

	    //ImageView img;
	   
	}
	
	public void viewphoto(int position){
		Intent i = new Intent(this, ViewPhoto.class);
		ViewPhoto.c = this.c;
		Controller c2 = this.c;
		ViewPhoto.p = a.getPhotos().get(position);
		ViewPhoto.photoid = this.getResources().getIdentifier(a.getPhotos().get(position).getPhotoName(), "drawable", this.getPackageName());
		startActivity(i);
		
	}
	    
	    
//	    images = new ArrayList<Integer>();
//	    for (int x = 0; x<photos.size(); x++){
//	    	images.add(this.getResources().getIdentifier(photos.get(x).getPhotoName(), null, this.getPackageName()));
//	    }
//	    images.add(R.drawable.test1);
//	    images.add(R.drawable.test2);
//	    images.add(R.drawable.test3);
//	    PhotoAdapter pa = new PhotoAdapter(this, R.layout.singlethumbnail, images);
//	    lv.setAdapter(pa);
//	}
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photooptions, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem mi){
		Intent i = null;
		if (mi.getItemId() == R.id.addphoto){
			i = new Intent(this, AddPhoto.class);
			startActivityForResult(i, 0);
		}
		else if (mi.getItemId() == R.id.deletephoto){
			i = new Intent(this, DeletePhoto.class);
			startActivityForResult(i, 0);
		}
		else if (mi.getItemId() == R.id.movephoto){
			
		}
		else if (mi.getItemId() == R.id.tback){
			finish();
		}
		return true;
	}
	
	protected void onActivityResult(int start, int end, Intent data){
	
		super.onActivityResult(start, end, data);
		Intent i = data;
		String[] ops = null;
		Bundle b = null;
		if (i != null){
			b = i.getExtras();
		}
		if (end == 1){
			String photoname = b.getString(AddPhoto.PHOTO);
			ops = new String[4];
			File f = new File(Environment.getExternalStorageDirectory()+"/"+photoname);
			String photopath = f.getAbsolutePath();//
			///Bitmap test2 = BitmapFactory.decodeFile(photopath);
			
			//LayoutInflater inflater = getLayoutInflater();
	        //View view = inflater.inflate(R.layout.toast_layout,
	          //                             (ViewGroup) findViewById(R.id.relativeLayout2));
	        //view.setBackgroundResource(myImgAdapter.getImgID(position));
	        //view.set
//	        ImageView view = new ImageView(this.getBaseContext());
//	        view.setImageBitmap(test2);
//	        Toast toast = new Toast(this.getBaseContext());
//	        toast.setView(view);
//	        toast.show();

//			
			ops[0] = "addPhoto"; ops[1] = photopath; ops[2] = ""; ops[3] = a.getAlbumName();
			c.selectAction(ops);
			photonums.clear();
			String name = "";
			photos.clear();
			for (int x = 0; x<a.getPhotos().size(); x++){
					name =	a.getPhotos().get(x).getPhotoName();
			    	int photonum = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
			    		photos.add(name);
			    		photonums.add(photonum);
			    	
			}
			
				pa = new PhotoAdapter(this, photos, 0);
			
			gv.setAdapter(pa);
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
			String photoname = b.getString(DeletePhoto.PHOTO);
//			File f = new File(Environment.getExternalStorageDirectory()+"/"+photoname);
//			String photopath = f.getAbsolutePath();
			ops = new String[3];
			ops[0] = "removePhoto"; ops[1] = photoname; ops[2] = a.getAlbumName();
			c.selectAction(ops);
			photonums.clear();
			String name = "";
			photos.clear();
			for (int x = 0; x<a.getPhotos().size(); x++){
					
					name = a.getPhotos().get(x).getPhotoName();
			    	int photonum = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
			    	
			    	
			    		photonums.add(photonum);
			    		photos.add(name);
			    	
			}
			
				pa = new PhotoAdapter(this, photos, 0);
			
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

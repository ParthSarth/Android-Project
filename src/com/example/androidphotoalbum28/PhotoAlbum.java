package com.example.androidphotoalbum28;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class PhotoAlbum extends Activity implements android.widget.AdapterView.OnItemClickListener {


	ListView lv;     // list view of route names
    ArrayList<String> albumNames;   // route names read from array in strings.xml
    //String[] routeDetails; // route details read from raw file routes.txt
    Controller c ;//= new Controller();
    // keys for shipping bundle to ShowRoute activity
    ArrayAdapter<String> albumadapter;
    public static final String ROUTE_NAME_KEY = "albumName";
    //public static final String ROUTE_DETAIL_KEY = "routeDetail";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.albumlistview);
		c = new Controller(this);
		lv = (ListView)findViewById(R.id.listview);
		
		albumNames = this.c.user.getAlbumNames();
		String[] albums = new String[albumNames.size()];
		
		
		albumadapter = new ArrayAdapter<String>(this, R.layout.albumtext, albumNames);
		lv.setAdapter(albumadapter);
		lv.setOnItemClickListener(this);
		
		
	}



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (parent == lv){
			String albumname = albumadapter.getItem(position);
			showphotothumbnails(albumname);
		}
		
	}
	
	public void showphotothumbnails(String albumname){
		//ArrayList<Photo> photos = this.c.user.getAlbum(albumname).getPhotos();
		//photos.add(new Photo("drawable/test1"));
		//photos.add(new Photo("drawable/test2"));
		//photos.add(new Photo("drawable/test3"));
		//Thumbnails.photos = photos;
		
		Intent i = new Intent(this, Thumbnails.class);
		Thumbnails.c = this.c;
		Thumbnails.a = this.c.user.getAlbum(albumname);
		
		startActivity(i);
		
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.albumoptions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem i){
		Intent intent = null;
		if (i.getItemId() == R.id.addalbum){
			intent = new Intent(this, AddAlbum.class);
			startActivityForResult(intent, 1);
			return true;
		}
		else if (i.getItemId() == R.id.deletealbum){
			intent = new Intent(this, DeleteAlbum.class);
			startActivityForResult(intent, 1);
			return true;
		}
		else if (i.getItemId() == R.id.renamealbum){
			intent = new Intent(this, RenameAlbum.class);
			startActivityForResult(intent, 1);
			return true;
		}
		else if (i.getItemId() == R.id.search){
			intent = new Intent(this, Search.class);
			startActivityForResult(intent, 1);
		}
		else if (i.getItemId() == R.id.exit){
			finish();
		}
		return true;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		String[] ops = new String[2];
		if (resultCode == 1){
			String album = data.getExtras().getString(AddAlbum.ALBUM);
			ops[0] = "createAlbum"; ops[1] = album;
			c.selectAction(ops);
			UserData u = this.c.user;
			
			//System.out.println("Check");
			albumadapter = new ArrayAdapter<String>(this, R.layout.albumtext, this.c.user.getAlbumNames());
			lv.setAdapter(albumadapter);
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
		if (resultCode == 2){
			String album = data.getExtras().getString(DeleteAlbum.ALBUM);
			ops[0] = "deleteAlbum"; ops[1] = album;
			c.selectAction(ops);
			UserData u = this.c.user;
			
			System.out.println("Check");
			albumadapter = new ArrayAdapter<String>(this, R.layout.albumtext, this.c.user.getAlbumNames());
			lv.setAdapter(albumadapter);
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
		if (resultCode == 3){
			String oldname = data.getExtras().getString(RenameAlbum.OLDALBUM);
			String newname = data.getExtras().getString(RenameAlbum.NEWALBUM);
			Album a = this.c.user.getAlbum(oldname);
			a.setAlbumName(newname);
			this.c.user.getAlbums().remove("dc");
			this.c.user.getAlbums().put(newname, a);
			albumadapter = new ArrayAdapter<String>(this, R.layout.albumtext, this.c.user.getAlbumNames());
			lv.setAdapter(albumadapter);
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
		if (resultCode == 4){
			String type = data.getExtras().getString(AddTag.TT);
			String val = data.getExtras().getString(AddTag.TV);
			Tag t = new Tag(type, val);
			ArrayList<Tag> tags = null;
			ArrayList<Photo> all = this.c.user.allphotos;
			ArrayList<String> results = new ArrayList<String>();
			for (int x = 0; x<all.size(); x++){
				tags = all.get(x).getTags();
				for (int y = 0; y<tags.size(); y++){
					if (type == null || type.equals("Tag Type")){
						if (val.equals(tags.get(y).tagValue) || tags.get(y).tagValue.startsWith(val)){
							results.add(all.get(x).getPhotoName());
						}
					}
					else if (val ==  null || val.equals("Tag Name")){
						if (type.equals(tags.get(y).tagType) || tags.get(y).tagType.startsWith(type)){
							results.add(all.get(x).getPhotoName());
						}
					}
					else{
						if (tags.get(y).equals(new Tag(type, val))){
						results.add(all.get(x).getPhotoName());
						}
					}
				}
			}
			SearchResults.results = results;
			Intent i2 = new Intent(this, SearchResults.class);
			startActivity(i2);
			
		}
	}
}
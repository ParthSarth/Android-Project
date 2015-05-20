package com.example.androidphotoalbum28;


import java.util.ArrayList;
import java.util.List;

import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.ClipData.Item;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PhotoAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Integer> photonums;
    private ArrayList<String> photos;

    public PhotoAdapter(Context c, ArrayList<Integer> p){
        mContext = c;
        photonums = p;
    }
    
   public PhotoAdapter(Context c, ArrayList<String> photos, int x){
	   this.photos = photos;
	   mContext = c;
   }

    public int getCount() {
        return photos.size();
       
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter c.openfileout(path, mode_private) user permission read external storage
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        Bitmap b = BitmapFactory.decodeFile(photos.get(position));
        //Bitmap b2 = b.createBitmap(b);
        //b = b.createScaledBitmap(b, 300, 300, true);
        imageView.setImageBitmap(b);
//        ImageView view = new ImageView(mContext);
//        view.setImageBitmap(b);
//        Toast toast = new Toast(mContext);
//        toast.setView(view);
//        toast.show();
       //imageView.setImageResource(photonums.get(position));
        
        	
        return imageView;
    }
    
	public Integer getImgID(int position){
		//return photos.get(position);
		return 0;
	}

   
}

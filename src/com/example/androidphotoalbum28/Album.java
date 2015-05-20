package com.example.androidphotoalbum28;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;
//import Photos.java;
/**
 * 
 * @author Parth Shorey
 *
 */
public class Album implements Serializable {
	
	public String albumName;
	private ArrayList<Photo> photos;
	
	/**
	 * makes an album
	 * @param albumName
	 */
	
	public Album(String albumName) {
		this.albumName = albumName;
		this.photos = new ArrayList<Photo>();
	}
	/**
	 * adds a photo object 
	 * @param photo
	 */
	public void addPhoto(Photo photo) {
			this.photos.add(photo);
			//Collections.sort(this.photos);
	}
	/**
	 * removes a photo object
	 * @param fileName
	 */
	public void removePhoto(String fileName) {
			this.photos.remove(this.getPhotoIndex(fileName));
			//Collections.sort(this.photos);
	}
	/**
	 * gets all photos in the current album
	 * @return all photos in album
	 */
	public ArrayList<Photo> getPhotos(){
		return this.photos;
	}
	/**
	 * gets number of photos in album
	 * @return
	 */
	public int getNumberOfPhotos() {
		return this.photos.size();
	}
	/**
	 * gets the index of the photo based on the name
	 * @param photoName
	 * @return
	 */
	public int getPhotoIndex(String photoName) {
		for (int i = 0; i < this.getNumberOfPhotos(); i++) {
			if (photos.get(i) != null && photos.get(i).getPhotoName().compareTo(photoName) == 0) {
				return i;
			}
		}

		return -1;
	}
	/**
	 * gets a list of all photo names in the album
	 * @return list of photo names
	 */
	public ArrayList<String> getPhotoNames() {
		
		ArrayList<String> photoNames = new ArrayList<String>(this.getNumberOfPhotos());
		
		for (int i = 0; i < this.getNumberOfPhotos(); i++) {
			if (this.photos.get(i) != null) {
				photoNames.add(this.photos.get(i).getPhotoName());
			}
		}
		
		return photoNames;
	}
	/** checks if a photo exists
	 * 
	 * @param photoName
	 * @return 
	 */
	public boolean hasPhoto(String photoName) {
		if (this.getPhotoIndex(photoName) >= 0) {
			return true;
		} 
		else return false;
	}
	/**
	 * gets the name of the album
	 * @return
	 */
	public String getAlbumName() {
		return this.albumName;
	}
	/** sets the name of the album
	 * 
	 * @param albumName
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
}

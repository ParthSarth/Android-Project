 package com.example.androidphotoalbum28;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
/**
 * 
 * @author Rusit Soni
 * @author Parth Shorey
 *
 */
public class UserData implements Serializable, Comparable<UserData> {
	
	//Transfer all the stuff method protocols from library.java
	
	//private String name;
	//private String id;
	private HashMap<String, Album> albums;
	public ArrayList<Photo> allphotos;
	//HashMap<String, Photo> photos;

	/**
	 * creates a new user and a hashmap of albums
	 * @param id
	 * @param name
	 */
	public UserData(){
		//this.name = name;
		//this.id = id;
		this.albums = new HashMap<String, Album>();
		this.allphotos = new ArrayList<Photo>();
	}
	/**
	 * gets the name of the user
	 * @return
	 */
//	
	/**
	 * sets the name of the user
	 * @param name
	 */

	/**
	 * returns all the albums for this user
	 * @return albums
	 */
	public HashMap<String, Album> getAlbums(){
		return this.albums;
	}
	/**
	 * gets the userId
	 * @return
	 */

	/**
	 * gets the number of albums for this user
	 * @return
	 */
	public int getNumberOfAlbums() {
		return this.albums.size();
	}

	/**
	 * gets a specific album from the user
	 * @param albumName
	 * @return one album
	 */
	public Album getAlbum(String albumName) {
		return this.albums.get(albumName);
		
	}
	/**
	 * gets the names of all the albums for this user
	 * @return all album names
	 */
	public ArrayList<String> getAlbumNames() {
		ArrayList<String> albumNames = new ArrayList<String>(this.getNumberOfAlbums()); ////copies arraylist 
		//System.out.println("Using EntrySet");
        for(String albumEntry : albums.keySet()){
        /// the new copied array list.							
				albumNames.add(albumEntry);
			}
		return albumNames;
	}

	
	public ArrayList<Photo> getPhotosInRange(Calendar first, Calendar second) throws Exception {
		ArrayList<Photo> inDateRange = new ArrayList<Photo>();
		ArrayList<String> albumNames = this.getAlbumNames();
		for (String albumName : albumNames) {
			ArrayList<String> namesOfPhotos = this.getAlbum(albumName).getPhotoNames();
			for (String photoName : namesOfPhotos) {
				if (this.getPhoto(photoName).getDate().after(first) && this.getPhoto(photoName).getDate().before(second)) {
					inDateRange.add(this.getPhoto(photoName));
				}
			}
		} 
		
		if (inDateRange.size() == 0){
			throw new Exception("Invalid Date");
		}
		return inDateRange;
	}
	
	protected ArrayList<Photo> photosWithTag(String tagType, String tagValue) throws Exception {
		ArrayList<Photo> withTag = new ArrayList<Photo>();
		ArrayList<String> albumNames = this.getAlbumNames();
		for (String albumName : albumNames) {
			ArrayList<String> nameOfPhotos = this.getAlbum(albumName).getPhotoNames();
			for (String photoName : nameOfPhotos) {
				if (this.getPhoto(photoName).hasTag(tagType, tagValue)) {
					withTag.add(this.getPhoto(photoName));
				}
			}
		} 
		
		if (withTag.size() == 0){
			throw new Exception("Invald Tag");
		}
		return withTag;
	}
	
	public Photo getPhoto(String photoName) throws Exception{
		ArrayList<String> albumNames = this.getAlbumNames();
		
		for (String albumName : albumNames) {
			if (this.getAlbum(albumName).hasPhoto(photoName)) {
				//return this.getAlbum(albumName).getPhoto(photoName);
			}
		} throw new Exception("invalid photo");
	}
	/**
	 * gets the information for one photo and returns it to the contoller
	 * @param photoname
	 * @return information for one photo
	 * @throws Exception
	 */
	public ArrayList<String> getPhotoInfo(String photoname) throws Exception {
		String str = "Photo file name: "+photoname+ "\n"+ "Album: ";
		ArrayList<String> albumNames = this.getAlbumNames();
		ArrayList<Photo> allphotos;
		String lastalbum = null;
		Photo p = null;
			for (int x = 0; x<albumNames.size(); x++){
				allphotos = this.albums.get(albumNames.get(x)).getPhotos();
				for (int y = 0; y<allphotos.size(); y++){
					if(photoname.equals(allphotos.get(y).getPhotoName())){
						str += albumNames.get(x)+", ";
						p = allphotos.get(y);
						lastalbum = albumNames.get(x);
					}
					
				}
				
			}
		int in = str.lastIndexOf(',');
		str = str.substring(0, in);
		str += "\n"+ "Date: "+p.convertDate(p.getDate())+"\n"+"Caption: "+p.getCaption()+"\n"+"Tags: "+"\n";
		for (int x = 0; x<p.getTags().size(); x++){
			str += p.getTags().get(x).toString() + "\n";
		}
		ArrayList<String> info = new ArrayList<String>();
		info.add(str);
		info.add(p.getPhotoName());
		info.add(lastalbum);
		
		
		
		return info;
	}
	public ArrayList<Photo> getPhotoInfo(Calendar c1, Calendar c2) throws Exception {
		//String str = "Photo file name: "+photoname+ "\n"+ "Album: ";
		ArrayList<String> albumNames = this.getAlbumNames();
		ArrayList<Photo> allphotos;
		String currphoto = "";
		String total = "";
		String lastdate = "";
		ArrayList<Photo> captions = new ArrayList<Photo>();
		//HashMap<Photo, ArrayList<String>> daterange = new HashMap<Photo, ArrayList<String>>();
		Photo p = null;
			for (int x = 0; x<albumNames.size(); x++){
				allphotos = this.albums.get(albumNames.get(x)).getPhotos();
				for (int y = 0; y<allphotos.size(); y++){
					if(allphotos.get(y).getDate().compareTo(c1)>=0 && allphotos.get(y).getDate().compareTo(c2)<=0){
						if (captions.contains(allphotos.get(y))){
							 captions.add(allphotos.get(y));
						}
						if(currphoto.equals(allphotos.get(y).getPhotoName())){
							total += albumNames.get(x) + ", ";
							lastdate = allphotos.get(y).convertDate(allphotos.get(y).getDate());
						}
						else{
							
							currphoto = allphotos.get(y).getPhotoName();
							if (!total.equals(""))
								total += " - Date: "+lastdate+"\n"+ allphotos.get(y).getCaption()+ " - Album:";
						}
					}
					
				}
				
			}
		//str += "\n"+ "Date: "+p.convertDate(p.getDate())+"\n"+"Caption: "+p.getCaption()+"\n"+"Tags: "+"\n";
		//for (int x = 0; x<p.getTags().size(); x++){
			//str += p.getTags().get(x).toString() + "\n";
		//}
//		ArrayList<String> info = new ArrayList<String>();
//		info.add(str);
//		info.add(p.getPhotoName());
//		info.add(lastalbum);
//		
//		
		return captions;
		//return info;
	}
	
	public String getAlbumFromPhoto(String photoName){
		String name = null;
		ArrayList<String> albumNames = this.getAlbumNames();
		for (String albumName : albumNames) {
			ArrayList<String> namesOfPhotos = this.getAlbum(albumName).getPhotoNames();
			for (String photoName2 : namesOfPhotos) {
			ArrayList<String> albumNames2 = this.getAlbumNames();
				for (int i = 0; i < this.getNumberOfAlbums(); i++) {
					if (this.getAlbum(albumNames.get(i)).hasPhoto(photoName)) {
						name = this.getAlbum(albumNames.get(i)).albumName;
					}
				}
			}
		}
		return name;
	}

	
	/**
	 * renames the album
	 * @param name
	 * @param newName
	 * @return
	 */
	
	public boolean renameAlbum(Album name, String newName){
		return false;
	}
	@Override
	public int compareTo(UserData another) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}

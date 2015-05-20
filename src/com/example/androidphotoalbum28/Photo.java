package com.example.androidphotoalbum28;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * 
 * @author Parth Shorey
 *
 */
public class Photo implements Serializable, Comparable<Photo> {
	
	private String photoName;
	private String caption;
	private ArrayList<Tag> tags;
	private Calendar dateCreated;

	/** creates a new photo
	 * 
	 * @param fileName
	 * @param caption
	 */
	public Photo(String fileName) {
		//File file = new File(fileName);
		this.photoName = fileName;
		this.caption = caption;
		this.tags = new ArrayList<Tag>();
		//this.dateCreated = Calendar.getInstance();
		//this.dateCreated.set(Calendar.MILLISECOND, 0);
	}
	/**
	 * gets the (canonical) name of the photo file
	 * @return photo name
	 */
	public String getPhotoName() {
		return this.photoName; //= photoName;
	}
	/** gets a list of all tags in the photo
	 * 
	 * @return all tags
	 */
	public ArrayList<Tag> getTags(){
		return this.tags;
	}
	/**
	 * sets the photo name
	 * @param photoName
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	/**
	 * gets the photo's caption
	 * @return
	 */
	public String getCaption() {
		return caption;
	}
	/**
	 * sets caption
	 * @param caption
	 */
	public void setCaption(String caption){
		this.caption = caption;
	}
	/**
	 * sets the date of the photo based on when it was last modified
	 * @param 
	 */
	public void setDate(File f){
		this.dateCreated = Calendar.getInstance();
		this.dateCreated.setTimeInMillis(f.lastModified());
		this.dateCreated.set(Calendar.MILLISECOND, 0);
	}
	/**
	 * returns the time photo was last modified
	 * @return
	 */
	public Calendar getDate(){
		return this.dateCreated;
	}
	/**
	 * converts the date to a string representation
	 * @param c
	 * @return
	 */
	public String convertDate(Calendar c){
		SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
		String date = s.format(c.getTime());
		return date;
	}
	/**
	 * checks to see if a tag exists
	 * @param tagType
	 * @param tagValue
	 * @return
	 */
	public boolean hasTag(String tagType, String tagValue) {
		for (Tag tag : tags) {
			if (tag.equals(new Tag(tagType, tagValue))) {
				return true;
			}
		} return false;
	}
	
	
	/**
	 * add a tag to the photo
	 * @param tagType
	 * @param tagValue
	 */
	public void addTag(String tagType, String tagValue) {
		this.tags.add(new Tag(tagType, tagValue));
	}
	/** removes a tag from the photo
	 * 
	 * @param tagType
	 * @param tagValue
	 */
	public void deleteTag(String tagType, String tagValue) {
		this.tags.remove(new Tag(tagType, tagValue));
	}
	/** gets the number of tags
	 * 
	 * @return
	 */
	public int getSizeOfTags() {
		return this.tags.size();
	}
	/**
	 * compares photos based on date
	 */
	@Override
	public int compareTo(Photo o) {
		return this.dateCreated.compareTo(o.dateCreated);
	}
	/**
	 * 
	 * @return
	 */
	
}

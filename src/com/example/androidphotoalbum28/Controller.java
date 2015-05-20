package com.example.androidphotoalbum28;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import android.content.Context;



public class Controller implements ControlUtil {

public UserData user = null;
public boolean loggedin = true;
public User back = null;	
	/**
	 * 
	 */
	public Controller(Context c){
		this.back = new User(c);
		this.user = this.back.getUser();
	}
	
	
	public void selectAction(String[] args){
		if (this.loggedin) {
			this.useractions(args);
		}
	}
			
	/**
	 * Logs in if the user exists, otherwise exits the program
	 * @param loginfo - command line arguments
	 */
	
	
	/**
	 * Main control actions - executes all actions that user inputs in 
	 * interactive mode
	 * @param info - user commands
	 */
	
	public void useractions(String[] info){
		String s = info[0];
		switch(s){
		
		case "createAlbum":
			if(info.length<2){
				//System.out.println("Error: not enough arguments");
				break;
			}
			if (this.user.getAlbums().get(info[1]) != null){
				//System.out.println("album exists for user "+this.user.getUserId()+":");
				//System.out.println(info[1]);
				break;
			}
			this.user.getAlbums().put(info[1], new Album(info[1]));
			//System.out.println("created album for user "+this.user.getUserId()+":");
			//System.out.println(info[1]);
			break;
		case "deleteAlbum":
			if(info.length<2){
				//System.out.println("Error: not enough arguments");
				break;
			}
			if (this.user.getAlbums().get(info[1]) == null){
				//System.out.println("album does not exist for user "+this.user.getUserId()+":");
				//System.out.println(info[1]);
				break;
			}
			this.user.getAlbums().remove(info[1]);
			//System.out.println("deleted album for user "+this.user.getUserId()+":");
			//System.out.println(info[1]);
			break;
		case "listAlbums":
			if(info.length<1){
				//System.out.println("Error: not enough arguments");
				break;
			}
			if (this.user.getAlbums().size() == 0){
				//System.out.println("No albums exist for user "+this.user.getUserId());
				break;
			}
			ArrayList<Album> values = new ArrayList<Album>();
			for (Album value : this.user.getAlbums().values()){
				values.add(value);
			}
			
			Photo first = null;
			Photo last = null;
			for (int x = 0; x<values.size(); x++){
				first = null;
				last = null;
				if (values.get(x).getPhotos().size()>0){
					first = values.get(x).getPhotos().get(0);
					last = values.get(x).getPhotos().get(values.get(x).getPhotos().size()-1);
				}
				//System.out.print(values.get(x).getAlbumName()+" number of photos: "+values.get(x).getNumberOfPhotos());
				if (first != null && last != null){
					//System.out.println(", "+first.convertDate(first.getDate())+" - "+last.convertDate(last.getDate()));
				}
				else{
					//System.out.println();
				}
			}
			break;
		case "listPhotos":
			if(info.length<2){
				//System.out.println("Error: not enough arguments");
				break;
			}
			if (this.user.getAlbums().get(info[1]) == null){
				//System.out.println("Album "+info[1]+" does not exist");
				break;
			}
			ArrayList<Photo> p = this.user.getAlbums().get(info[1]).getPhotos();
			for (int x = 0; x<p.size(); x++){
				//System.out.println(p.get(x).getPhotoName()+" - "+p.get(x).convertDate(p.get(x).getDate()));
			}
			break;
		case "addPhoto":
			if(info.length<4){
				//System.out.println("Error: not enough arguments");
				break;
			}
			//File f = new File(info[1]);
			String photoname = null;
//			try {
//				photoname = f.getCanonicalPath();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//File f3 = new File(photoname);
			//if (!f3.exists()){
				//System.out.println("File "+info[1]+" does not exist");
				//break;
			//}
			//else if (this.user.getAlbums().get(info[3]) == null){
				//System.out.println("Album "+info[3]+" does not exist");
				//break;
			//}
			
//			String photoname = null;
//			try {
//				photoname = f.getCanonicalPath();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//if (this.user.getAlbums().get(info[3]).getPhotoIndex(photoname) > -1){
				//System.out.println("Photo "+info[1]+" already exists in album "+info[3]);
				//break;
			//}
			Photo photo = new Photo(info[1]);
			//photo.setDate(f);
			this.user.getAlbums().get(info[3]).addPhoto(photo);
			this.user.allphotos.add(photo);
			//System.out.println("Added photo "+info[1]+":");
			//System.out.println(info[2]+ " - Album: "+info[3]);
			break;
		case "movePhoto":
			if(info.length<4){
				//System.out.println("Error: not enough arguments");
				break;
			}
			File file = new File(info[1]);
			
			String pname = null;
			try {
				pname = file.getCanonicalPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Album A = this.user.getAlbums().get(info[2]);
			Album B = this.user.getAlbums().get(info[3]);
			if (A==null || B==null){
//				if (A == null){ System.out.println("Album "+info[2]+" does not exist");}
//				else{System.out.println("Album "+info[3]+ " does not exist");}
//				break;
			}
			if (A.getPhotoIndex(pname) == -1){
				//System.out.println("Photo "+info[1]+ " does not exist in album "+info[2]);
				break;
			}
			if (B.getPhotoIndex(pname)>-1){
				break;
			}
			Photo P = A.getPhotos().get(A.getPhotoIndex(pname));
			A.removePhoto(pname);
			B.addPhoto(P);
			//System.out.println("Moved photo "+info[1]+":");
			//System.out.println(info[1]+" - From album "+info[2]+" to album "+info[3]);
			break;
		case "removePhoto":
			if(info.length<3){
				//System.out.println("Error: not enough arguments");
				break;
			}
//			File file2 = new File(info[1]);
//			String rname=null;
//			try {
//				rname = file2.getCanonicalPath();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (this.user.getAlbums().get(info[2]) == null){
//				//System.out.println("Album "+info[2]+ " does not exist");
//				break;
//			}
//			if (this.user.getAlbums().get(info[2]).getPhotoIndex(rname)==-1){
//				//System.out.println("Photo "+info[1]+ " is not in album "+info[2]);
//				break;
//			}
			this.user.getAlbums().get(info[2]).removePhoto(info[1]);
			for (int x = 0; x<this.user.allphotos.size(); x++){
				if (info[1].equals(this.user.allphotos.get(x).getPhotoName())){
					this.user.allphotos.remove(x);
				}
			}
			//System.out.println("Removed photo:");
			//System.out.println(info[1]+" - From album "+info[2]);
			break;
		case "addTag":
			if(info.length<3){
				//System.out.println("Error: not enough arguments");
				break;
			}
			File f5 = new File(info[1]);
			String photoname3 =null;
			ArrayList<String> photoinfo2 = null;
			try {
				photoname3 = f5.getCanonicalPath();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				photoinfo2 = this.user.getPhotoInfo(photoname3);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				
				//System.out.println("Photo "+ info[1]+ " does not exist");
				break;
			}
			int temp2 = this.user.getAlbums().get(photoinfo2.get(2)).getPhotoIndex(photoinfo2.get(1));
			Photo p2 = this.user.getAlbums().get(photoinfo2.get(2)).getPhotos().get(temp2);
			String tagtype = info[2].substring(0, info[2].indexOf(':'));
			String tag = info[2].substring(info[2].indexOf(':')+1);
			String tester = tagtype+":"+tag;
			boolean tagcheck = false;
			for (int x = 0; x<p2.getTags().size(); x++){
				if (tester.equals(p2.getTags().get(x).toString())){
					//System.out.println("Tag already exists for "+info[1]+" "+tester);
					tagcheck = true;
					break;
				}
			}
			if(tagcheck){
				break;
			}
			p2.addTag(tagtype, tag);
			//System.out.println("Added tag:");
			//System.out.println(info[1]+" "+tester);
			break;
		case "deleteTag":
			if(info.length<3){
				//System.out.println("Error: not enough arguments");
				break;
			}
			File f6 = new File(info[1]);
			String photoname4 =null;
			ArrayList<String> photoinfo3 = null;
			try {
				photoname4 = f6.getCanonicalPath();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				photoinfo3 = this.user.getPhotoInfo(photoname4);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				
				//System.out.println("Photo "+ info[1]+ " does not exist");
				break;
			}
			int temp3 = this.user.getAlbums().get(photoinfo3.get(2)).getPhotoIndex(photoinfo3.get(1));
			Photo p3 = this.user.getAlbums().get(photoinfo3.get(2)).getPhotos().get(temp3);
			String tagtype1 = info[2].substring(0, info[2].indexOf(':'));
			String tag1 = info[2].substring(info[2].indexOf(':')+1);
			String tester1 = tagtype1+":"+tag1;
			boolean tagcheck1 = false;
			Tag t =null;
			for (int x = 0; x<p3.getTags().size(); x++){
				if (tester1.equals(p3.getTags().get(x).toString())){
					//System.out.println("Tag does not exist for "+info[1]+" "+tester1);
					t = p3.getTags().get(x);
					tagcheck1 = true;
					break;
				}
			}
			if(!tagcheck1){
				//System.out.println("Tag does not exist for "+info[1]+" "+tester1);
				break;
			}
			p3.getTags().remove(t);
			//System.out.println("Deleted tag:");
			//System.out.println(info[1]+" "+tester1);
			break;
		case "listPhotoInfo":
			if(info.length<2){
				//System.out.println("Error: not enough arguments");
				break;
			}

			String str = null;
			File f4 = new File(info[1]);
			String photoname2 = null;
			ArrayList<String> photoinfo = null;
			try {
				photoname2 = f4.getCanonicalPath();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				photoinfo = this.user.getPhotoInfo(photoname2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//System.out.println("Photo "+info[1]+" does not exist");
				break;
			}
			//System.out.println(photoinfo.get(0));
			break;
		case "getPhotosByDate":
			Calendar c = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
			try {
				c.setTime(sdf.parse(info[1]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				c2.setTime(sdf.parse(info[2]));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Photo> results = null;
			try {
				results = this.user.getPhotoInfo(c, c2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//System.out.println("Error: messedup");
			}
			//System.out.println("Photos for user "+ this.user.getUserId()+" in range "+ info[1]+ " to " +info[2]+":");
			for (int x = 0; x<results.size(); x++){
				//System.out.println(results.get(x).getCaption()+" - Date: "+results.get(x).convertDate(results.get(x).getDate()));
			}
			break;
		case "getPhotosByTag":
			//System.out.println("not enough time to finish this method");
			break;
		case "logout":
			this.loggedin=false;
			break;
		default:
			//System.out.println("Error: invalid command given");
			break;
		
			
		}
		
	}
	
	@Override
	public void addorcreate(String[] info) {
		
		
	}


	@Override
	public void delete(String[] details) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void listall(String[] details) {
		// TODO Auto-generated method stub
		
	}
	
	
}

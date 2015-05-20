package com.example.androidphotoalbum28;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.*;
import android.content.Context;
/**
 * 
 * @author Rusit Soni
 * @author Parth Shorey
 *
 */
public class User implements Serializable{

	private UserData user;
	private String filename = "data.bin";
	
	/**
	 * creates the backend object and reads data from file
	 */
	public User(Context c) {
		this.user = new UserData();
		ObjectInputStream objInput = null;
		FileInputStream fileInput = null;

		try {
			fileInput = c.openFileInput(this.filename);
			objInput = new ObjectInputStream(fileInput);
			this.user = (UserData)objInput.readObject();
			fileInput.close();
			objInput.close();
		} catch (Exception e) {
			this.user = new UserData();
		}

		
	}
	
	public UserData getUser(){
		return this.user;
	}
	
	public void write(Context c) throws IOException, FileNotFoundException {
		//File output = new File(this.filename);
		FileOutputStream f = null;
		ObjectOutputStream o = null;
		f = c.openFileOutput(this.filename, Context.MODE_PRIVATE);
		o = new ObjectOutputStream(f);
		o.writeObject(this.user);
		o.flush();
		o.close();
		//f.close();
	}
	//@Override
//	public HashMap<String, User> getUserList() {
//		
//	}
	
}

package com.example.androidphotoalbum28;

import java.util.HashMap;
/**
 * 
 * @author Parth Shorey
 *
 */
public interface Backend{

		/**
		 * gets a user
		 * @param id
		 * @return
		 */
		public UserData getUser(String Id);
		
		/**
		 * adds a user
		 * @param u
		 */
		public void addUser(UserData u);
		
		/**
		 * delets a user
		 * @param id
		 * @return
		 */
		public void deleteUser(String Id);
		
		/**
		 * gets all the users
		 * @return
		 */
		public HashMap<String, UserData> getUserList();

		
}

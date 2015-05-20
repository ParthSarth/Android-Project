package com.example.androidphotoalbum28;
/**
 * 
 * @author Rusit Soni
 *
 */
public interface ControlUtil {
	/**adds users
	 * 
	 * @param details - from command line
	 * 
	 */
	void addorcreate(String [] details);
	/** deletes users
	 * 
	 * @param details - from command line
	 *
	 */
	void delete(String [] details);
	/** list all users
	 * 
	 * @param details - from command line
	 */
	void listall(String [] details);
}

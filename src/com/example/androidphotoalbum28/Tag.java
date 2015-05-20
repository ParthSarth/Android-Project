package com.example.androidphotoalbum28;

import java.io.Serializable;
/**
 * 
 * @author Parth Shorey
 *
 */
public class Tag implements Serializable {

	public String tagType;
	public String tagValue;
	/**
	 * creates a new tag
	 * @param tagType
	 * @param tagValue
	 */
	public Tag(String tagType, String tagValue) {
		this.tagType = tagType;
		this.tagValue = tagValue;
	}
	/**
	 * compares tags
	 */
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Tag)) {
			return false;
		} else {
			Tag tag = ((Tag) o);

			if ((this.tagType.equals(tag.tagType) || this.tagType.startsWith(tag.tagType)) 
					&& (this.tagValue.equals(tag.tagValue) || this.tagValue.startsWith(tag.tagValue)) ){
				return true;
			} else {
				return false;
			}
		}
	}
	/**
	 * returns string representation of tag
	 */
	public String toString() {
		return this.tagType + ":" + tagValue;
	}
}

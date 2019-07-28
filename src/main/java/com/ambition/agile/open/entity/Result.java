/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.open.entity;


/**
 *  intent  data   result 后处理函数 
 * @author harry
 * @version 2019-07-05
 */
public class Result {
	
	private static final long serialVersionUID = 1L;
	/**
	  * Copyright 2019 bejson.com 
	  */
	    private String courseName;
	    
	    private String duration;
	    
	    private int durationLong;
	    
	    private String voicePath;
	    
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		public String getDuration() {
			return duration;
		}
		public void setDuration(String duration) {
			this.duration = duration;
		}
		public int getDurationLong() {
			return durationLong;
		}
		public void setDurationLong(int durationLong) {
			this.durationLong = durationLong;
		}
		public String getVoicePath() {
			return voicePath;
		}
		public void setVoicePath(String voicePath) {
			this.voicePath = voicePath;
		}

	
}
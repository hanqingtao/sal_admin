/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.open.entity;


/**
 * 课程Entity
 * @author harry
 * @version 2019-07-05
 */
public class Data {
	
	private static final long serialVersionUID = 1L;
	/**
	  * Copyright 2019 bejson.com 
	  */
	    private String sub;
	    private String auth_id;
	    private String text;
	    private int result_id;
	    private Intent intent;
	    
	    public void setSub(String sub) {
	         this.sub = sub;
	     }
	     public String getSub() {
	         return sub;
	     }

	    public void setAuth_id(String auth_id) {
	         this.auth_id = auth_id;
	     }
	     public String getAuth_id() {
	         return auth_id;
	     }

	    public void setText(String text) {
	         this.text = text;
	     }
	     public String getText() {
	         return text;
	     }

	    public void setResult_id(int result_id) {
	         this.result_id = result_id;
	     }
	     public int getResult_id() {
	         return result_id;
	     }
		public Intent getIntent() {
			return intent;
		}
		public void setIntent(Intent intent) {
			this.intent = intent;
		}
	
}
/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.open.entity;


/**
 * 课程Entity
 * @author harry
 * @version 2019-07-05
 */
public class TtsData {
	
	private static final long serialVersionUID = 1L;
	/**
	  * Copyright 2019 bejson.com 
	  */
	    private String sub;
	    private String auth_id;
	    private String content;
	    private int result_id;
	    
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

	    public void setContent(String content) {
	         this.content = content;
	     }
	     public String getContent() {
	         return content;
	     }

	    public void setResult_id(int result_id) {
	         this.result_id = result_id;
	     }
	     public int getResult_id() {
	         return result_id;
	     }
	
}
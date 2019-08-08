/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.open.entity;

import java.util.List;

/**
 * 课程Entity
 * @author harry
 * @version 2019-07-05
 */
public class AIUITtsEntity {
	
	private static final long serialVersionUID = 1L;
	 private List<TtsData> data;
	private String sid;
	    private String code;
	    
	    private String desc;
	    
	    
	    public void setData(List<TtsData> data) {
	         this.data = data;
	     }
	     public List<TtsData> getData() {
	         return data;
	     }

	    public void setSid(String sid) {
	         this.sid = sid;
	     }
	     public String getSid() {
	         return sid;
	     }

	    public void setCode(String code) {
	         this.code = code;
	     }
	     public String getCode() {
	         return code;
	     }

	    public void setDesc(String desc) {
	         this.desc = desc;
	     }
	     public String getDesc() {
	         return desc;
	     }
	
}
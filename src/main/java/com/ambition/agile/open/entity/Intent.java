/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.open.entity;


/**
 * Intent Entity
 * @author harry
 * @version 2019-07-05
 */

public class Intent {
	
	private static final long serialVersionUID = 1L;

		private int no_nlu_result;
		private String operation;
		private int rc;
		private String service;
		private String sid;
		private String text;
		private String uuid;
		private Answer answer;
		
		public int getNo_nlu_result() {
			return no_nlu_result;
		}
		public void setNo_nlu_result(int no_nlu_result) {
			this.no_nlu_result = no_nlu_result;
		}
		public String getOperation() {
			return operation;
		}
		public void setOperation(String operation) {
			this.operation = operation;
		}
		public int getRc() {
			return rc;
		}
		public void setRc(int rc) {
			this.rc = rc;
		}
		public String getService() {
			return service;
		}
		public void setService(String service) {
			this.service = service;
		}
		public String getSid() {
			return sid;
		}
		public void setSid(String sid) {
			this.sid = sid;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public Answer getAnswer() {
			return answer;
		}
		public void setAnswer(Answer answer) {
			this.answer = answer;
		}
		
		
	  
	
}
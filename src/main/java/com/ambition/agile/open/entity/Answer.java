/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.open.entity;


/**
 * 课程Entity
 * @author harry
 * @version 2019-07-05
 */
public class Answer {
	
	private static final long serialVersionUID = 1L;

		private String answerType;
		private String emotion;
		private String text;
		private String topicID;
		private String type;
		private Question question;
		
		public String getAnswerType() {
			return answerType;
		}
		public void setAnswerType(String answerType) {
			this.answerType = answerType;
		}
		public String getEmotion() {
			return emotion;
		}
		public void setEmotion(String emotion) {
			this.emotion = emotion;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getTopicID() {
			return topicID;
		}
		public void setTopicID(String topicID) {
			this.topicID = topicID;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public Question getQuestion() {
			return question;
		}
		public void setQuestion(Question question) {
			this.question = question;
		}
		
}
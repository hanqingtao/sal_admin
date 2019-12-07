/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.users.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 批次Entity
 * @author harry
 * @version 2019-12-07
 */
public class Batch extends DataEntity<Batch> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 批次名称
	private Date beginTime;		// 开始时间
	private Date endTime;		// 结束时间
	private String createName;		// create_name
	private String count;		// 数量
	private String pre;		// 前缀
	private Date beginBeginTime;		// 开始 开始时间
	private Date endBeginTime;		// 结束 开始时间
	private Date beginEndTime;		// 开始 结束时间
	private Date endEndTime;		// 结束 结束时间
	
	public Batch() {
		super();
	}

	public Batch(String id){
		super(id);
	}

	@Length(min=0, max=64, message="批次名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	@Length(min=0, max=32, message="create_name长度必须介于 0 和 32 之间")
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
	@Length(min=0, max=5, message="数量长度必须介于 0 和 5 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@Length(min=0, max=16, message="前缀长度必须介于 0 和 16 之间")
	public String getPre() {
		return pre;
	}

	public void setPre(String pre) {
		this.pre = pre;
	}
	
	public Date getBeginBeginTime() {
		return beginBeginTime;
	}

	public void setBeginBeginTime(Date beginBeginTime) {
		this.beginBeginTime = beginBeginTime;
	}
	
	public Date getEndBeginTime() {
		return endBeginTime;
	}

	public void setEndBeginTime(Date endBeginTime) {
		this.endBeginTime = endBeginTime;
	}
		
	public Date getBeginEndTime() {
		return beginEndTime;
	}

	public void setBeginEndTime(Date beginEndTime) {
		this.beginEndTime = beginEndTime;
	}
	
	public Date getEndEndTime() {
		return endEndTime;
	}

	public void setEndEndTime(Date endEndTime) {
		this.endEndTime = endEndTime;
	}
		
}
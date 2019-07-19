/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.course.entity;

import org.hibernate.validator.constraints.Length;
import com.ambition.agile.modules.sys.entity.Office;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 课程Entity
 * @author harry
 * @version 2019-07-05
 */
public class Course extends DataEntity<Course> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 课程名称
	private String courseCode;		// 课程唯一标识
	private String courseType;		// 课程类型
	private String categoryName;		// 所属分类名称
	private String categoryCode;		// 视频分类
	private String coverPath;		// 课程封面
	private String duration;		// 时长 **:**:**
	private Integer durationLong;		// 时长
	private String status;		// 视频状态
	private Integer playNum;		// 课程的播放次数
	private Integer orderNum;		// 推荐排序
	private String videoPath;		// 视频路径
	private String videoName;		// 视频路径

	private Integer categoryId;		// 分类id
	private String categoryIds;		// 分类ids
	
	public Course() {
		super();
	}

	public Course(String id){
		super(id);
	}

	@Length(min=1, max=128, message="课程名称长度必须介于 1 和 128 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="课程唯一标识长度必须介于 0 和 32 之间")
	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	@Length(min=0, max=1, message="课程类型长度必须介于 0 和 1 之间")
	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
	@Length(min=0, max=32, message="所属分类名称长度必须介于 0 和 32 之间")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Length(min=0, max=32, message="视频分类长度必须介于 0 和 32 之间")
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	@Length(min=0, max=64, message="课程封面长度必须介于 0 和 64 之间")
	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
	
	@Length(min=0, max=9, message="时长 **:**:**长度必须介于 0 和 9 之间")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public Integer getDurationLong() {
		return durationLong;
	}

	public void setDurationLong(Integer durationLong) {
		this.durationLong = durationLong;
	}
	
	@Length(min=0, max=1, message="视频状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Integer getPlayNum() {
		return playNum;
	}

	public void setPlayNum(Integer playNum) {
		this.playNum = playNum;
	}
	
	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
	@Length(min=1, max=256, message="视频路径长度必须介于 1 和 256 之间")
	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	
	@Length(min=1, max=64, message="视频名称 长度必须介于 1 和 64 之间")
	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	@Length(min=0, max=2000, message="分类ids长度必须介于 0 和 2000 之间")
	public String getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(String categoryIds) {
		this.categoryIds = categoryIds;
	}
	
}
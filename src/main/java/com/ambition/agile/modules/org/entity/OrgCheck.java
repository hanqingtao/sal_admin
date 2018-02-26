/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.entity;

import org.hibernate.validator.constraints.Length;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 抽查管理Entity
 * @author harry
 * @version 2017-10-23
 */
public class OrgCheck extends DataEntity<OrgCheck> {
	
	private static final long serialVersionUID = 1L;
	private String sn;		// 编号  根据年月日时分秒生成 20171023141420
	private Integer year;		// 年份
	private Double rateOrg;		// 机构抽查比例
	private Double rateProject;		// 项目抽查比例
	private String path;		// 文件生成地址
	
	public OrgCheck() {
		super();
	}

	public OrgCheck(String id){
		super(id);
	}

	@Length(min=0, max=32, message="编号  根据年月日时分秒生成 20171023141420长度必须介于 0 和 32 之间")
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Double getRateOrg() {
		return rateOrg;
	}

	public void setRateOrg(Double rateOrg) {
		this.rateOrg = rateOrg;
	}
	
	public Double getRateProject() {
		return rateProject;
	}

	public void setRateProject(Double rateProject) {
		this.rateProject = rateProject;
	}
	
	@Length(min=0, max=64, message="文件生成地址长度必须介于 0 和 64 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
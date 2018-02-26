/**
 * Copyright &copy; 2012-2017  All rights reserved.
 */
package com.ambition.agile.modules.org.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.ambition.agile.common.persistence.DataEntity;

/**
 * 代理机构招标业绩Entity
 * @author harry
 * @version 2017-08-03
 */
public class OrgAchieve extends DataEntity<OrgAchieve> {
	
	private static final long serialVersionUID = 1L;
	private String num;		// 项目编号
	private String name;		// 项目名称
	private String type;		// 项目类型
	private String entrustUnit;		// 委托单位
	private String entrustMoney;		// 委托金额
	private String bidMoney;		// 中标金额
	private Date tenderOpenTime;		// 开标时间
	private Date bidTime;		// 中标时间
	private String noticeMedia;		// 公告媒体
	private Date noticeDate;		// 公告时间
	private String remark;		// 备注
	private Integer orgId;		// 机构标识
	private String beginEntrustMoney;		// 开始 委托金额
	private String endEntrustMoney;		// 结束 委托金额
	private String beginBidMoney;		// 开始 中标金额
	private String endBidMoney;		// 结束 中标金额
	private Date beginTenderOpenTime;		// 开始 开标时间
	private Date endTenderOpenTime;		// 结束 开标时间
	private Date beginBidTime;		// 开始 中标时间
	private Date endBidTime;		// 结束 中标时间
	private Integer isHistory;  // 是否业绩，1是0否
	private Integer isCenter;  // 是否中央投资项目，1是0否
	
	
	
	public Integer getIsCenter() {
		return isCenter;
	}

	public void setIsCenter(Integer isCenter) {
		this.isCenter = isCenter;
	}

	/**
	 * @return the isHistory
	 */
	public Integer getIsHistory() {
		return isHistory;
	}

	/**
	 * @param isHistory the isHistory to set
	 */
	public void setIsHistory(Integer isHistory) {
		this.isHistory = isHistory;
	}
	public OrgAchieve() {
		super();
	}

	public OrgAchieve(String id){
		super(id);
	}

	@Length(min=0, max=16, message="项目编号长度必须介于 0 和 16 之间")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=0, max=32, message="项目名称长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="项目类型长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=32, message="委托单位长度必须介于 0 和 32 之间")
	public String getEntrustUnit() {
		return entrustUnit;
	}

	public void setEntrustUnit(String entrustUnit) {
		this.entrustUnit = entrustUnit;
	}
	
	public String getEntrustMoney() {
		return entrustMoney;
	}

	public void setEntrustMoney(String entrustMoney) {
		this.entrustMoney = entrustMoney;
	}
	
	public String getBidMoney() {
		return bidMoney;
	}

	public void setBidMoney(String bidMoney) {
		this.bidMoney = bidMoney;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTenderOpenTime() {
		return tenderOpenTime;
	}

	public void setTenderOpenTime(Date tenderOpenTime) {
		this.tenderOpenTime = tenderOpenTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBidTime() {
		return bidTime;
	}

	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}
	
	@Length(min=0, max=16, message="公告媒体长度必须介于 0 和 16 之间")
	public String getNoticeMedia() {
		return noticeMedia;
	}

	public void setNoticeMedia(String noticeMedia) {
		this.noticeMedia = noticeMedia;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}
	
	@Length(min=0, max=512, message="备注长度必须介于 0 和 512 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Length(min=0, max=11, message="机构标识长度必须介于 0 和 11 之间")
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	public String getBeginEntrustMoney() {
		return beginEntrustMoney;
	}

	public void setBeginEntrustMoney(String beginEntrustMoney) {
		this.beginEntrustMoney = beginEntrustMoney;
	}
	
	public String getEndEntrustMoney() {
		return endEntrustMoney;
	}

	public void setEndEntrustMoney(String endEntrustMoney) {
		this.endEntrustMoney = endEntrustMoney;
	}
		
	public String getBeginBidMoney() {
		return beginBidMoney;
	}

	public void setBeginBidMoney(String beginBidMoney) {
		this.beginBidMoney = beginBidMoney;
	}
	
	public String getEndBidMoney() {
		return endBidMoney;
	}

	public void setEndBidMoney(String endBidMoney) {
		this.endBidMoney = endBidMoney;
	}
		
	public Date getBeginTenderOpenTime() {
		return beginTenderOpenTime;
	}

	public void setBeginTenderOpenTime(Date beginTenderOpenTime) {
		this.beginTenderOpenTime = beginTenderOpenTime;
	}
	
	public Date getEndTenderOpenTime() {
		return endTenderOpenTime;
	}

	public void setEndTenderOpenTime(Date endTenderOpenTime) {
		this.endTenderOpenTime = endTenderOpenTime;
	}
		
	public Date getBeginBidTime() {
		return beginBidTime;
	}

	public void setBeginBidTime(Date beginBidTime) {
		this.beginBidTime = beginBidTime;
	}
	
	public Date getEndBidTime() {
		return endBidTime;
	}

	public void setEndBidTime(Date endBidTime) {
		this.endBidTime = endBidTime;
	}
		
}
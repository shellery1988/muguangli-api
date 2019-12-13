package com.muguangli.api.vo;

import org.apache.commons.lang.StringUtils;

import com.muguangli.api.pojo.UserStudyRecord;

public class UserStudyRecordVO extends UserStudyRecord{
	
	private String stageName;

	private String createTimeFormat;
	
	private String hasDone;
	
	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getCreateTimeFormat() {
		return createTimeFormat;
	}

	public void setCreateTimeFormat(String createTimeFormat) {
		this.createTimeFormat = createTimeFormat;
	}

	public String getHasDone() {
		hasDone = StringUtils.isBlank(createTimeFormat) ? "0":"1";
		return hasDone;
	}

	public void setHasDone(String hasDone) {
		this.hasDone = hasDone;
	}
	
}

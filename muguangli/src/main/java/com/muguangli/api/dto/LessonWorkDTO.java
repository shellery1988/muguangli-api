package com.muguangli.api.dto;

import com.muguangli.api.pojo.LessonWork;
import com.muguangli.api.pojo.LessonWorkCommentTeacher;
import com.muguangli.util.DateFormatUtil;

public class LessonWorkDTO extends LessonWork{
	
	private LessonWorkCommentTeacher teacher;

	private String createTimeFormat;
	
	private String updateTimeFormat;
	
	public LessonWorkCommentTeacher getTeacher() {
		return teacher;
	}

	public void setTeacher(LessonWorkCommentTeacher teacher) {
		this.teacher = teacher;
	}

	public String getCreateTimeFormat() {
		if(null != getCreateTime()) {
			this.createTimeFormat = DateFormatUtil.date2StringFull(getCreateTime());
		}
		return createTimeFormat;
	}

	public void setCreateTimeFormat(String createTimeFormat) {
		this.createTimeFormat = createTimeFormat;
	}

	public String getUpdateTimeFormat() {
		if(null != getUpdateTime()) {
			this.updateTimeFormat = DateFormatUtil.date2StringFull(getUpdateTime());
		}
		return updateTimeFormat;
	}

	public void setUpdateTimeFormat(String updateTimeFormat) {
		this.updateTimeFormat = updateTimeFormat;
	}
	
}

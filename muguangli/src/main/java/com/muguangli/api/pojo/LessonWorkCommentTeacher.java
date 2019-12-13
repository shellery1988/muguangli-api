package com.muguangli.api.pojo;

import java.util.Date;

import com.muguangli.util.DateFormatUtil;

public class LessonWorkCommentTeacher {
    private Integer id;

    private Integer workId;

    private String teacherName;

    private String commentContent;

    private Date createTime;
    
	private String createTimeFormat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
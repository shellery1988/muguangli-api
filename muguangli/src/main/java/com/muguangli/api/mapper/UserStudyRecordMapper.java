package com.muguangli.api.mapper;

import java.util.List;

import com.muguangli.api.pojo.UserStudyRecord;
import com.muguangli.api.vo.UserStudyRecordVO;

public interface UserStudyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserStudyRecord record);

    int insertSelective(UserStudyRecord record);

    UserStudyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserStudyRecord record);

    int updateByPrimaryKey(UserStudyRecord record);
    
    Integer queryTotalUnlockScoreWithLesson(UserStudyRecord record);
    
    List<UserStudyRecordVO> queryUserStudyRecordInAllStage(UserStudyRecord record);
    
    List<UserStudyRecord> queryUserStudyRecordList(UserStudyRecord record);
    
}
package com.muguangli.api.enums;

public enum StageScoreEnum {
	
	STAGE_O_SCORE(0, 0),
	STAGE_1_SCORE(1, 3),
	STAGE_2_SCORE(2, 2),
	STAGE_3_SCORE(3, 2),
	STAGE_4_SCORE(4, 3),
	;

	private StageScoreEnum(Integer stage, Integer score){
		this.stage = stage;
		this.score = score;
	}
	
	/**
	 * 类型
	 */
	private Integer stage;
	
	/**
	 * 描述
	 */
	private Integer score;

	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
	public static StageScoreEnum getByStage(Integer stage) {  
        for (StageScoreEnum stageScoreEnum : values()) {  
            if (stage.intValue() == stageScoreEnum.getStage().intValue()) {  
                return stageScoreEnum;
            }
        }  
        return null;  
    }
	
}

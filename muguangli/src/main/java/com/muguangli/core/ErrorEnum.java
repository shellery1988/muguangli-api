package com.muguangli.core;

/**
 * 
 * @Description:错误枚举类
 * @Version:1.1.0
 */

public enum ErrorEnum {
    /**
     * 系统成功
     */
    SUCCESS("1", "成功"),

    /**
     * 系统失败
     */
    ERROR("0", "失败"),

    /**
     * 判断是否登陆
     */
    NOT_LOGIN("2", "用户没有登陆"),

    // 00 公共错误
    /**
     * 数据库异常
     */
    DB_ERROR("0001", "数据库异常"),

    /**
     * 上传文件失败
     */
    FILE_UPLOAD_ERROR("0009", "上传文件失败"),

    /**
     * 参数错误
     */
    PARAM_ERROR("0010", "参数错误"),

    /**
     * 参数校验失败
     */
    PARAM_NULL("0011", "参数不能为空"),

    /**
     * 参数校验失败
     */
    STRING_TO_JSON_ERROR("0012", "STRING转型JSON异常"),

    /**
     * 参数校验失败
     */
    STRING_TO_LONG_ERROR("0013", "STRING转型LONG异常"),

    /**
     * 参数校验失败
     */
    PARAM_TO_CLASS_ERROR("0014", "param转ClASS异常"),

    /**
     * 参数校验失败
     */
    JSON_TO_CLASS_ERROR("0015", "JSON转CLASS异常"),

    /**
     * 参数校验失败
     */
    REDIS_CALL_ERROR("0016", "调用REDIS异常"),

    /**
     * 系统成功
     */
    PARAM_DECODE_ERROR("0017", "参数解码异常"),

    /**
     * 系统成功
     */
    STRING_TO_INT_ERROR("0018", "STRING转型int异常"),

    // 11 题库
    /**
     * 系统成功
     */
    CAT1_Id_NULL("1101", "类目不能为空"),

    /**
     * 系统成功
     */
    SEX_NULL("1102", "性别不能为空"),

    /**
     * 系统成功
     */
    MONTH_ERROR("1103", "总月数参数错误"),

    /**
     * 系统成功
     */
    BABYID_NULL("1103", "babyId不能为空"),

    // 12 题目,答案

    /**
     * 参数校验失败
     */
    EXAM_NUM_NULL("1201", "考号为空"),

    /**
     * 参数校验失败
     */
    ANS_NULL("1202", "答案为空"),

    // 13 评分结果

    // 14

    // 2 标签
    /**
     * 参数校验失败
     */
    LABEL_PID_NULL("2001", "pid不能为空"),

    /**
     * 参数校验失败
     */
    LABEL_CATID_NULL("2002", "catId不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_TYPE_NULL("2003", "type不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_FOREIGNKEY_NULL("2004", "foreignKey不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_LABELLIST_NULL("2005", "list不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_ERROR("0", "批量打标失败"),

    /**
     * 参数校验失败
     */
    MARK_ITE_FROM_AGE_NULL("2006", "fromAge不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_FROM_MONTH_NULL("2007", "fromMonth不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_TO_AGE_NULL("2008", "toAge不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_TO_MONTH_NULL("2009", "toMonth不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_SEX_NULL("2010", "sex不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_TOTAL_MONTH_NULL("2011", "totalMonthFrom不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_BABYID_NULL("2012", "babyId不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_YN_NULL("2013", "yn不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_UPDATEDATE_NULL("2014", "updateDate不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_LAT_NULL("2013", "lat不能为空"),

    /**
     * 参数校验失败
     */
    MARK_ITE_LNG_NULL("2014", "lng不能为空"),

    /**
     * 校验失败
     */
    LABEL_LABELP_YNERR("2015", "父类标签状态不可用"),

    /**
     * 校验失败
     */
    LABEL_LABELSON_YNERR("2016", "存在有效子类，不可以停用"),

    /**
     * 校验失败
     */
    LABEL_LABEL_YNERR("2017", "存在有效下属标签，不可以停用"),
    /**
     * 校验失败
     */
    NODE_NOT_EXIST("3001", "节点不存在"),
    /**
     * 校验失败
     */
    NODE_HAD_EXIST("3002", "节点已存在"), 
    /**
     * 请求重复
     */
    REPEAT_DATE("3003", "请勿重复提交"),
    
    /**
     * 商品列表不能为空
     */
    GOODS_LIST_IS_NULL("4001", "商品列表不能为空"),
    /**
     * poCode不能为空
     */
    PO_CODE_IS_NULL("4001", "poCode不能为空"),
    /**
     * goodsCode不能为空
     */
    GOODS_CODE_IS_NULL("4002", "商品编码不能为空"),
    /**
     * po不能为空
     */
    PO_IS_NULL("4003", "采购单不存在"),
    /**
     * receiveNum不能为空
     */
    RECEICE_NUM_IS_NULL("4004", "收货数量不能为空"),
    /**
     * arrivedDate不能为空
     */
    RECEICE_DATE_IS_NULL("4005", "收货数量不能为空"),
    /**
     * appointment不能为空
     */
    APPOINTMENT_IS_NULL("4006", "预约单不能为空"),
    /**
     * appointment不能为空
     */
    CANT_ADD_PO_IS_NULL("4007", "过期采购单不可以补单"),
    /**
     * appointment不能为空
     */
    CANT_ADD_PO_IS_OVER("4009", "订单过期，未超有效期1个月，并且未完全收货，才可补单"),
    /**
     * appointment不能为空
     */
    IS_ADD_CANT_ADD("4008", "补单数据不可以再补单"),
    /**
     * 不可以重复补单
     */
    HAD_ADD_CANT_OPT("4009", "不可以重复补单"),
    
    /**
     * 补单进行中，不可重复单
     */
    HAD_ADD_CANT_OPT_BEGIN("4010", "创建补单中，不可重复补单"),

    /**
     * 接口调用异常
     */
    REQUEST_EXCEPTION("9001", "接口调用异常"),
    
    /**
     * 请求重复
     */
    REQUEST_REPEAT("9009", "重复请求，已被拦截"),
    
    RECEIVE_EXPIRE_EFECTDATE("4010","收货日期不能超出订单有效日期"),
    RECEIVE_BILL_STATE("4011","订单状态必须是部分收获或者待收货状态"),
    RECEIVE_RECEIVEAMOUNT("4012","收货数量超出订货数量"),
    RECEIVE_ORDERTAXRATE("4013","税率不一致"),
    RECEIVE_PRODUCTIONDATEFORMAT("4014","收货日期格式不对，应yyyyMMdd"),
    RECEIVE_PRODUCTIONDATEEXP("4015","生产日期不可大于当前时间"),
    RECEIVE_CONTRACTEXIT("4016","订单合同号不存在"),
    RECEIVE_CONTRACTEXP("4017","订单合同号不存有效期内"),
    RECEIVE_REDGOODSSTOCK("4018","出货数量超出仓库库存"),

    SAFEDAYCONFIG_CHECK_ERROR("4019","安全天数优先级配置重复，请检查配置项和优先级"),
    SAFEDAYCONFIG_DELETE_ERROR("4020","安全天数优先级作废失败，该优先级有对应的安全天数"),

    
    EXHIBITCONFIG_CHECK_PRIORITY_REPEAT("4021","陈列量维度优先级重复，请检查是否有相同的维度优先级"),
    EXHIBITCONFIG_CHECK_TYPE_REPEAT("4022","陈列量维度类型重复，请检查是否有相同的维度类型"),
    EXHIBITCONFIG_CHECK_TYPE_USED("4024","该陈列量维度类型已被使用"),

	PACKRATECONFIG_CHECK_ERROR("4025","包装率优先级配置重复，优先级和配置项不能重复"),

    EXHIBIT_CHECK_REPEAT("4023","陈列量已重复"),
    
    PO_DELAY_STATE_CHECK("4026", "已审核、部分完成状态订单才可延期"),
    
    DC_REFUND_RECEIVE_REPEAT("4027","大仓退货重复收货"),
    
    PO_DELAY_REPEAT_CHECK("4028","订单只能进行一次延期操作"),
    
    PO_SUBMIT_STATE_CHECK("4030","订单状态已发生变化，请检查当前订单是否已提交"),
    
    PO_APPROVE_STATE_CHECK("4031","订单状态已发生变化，请检查当前订单是否已审批"),
    
    PO_APPROVE_NODE_CURRENT_ERROR("4032","当前审核流程信息获取错误"),
    
    PO_APPROVE_NODE_CURRENT_NULL("4033","当前待审批信息不存在"),
    
    PO_APPROVE_HANDLE_ERROR("4034","审核流程流转错误"),
    
    PO_APPROVE_DETAILNULL_ERROR("4035","明细为空，无法提交"),
    
    PO_APPROVE_NODE_NEXT_ERROR("4036","下一审核节点信息获取错误"),
    
    PO_ADD_DETAIL_NULL("4037", "可补单明细为空，无法补单"),
    
    ;
    // 错误码
    private String errorCode;
    // 提示信息
    private String msg;

    /**
     * 
     * 创建一个新的实例 CommonError.
     * 
     * @param errorCode
     * @param msg
     */
    ErrorEnum(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
    }

    /**
     * errorCode
     * 
     * @return the errorCode
     */

    public String getErrorCode() {
        return errorCode;
    }

    /**
     * msg
     * 
     * @return the msg
     */

    public String getMsg() {
        return msg;
    }

}

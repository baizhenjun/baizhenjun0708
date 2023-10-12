package com.example.java_springboot.batis;

import lombok.Getter;

/**
 * 结果枚举
 *@author Bryan Wang
 */
@Getter
public enum ResultEnum {


    /**
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * 失败
     */
    ERROR(3,"失败"),
    /**
     * 接口异常
     */
    API_ERROR(1,"系统出现问题，请联系管理员处理"),
    /**
     * 报送已结束
     */
    SUBMIT_TASK_API_ERROR(201,"当前报送任务已结束"),
    /**
     * LICENSE校验错误
     */
    LICENSE_VERTIFY_ERROR(2,"License不正确或已过期"),
    START_FLOW_TRACK_ERROR(4,"当前审计工作流程跟踪接口异常"),
    /**
     * 参数
     */
    PARAM_ERROR(1000,"参数不正确"),

    /**
     * 数据已存在
     */
    DATA_UNIQUE_ERROR(2000,"数据已存在"),
    CODE_TYPE_ERROR(2001,"代码类型编码已存在"),
    DATA_NONEXISTENT_ERROR(2002,"数据不存在"),
    CLOSING_STATEMENT_ERROR(2003,"结项书数据不存在，请添加"),
    DATA_COPY_ERROR(2004,"数据复制异常"),
    DATA_PART_NONEXISTENT_ERROR(2005,"部分数据不存在"),



    /**
     * 用户信息
     */
    USER_NOT_EXIST(2011,"用户不存在"),
    LOGIN_INF_EXPIRE(2012,"登录信息失效"),
    LOGIN_FAIL(2013,"登录失败，登录信息不正确"),
    GET_USER_INFO_ERROR(2014,"获取用户信息失败"),
    LOGOUT_SUCCESS(2016,"登出成功"),

    /**
     * 审计计划
     * 2020-2049
     */

    AUDIT_PLAN_DEL(2020,"审计计划只能删除草稿状态的数据"),
    AUDIT_PLAN_SUBMIT_TIPS(2021,"您提交的审计计划数据包含已审核中的,请刷新当前页面后重试"),
    AUDIT_PLAN_SUBMIT_TIPS_2(2022,"您提交的审计计划数据包含未变更的,请修改或刷新当前页面后重试"),




    /**
     * 审计作业立项
     * 2050-2099
     */
    AUDIT_TASK_PROJECT_INIT_PERSON_ROLE(2050,"审计作业-立项人员主审不能重复添加"),
    AUDIT_TASK_PROJECT_INIT_PERSON_LEADER_ROLE(2054,"审计作业-立项人员组长、主审不能重复添加"),
    AUDIT_TASK_PROJECT_INIT_STAGE_TIME(2051,"审计作业-%s%s时间必须设置"),
    AUDIT_TASK_PROJECT_INIT_PERSON_ROLE_NO(2052,"审计作业-立项人员组长、主审必须有"),
    AUDIT_TASK_PROJECT_INIT_NO_SUBMIT(2053,"当前项目已完结不能进行立项调整"),
    AUDIT_TASK_PROJECT_INIT_APPROVE_ERROR(2055,"审计作业立项已审核，请刷新页面"),
    AUDIT_TASK_PROJECT_AUDITEE_LIST(2056,"当前没有可执行的项目，请刷新页面"),
    AUDIT_TASK_PROJECT_SET_LEADER(2057,"一个项目只能设置一个组长、一个主审，请修改下再设置"),
    AUDIT_TASK_PROJECT_INIT_PERSON_ROLE_MEMBER_NO(2058,"审计作业-立项人员组员必须有"),
    AUDIT_TASK_PROJECT_INIT_CONNECTOR_NO(2059,"审计作业立项-请设置被审计对象对接人后再提交"),

    /**
     * 审计方案
     * 2101-2199
     */
    AUDIT_TASK_SCHEME_NOT_SUBMIT(2101,"审计方案有人员或审计程序未设置"),
    AUDIT_TASK_SCHEME_APPROVE_ERROR(2102,"审计方案已审核，请刷新页面"),
    AUDIT_TASK_SCHEME_BATCH_IMPORT_ITEM_ERROR(2103,"审计方案批量引入请勾选事项"),

    /**
     * 审计通知书
     * 2101-2199
     */
    AUDIT_TASK_NOT_LIXIANG(2607,"审计项目现场阶段立项未完成，请先到项目操作台的项目基本信息节点，点击【完善项目信息】并完成审批流程!"),
    AUDIT_TASK_NOTICE_NOT_SUBMIT(2601,"此项目处于计划发布阶段，请先到项目操作台的项目基本信息节点，点击【完善项目信息】并完成审批流程!"),
    AUDIT_TASK_FIND_NOT_SUBMIT(2602,"准备阶段未结束，请先到准备阶段上传审计通知书！"),
    AUDIT_TASK_NOT_UPLOAD(2603,"审计通知书正式稿未上传，不能结项!"),
    AUDIT_TASK_IMPLEMENT_NOT_END(2604,"请先在“审计发现及建议汇总”环节完成确认实施阶段结束工作，再提交审批！"),
    AUDIT_TASK_REPORT_NOT_END(2605,"未到报告阶段结束时间，不可以结项"),
    AUDIT_TASK_IMPLEMENT_TIME_NOT_END(2606,"实施阶段未开始，还不能设置结束实施阶段（实施阶段开始时间是第一份审计通知书上传时间加3个自然天）！"),
    /**
     * 审计底稿
     */

    AUDIT_DRAFT_APPROVE_ERROR(2201,"审计底稿已审核，请刷新页面"),

    /**
     * 审计作业 - 审计发现及建议
     */
    AUDIT_TASK_FIND_PROBLEM(2301,"请设置问题类型才能入库"),
    AUDIT_TASK_FIND_PROBLEM_PARAM(2302,"请选择问题"),

    /**
     * 审计报告
     */
    AUDIT_REPORT_APPROVE_ERROR(2401,"审计报告初稿已审核，请刷新页面"),
    AUDIT_REPORT_OFFICE_DRAFT_NO_UPLOAD(2402,"审计报告盖章件未上传，不能结项!"),

    /**
     * 审计项目结项 归档
     */
    AUDIT_CLOSE_PROJECT(2501,"当前项目未结项，不能归档!"),


    //图集或视频内容错误,
    FILE_UPLOAD_FAILED(2501,"文件上传失败！"),
    FILE_DOWNLOAD_FAILED(2502,"文件下载失败！"),
    FILE_IS_NOT_EXIST(2503,"文件不存在！"),
    STARFLOW_TERMINATE_ERROR(2504,"流程终止异常"),
    FILE_REMOVE_FAILED(2505,"文件删除失败!"),

    EDITING_NOT_ALLOWED(1001001,"存在不可编辑/删除数据"),

    DATA_NOT_EXIST(3000,"暂无不存在"),
    DOCUMENT_NUM_ERROR(3001,"文号已存在"),
    AUTH_NUM_EXIST(3002,"权限数据不存在"),
    QUERY_TYPE_NONEXISTENT(3003,"资料类型信息未输入"),
    PROJECT_ID_NONEXISTENT(3004,"项目id信息未输入"),
    DATA_REPEAT_ERROR(3005,"数据重复异常"),
    PERDON_DATA_ERROR(3006,"人员数据不存在"),
    NOT_AUTH_ERROR(3007,"没有数据权限"),
    NOT_ID_ERROR(3008,"未传ID"),
    NOT_FILE(3009,"无可下载文件"),
    NOT_SCHEME(3010,"未找到可生成方案"),
    /**
     * 内控
     */
    AUDIT_ICE_SUBMIT(3030,"公司不允许重复"),

    SCHEME_NONEXISTENT(4000,"审计方案不存在"),

    TASK_NONEXISTENT(5000,"项目不存在"),

    /**
     * 审计指引
     */
    AUDIT_GUIDELINES_NON_EXISTENT(6000,"审计指引不存在"),
    AUDIT_GUIDELINES_EXISTENT(6001,"审计指引已存在"),
    AUDIT_GUIDELINES_KEY_EXISTENT(6002,"审计指引key已存在"),

    /**
     * 复核
     */
    ICE_REVIEW_TASK_NO(7000,"未查询到需要提交的任务!"),
    ICE_REVIEW_TASK_ALL_SUBMIT_NO(7001,"请全部填写后提交!"),
    ICE_REVIEW_TASK_ALL_SUBMIT(7002,"当前任务已全部处理完成！"),


    /**
     * 内控错误说明
     */
    ICE_ACTIVITIES_DATA_NOT_EXIST(900001,"矩阵数据不存在"),

    /**
     * 流程相关的说明
     */
    ICE_PROCESS_DATA_EXIST(900101,"标准流程在当前公司下已存在"),
    ICE_PROCESS_REQUEST_PARENT_NO_PASS(900102,"标准流程申请父级[%s]未审批通过"),
    ICE_PROCESS_REQUEST_CHILD_NO_SUBMIT(900103,"标准流程申请--流程/环节[%s]，请您一并提交"),

    Archiving(2,"归档任务已启动"),

    Archived(1,"归档中或已完成，请勿重复操作"),


    ;



    /**
     * code表示返回信息code
     */
    private Integer code;
    /**
     * 枚举类型信息
     */
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getValue(Integer code) {
        ResultEnum[] resultEnums = values();
        for (ResultEnum resultEnum : resultEnums) {
            if (resultEnum.getCode().equals(code)) {
                return resultEnum.getMessage();
            }
        }
        return null;
    }

}

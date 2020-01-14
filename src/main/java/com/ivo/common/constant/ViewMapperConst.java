package com.ivo.common.constant;

/**
 * 视图映射
 * @author wj
 * @version 1.0
 */
public class ViewMapperConst {

    // 入口页面
    public static final String view_index = "/index";

    // 登录页面
    public static final String view_login = "/login";

    /** System模块 **/
    // 菜单资源管理页面
    public static final String view_system_resource = "/page/system/resource";

    /** coq质量成本模块 **/
    // 机种列表页面
    public static final String view_coq_project = "/page/coq/project";
    // 机种阶段页面
    public static final String view_coq_stage = "/page/coq/stage";
    // 机种进度页面
    public static final String view_coq_schedule = "/page/coq/schedule";
    // 机种每个阶段EE单关联页面
    public static final String view_coq_stageEe = "/page/coq/stageEe";
    // 机种的项目成员界面
    public static final String view_coq_member = "/page/coq/member";

    // 机种成本页面
    public static final String view_coq_cost = "/page/coq/cost";
    // 直接材料成本页面
    public static final String view_coq_directMaterial = "page/coq/directMaterial";

    // 实验机台信息页面
    public static final String view_coq_verificationMachine = "page/coq/verificationMachine";
    // 验证项目信息页面
    public static final String view_coq_verificationSubject = "page/coq/verificationSubject";
    // 验证费用页面
    public static final String view_coq_verificationCost = "page/coq/verificationCost";
    // 治工具费用页面
    public static final String view_coq_jigCost = "page/coq/jigCost";
    // 研发人员薪资成本页面
    public static final String view_coq_personnelSalaryCost = "page/coq/personnelSalaryCost";
    // 差旅费用页面
    public static final String view_coq_travelCost = "page/coq/travelCost";
    // OBA费用界面
    public static final String view_coq_obaCost = "page/coq/obaCost";

    // IE站点费用
    public static final String view_coq_station = "page/coq/station";
    // 生产费用
    public static final String view_coq_productionCost = "page/coq/productionCost";
    // 重工报废费用
    public static final String view_coq_reworkScrapCost = "page/coq/reworkScrapCost";

}

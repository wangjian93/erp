package com.ivo.common.constant;

/**
 * 数据状态有效性常量
 * @author wj
 * @version 1.0
 */
public class ValidStatusConst {

    /**
     * 删除状态码
     */
    public static final byte DELETE = 0;

    /**
     * 正常有效状态码
     */
    public static final byte VALID = 1;


    /**
     * SQL语句逻辑删除
     */
    public static final String SLICE_Delete_SQL = "set valid_flag=" + DELETE + " WHERE id=?";
    public static final String SLICE_Delete_For_User_SQL = "set valid_flag=" + DELETE + " WHERE userid=?";
    public static final String SLICE_Delete_For_Role_SQL = "set valid_flag=" + DELETE + " WHERE name=?";
    public static final String SLICE_Delete_For_Project_SQL = "set valid_flag=" + DELETE + " WHERE project=?";



    /**
     * SQL语句有效数据筛选
     */
    public static final String NOT_DELETE_SQL = "valid_Flag != " + DELETE;
}

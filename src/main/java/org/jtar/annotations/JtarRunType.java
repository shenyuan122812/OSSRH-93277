package org.jtar.annotations;

/**
 * @author Allen.Shen
 * @createTime 2023/6/11 21:35
 * @description
 */
@SuppressWarnings("all")
public enum JtarRunType {

    CAREATE_NEW_TESTCASE,
    NOT_CAREATE_NEW_TESTCASE,
    OPEN_ORDINARY_ASSERTION,
    CLOSE_ORDINARY_ASSERTION,

    OPEN_SQL_ASSERTION,
    CLOSE_SQL_ASSERTION,
    DINGTALK,
    WECOM,
    EMAIL,
    ClOSED_NOTICE

}

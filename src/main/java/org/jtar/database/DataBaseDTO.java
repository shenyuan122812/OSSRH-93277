package org.jtar.database;


import lombok.Data;
import org.jtar.annotations.DBEnum;

/**
 * author Allen.Shen
 * createTime 2023/6/12 11:58
 * description 数据库类
 */
@Data
public class DataBaseDTO {

    private DBEnum dbType;

    private String host;

    private String username;

    private String passwd;

    private int port;

}

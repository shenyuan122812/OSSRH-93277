package org.jtar.database.oracle;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.jtar.annotations.DBEnum;
import org.jtar.database.DataBaseDTO;
import org.jtar.database.DbConfiguration;

@Slf4j
public class OracleConfigurationImpl implements DbConfiguration {
    private DataBaseDTO dataBaseDTO;
    public  OracleConfigurationImpl(){

    }
    public  OracleConfigurationImpl(DataBaseDTO dataBaseDTO){
                this.dataBaseDTO = dataBaseDTO;
    }

    /**
     * 校验oracle
     */
    @Override
    public void check() {
        if (ObjectUtils.isNotEmpty(dataBaseDTO) && DBEnum.ORACLE.equals(dataBaseDTO.getDbType())){
            log.info("使用Oracle");
        }else {
            log.info("不使用Oracle");
        }
    }
}

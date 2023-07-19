package org.jtar.time_tool;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author Allen.Shen
 * createTime 2023/5/28 5:34
 * description
 */

public class TimeControl {

    public  String generationTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

}

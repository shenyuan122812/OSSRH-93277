package org.jtar.notice;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import org.jtar.yaml.YamlReader;

import java.util.HashMap;
import java.util.Map;

public class FeiShu {
    private    String WebHookUrl ;
    public  FeiShu(){

    }
    public  FeiShu(String  WebHookUrl){
        this.WebHookUrl= WebHookUrl;
    }
    public void sendMessage(String msg){
        //请求的JSON数据，这里用map在工具类里转成json格式
        Map<String,Object> json=new HashMap<>();
        Map<String,Object> text=new HashMap<>();
        json.put("msg_type", "text");

        text.put("text", "项目环境地址：" + msg);
        text.put("text", "项目环境地址：" + msg);
        text.put("text", "项目环境地址：" + msg);
        json.put("content", text);
        //发送post请求
        String result = HttpRequest.post(this.WebHookUrl).body(JSON.toJSONString(json), "application/json;charset=UTF-8").execute().body();
    }

    public static void main(String[] args) {
        FeiShu feiShu = new FeiShu("https://open.feishu.cn/open-apis/bot/v2/hook/6058cd4c-e746-4d73-91ae-cd58558a542d");
        feiShu.sendMessage("https://demojti.jtg2g.com/app/bulkCargoIndex/airSplitOrderView/541907361163292714");
    }
}

package com.markerhub;

import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootTest
class VueblogApplicationTests {


    @Test
    void contextLoads() {
    }


    @Test
    void testSSO() {
        /** appKey,在调试此demo时请替换成自身的appKey*/
        String APP_KEY = "test3";
/** token,在调试此demo时请替换成自身的token*/
        String TOKEN = "347c6161e79f4b6a8873202dd5fe7e8f";
/** 统一身份API开放中心地址,在调试此demo时请根据接入手册替换URL*/
        String UIM_OPA_URL = "http://test.ssa.jd.com/sso/login";

        try {
            Map<String, String> params = new HashMap<String, String>();
            //设置appKey
            params.put("app_key", APP_KEY);
            //设置版本号
            params.put("v", "2.0");
            //设置返回数据类型
            params.put("format", "json");
            //设置时间戳
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = simpleDateFormat.format(new Date());
            params.put("timestamp", timestamp);
            //设置随机数
            String random = new Random().nextInt(1000000000)+"";
            params.put("random", random);
            //生成签名
            String sign = JopApiDigest.getInstance().generate(APP_KEY, TOKEN, timestamp, random);
            params.put("sign", sign);//设置参数签名
            //==========以上代码不用修改
            //设置API名称
            params.put("method", "uim.auth.res.getAuthAppChildMenus");
            //设置请求参数
            params.put("systemResCode", "ERP");//系统的资源码
            params.put("erpId", "louqiang4");//erp账号
            //设置返回参数字段列表(可选)
            //params.put("fields", "personId,personName");
            //
            //执行，调用封装的httpUtils接口
            YesHttpUtil yesHttpUtil = new YesHttpUtil();
            com.alibaba.fastjson.JSONObject response = yesHttpUtil.httpRequestPost(UIM_OPA_URL, JSONObject.fromObject(params).toString(), Integer.parseInt("20000"));
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("调用统一身份开放中心接口出错,"+e.getMessage());
            e.printStackTrace();
        }

    }
    @Test
    void testSwitch() {
        String jdPin = "jd_sjIgFPfuhVoL";
        int hash = Math.abs(jdPin.hashCode()) % 100 + 1;
        System.out.println(hash);

    }

}

package com.kpi.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kpi.system.domain.KPI;
import com.kpi.system.service.KpiService;
import com.kpi.system.token.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "kpi系统")
@RestController
public class KPIController {
    @Autowired
    KpiService kpiService;
//18829098761

    @ApiOperation("登录并获取权限")
    @PostMapping("/login")
    public Object login(String phone, String password){
        boolean k = kpiService.validateAccount(phone,password);
        String root = kpiService.validateRoot(phone);
        JSONObject jsonObject = new JSONObject();
        if (k){
            String token = TokenUtil.sign(phone,password);
            jsonObject.put("token",token);
            jsonObject.put("phone",phone);
            jsonObject.put("password",password);
            jsonObject.put("root",root);
            jsonObject.put("msg","登录成功");
            jsonObject.put("code",200);
        }else{
            jsonObject.put("msg","账号或密码错误");
            jsonObject.put("code",500);
        }
        return jsonObject;
    }

    @ApiOperation("用户信息")
    @GetMapping("/user")
    public Object user(String phone){
        List<KPI> kpis = kpiService.showUser(phone);
        JSONObject jsonObject = new JSONObject();
        JSONArray list =JSONObject.parseArray(JSON.toJSONString(kpis));
        if(kpis==null){
            jsonObject.put("msg","获取失败");
            jsonObject.put("code",500);
        }else{
            jsonObject.put("msg","获取成功");
            jsonObject.put("code",200);
            jsonObject.put("userinfo",list);
        }
        return jsonObject;
    }

    @ApiOperation("上司kpi")
    @GetMapping("/getKPI")
    public Object getKPI(String phone) {
        Integer id = kpiService.getId(phone);
        String name = kpiService.getName(phone);
        List<KPI> kpis = kpiService.showSkpi(id);
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(kpis));
        if (kpis != null && id != null && name != null) {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("KPI", list);
            jsonObject.put("name", name);
        } else {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        }
        return jsonObject;
    }
}

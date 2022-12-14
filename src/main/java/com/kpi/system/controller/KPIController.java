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

    @ApiOperation("登录并获取权限")
    @PostMapping("/login")
    public Object login(String phone, String password) {
        boolean k = kpiService.validateAccount(phone, password);
        String root = kpiService.validateRoot(phone);
        JSONObject jsonObject = new JSONObject();
        if (k) {
            String token = TokenUtil.sign(phone, password);
            jsonObject.put("token", token);
            jsonObject.put("phone", phone);
            jsonObject.put("password", password);
            jsonObject.put("root", root);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("code", 200);
        } else {
            jsonObject.put("msg", "账号或密码错误");
            jsonObject.put("code", 500);
        }
        return jsonObject;
    }

    @ApiOperation("用户信息")
    @GetMapping("/user")
    public Object user(String phone) {
        List<KPI> kpis = kpiService.showUser(phone);
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(kpis));
        if (kpis.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("userinfo", list);
        }
        return jsonObject;
    }

    @ApiOperation("员工kpi")
    @GetMapping("/getKPI")
    public Object getKPI(String phone) {
        List<KPI> kpiindex = kpiService.showKpiindex();
        Integer id = kpiService.getId(phone);
        String name = kpiService.getName(phone);
        Integer mark = kpiService.getConfirm(phone);
        List<KPI> kpis = kpiService.showKpi(id);
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(kpis));
        JSONArray kpiindexs = JSONObject.parseArray(JSON.toJSONString(kpiindex));
        if (kpis.isEmpty() || id.equals(0) || name.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("score", list);
            jsonObject.put("names", kpiindexs);
            jsonObject.put("name", name);
            jsonObject.put("mark", mark);
        }
        return jsonObject;
    }

    @ApiOperation("员工申诉")
    @PostMapping("/sendAppeal")
    public Object sendAppeal(String phone, String petition) {
        Integer id = kpiService.getId(phone);
        Integer petitions = kpiService.updatePetition(id, petition);
        JSONObject jsonObject = new JSONObject();
        if (petitions == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", petitions);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", petitions);
        }
        return jsonObject;
    }

    @ApiOperation("员工承诺书")
    @PostMapping("/commitment")
    public Object commitment(String phone, Integer mark) {
        Integer commitment = kpiService.setCovenant(phone, mark);
        JSONObject jsonObject = new JSONObject();
        if (commitment == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", commitment);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", commitment);
        }
        return jsonObject;
    }

    @ApiOperation("员工获取申诉")
    @GetMapping("/getComplain")
    public Object getComplain(String phone) {
        Integer id = kpiService.getId(phone);
        List<KPI> complain = kpiService.getPetition(id);
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(complain));
        if (complain.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", list);
        }
        return jsonObject;
    }

    @ApiOperation("HR反馈结果")
    @PostMapping("/surveyResult")
    public Object surveyResult(String name, String result) {
        Integer results = kpiService.updateResult(name, result);
        JSONObject jsonObject = new JSONObject();
        if (results == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", results);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", results);
        }
        return jsonObject;
    }

    @ApiOperation("HR提交反馈")
    @PostMapping("/sendResult")
    public Object sendResult(String name, Integer commit) {
        Integer results = kpiService.updateCommit(name, commit);
        JSONObject jsonObject = new JSONObject();
        if (results == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", results);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", results);
        }
        return jsonObject;
    }

    @ApiOperation("确认kpi")
    @PostMapping("/confirm")
    public Object confirm(String name, Integer confirm) {
        Integer results = kpiService.updateConfirm(name, confirm);
        JSONObject jsonObject = new JSONObject();
        if (results == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", results);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", results);
        }
        return jsonObject;
    }

    @ApiOperation("自评kpi")
    @PostMapping("/selfAssesment")
    public Object selfAssesment(String phone, Integer id, String score) {
        Integer staffid = kpiService.getId(phone);
        String letter = null;
        if (id == 1) {
            letter = "A";
        } else if (id == 2) {
            letter = "B";
        } else if (id == 3) {
            letter = "C";
        } else if (id == 4) {
            letter = "D";
        } else if (id == 5) {
            letter = "E";
        } else if (id == 6) {
            letter = "F";
        } else if (id == 7) {
            letter = "G";
        } else if (id == 8) {
            letter = "H";
        } else if (id == 9) {
            letter = "I";
        } else {
            letter = "J";
        }
        Integer results = kpiService.updateKpi(letter, score, staffid);
        JSONObject jsonObject = new JSONObject();
        if (results == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", results);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", results);
        }
        return jsonObject;
    }

    @ApiOperation("查找反馈")
    @GetMapping("/search")
    public Object search(String name) {
        List<KPI> result = kpiService.getResultByName(name);
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(result));
        if (result.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", list);
        }
        return jsonObject;
    }

    @ApiOperation("领导查看绩效")
    @GetMapping("/searchStaff")
    public Object searchStaff(String name) {
        Integer id = kpiService.getIdByName(name);
        List<KPI> result = kpiService.showKpi(id);
        List<KPI> kpiindex = kpiService.showKpiindex();
        Integer mark = kpiService.getAudit(id);
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(result));
        JSONArray kpiindexs = JSONObject.parseArray(JSON.toJSONString(kpiindex));
        if (result.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("score", list);
            jsonObject.put("name", kpiindexs);
            jsonObject.put("mark",mark);
        }
        return jsonObject;
    }

    @ApiOperation("领导审核")
    @PostMapping("/leadCheck")
    public Object leadCheck(String name, Integer mark) {
        Integer id = kpiService.getIdByName(name);
        Integer results = kpiService.updateAudit(id, mark);
        JSONObject jsonObject = new JSONObject();
        if (results == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", results);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", results);
        }
        return jsonObject;
    }

    @ApiOperation("上司查询kpi")
    @GetMapping("/formulateKPI")
    public Object formulateKPI(String name) {
        List<KPI> result = kpiService.showKpiindex();
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(result));
        if (result.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("kpi", list);
            jsonObject.put("name",name);
        }
        return jsonObject;
    }

    @ApiOperation("上司修改承诺书")
    @PostMapping("/changeCommitment")
    public Object changeCommitment(String commitment) {
        Integer results = kpiService.updateCovenants(commitment);
        JSONObject jsonObject = new JSONObject();
        if (results == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", results);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", results);
        }
        return jsonObject;
    }

    @ApiOperation("员工确认承诺")
    @GetMapping("/searchCommitment")
    public Object searchCommitment(String phone, Integer covenant) {
        Integer results = kpiService.updateCovenant(phone,covenant);
        JSONObject jsonObject = new JSONObject();
        if (results == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", results);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", results);
        }
        return jsonObject;
    }


    @ApiOperation("获取承诺书")
    @GetMapping("/getCommitment")
    public Object getCommitment(String phone) {
        Integer covenant = kpiService.getCovenant(phone);
        String result = kpiService.getCovenant();
        JSONObject jsonObject = new JSONObject();
        if (result == null) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", result);
            jsonObject.put("mark",covenant);
        }
        return jsonObject;
    }


    @ApiOperation("上司获取员工绩效与分数")
    @GetMapping("/getScroe")
    public Object getScroe(String name) {
        Integer id = kpiService.getIdByName(name);
        List<KPI> kpiindex = kpiService.showKpiindex();
        List<KPI> kpis = kpiService.showSkpi(id);
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(kpiindex));
        JSONArray lists = JSONObject.parseArray(JSON.toJSONString(kpis));
        if (kpis.isEmpty()|| kpiindex.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("kpi", list);
            jsonObject.put("score",lists);
            jsonObject.put("name",name);
        }
        return jsonObject;
    }

    @ApiOperation("上司修改kpi")
    @PostMapping("/changeScore")
    public Object changeScore(String name, String score, Integer id) {
        Integer staffid = kpiService.getIdByName(name);
        String letter = null;
        if (id == 1) {
            letter = "A";
        } else if (id == 2) {
            letter = "B";
        } else if (id == 3) {
            letter = "C";
        } else if (id == 4) {
            letter = "D";
        } else if (id == 5) {
            letter = "E";
        } else if (id == 6) {
            letter = "F";
        } else if (id == 7) {
            letter = "G";
        } else if (id == 8) {
            letter = "H";
        } else if (id == 9) {
            letter = "I";
        } else {
            letter = "J";
        }
        Integer results = kpiService.updateKpi(letter, score, staffid);
        JSONObject jsonObject = new JSONObject();
        if (results == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", results);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", results);
        }
        return jsonObject;
    }

    @ApiOperation("查看员工是否确认绩效")
    @GetMapping("/getPerformance")
    public Object getPerformance() {
        List<KPI> result = kpiService.getNameConfirm();
        List<KPI> results = kpiService.showKpiindex();
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(result));
        JSONArray lists = JSONObject.parseArray(JSON.toJSONString(results));
        if (result.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", list);
            jsonObject.put("kpi",lists);
        }
        return jsonObject;
    }

    @ApiOperation("上司确认员工绩效")
    @PostMapping("/updatePerformance")
    public Object updatePerformance(String name, Integer mark){
        Integer result = kpiService.updateSconfirm(name,mark);
        JSONObject jsonObject = new JSONObject();
        if (result == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", result);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", result);
        }
        return jsonObject;
    }


    @ApiOperation("修改指标库")
    @PostMapping ("/indicatorsLibrary")
    public Object indicatorsLibrary(Integer id,String kpiindex) {
        String letter = null;
        if (id == 1) {
            letter = "A";
        } else if (id == 2) {
            letter = "B";
        } else if (id == 3) {
            letter = "C";
        } else if (id == 4) {
            letter = "D";
        } else if (id == 5) {
            letter = "E";
        } else if (id == 6) {
            letter = "F";
        } else if (id == 7) {
            letter = "G";
        } else if (id == 8) {
            letter = "H";
        } else if (id == 9) {
            letter = "I";
        } else {
            letter = "J";
        }
        Integer results = kpiService.updateKpiindex(letter,kpiindex);
        JSONObject jsonObject = new JSONObject();
        if (results == 0) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
            jsonObject.put("data", results);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", results);
        }
        return jsonObject;
    }

    @ApiOperation("指标库显示")
    @GetMapping("/indicatorsShow")
    public Object getIndicatorsShow(){
        List<KPI> result = kpiService.showKpiindex();
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(result));
        if (result.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", list);
        }
        return jsonObject;
    }

    @ApiOperation("上司查询申诉")
    @GetMapping("/searchComplain")
    public Object searchComplain(String name){
        List<KPI> result = kpiService.getCommitresult(name);
        JSONObject jsonObject = new JSONObject();
        JSONArray list = JSONObject.parseArray(JSON.toJSONString(result));
        if (result.isEmpty()) {
            jsonObject.put("msg", "获取失败");
            jsonObject.put("code", 500);
        } else {
            jsonObject.put("msg", "获取成功");
            jsonObject.put("code", 200);
            jsonObject.put("data", list);
            jsonObject.put("name", name);
        }
        return jsonObject;
    }
}
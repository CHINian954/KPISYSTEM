package com.kpi.system.service;

import com.kpi.system.domain.KPI;

import java.util.List;

public interface KpiService {
    //登录验证
    public boolean validateAccount(String phone, String password);
    //查找权限
    public String validateRoot(String phone);
    //用户信息
    public List<KPI> showUser(String phone);
    //上司kpi
    public List<KPI> showSkpi(Integer id);
    //员工kpi
    public List<KPI> showKpi(Integer id);
    //kpi指标库
    public List<KPI> showKpiindex();
    //查找id
    public Integer getId(String phone);
    //查找名字
    public String getName(String phone);
    //提交申诉
    public Integer updatePetition(Integer id, String petition);
    //查找确认
    public Integer getConfirm(String phone);
    //确认承诺
    public Integer setCovenant(String phone,Integer convenant);
    //查找申诉结果
    public List<KPI> getPetition(Integer id);
    //反馈申诉结果
    public Integer updateResult(String name, String result);
    //提交结果
    public Integer updateCommit(String name, Integer commit);
    //确认kpi
    public Integer updateConfirm(String name, Integer confirm);
    //自评kpi
    public Integer updateKpi(String letter, String score, Integer id);
    //查找反馈
    public List<KPI> getResultByName(String name);
    //通过name查找id
    public Integer getIdByName(String name);
    //通过名字查找phone
    public String getPhoneByName(String name);
    //通过名字修改audit
    public Integer updateAudit(Integer id, Integer audit);
    //修改承诺书
    public Integer updateCovenants(String covenants);
    //确认承诺
    public Integer updateCovenant(String phone , Integer covenant);
    //获取承诺书
    public String getCovenant();
    //修改员工kpi
    public Integer updateSkpi(String letter, String score, Integer id);
    //获取员工名字与确认标识
    public List<KPI> getNameConfirm();
    //修改上司确认标识
    public Integer updateSconfirm(String name , Integer sconfirm);
    //修改kpi指标库
    public Integer updateKpiindex(String letter , String kpiindex);

    public List<KPI> getCommitresult(String name);

    public Integer getAudit(Integer id);

    public Integer getCovenant(String phone);
}

package com.kpi.system.service.serviceImpl;

import com.kpi.system.dao.KPIDao;
import com.kpi.system.domain.KPI;
import com.kpi.system.service.KpiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KpiService")
public class KpiServiceImpl implements KpiService {
    @Autowired
    KPIDao kpiDao;

    //登录
    @Override
    public boolean validateAccount(String phone, String password) {
        KPI kpi = new KPI();
        kpi.setPhone(phone);
        kpi.setPassword(password);
        List<KPI> kpis = kpiDao.findByUsernamePassword(kpi);
        if (kpis.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    //获取权限
    @Override
    public String validateRoot(String phone) {
        String root = kpiDao.finByPhoneRoot(phone);
        if (root.isEmpty()) {
            return "false";
        } else {
            return root;
        }
    }

    //获取用户信息
    @Override
    public List<KPI> showUser(String phone) {
        List<KPI> kpis = kpiDao.findUserByPhone(phone);
        if (kpis == null) {
            return null;
        } else {
            return kpis;
        }
    }

    //获取指标库
    @Override
    public List<KPI> showKpiindex() {

        List<KPI> kpis = kpiDao.findKpiAll();
        if (kpis == null) {
            return null;
        }else {
            return kpis;
       }
    }

    //获取id
    @Override
    public Integer getId(String phone) {
        Integer id = kpiDao.findIdByPhone(phone);
        if (id == null) {
            return null;
        } else {
            return id;
        }
    }

    //获取上司kpi
    @Override
    public List<KPI> showSkpi(Integer id){
        List<KPI> kpis = kpiDao.findSkpiById(id);
        if (kpis == null) {
            return null;
        }else {
            return kpis;
        }
    }

    //获取员工kpi
    @Override
    public List<KPI> showKpi(Integer id) {
        List<KPI> kpis = kpiDao.findKpiById(id);
        if (kpis == null) {
            return null;
        }else {
            return kpis;
        }
    }

    //获取名字
    @Override
    public String getName(String phone) {
        String name = kpiDao.findNameByPhone(phone);
        if (name == null) {
            return null;
        } else {
            return name;
        }
    }

    //提交申请
    @Override
    public Integer updatePetition(Integer id, String petition) {

        Integer petitions = kpiDao.updateKpipetition(id,petition);
        if (petitions == null) {
            return null;
        } else {
            return petitions;
        }
    }

    //查找确认
    @Override
    public Integer getConfirm(String phone) {
        Integer confirm = kpiDao.findConfirmByphone(phone);
        if (confirm == null) {
            return null;
        }else {
            return confirm;
        }
    }
}
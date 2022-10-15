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
        if(kpis.isEmpty()){
            return false;
        }
        else {
            return true;
        }

    }

    //获取权限
    @Override
    public String validateRoot(String phone){
        String root = kpiDao.finByPhoneRoot(phone);
        if(root.isEmpty()){
            return "false";
        }
        else {
            return root;
        }
    }
    
    //获取用户信息
    @Override
    public List<KPI> showUser(String phone){
        List<KPI> kpis = kpiDao.findUserByPhone(phone);
        if (kpis== null){
            return null;
        }else{
            return kpis;
        }

    }
}

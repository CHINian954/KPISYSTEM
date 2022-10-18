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

    //确认承诺
    @Override
    public Integer setCovenant(String phone, Integer convenant) {
        Integer convenants = kpiDao.updateCovenant(phone, convenant);
        if (convenants == null) {
            return null;
        }else {
            return convenants;
        }
    }

    //获取反馈
    @Override
    public List<KPI> getPetition(Integer id) {
        List<KPI> kpis = kpiDao.findPetitionById(id);
        if (kpis == null) {
            return null;
        }else {
            return kpis;
        }
    }

    //提交反馈
    @Override
    public Integer updateResult(String name, String result) {
        Integer updateResult = kpiDao.updateResultByname(name, result);
        if (updateResult == null) {
            return null;
        }else {
            return updateResult;
        }
    }

    //确认提交
    @Override
    public Integer updateCommit(String name, Integer commit) {
        Integer updateCommit = kpiDao.updatecommitByname(name, commit);
        if (updateCommit == null) {
            return null;
        }else {
            return updateCommit;
        }
    }

    //确认kpi
    @Override
    public Integer updateConfirm(String name, Integer confirm) {
        Integer updateConfirm = kpiDao.updateConfirmByname(name, confirm);
        if (updateConfirm == null) {
            return null;
        }else {
            return updateConfirm;
        }
    }

    //自评kpi
    @Override
    public Integer updateKpi(String letter, String score, Integer id) {
        Integer updateKpi = kpiDao.updateScore(letter,score,id);
        if (updateKpi == null) {
            return null;
        }else {
            return updateKpi;
        }
    }
    //查找反馈
    @Override
    public List<KPI> getResultByName(String name) {
        List<KPI> kpis = kpiDao.findReultByName(name);
        if (kpis == null) {
            return null;
        } else {
            return kpis;
        }
    }

    //通过名字查找id
    @Override
    public Integer getIdByName(String name) {
        Integer id = kpiDao.findIdByName(name);
        if (id == null) {
            return null;
        }else {
            return id;
        }
    }

    //通过名字查找phone
    @Override
    public String getPhoneByName(String name) {
        String phone = kpiDao.findPhoneByName(name);
        if (phone == null) {
            return null;
        }else {
            return phone;
        }
    }

    @Override
    public Integer updateAudit(String name, Integer audit) {
        Integer Audit = kpiDao.updateAudit(name, audit);
        if (Audit == null) {
            return null;
        }else {
            return Audit;
        }

    }

}
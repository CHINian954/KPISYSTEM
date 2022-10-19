package com.kpi.system.dao;

import com.kpi.system.domain.KPI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KPIDao {
    //员工登录验证
    @Select("select * from staffinfo where phone=#{phone} and password=#{password}")
    public List<KPI> findByUsernamePassword(KPI kpi);

    //通过号码查权限
    @Select("SELECT root FROM staffinfo WHERE phone=#{phone}")
    public String finByPhoneRoot(String phone);

    //通过号码查信息
    @Select("SELECT name,sex,age,phone,email,adress,idc FROM staffinfo WHERE phone=#{phone}")
    public List<KPI> findUserByPhone(String phone);

    //查找kpi指标库
    @Select("SELECT A,B,C,D,E,F,G,H,I,J from skpiscore where id=1")
    public List<KPI> findKpiAll();

    //通过phone查找id
    @Select("select id from staffinfo where phone=#{phone}")
    public Integer findIdByPhone(String phone);

    //通过id查找上司kpi
    @Select("SELECT A,B,C,D,E,F,G,H,I,J FROM skpiscore WHERE id=#{id}")
    public List<KPI> findSkpiById(Integer id);

    //通过id查找员工kpi
    @Select("SELECT A,B,C,D,E,F,G,H,I,J FROM skpiscore WHERE id=#{id}")
    public List<KPI> findKpiById(Integer id);

    //通过号码查找名字
    @Select("SELECT name FROM staffinfo WHERE phone=#{phone}")
    public String findNameByPhone(String phone);

    //通过id修改申诉
    @Update("update kpipetition set petition=#{petition} where id=#{id}")
    public Integer updateKpipetition(Integer id, String petition);

    //通过号码查找确认标识
    @Select("SELECT confirm from staffinfo where phone=#{phone}")
    public Integer findConfirmByphone(String phone);

    //确认承诺
    @Update("update staffinfo set covenant=#{covenant} where phone=#{phone}")
    public Integer updateCovenant(String phone, Integer covenant);

    //通过id获取申诉结果
    @Select("select name,petition,result from kpipetition where id=#{id}")
    public List<KPI> findPetitionById(Integer id);

    //通过名字反馈申诉结果
    @Update("update kpipetition set result=#{result} where name=#{name}")
    public Integer updateResultByname(String name, String result);

    //通过名字提交反馈
    @Update("update kpipetition set commit=#{commit} where name=#{name}")
    public Integer updatecommitByname(String name, Integer commit);

    //确认上司评分
    @Update("update staffinfo set confirm=#{confirm} where name=#{name}")
    public Integer updateConfirmByname(String name, Integer confirm);

    //自评kpi
    @Update("update skpiscore set ${letter}=#{score} where id=#{id}")
    public Integer updateScore(String letter, String score,Integer id);

    //查找反馈
    @Select("select name,petition,result from kpipetition where name=#{name}")
    public List<KPI> findReultByName(String name);

    //通过名字查找id
    @Select("select id from staffinfo where name=#{name}")
    public Integer findIdByName(String name);

    //用名字查找phone
    @Select("select phone from staffinfo where name=#{name}")
    public String findPhoneByName(String name);

    //领导审核
    @Update("update skpiscore set audit=#{audit} where id=#{id}")
    public Integer updateAudit(Integer id, Integer audit);

    //上司修改承诺书
    @Update("update kpicovenant set covenants=#{covenants}")
    public Integer updateCovenants(String covenants);

    //员工确认承诺书
    @Update("update staffinfo set covenant=#{covenant} where phone=#{phone}")
    public Integer updateCovenantByPhone(String phone, Integer covenant);

    //获取承诺书
    @Select("select covenants from kpicovenant")
    public String findCovenants();

    //修改上司评分
    @Update("update skpiscore set ${letter}=#{score} where id=#{id}")
    public Integer updateSScore(String letter, String score,Integer id);

    //获取员工名与确认标识
    @Select("select name,sconfirm from staffinfo where root=1")
    public List<KPI> findNameConfirm();

    //修改上司确认员工绩效标识
    @Update("update staffinfo set sconfirm=#{sconfirm} where name=#{name}")
    public Integer updateSconfirm(String name , Integer sconfirm);

    //建立指标库
    @Update("update skpiscore set ${letter}=#{kpiindex} where id=1")
    public Integer updateKpiindex(String letter , String kpiindex);

    @Select("select petition,result from kpipetition where commit=1 and name=#{name}")
    public List<KPI> findCommitresult(String name);

    @Select("select audit from skpiscore where id=#{id}")
    public Integer findAudit(Integer id);

    @Select("select covenant from staffinfo where phone=#{phone}")
    public Integer findCovenant(String phone);


}

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
    @Select("SELECT kpi FROM kpiindex")
    public List<KPI> findKpiAll();

    //通过phone查找id
    @Select("select id from staffinfo where phone=#{phone}")
    public Integer findIdByPhone(String phone);

    //通过id查找上司kpi
    @Select("SELECT skpi1,skpi2,skpi3,skpi4,skpi5,skpi6,skpi7,skpi8,skpi9,skpi10 FROM kpiscore WHERE id=#{id}")
    public List<KPI> findSkpiById(Integer id);

    //通过id查找员工kpi
    @Select("SELECT kpi1,kpi2,kpi3,kpi4,kpi5,kpi6,kpi7,kpi8,kpi9,kpi10 FROM kpiscore WHERE id=#{id}")
    public List<KPI> findKpiById(Integer id);

    //通过号码查找名字
    @Select("SELECT name FROM staffinfo WHERE phone=#{phone}")
    public String findNameByPhone(String phone);

    //通过id修改申诉
    @Update("update kpipetition set petition=#{petition} where id=#{id}")
    public Integer updateKpipetition(Integer id, String petition);

}

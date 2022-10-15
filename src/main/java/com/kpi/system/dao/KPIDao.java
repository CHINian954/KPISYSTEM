package com.kpi.system.dao;

import com.kpi.system.domain.KPI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KPIDao {

    @Select("select * from staffinfo where phone=#{phone} and password=#{password}")
    public List<KPI> findByUsernamePassword(KPI kpi);

    @Select("SELECT root FROM staffinfo WHERE phone=#{phone}")
    public String finByPhoneRoot(String phone);

    @Select("SELECT name,sex,age,phone,email,adress,idc FROM staffinfo WHERE phone=#{phone}")
    public List<KPI> findUserByPhone(String phone);
}

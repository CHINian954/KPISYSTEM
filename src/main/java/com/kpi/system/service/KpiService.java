package com.kpi.system.service;

import com.kpi.system.domain.KPI;

import java.util.List;

public interface KpiService {
    public boolean validateAccount(String phone, String password);
    public String validateRoot(String phone);
    public List<KPI> showUser(String phone);
}

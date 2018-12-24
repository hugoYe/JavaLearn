package com.system.business.dashboard.service;

import com.system.business.dashboard.dto.DashboardDto;

import java.util.List;

public interface DashboardService {

    List<DashboardDto> queryDashboard(Integer userId);
}

package com.system.business.router;

import com.system.business.router.vo.RouteVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RouteConstant {

    public static final String ROUTE_ID_DASHBOARD = "1";    //  首页仪表盘
    public static final String ROUTE_ID_INCOME = "2";       //  每天收入
    public static final String ROUTE_ID_CHANNELS = "3";     //  渠道管理
    public static final String ROUTE_ID_USERS = "4";        //  用户管理
    public static final String ROUTE_ID_USER_DETAIL = "41"; //  用户详情
    public static final String ROUTE_ID_USERCENTER = "5";   //  个人中心

    public static final String[] MANAGER_ROUTE_IDS = {ROUTE_ID_DASHBOARD, ROUTE_ID_INCOME,
            ROUTE_ID_CHANNELS, ROUTE_ID_USERS, ROUTE_ID_USER_DETAIL, ROUTE_ID_USERCENTER};

    public static final String[] VISTOR_ROUTE_IDS = {ROUTE_ID_DASHBOARD, ROUTE_ID_INCOME, ROUTE_ID_USERCENTER};

    public static final HashMap<String, RouteVO> ROUTE_LIST = new HashMap<>();
    public static List<RouteVO> manager_route_list = new ArrayList<>();
    public static List<RouteVO> vistor_route_list = new ArrayList<>();

    static {
        RouteVO routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_DASHBOARD);
        routeVO.setName("Dashboard");
        routeVO.setIcon("dashboard");
        routeVO.setZhName("仪表盘");
        routeVO.setRoute("/dashboard");
        ROUTE_LIST.put(ROUTE_ID_DASHBOARD, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_INCOME);
        routeVO.setName("Income Per Day");
        routeVO.setIcon("bar-chart");
        routeVO.setZhName("每天收入");
        routeVO.setRoute("/income");
        routeVO.setBreadcrumbParentId("1");
        ROUTE_LIST.put(ROUTE_ID_INCOME, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_CHANNELS);
        routeVO.setName("Channels");
        routeVO.setIcon("appstore");
        routeVO.setZhName("客户渠道管理");
        routeVO.setRoute("/channels");
        routeVO.setBreadcrumbParentId("1");
        ROUTE_LIST.put(ROUTE_ID_CHANNELS, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_USERS);
        routeVO.setName("Users");
        routeVO.setIcon("team");
        routeVO.setZhName("客户管理");
        routeVO.setRoute("/user");
        routeVO.setBreadcrumbParentId("1");
        ROUTE_LIST.put(ROUTE_ID_USERS, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_USER_DETAIL);
        routeVO.setName("User Detail");
        routeVO.setZhName("客户详情");
        routeVO.setRoute("/user/:id");
        routeVO.setMenuParentId("-1");
        routeVO.setBreadcrumbParentId(ROUTE_ID_USERS);
        ROUTE_LIST.put(ROUTE_ID_USER_DETAIL, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_USERCENTER);
        routeVO.setName("User Center");
        routeVO.setIcon("user");
        routeVO.setZhName("个人中心");
        routeVO.setRoute("/usercenter");
        routeVO.setBreadcrumbParentId("1");
        ROUTE_LIST.put(ROUTE_ID_USERCENTER, routeVO);

        for (String id : MANAGER_ROUTE_IDS) {
            RouteVO vo = ROUTE_LIST.get(id);
            manager_route_list.add(vo);
        }

        for (String id : VISTOR_ROUTE_IDS) {
            RouteVO vo = ROUTE_LIST.get(id);
            vistor_route_list.add(vo);
        }
    }


}

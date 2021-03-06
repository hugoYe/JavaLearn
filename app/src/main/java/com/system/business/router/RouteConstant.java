package com.system.business.router;

import com.system.business.router.vo.RouteVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RouteConstant {

    public static final String ROUTE_ID_MENU_BASE = "1";    // 基础业务菜单
    public static final String ROUTE_ID_DASHBOARD = "11";    //  首页仪表盘
    public static final String ROUTE_ID_INCOME = "12";       //  每天收入
    public static final String ROUTE_ID_CHANNELS = "13";     //  渠道管理
    public static final String ROUTE_ID_USERS = "14";        //  用户管理
    public static final String ROUTE_ID_USER_DETAIL = "41"; //  用户详情
    public static final String ROUTE_ID_MENU_OFFER = "2";    // API Offer 业务菜单
    public static final String ROUTE_ID_ADVERTISER = "21";    // 广告主管理
    public static final String ROUTE_ID_ADV_CUSTOMER = "22";    // 广告业务客户管理
    public static final String ROUTE_ID_OFFER = "23";       // 广告业务offer管理
    public static final String ROUTE_ID_API_OFFER = "24";       // 广告业务api offer
    public static final String ROUTE_ID_OFFER_REPORT = "25";       // 广告业务report
    public static final String ROUTE_ID_USERCENTER = "3";   //  个人中心

    // 管理员访问权限菜单
    public static final String[] MANAGER_ROUTE_IDS = {ROUTE_ID_MENU_BASE, ROUTE_ID_DASHBOARD, ROUTE_ID_INCOME,
            ROUTE_ID_CHANNELS, ROUTE_ID_USERS, ROUTE_ID_USER_DETAIL, ROUTE_ID_MENU_OFFER, ROUTE_ID_ADVERTISER, ROUTE_ID_ADV_CUSTOMER,
            ROUTE_ID_OFFER, ROUTE_ID_API_OFFER, ROUTE_ID_OFFER_REPORT, ROUTE_ID_USERCENTER};

    // 基础业务客户访问权限菜单
    public static final String[] VISITOR_ROUTE_IDS = {ROUTE_ID_MENU_BASE, ROUTE_ID_DASHBOARD, ROUTE_ID_INCOME, ROUTE_ID_USERCENTER};

    // 广告业务客户访问权限菜单
    public static final String[] ADV_CUSTOMER_ROUTE_IDS = {ROUTE_ID_MENU_OFFER, ROUTE_ID_ADVERTISER, ROUTE_ID_ADV_CUSTOMER, ROUTE_ID_OFFER,
            ROUTE_ID_API_OFFER, ROUTE_ID_OFFER_REPORT, ROUTE_ID_USERCENTER};

    public static final HashMap<String, RouteVO> ROUTE_LIST = new HashMap<>();
    public static List<RouteVO> manager_route_list = new ArrayList<>();
    public static List<RouteVO> visitor_route_list = new ArrayList<>();
    public static List<RouteVO> adv_customer_route_list = new ArrayList<>();

    static {
        RouteVO routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_MENU_BASE);
        routeVO.setName("基础业务");
        routeVO.setIcon("code-o");
        routeVO.setZhName("基础业务");
        ROUTE_LIST.put(ROUTE_ID_MENU_BASE, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_DASHBOARD);
        routeVO.setBreadcrumbParentId(ROUTE_ID_MENU_BASE);
        routeVO.setMenuParentId(ROUTE_ID_MENU_BASE);
        routeVO.setName("Dashboard");
        routeVO.setIcon("dashboard");
        routeVO.setZhName("仪表盘");
        routeVO.setRoute("/dashboard");
        ROUTE_LIST.put(ROUTE_ID_DASHBOARD, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_INCOME);
        routeVO.setBreadcrumbParentId(ROUTE_ID_MENU_BASE);
        routeVO.setMenuParentId(ROUTE_ID_MENU_BASE);
        routeVO.setName("Income Per Day");
        routeVO.setIcon("bar-chart");
        routeVO.setZhName("每天收入");
        routeVO.setRoute("/income");
        ROUTE_LIST.put(ROUTE_ID_INCOME, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_CHANNELS);
        routeVO.setBreadcrumbParentId(ROUTE_ID_MENU_BASE);
        routeVO.setMenuParentId(ROUTE_ID_MENU_BASE);
        routeVO.setName("Channels");
        routeVO.setIcon("appstore");
        routeVO.setZhName("渠道管理");
        routeVO.setRoute("/channels");
        ROUTE_LIST.put(ROUTE_ID_CHANNELS, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_USERS);
        routeVO.setBreadcrumbParentId(ROUTE_ID_MENU_BASE);
        routeVO.setMenuParentId(ROUTE_ID_MENU_BASE);
        routeVO.setName("Users");
        routeVO.setIcon("team");
        routeVO.setZhName("客户管理");
        routeVO.setRoute("/user");
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
        routeVO.setId(ROUTE_ID_MENU_OFFER);
        routeVO.setName("广告业务");
        routeVO.setIcon("dollar");
        routeVO.setZhName("广告业务");
        ROUTE_LIST.put(ROUTE_ID_MENU_OFFER, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_ADVERTISER);
        routeVO.setBreadcrumbParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setMenuParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setName("Advertiser");
        routeVO.setIcon("contacts");
        routeVO.setZhName("广告主管理");
        routeVO.setRoute("/advertiser");
        ROUTE_LIST.put(ROUTE_ID_ADVERTISER, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_ADV_CUSTOMER);
        routeVO.setBreadcrumbParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setMenuParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setName("Customer");
        routeVO.setIcon("usergroup-add");
        routeVO.setZhName("客户管理");
        routeVO.setRoute("/customer");
        ROUTE_LIST.put(ROUTE_ID_ADV_CUSTOMER, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_OFFER);
        routeVO.setBreadcrumbParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setMenuParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setName("Offer");
        routeVO.setIcon("database");
        routeVO.setZhName("offer管理");
        routeVO.setRoute("/offermanager");
        ROUTE_LIST.put(ROUTE_ID_OFFER, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_API_OFFER);
        routeVO.setBreadcrumbParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setMenuParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setName("API Offer");
        routeVO.setIcon("cloud-server");
        routeVO.setZhName("API Offer");
        routeVO.setRoute("/apioffer");
        ROUTE_LIST.put(ROUTE_ID_API_OFFER, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_OFFER_REPORT);
        routeVO.setBreadcrumbParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setMenuParentId(ROUTE_ID_MENU_OFFER);
        routeVO.setName("Offer Report");
        routeVO.setIcon("sliders");
        routeVO.setZhName("Offer Report");
        routeVO.setRoute("/offerreport");
        ROUTE_LIST.put(ROUTE_ID_OFFER_REPORT, routeVO);

        routeVO = new RouteVO();
        routeVO.setId(ROUTE_ID_USERCENTER);
        routeVO.setName("User Center");
        routeVO.setIcon("user");
        routeVO.setZhName("个人中心");
        routeVO.setRoute("/usercenter");
        ROUTE_LIST.put(ROUTE_ID_USERCENTER, routeVO);

        for (String id : MANAGER_ROUTE_IDS) {
            RouteVO vo = ROUTE_LIST.get(id);
            manager_route_list.add(vo);
        }

        for (String id : VISITOR_ROUTE_IDS) {
            RouteVO vo = ROUTE_LIST.get(id);
            visitor_route_list.add(vo);
        }

        for (String id : ADV_CUSTOMER_ROUTE_IDS) {
            RouteVO vo = ROUTE_LIST.get(id);
            adv_customer_route_list.add(vo);
        }
    }


}

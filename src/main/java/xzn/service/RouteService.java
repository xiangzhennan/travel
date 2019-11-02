package xzn.service;

import xzn.domain.PageBean;
import xzn.domain.Route;

public interface RouteService {
    PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);

    Route findOne(int rid);
}

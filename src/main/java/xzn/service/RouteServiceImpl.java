package xzn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzn.dao.RouteDao;
import xzn.domain.PageBean;
import xzn.domain.Route;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService{
    @Autowired
    private RouteDao routeDao;

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装pageBean
        PageBean<Route> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);
        int totalCount = routeDao.findRecordsNum(cid,rname);
        pb.setTotalCount(totalCount);
        //封装数据集合
        int start = (currentPage-1)*pageSize;
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);

        int totalPage = (totalCount%pageSize==0?0:1)+totalCount/pageSize;
        pb.setTotalPage(totalPage);

        return pb;
    }

    @Override
    public Route findOne(int rid) {
        return routeDao.findOne(rid);
    }
}

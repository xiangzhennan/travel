package xzn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xzn.domain.PageBean;
import xzn.domain.Route;
import xzn.service.RouteServiceImpl;
import xzn.util.JsonUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/route")
public class PageController {
    @Autowired
    private RouteServiceImpl routeService;

    @RequestMapping("/pageQuery")
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接受参数
        String currentPageString = request.getParameter("currentPage");
        String pageSizeString = request.getParameter("pageSize");
        String cidString = request.getParameter("cid");
        int cid = 0;
        //处理参数
        if (cidString!=null&&cidString.length()>0){
            cid = Integer.parseInt(cidString);
        }
        int currentPage = 1;
        if (currentPageString!=null&&currentPageString.length()>0){
            currentPage = Integer.parseInt(currentPageString);
        }
        int pageSize = 5;
        if (pageSizeString!=null&&pageSizeString.length()>0){
            pageSize = Integer.parseInt(pageSizeString);
        }
        //pageservice调用，查询数据
        PageBean<Route> routePageBean = routeService.pageQuery(cid, currentPage, pageSize);
        JsonUtil.writeJsonToClient(routePageBean,response);
    }
}

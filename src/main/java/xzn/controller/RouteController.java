package xzn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xzn.domain.PageBean;
import xzn.domain.Route;
import xzn.domain.User;
import xzn.service.FavoriteService;
import xzn.service.FavoriteServiceImpl;
import xzn.service.RouteServiceImpl;
import xzn.util.JsonUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//线路图展示
@Controller
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteServiceImpl routeService;
    @Autowired
    private FavoriteServiceImpl favoriteService;
    //提供旅游线路浏览页面信息
    @RequestMapping("/pageQuery")
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接受参数
        String currentPageString = request.getParameter("currentPage");
        String pageSizeString = request.getParameter("pageSize");
        String cidString = request.getParameter("cid");
        String rname = request.getParameter("rname");
        int cid = 0;
        //处理参数
        if (cidString!=null&&cidString.length()>0&&!"null".equals(cidString)){
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
        if (rname!=null&&!rname.equals(""))rname = "%"+rname+"%";
        //pageservice调用，查询数据
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize,rname);
        JsonUtil.writeJsonToClient(pb,response);
    }
    @RequestMapping("/findOne")
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ridString = request.getParameter("rid");
        int rid = Integer.parseInt(ridString);
        Route route = routeService.findOne(rid);
        //收藏次数查询
        route.setCount(favoriteService.findCountByRid(rid));
        JsonUtil.writeJsonToClient(route,response);
    }
    //响应是否收藏
    @RequestMapping("/isFavorite")
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ridString = request.getParameter("rid");
        int rid = Integer.parseInt(ridString);
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        if (user!=null)uid =user.getUid();
        boolean flag = favoriteService.isFavorite(rid, uid);
        System.out.println("运行到这里" + flag);
        JsonUtil.writeJsonToClient(flag,response);
    }
    //响应添加收藏
    @RequestMapping("/addFavorite")
    public void addFavorite(HttpServletRequest request, HttpServletResponse response){
        String ridString = request.getParameter("rid");
        int rid = Integer.parseInt(ridString);
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        if (user!=null)uid =user.getUid();
        boolean b = favoriteService.addFavorite(rid, uid);
        if (!b)throw new RuntimeException("添加收藏错误");
    }
}

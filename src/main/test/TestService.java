import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xzn.dao.RouteDao;
import xzn.dao.userDao;
import xzn.domain.PageBean;
import xzn.domain.Route;
import xzn.domain.User;
import xzn.service.RouteServiceImpl;

import java.util.List;

public class TestService {
    @Autowired
    private RouteDao routeDao;
    @Autowired
    private userDao userDao;
    @Test
    public void testPage(){
//        List<Route> byPage = routeDao.findByPage(5, 1, 5);

        System.out.println("测试");
    }
}

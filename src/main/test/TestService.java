import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xzn.dao.CategoryDao;
import xzn.dao.RouteDao;
import xzn.dao.userDao;
import xzn.domain.Category;
import xzn.domain.PageBean;
import xzn.domain.Route;
import xzn.domain.User;
import xzn.service.RouteServiceImpl;

import java.util.List;

public class TestService {

    @Test
    public void testPage(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        SqlSessionFactory factory = (SqlSessionFactory) ac.getBean("sessionFactory");
        SqlSession session = factory.openSession();
        //CategoryDao mapper = session.getMapper(CategoryDao.class);
   //     RouteDao mapper = session.getMapper(RouteDao.class);
//        List<Category> all = mapper.findAll();
//        System.out.println(all);
//        int recordsNum = mapper.findRecordsNum(5, "%三亚%");
//        System.out.println(recordsNum);
  //      List<Route> byPage = mapper.findByPage(5, 2, 5, "%三亚%");
        RouteDao mapper = session.getMapper(RouteDao.class);
        Route route = mapper.findOne(1);

        System.out.println(route);
        System.out.println(route.getRouteImgList());
        System.out.println(route.getSeller());
        System.out.println("测试");

    }
}

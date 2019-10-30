package xzn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;
import xzn.dao.CategoryDao;
import xzn.domain.Category;
import xzn.util.JedisUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryDao categoryDao;
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();
        //Set<String> categories=jedis.zrange("category",0,-1);
        Set<Tuple> categories = jedis.zrangeWithScores("category",0,-1);
        List<Category> cs = null;
        //redis中没有缓存
        if (categories==null||categories.size()==0){
            cs = categoryDao.findAll();
            for (int i= 0 ;i<cs.size();i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            //redis中有缓存
            cs = new ArrayList<Category>();
            for (Tuple tuple:categories
                 ) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int) tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}

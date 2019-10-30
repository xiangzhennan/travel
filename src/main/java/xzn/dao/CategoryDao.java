package xzn.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xzn.domain.Category;

import java.util.List;
@Repository
public interface CategoryDao {
//    @Select()
//    Category findOne();
    @Select("select * from tab_category")
    List<Category> findAll();

}

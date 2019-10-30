package xzn.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xzn.domain.Route;

import java.util.List;
@Repository
public interface RouteDao {
    @Select("select count(*) from tab_route where cid = #{arg0}")
    int findRecordsNum(int cid);
    @Select("select * from tab_route where cid = #{arg0} limit #{arg1},#{arg2}")
    List<Route>findByPage(int cid,int start,int pageSize);
}

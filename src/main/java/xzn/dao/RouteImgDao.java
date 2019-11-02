package xzn.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xzn.domain.RouteImg;

import java.util.List;

@Repository
public interface RouteImgDao {
    @Select("select * from tab_route_img where rid = #{rid}")
    List<RouteImg>findImgById(int rid);
}

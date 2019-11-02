package xzn.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xzn.domain.Favorite;

@Repository
public interface FavoriteDao {
    @Select("select * from tab_favorite where rid = #{arg0} and uid = #{arg1}")
    Favorite isFavorite(int rid, int uid);

    @Insert("insert into tab_favorite (rid,uid,date) values (#{arg0},#{arg1},#{arg2})")
    int addFavorite(int rid,int uid,String date);

    @Select("select count(*) from tab_favorite where rid = #{rid}")
    int findCountByRid(int rid);
}

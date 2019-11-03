package xzn.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ZParams;
import xzn.domain.Route;

import java.util.List;
import java.util.Map;

@Repository
public interface RouteDao {
    @SelectProvider(type = SqlGenerate.class ,method = "findRecordsNumSql")
    int findRecordsNum(int cid,String rname);
    @SelectProvider(type = SqlGenerate.class ,method = "findByPageSql")
    List<Route>findByPage(int cid,int start,int pageSize,String rname);
    class SqlGenerate{
        //结构化SQL
        public String findRecordsNumSql(Map<String,Object> params){
            return new SQL(){{
                SELECT("count(*)");
                FROM("tab_route");
                if((int)params.get("arg0")!=0)WHERE("cid="+params.get("arg0"));
                if(params.get("arg1")!=null&&!params.get("arg1").equals(""))WHERE("rname like '"+params.get("arg1")+"'");
            }}.toString();
        }
        //自定义方法组装sql
        public String findByPageSql(Map<String,Object> params){
            StringBuilder sql= new StringBuilder("select * from tab_route where 1=1 ");
            if((int)params.get("arg0")!=0)sql.append("and cid = #{arg0} ");
            if(params.get("arg3")!=null&&!params.get("arg3").equals(""))sql.append("and rname like #{arg3} ");
            sql.append("limit #{arg1},#{arg2}");
            return sql.toString();
        }
    }
    @Select("select * from tab_route where rid = #{rid}")
    @Results(value = {
            @Result(property = "seller",column = "sid",one = @One(select = "xzn.dao.SellerDao.findSellerById",fetchType= FetchType.EAGER)),
            @Result(property = "routeImgList",column = "rid",many = @Many(select = "xzn.dao.RouteImgDao.findImgById",fetchType= FetchType.EAGER))
    })
    Route findOne(int rid);
}

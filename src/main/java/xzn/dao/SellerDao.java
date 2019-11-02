package xzn.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import xzn.domain.Seller;

@Repository
public interface SellerDao {
    @Select("select * from tab_seller where sid =#{sid}")
    Seller findSellerById(int sid);
}

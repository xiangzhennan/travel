package xzn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xzn.dao.FavoriteDao;

import java.sql.Date;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;

    @Override
    public boolean isFavorite(int rid, int uid) {
        return favoriteDao.isFavorite(rid,uid)!=null;
    }

    @Override
    public boolean addFavorite(int rid, int uid) {
        Date time= new java.sql.Date(new java.util.Date().getTime());
        return favoriteDao.addFavorite(rid,uid,time.toString())!=0;
    }

    @Override
    public int findCountByRid(int rid) {
        return favoriteDao.findCountByRid(rid);
    }
}

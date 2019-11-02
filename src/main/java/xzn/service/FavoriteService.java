package xzn.service;

public interface FavoriteService {
    boolean isFavorite(int rid,int uid);
    boolean addFavorite(int rid, int uid);
    int findCountByRid(int rid);
}

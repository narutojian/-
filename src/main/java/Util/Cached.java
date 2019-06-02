package Util;

/**
 * 缓存类
 * 缓存一些需要经常到数据库中读取的有规律性的数据
 * 如 cid，fid
 */
public class Cached {

    private static int cid = -1;
    private static int fid = -1;
    private static int oid = -1;

    public static int getCid() {
        return cid;
    }

    public static void setCid(int cid) {
        Cached.cid = cid;
    }

    public static int getFid() {
        return fid;
    }

    public static int getOid() {
        return oid;
    }
}

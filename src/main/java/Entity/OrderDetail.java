package Entity;

public abstract class OrderDetail {

    private int odid;
    private int oid;
    private int fid;
    private int fnum;

    /*
    计算价格
     */
    public abstract double getPrice();

}

package cn.edu.gdmec.w07150837.contacts;

/**
 * Created by weiruibo on 12/11/16.
 */

public class User {

    /**
     * Created by weiruibo on 10/23/16.
     */

    public final static String NAME = "name";
    public final static String MOBILE = "mobile";
    public final static String COMPANY = "company";
    public final static String QQ = "qq";
    public final static String ADDRESS = "address";

    private String name;
    private String mobile;
    private String company;
    private String qq;
    private String address;
    private int id_DB = -1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId_DB() {
        return id_DB;
    }

    public void setId_DB(int id_DB) {
        this.id_DB = id_DB;
    }
}

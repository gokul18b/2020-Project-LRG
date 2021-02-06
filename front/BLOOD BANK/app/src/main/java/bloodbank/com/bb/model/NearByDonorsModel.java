package bloodbank.com.bb.model;

/**
 * Created by Admin on 09-02-2018.
 */

public class NearByDonorsModel {
    String id, img, name, mobile, blood, address,lastupdate,dis;
    public NearByDonorsModel(String id, String img, String name, String mobile, String blood, String address, String lastupdate,String dis) {
        this.id=id;
        this.img=img;
        this.name=name;
        this.mobile=mobile;
        this.blood=blood;
        this.address=address;
        this.lastupdate=lastupdate;
        this.dis =dis;
    }

    public String getDis() {
        return dis;
    }

    public String getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getBlood() {
        return blood;
    }

    public String getAddress() {
        return address;
    }

    public String getLastupdate() {
        return lastupdate;
    }
}

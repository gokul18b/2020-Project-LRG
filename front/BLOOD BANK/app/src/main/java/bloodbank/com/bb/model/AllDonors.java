package bloodbank.com.bb.model;

/**
 * Created by Admin on 11-02-2018.
 */

public class AllDonors {
    String id, img, name, mobile, blood, address;
    public AllDonors(String id, String img, String name, String mobile, String blood, String address) {
        this.id=id;
        this.img=img;
        this.name=name;
        this.mobile=mobile;
        this.blood=blood;
        this.address=address;
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
}

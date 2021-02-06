package bloodbank.com.bb.model;

/**
 * Created by gokul on 12-12-2017.
 */

public class DonorListModel {
    String id,image,name,mobile,blood,address;

    public DonorListModel(String id,String image, String name, String mobile, String blood, String address) {
        this.id=id;
        this.image = image;
        this.name = name;
        this.mobile = mobile;
        this.blood = blood;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
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

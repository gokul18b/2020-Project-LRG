package bloodbank.com.bb.model;

/**
 * Created by gokul on 15-12-2017.
 */

public class AcceptedListModel {

    String image,name,mobile,blood,address,id;

    public AcceptedListModel(String id,String image, String name, String mobile, String blood, String address) {
        this.id = id;
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

package bloodbank.com.bb;

/**
 * Created by gokul on 16-12-2017.
 */

public class URL {
//     public final static String URL="http://192.168.43.94/bb/bb";
     public final static String URL="http://192.168.43.250/bb";


    public final static String register =URL+"/register.php";
    public final static String login=URL+"/login.php";
    public final static String profile=URL+"/profile.php?id=";

    public final static  String alldonorslist=URL+"/alldonorslist.php?id=";
    public final static  String pendingdonors=URL+"/pendingdonors.php?id=";
    public final static String sendrequest=URL+"/sendingrequest.php?";
    public final static String pendingsendrequest=URL+"/pendingrequest.php?";
    public final static String rejectrequest=URL+"/rejectrequest.php?";

    //received
    public final static String receiveall=URL+"/receiveall.php?";
    public final static String rejectIncommingRequest=URL+"/rejectIncommingRequest.php?";
    public final static String acceptIncommingRequest=URL+"/acceptIncommingRequest.php?";
    public final static String acceptedIncommingRequest=URL+"/acceptedIncommingRequest.php?";
    public final static String rejectAcceptedDonor=URL+"/rejectAcceptedDonor.php?";

    public final static String acceptmyrequest=URL+"/acceptmyrequest.php?";
    public final static String donatedrequest=URL+"/donated.php?";
    public final static String rejectmydonors=URL+"/rejectmydonors.php?";
    public final static String rejectedmyrequest=URL+"/rejectedmyrequest.php?";

    public final static String mydonated=URL+"/mydonated.php?";
    public final static String myreceived=URL+"/myreceived.php?";


    public final  static String setLastLocation=URL+"/setLastLocation.php?";
    public final static String nearbydonors=URL+"/nearbynonors.php?";
    public final static String alldonors=URL+"/alldonors.php";
}

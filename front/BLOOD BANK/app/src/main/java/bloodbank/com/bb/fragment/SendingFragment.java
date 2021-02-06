package bloodbank.com.bb.fragment;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import bloodbank.com.bb.Adapter.DonorListadapter;
import bloodbank.com.bb.Adapter.NearByDonorAdapter;
import bloodbank.com.bb.Adapter.PendingListAdapter;
import bloodbank.com.bb.MainActivity;
import bloodbank.com.bb.R;
import bloodbank.com.bb.URL;
import bloodbank.com.bb.model.DonorListModel;
import bloodbank.com.bb.model.NearByDonorsModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendingFragment extends Fragment implements View.OnClickListener, LocationListener {
    Button all_donors, pending_request, nearby;
    RecyclerView recyclerView;
    DonorListadapter donorListadapter;
    NearByDonorAdapter nearByDonorAdapter;
    PendingListAdapter pendingListAdapter;
    URL url;
    View v;
    DonorListModel donorListModel;
    String id, selected_bg;
    Spinner spinner;

    LinearLayout nearbylayout;
    EditText ed_nearby;
    Button bt_nearby;


    final String TAG = "GPS";
    private final static int ALL_PERMISSIONS_RESULT = 101;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    //TextView tvLatitude, tvLongitude, tvTime;
    LocationManager locationManager;
    Location loc;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    boolean isGPS = false;
    boolean isNetwork = false;
    boolean canGetLocation = true;


    Double mylattitude,mylongtitude;

    public SendingFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public SendingFragment(String id) {
        // Required empty public constructor
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_donor_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        all_donors = (Button) v.findViewById(R.id.all_donors);
        pending_request = (Button) v.findViewById(R.id.pending_request);
        nearby = (Button) v.findViewById(R.id.nearbydonors);
        spinner = (Spinner) v.findViewById(R.id.spinner);

        ed_nearby = (EditText) v.findViewById(R.id.ed_nearby);
        bt_nearby = (Button) v.findViewById(R.id.bt_nearby);
        nearbylayout = (LinearLayout) v.findViewById(R.id.nearbylayout);


        final ArrayList<String> bgList = new ArrayList<>();


        bgList.add("A-");
        bgList.add("A+");
        bgList.add("B-");
        bgList.add("B+");
        bgList.add("O-");
        bgList.add("O+");
        bgList.add("AB-");
        bgList.add("AB+");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, bgList);
        spinner.setAdapter(arrayAdapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);


        all_donors.setOnClickListener(this);
        pending_request.setOnClickListener(this);
        nearby.setOnClickListener(this);
        bt_nearby.setOnClickListener(this);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_bg = bgList.get(i);
                getAllDonorsData(selected_bg);
                nearbylayout.setVisibility(View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        initLocation();

        return v;
    }

    private void getAllDonorsData(final String bg) {
        final ArrayList<String> idList = new ArrayList<>();
        final ArrayList<String> imgList = new ArrayList<>();
        final ArrayList<String> nameList = new ArrayList<>();
        final ArrayList<String> mobileList = new ArrayList<>();
        final ArrayList<String> bloodList = new ArrayList<>();
        final ArrayList<String> addressList = new ArrayList<>();
        final ArrayList<DonorListModel> datas = new ArrayList<>();

        datas.clear();
        idList.clear();
        imgList.clear();
        nameList.clear();
        mobileList.clear();
        bloodList.clear();
        addressList.clear();

        final RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url.alldonorslist + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = "{\"data\":[{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"}]}";
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    if (jsonObject1.getInt("status") == 1) {
                        JSONArray jsonArray = jsonObject1.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject11 = jsonArray.getJSONObject(i);

                            if (selected_bg.equals(jsonObject11.getString("Blood"))) {
                                String id = jsonObject11.getString("id");
                                String img = jsonObject11.getString("img");
                                String name = jsonObject11.getString("name");
                                String mobile = jsonObject11.getString("mobile");
                                String blood = jsonObject11.getString("Blood");
                                String address = jsonObject11.getString("address");
                                datas.add(new DonorListModel(id, img, name, mobile, blood, address));
                            }


                        }

                        donorListadapter = new DonorListadapter(v.getContext(), datas, new DonorListadapter.clicklistner() {
                            @Override
                            public void sendbtn(int pos) {

                                String rid = datas.get(pos).getId();
                                sendReques(rid);

                            }

                            @SuppressLint("MissingPermission")
                            @Override
                            public void call(int pos) {
                              //  v.getContext().startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(datas.get(pos).getMobile())));
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + datas.get(pos).getMobile()));
                                if (ActivityCompat.checkSelfPermission(v.getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(callIntent);
                            }


                        });
                        recyclerView.setAdapter(donorListadapter);
                    } else {
                        donorListadapter = new DonorListadapter(v.getContext(), datas, new DonorListadapter.clicklistner() {
                            @Override
                            public void sendbtn(int pos) {
                                String rid = datas.get(pos).getId();
                                sendReques(rid);

                            }

                            @Override
                            public void call(int pos) {
                                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(datas.get(pos).getMobile())));
                            }


                        });
                        recyclerView.setAdapter(donorListadapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    recyclerView.setAdapter(donorListadapter);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                recyclerView.setAdapter(donorListadapter);
            }
        });
        requestQueue.add(stringRequest);

    }

    private void sendReques(String rid) {


        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.sendrequest + "sid=" + id + "&rid=" + rid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getAllDonorsData(selected_bg);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }

    private void toast(String msg) {

        Toast.makeText(v.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_donors:
                all_donors.setBackground(getResources().getDrawable(R.color.blue));
                pending_request.setBackground(getResources().getDrawable(R.color.white));
                nearby.setBackground(getResources().getDrawable(R.color.white));

                spinner.setVisibility(View.VISIBLE);
                nearbylayout.setVisibility(View.GONE);
                getAllDonorsData(selected_bg);

                break;
            case R.id.pending_request:
                spinner.setVisibility(View.GONE);
                nearbylayout.setVisibility(View.GONE);

                all_donors.setBackground(getResources().getDrawable(R.color.white));
                pending_request.setBackground(getResources().getDrawable(R.color.blue));
                nearby.setBackground(getResources().getDrawable(R.color.white));
                getPendingRequest();
                break;
            case R.id.nearbydonors:
                spinner.setVisibility(View.GONE);
                nearbylayout.setVisibility(View.VISIBLE);
                all_donors.setBackground(getResources().getDrawable(R.color.white));
                pending_request.setBackground(getResources().getDrawable(R.color.white));
                nearby.setBackground(getResources().getDrawable(R.color.blue));
                nearByDonors();
                break;
            case R.id.bt_nearby:

                nearByDonors();
                break;
        }

    }

    private void nearByDonors() {


        final ArrayList<String> idList = new ArrayList<>();
        final ArrayList<String> imgList = new ArrayList<>();
        final ArrayList<String> nameList = new ArrayList<>();
        final ArrayList<String> mobileList = new ArrayList<>();
        final ArrayList<String> bloodList = new ArrayList<>();
        final ArrayList<String> addressList = new ArrayList<>();
        final ArrayList<NearByDonorsModel> datas = new ArrayList<>();

        datas.clear();
        idList.clear();
        imgList.clear();
        nameList.clear();
        mobileList.clear();
        bloodList.clear();
        addressList.clear();


        final RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url.nearbydonors+"id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String json = "{\"data\":[{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"}]}";
                try {
                    JSONObject jsonObject1 = new JSONObject(response);

                    if (jsonObject1.getInt("status") == 1) {
                        JSONArray jsonArray = jsonObject1.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject11 = jsonArray.getJSONObject(i);


                              //  Toast.makeText(v.getContext(),dis.toString(),Toast.LENGTH_SHORT).show();

                                String id = jsonObject11.getString("id");
                                String img = jsonObject11.getString("img");
                                String name = jsonObject11.getString("name");
                                String mobile = jsonObject11.getString("mobile");
                                String blood = jsonObject11.getString("Blood");
                                String address = jsonObject11.getString("address");
                                String lastupdate = jsonObject11.getString("lastupdate");

                                Double lattitude = jsonObject11.getDouble("lattitude");
                                Double longtitude = jsonObject11.getDouble("longtitude");
                                Double dis=  distance(mylattitude,mylongtitude,lattitude,longtitude,"K");
                                Log.d(dis+"","");
                                Double dist=Double.parseDouble(new DecimalFormat("##.####").format(dis));

                                int  fulldis=dist.intValue();

                                int userdist=Integer.parseInt(String.valueOf(ed_nearby.getText()));
                                if(fulldis<userdist) {
                                    datas.add(new NearByDonorsModel(id, img, name, mobile, blood, address, lastupdate, dist.toString()));
                                }



                        }

                        nearByDonorAdapter = new NearByDonorAdapter(v.getContext(), datas, new NearByDonorAdapter.clickListner() {
                            @Override
                            public void sendRequest(int pos) {
                                String rid = datas.get(pos).getId();
                                sendnearNyReques(rid);
                            }

                            @Override
                            public void call(int pos) {

                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + datas.get(pos).getMobile()));
                                if (ActivityCompat.checkSelfPermission(v.getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(callIntent);

                            }
                        });
                        recyclerView.setAdapter(nearByDonorAdapter);
                    } else {
                        nearByDonorAdapter = new NearByDonorAdapter(v.getContext(), datas, new NearByDonorAdapter.clickListner() {
                            @Override
                            public void sendRequest(int pos) {

                            }

                            @Override
                            public void call(int pos) {

                            }
                        });
                        recyclerView.setAdapter(nearByDonorAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    recyclerView.setAdapter(donorListadapter);
                    toast(e.toString());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                recyclerView.setAdapter(donorListadapter);
                toast(e.toString());
            }
        });
        requestQueue.add(stringRequest);
    }

    private void sendnearNyReques(String rid) {


        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.sendrequest + "sid=" + id + "&rid=" + rid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                nearByDonors();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }

    private static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }

        return (dist);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private void getPendingRequest() {
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());

        final ArrayList<String> imgList = new ArrayList<>();
        final ArrayList<String> idList = new ArrayList<>();
        final ArrayList<String> nameList = new ArrayList<>();
        final ArrayList<String> mobileList = new ArrayList<>();
        final ArrayList<String> bloodList = new ArrayList<>();
        final ArrayList<String> addressList = new ArrayList<>();
        final ArrayList<DonorListModel> datas = new ArrayList<>();


        datas.clear();
        idList.clear();
        imgList.clear();
        nameList.clear();
        mobileList.clear();
        bloodList.clear();
        addressList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.pendingsendrequest + "id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String json = "{\"data\":[{\"img\":\"\",\"name\":\"test\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"}]}";
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    if (jsonObject1.getInt("status") == 1) {
                        JSONArray jsonArray = jsonObject1.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                            idList.add(jsonObject11.getString("id"));
                            imgList.add(jsonObject11.getString("img"));
                            nameList.add(jsonObject11.getString("name"));
                            mobileList.add(jsonObject11.getString("mobile"));
                            bloodList.add(jsonObject11.getString("Blood"));
                            addressList.add(jsonObject11.getString("address"));
                            datas.add(new DonorListModel(idList.get(i), imgList.get(i), nameList.get(i), mobileList.get(i), bloodList.get(i), addressList.get(i)));
                        }

                        pendingListAdapter = new PendingListAdapter(v.getContext(), datas, new PendingListAdapter.clicklistner() {
                            @Override
                            public void reject(int pos) {
                                rejectRequest(pos, datas);
                            }
                        });
                        recyclerView.setAdapter(pendingListAdapter);
                    } else {
                        pendingListAdapter = new PendingListAdapter(v.getContext(), datas, new PendingListAdapter.clicklistner() {
                            @Override
                            public void reject(int pos) {

                            }
                        });
                        recyclerView.setAdapter(pendingListAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    toast(e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }

    private void rejectRequest(int pos, ArrayList<DonorListModel> datas) {
        String rid = datas.get(pos).getId();
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.rejectrequest + "sid=" + id + "&rid=" + rid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getPendingRequest();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }



    private void initLocation() {
        locationManager = (LocationManager)v.getContext().getSystemService(Service.LOCATION_SERVICE);
        isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        permissionsToRequest = findUnAskedPermissions(permissions);

        if (!isGPS && !isNetwork) {
            Log.d(TAG, "Connection off");
            showSettingsAlert();
            getLastLocation();
        } else {
            Log.d(TAG, "Connection on");
            // check permissions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                            ALL_PERMISSIONS_RESULT);
                    Log.d(TAG, "Permission requests");
                    canGetLocation = false;
                }
            }

            // get location
            getLocation();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged");
        //updateUI(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
        getLocation();
    }

    @Override
    public void onProviderDisabled(String s) {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    private void getLocation() {
        try {
            if (canGetLocation) {
                Log.d(TAG, "Can get location");
                if (isGPS) {
                    // from GPS
                    Log.d(TAG, "GPS on");
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else if (isNetwork) {
                    // from Network Provider
                    Log.d(TAG, "NETWORK_PROVIDER on");
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    if (locationManager != null) {
                        loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (loc != null)
                            updateUI(loc);
                    }
                } else {
                    loc.setLatitude(0);
                    loc.setLongitude(0);
                    updateUI(loc);
                }
            } else {
                Log.d(TAG, "Can't get location");
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private void getLastLocation() {
        try {
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);
            Location location = locationManager.getLastKnownLocation(provider);
            Log.d(TAG, provider);
            Log.d(TAG, location == null ? "NO LastLocation" : location.toString());
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (v.getContext().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                Log.d(TAG, "onRequestPermissionsResult");
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.toArray(
                                                        new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                } else {
                    Log.d(TAG, "No rejected permissions.");
                    canGetLocation = true;
                    getLocation();
                }
                break;
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
        alertDialog.setTitle("GPS is not Enabled!");
        alertDialog.setMessage("Do you want to turn on GPS?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(v.getContext())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void updateUI(Location loc) {


        //toast("Get my lat and lang");
        mylattitude=loc.getLatitude();
        mylongtitude=loc.getLongitude();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }
}

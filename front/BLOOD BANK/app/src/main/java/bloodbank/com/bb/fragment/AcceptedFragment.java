package bloodbank.com.bb.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;

import bloodbank.com.bb.Adapter.AcceptedListAdapter;
import bloodbank.com.bb.Adapter.RejectedListAdapter;
import bloodbank.com.bb.R;
import bloodbank.com.bb.URL;
import bloodbank.com.bb.model.AcceptedListModel;
import bloodbank.com.bb.model.IncommingListModel;
import bloodbank.com.bb.model.RejectedListModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AcceptedFragment extends Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    Button accepted, rejected;
    View v;
    IncommingListModel incommingListModel;
    AcceptedListAdapter acceptedListAdapter;
    RejectedListAdapter rejectedListAdapter;
    String id;
    final ArrayList<AcceptedListModel> accepteddatas = new ArrayList<>();

    public AcceptedFragment() {
        // Required empty public constructor
    }

    public AcceptedFragment(String id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_accepted, container, false);
        accepted = (Button) v.findViewById(R.id.accpted);
        rejected = (Button) v.findViewById(R.id.rejected);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        accepted.setBackground(getResources().getDrawable(R.color.blue));
        rejected.setBackground(getResources().getDrawable(R.color.white));
        getAcceptedData();
        accepted.setOnClickListener(this);
        rejected.setOnClickListener(this);

        return v;
    }

    private void getAcceptedData() {

        final RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());

        final ArrayList<String> idList = new ArrayList<>();
        final ArrayList<String> imgList = new ArrayList<>();
        final ArrayList<String> nameList = new ArrayList<>();
        final ArrayList<String> mobileList = new ArrayList<>();
        final ArrayList<String> bloodList = new ArrayList<>();
        final ArrayList<String> addressList = new ArrayList<>();


        accepteddatas.clear();
        idList.clear();
        imgList.clear();
        nameList.clear();
        mobileList.clear();
        bloodList.clear();
        addressList.clear();

        final String json = "{\"data\":[{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"}]}";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.acceptmyrequest + "id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
                            accepteddatas.add(new AcceptedListModel(idList.get(i), imgList.get(i), nameList.get(i), mobileList.get(i), bloodList.get(i), addressList.get(i)));
                        }
                    }
                    acceptedListAdapter = new AcceptedListAdapter(v.getContext(), accepteddatas, new AcceptedListAdapter.onclick() {
                        @Override
                        public void donated(int pos) {
                            Donated(pos);
                        }

                        @Override
                        public void reject(int pos) {
                            rejectMyDonors(pos);
                        }

                        @Override
                        public void call(int pos) {
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" + accepteddatas.get(pos).getMobile()));
                            if (ActivityCompat.checkSelfPermission(v.getContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            startActivity(callIntent);
                        }
                    });
                    recyclerView.setAdapter(acceptedListAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }

    private void rejectMyDonors(int pos) {
        String id = accepteddatas.get(pos).getId();
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.rejectmydonors + "bbid=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getAcceptedData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void Donated(int pos) {
        String id = accepteddatas.get(pos).getId();
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.donatedrequest + "bbid=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getAcceptedData();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private void toast(String id) {
        Toast.makeText(v.getContext(), id, Toast.LENGTH_LONG).show();
    }

    private void getRejectedData() {
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());

        final ArrayList<String> imgList = new ArrayList<>();
        final ArrayList<String> nameList = new ArrayList<>();
        final ArrayList<String> mobileList = new ArrayList<>();
        final ArrayList<String> bloodList = new ArrayList<>();
        final ArrayList<String> addressList = new ArrayList<>();
        final ArrayList<RejectedListModel> datas = new ArrayList<>();

        datas.clear();
        imgList.clear();
        nameList.clear();
        mobileList.clear();
        bloodList.clear();
        addressList.clear();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.rejectedmyrequest+"id="+id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    if(jsonObject1.getInt("status")==1) {
                        JSONArray jsonArray = jsonObject1.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                            imgList.add(jsonObject11.getString("img"));
                            nameList.add(jsonObject11.getString("name"));
                            mobileList.add(jsonObject11.getString("mobile"));
                            bloodList.add(jsonObject11.getString("Blood"));
                            addressList.add(jsonObject11.getString("address"));
                            datas.add(new RejectedListModel(imgList.get(i), nameList.get(i), mobileList.get(i), bloodList.get(i), addressList.get(i)));
                        }
                    }

                    rejectedListAdapter = new RejectedListAdapter(v.getContext(), datas, new RejectedListAdapter.onclickListner() {
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
                    recyclerView.setAdapter(rejectedListAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
        String json = "{\"data\":[{\"img\":\"\",\"name\":\"test\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"}]}";


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accpted:
                accepted.setBackground(getResources().getDrawable(R.color.blue));
                rejected.setBackground(getResources().getDrawable(R.color.white));
                getAcceptedData();
                break;
            case R.id.rejected:
                accepted.setBackground(getResources().getDrawable(R.color.white));
                rejected.setBackground(getResources().getDrawable(R.color.blue));
                getRejectedData();
                break;
        }

    }
}

package bloodbank.com.bb.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import bloodbank.com.bb.Adapter.IncommingAcceptedAdapter;
import bloodbank.com.bb.Adapter.IncommingListAdapter;
import bloodbank.com.bb.R;
import bloodbank.com.bb.URL;
import bloodbank.com.bb.model.IncommingListModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReceivedFragment extends android.app.Fragment implements View.OnClickListener {
    Button all, accepted;
    RecyclerView recyclerView;
    IncommingListAdapter incommingListAdapter;
    View v;
    IncommingListModel incommingListModel;
    IncommingAcceptedAdapter incommingAccepterAdapter;
    String id;

    public ReceivedFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ReceivedFragment(String id) {
        // Required empty public constructor
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_incomming_request, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        all = (Button) v.findViewById(R.id.received_all);
        accepted = (Button) v.findViewById(R.id.received_accepted);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        all.setBackground(getResources().getDrawable(R.color.blue));
        accepted.setBackground(getResources().getDrawable(R.color.white));
        getRecivedAllList();

        all.setOnClickListener(this);
        accepted.setOnClickListener(this);

        return v;
    }

    private void getRecivedAllList() {
        final ArrayList<String> imgList = new ArrayList<>();
        final ArrayList<String> nameList = new ArrayList<>();
        final ArrayList<String> mobileList = new ArrayList<>();
        final ArrayList<String> bloodList = new ArrayList<>();
        final ArrayList<String> addressList = new ArrayList<>();
        final ArrayList<IncommingListModel> datas = new ArrayList<>();

        datas.clear();
        imgList.clear();
        nameList.clear();
        mobileList.clear();
        bloodList.clear();
        addressList.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.receiveall + "id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String json = "{\"data\":[{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"}]}";
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    if (jsonObject1.getInt("status") == 1) {
                        JSONArray jsonArray = jsonObject1.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                            imgList.add(jsonObject11.getString("img"));
                            nameList.add(jsonObject11.getString("name"));
                            mobileList.add(jsonObject11.getString("mobile"));
                            bloodList.add(jsonObject11.getString("Blood"));
                            addressList.add(jsonObject11.getString("address"));

                            String id = jsonObject11.getString("id");
                            String userid = jsonObject11.getString("userid");


                            datas.add(new IncommingListModel(id, userid, imgList.get(i), nameList.get(i), mobileList.get(i), bloodList.get(i), addressList.get(i)));
                        }

                        incommingListAdapter = new IncommingListAdapter(v.getContext(), datas, new IncommingListAdapter.clicklistner() {
                            @Override
                            public void acceptrequest(int pos) {
                                aceeptIncomingRequest(pos, datas);
                            }

                            @Override
                            public void rejectRequest(int pos) {
                                rejectIncomminRequest(pos, datas);

                            }
                        });
                        recyclerView.setAdapter(incommingListAdapter);
                    } else {
                        incommingListAdapter = new IncommingListAdapter(v.getContext(), datas, new IncommingListAdapter.clicklistner() {
                            @Override
                            public void acceptrequest(int pos) {

                            }

                            @Override
                            public void rejectRequest(int pos) {

                            }
                        });
                        recyclerView.setAdapter(incommingListAdapter);
                    }


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

    private void rejectIncomminRequest(int pos, ArrayList<IncommingListModel> datas) {
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        String bbid = datas.get(pos).getId();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.rejectIncommingRequest + "id=" + id + "&bbid=" + bbid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getRecivedAllList();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }

    private void aceeptIncomingRequest(int pos, ArrayList<IncommingListModel> datas) {

        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        String bbid = datas.get(pos).getId();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.acceptIncommingRequest + "sid=" + id + "&bbid=" + bbid, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getRecivedAllList();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }

    private void getAcceptedData() {
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        final ArrayList<String> imgList = new ArrayList<>();
        final ArrayList<String> nameList = new ArrayList<>();
        final ArrayList<String> mobileList = new ArrayList<>();
        final ArrayList<String> bloodList = new ArrayList<>();
        final ArrayList<String> addressList = new ArrayList<>();
        final ArrayList<IncommingListModel> datas = new ArrayList<>();

        datas.clear();
        imgList.clear();
        nameList.clear();
        mobileList.clear();
        bloodList.clear();
        addressList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.acceptedIncommingRequest + "id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //String json = "{\"data\":[{\"img\":\"\",\"name\":\"test\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"}]}";
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    if (jsonObject1.getInt("status") == 1) {
                        JSONArray jsonArray = jsonObject1.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                            imgList.add(jsonObject11.getString("img"));
                            nameList.add(jsonObject11.getString("name"));
                            mobileList.add(jsonObject11.getString("mobile"));
                            bloodList.add(jsonObject11.getString("Blood"));
                            addressList.add(jsonObject11.getString("address"));

                            String id = jsonObject11.getString("id");
                            String userid = jsonObject11.getString("userid");


                            datas.add(new IncommingListModel(id, userid, imgList.get(i), nameList.get(i), mobileList.get(i), bloodList.get(i), addressList.get(i)));
                        }


                    }
                    incommingAccepterAdapter = new IncommingAcceptedAdapter(v.getContext(), datas, new IncommingAcceptedAdapter.onclick() {
                        @Override
                        public void rejectListner(int pos) {
                            rejectAcceptedDonor(pos, datas);
                        }
                    });
                    recyclerView.setAdapter(incommingAccepterAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                    toast(e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                toast(error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }

    private void rejectAcceptedDonor(int pos, ArrayList<IncommingListModel> datas) {
        String id = datas.get(pos).getId();
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.rejectIncommingRequest+"bbid="+id, new Response.Listener<String>() {
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

    private void toast(String s) {
        Toast.makeText(v.getContext(), s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.received_all:
                all.setBackground(getResources().getDrawable(R.color.blue));
                accepted.setBackground(getResources().getDrawable(R.color.white));
                getRecivedAllList();
                break;
            case R.id.received_accepted:
                all.setBackground(getResources().getDrawable(R.color.white));
                accepted.setBackground(getResources().getDrawable(R.color.blue));
                getAcceptedData();
                break;
        }
    }


}

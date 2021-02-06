package bloodbank.com.bb.fragment;


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

import bloodbank.com.bb.Adapter.AcceptedListAdapter;
import bloodbank.com.bb.Adapter.DonatedListAdapter;
import bloodbank.com.bb.Adapter.ReceivedListAdapter;
import bloodbank.com.bb.Adapter.RejectedListAdapter;
import bloodbank.com.bb.R;
import bloodbank.com.bb.URL;
import bloodbank.com.bb.model.AcceptedListModel;
import bloodbank.com.bb.model.DonatedListModel;
import bloodbank.com.bb.model.IncommingListModel;
import bloodbank.com.bb.model.ReceivedListModel;
import bloodbank.com.bb.model.RejectedListModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends android.app.Fragment implements View.OnClickListener {

    RecyclerView recyclerView;
    Button donated, received;
    String id;
    View v;

    DonatedListAdapter donatedListAdapter;
    ReceivedListAdapter receivedListAdapter;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public HistoryFragment(String id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_history, container, false);
        donated = (Button) v.findViewById(R.id.donated);
        received = (Button) v.findViewById(R.id.received);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getDonatedData();

        donated.setBackground(getResources().getDrawable(R.color.blue));
        received.setBackground(getResources().getDrawable(R.color.white));

        donated.setOnClickListener(this);
        received.setOnClickListener(this);

        return v;
    }

    private void getDonatedData() {
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        final ArrayList<String> imgList = new ArrayList<>();
        final ArrayList<String> nameList = new ArrayList<>();
        final ArrayList<String> mobileList = new ArrayList<>();
        final ArrayList<String> bloodList = new ArrayList<>();
        final ArrayList<String> addressList = new ArrayList<>();
        final ArrayList<DonatedListModel> datas = new ArrayList<>();

        datas.clear();
        imgList.clear();
        nameList.clear();
        mobileList.clear();
        bloodList.clear();
        addressList.clear();

        final String json = "{\"data\":[{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"}]}";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.mydonated + "id=" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    if (jsonObject1.getInt("status") == 1) {
                    JSONArray jsonArray = jsonObject1.getJSONArray("data");
                    Toast.makeText(v.getContext(),String.valueOf(jsonObject1.getInt("status")),Toast.LENGTH_LONG).show();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                            String id = jsonObject11.getString("id");
                            imgList.add(jsonObject11.getString("img"));
                            nameList.add(jsonObject11.getString("name"));
                            mobileList.add(jsonObject11.getString("mobile"));
                            bloodList.add(jsonObject11.getString("Blood"));
                            addressList.add(jsonObject11.getString("address"));
                            datas.add(new DonatedListModel(id, imgList.get(i), nameList.get(i), mobileList.get(i), bloodList.get(i), addressList.get(i)));
                        }

                        donatedListAdapter = new DonatedListAdapter(v.getContext(), datas);
                        recyclerView.setAdapter(donatedListAdapter);
                    } else {

                        donatedListAdapter = new DonatedListAdapter(v.getContext(), datas);
                        recyclerView.setAdapter(donatedListAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(v.getContext(),e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }

    private void getReceivedData() {
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        final ArrayList<String> imgList = new ArrayList<>();
        final ArrayList<String> nameList = new ArrayList<>();
        final ArrayList<String> mobileList = new ArrayList<>();
        final ArrayList<String> bloodList = new ArrayList<>();
        final ArrayList<String> addressList = new ArrayList<>();
        final ArrayList<ReceivedListModel> datas = new ArrayList<>();

        datas.clear();
        imgList.clear();
        nameList.clear();
        mobileList.clear();
        bloodList.clear();
        addressList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.myreceived+"id="+id, new Response.Listener<String>() {
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
                            datas.add(new ReceivedListModel(imgList.get(i), nameList.get(i), mobileList.get(i), bloodList.get(i), addressList.get(i)));
                        }
                        receivedListAdapter = new ReceivedListAdapter(v.getContext(), datas);
                        recyclerView.setAdapter(receivedListAdapter);
                    }else {
                        receivedListAdapter = new ReceivedListAdapter(v.getContext(), datas);
                        recyclerView.setAdapter(receivedListAdapter);
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
        String json = "{\"data\":[{\"img\":\"\",\"name\":\"test\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"},{\"img\":\"\",\"name\":\"Gokul\",\"mobile\":\"+91 7418227833\",\"Blood\":\"B\",\"address\":\"70 Anna street Kumaranandhapuram , Tirupur 641602\"}]}";


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.donated:
                donated.setBackground(getResources().getDrawable(R.color.blue));
                received.setBackground(getResources().getDrawable(R.color.white));
                getDonatedData();
                break;
            case R.id.received:
                donated.setBackground(getResources().getDrawable(R.color.white));
                received.setBackground(getResources().getDrawable(R.color.blue));
                getReceivedData();
                break;
        }

    }
}
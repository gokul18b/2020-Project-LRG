package bloodbank.com.bb;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

import bloodbank.com.bb.Adapter.DonorListadapter;
import bloodbank.com.bb.Adapter.EmergencyListAdapter;
import bloodbank.com.bb.model.AllDonors;
import bloodbank.com.bb.model.DonorListModel;

public class SkipLoginActivity extends AppCompatActivity {
RecyclerView recyclerView;
LinearLayoutManager linearLayoutManager;
    Spinner spinner;
    String id, selected_bg;

    ArrayList<AllDonors> datas = new ArrayList<>();
    EmergencyListAdapter emergencyListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skip_login);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        spinner = (Spinner) findViewById(R.id.spinner);

        final ArrayList<String> bgList = new ArrayList<>();


        bgList.add("A-");
        bgList.add("A+");
        bgList.add("B-");
        bgList.add("B+");
        bgList.add("O-");
        bgList.add("O+");
        bgList.add("AB-");
        bgList.add("AB+");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, bgList);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_bg = bgList.get(i);
                getAllDonors();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getAllDonors( ) {
        datas.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL.alldonors, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                                datas.add(new AllDonors(id, img, name, mobile, blood, address));
                            }


                        }

                        emergencyListAdapter = new EmergencyListAdapter(getApplicationContext(), datas, new EmergencyListAdapter.onclick() {
                            @Override
                            public void click(int pos) {
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + datas.get(pos).getMobile()));
                                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(callIntent);
                            }
                        });
                        recyclerView.setAdapter(emergencyListAdapter);
                    } else {
                        emergencyListAdapter = new EmergencyListAdapter(getApplicationContext(), datas, new EmergencyListAdapter.onclick() {
                            @Override
                            public void click(int pos) {

                            }
                        });
                        recyclerView.setAdapter(emergencyListAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    recyclerView.setAdapter(emergencyListAdapter);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                recyclerView.setAdapter(emergencyListAdapter);
            }
        });
        requestQueue.add(stringRequest);
    }

}

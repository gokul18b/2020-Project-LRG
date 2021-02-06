package bloodbank.com.bb.fragment;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import bloodbank.com.bb.R;
import bloodbank.com.bb.URL;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    TextView name, mobile, address,age,bloodgroup,username,city,district,pincode;
    CircleImageView image;
    View v;
    String id;
    URL url;

    public ProfileFragment() {

    }

    @SuppressLint("ValidFragment")
    public ProfileFragment(String id) {
        this.id = id;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_profile, container, false);

        image = (CircleImageView) v.findViewById(R.id.profile_image);
        name = (TextView) v.findViewById(R.id.profile_name);
        mobile = (TextView) v.findViewById(R.id.profile_mobile);
        address = (TextView) v.findViewById(R.id.profile_fulladdress);
        city = (TextView) v.findViewById(R.id.profile_city);
        pincode = (TextView) v.findViewById(R.id.profile_pincode);
        username = (TextView) v.findViewById(R.id.profile_username);
        age = (TextView) v.findViewById(R.id.profile_age);
        bloodgroup = (TextView) v.findViewById(R.id.profile_bloodgroup);
        district=(TextView)v.findViewById(R.id.profile_district);



        getData(id);
        return v;
    }

    private void getData(String id) {
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url.profile + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                    name.setText(jsonObject1.getString("name"));
                    age.setText(jsonObject1.getString("age"));
                    bloodgroup.setText(jsonObject1.getString("bloodgroup"));
                    mobile.setText(jsonObject1.getString("mobile"));
                    username.setText(jsonObject1.getString("username"));
                    city.setText(jsonObject1.getString("city"));
                    district.setText(jsonObject1.getString("district"));
                    pincode.setText(jsonObject1.getString("pincode"));
                    address.setText(jsonObject1.getString("address"));

                } catch (JSONException e) {
                    toast(String.valueOf(e));
                }


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


}

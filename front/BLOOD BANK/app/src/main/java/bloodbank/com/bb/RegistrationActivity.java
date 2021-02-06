package bloodbank.com.bb;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    TextInputEditText name, age, mobile, username, password, confirmpassword, city, district, pincode, fulladdress;
    Button register;
    List<String> categories = new ArrayList<String>();
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name = (TextInputEditText) findViewById(R.id.name);
        age = (TextInputEditText) findViewById(R.id.age);
        mobile = (TextInputEditText) findViewById(R.id.mobile);
        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        confirmpassword = (TextInputEditText) findViewById(R.id.confirmpassword);
        city = (TextInputEditText) findViewById(R.id.city);
        district = (TextInputEditText) findViewById(R.id.district);
        pincode = (TextInputEditText) findViewById(R.id.pincode);
        fulladdress = (TextInputEditText) findViewById(R.id.address);
        spinner = (Spinner) findViewById(R.id.bloodgroup);
        register = (Button) findViewById(R.id.register);

        spinner.setOnItemSelectedListener(this);

        categories.add("A-");
        categories.add("A+");
        categories.add("B-");
        categories.add("B+");
        categories.add("O-");
        categories.add("O+");
        categories.add("AB+");
        categories.add("AB-");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
    }

    private void checkValidation() {
        final String _name = name.getText().toString().trim();
        final String _age = age.getText().toString().trim();
        final String _mobile = mobile.getText().toString().trim();
        final String _username = username.getText().toString().trim();
        final String _password = password.getText().toString().trim();
        String _confirmpassword = confirmpassword.getText().toString().trim();
        final String _city = city.getText().toString().trim();
        final String _district = district.getText().toString().trim();
        final String _pincode = pincode.getText().toString().trim();
        final String _fulladdress = fulladdress.getText().toString().trim();
        int _bg = spinner.getSelectedItemPosition();
        final String _bloodgroup = categories.get(_bg).trim();

        if (_name.length() == 0 || _age.length() == 0 || _mobile.length() == 0 || _username.length() == 0 || _password.length() == 0 || _confirmpassword.length() == 0 || _city.length() == 0 || _district.length() == 0 || _pincode.length() == 0 || _fulladdress.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter All Fields values", Toast.LENGTH_SHORT).show();
        } else {
            if (_mobile.length() < 10) {
                Toast.makeText(getApplicationContext(), "Invalid Mobile number", Toast.LENGTH_SHORT).show();
            } else if (_pincode.length() < 6) {
                Toast.makeText(getApplicationContext(), "Invalid Pincode Number", Toast.LENGTH_SHORT).show();
            } else if (Integer.parseInt(_age) <= 0) {
                Toast.makeText(getApplicationContext(), "Invalid Age", Toast.LENGTH_SHORT).show();
            } else if (!_password.equals(_confirmpassword)) {
                Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
            } else {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url.register, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            JSONObject jsonObject11=jsonObject1.getJSONObject("data");
                            if(jsonObject11.getInt("status")==1) {
                                Toast.makeText(getApplicationContext(), String.valueOf(jsonObject11.getString("msg")),Toast.LENGTH_LONG).show();
                                name.setText("");
                                age.setText("");
                                mobile.setText("");
                                username.setText("");
                                password.setText("");
                                confirmpassword.setText("");
                                city.setText("");
                                district.setText("");
                                pincode.setText("");
                                fulladdress.setText("");

                            }else if(jsonObject11.getInt("status")==0){
                                Toast.makeText(getApplicationContext(), String.valueOf(jsonObject11.getString("msg")),Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("name", _name);
                        params.put("age", _age);
                        params.put("bloodgroup", _bloodgroup);
                        params.put("mobile", _mobile);
                        params.put("username", _username);
                        params.put("password", _password);
                        params.put("city", _city);
                        params.put("district", _district);
                        params.put("pincode", _pincode);
                        params.put("fulladdress", _fulladdress);


                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }

        }


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}

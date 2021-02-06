package bloodbank.com.bb;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button login;
    Button createAccount,skip;
    EditText username, password;
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btn_login);
        createAccount = (Button) findViewById(R.id.createaccount);
        skip = (Button) findViewById(R.id.btn_skip);

        login.setOnClickListener(this);
        createAccount.setOnClickListener(this);
        skip.setOnClickListener(this);

        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                checkLogin();
                break;
            case R.id.createaccount:
                Intent intent1 = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_skip:
                Intent intent = new Intent(LoginActivity.this,SkipLoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void checkLogin() {
        String _username = username.getText().toString().trim();
        String _password = password.getText().toString().trim();
        if (_username.length() != 0 || _password.length() != 0) {
            login(_username, _password);
        } else {
            Toast.makeText(getApplicationContext(), "Should Enter username and password", Toast.LENGTH_SHORT).show();
        }

    }

    private void login(final String username, final String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.login, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject jsonObject11 = jsonObject1.getJSONObject("data");

                    if (jsonObject11.getInt("status") == 1) {
                        Toast.makeText(getApplicationContext(), "Admin", Toast.LENGTH_LONG).show();
                    } else if (jsonObject11.getInt("status") == 0) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("id",String.valueOf(jsonObject11.getInt("id")));
                        startActivity(intent);

                    } else if (jsonObject11.getInt("status") == 2) {
                        Toast.makeText(getApplicationContext(), String.valueOf(jsonObject11.getString("msg")), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), String.valueOf(e), Toast.LENGTH_LONG).show();

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
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
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
}

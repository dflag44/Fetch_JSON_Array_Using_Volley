package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Delayed;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        TextView textView=(TextView) findViewById(R.id.txtvw);



        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,
                "https://corona.lmao.ninja/v2/countries", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++)
                    {
                        JSONObject obj=response.getJSONObject(i);
                        Log.d("myapp",obj.getString("country")+"  Caese= "+obj.getInt("cases"));

                        String ind="India";
                       if("India".equals(obj.getString("country"))) //checking
                       { textView.setText(obj.getString("country"));}

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp","something went wrong");
            }
        });
        requestQueue.add(jsonArrayRequest);


    }
}
package com.example.projectcloudcomputing;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class jsonActivity extends AppCompatActivity {
ImageButton imageButton ;
TextView cases,recovered ,desths;
RequestQueue requestQueue;
JsonObjectRequest jsonObjectRequest;
String uri="https://api.covid19api.com/summary";
String casess, recoveredd,desthss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        imageButton=findViewById(R.id.imageButton_json);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cases=findViewById(R.id.cases);
        recovered=findViewById(R.id.recovered);
        desths=findViewById(R.id.desths);
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject=response.getJSONObject("Global");
                    casess=jsonObject.getString("TotalConfirmed");
                    desthss=jsonObject.getString("TotalDeaths");
                    recoveredd=jsonObject.getString("TotalRecovered");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cases.setText(casess);
                desths.setText(desthss);
                recovered.setText(recoveredd);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}

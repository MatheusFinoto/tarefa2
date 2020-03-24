package com.example.tarefa2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    private ListView minhaLista;
    private Countries[] countries = new Countries[]{
            new Countries("","","","","","","","","")
    };

    private List<String> mLista;

    private String url = "https://restcountries.eu/rest/v2/lang/pt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minhaLista = (ListView) findViewById(R.id.minhaLista);
        mLista = new ArrayList<String>();
        //String url = "https://restcountries.eu/rest/v2/lang/pt";

        RequestQueue queue = Volley.newRequestQueue(this);



        StringRequest getData = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();
                List countries = new ArrayList();
                try {
                    //JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = new JSONArray(response);
                    for(int u = 0; u < jsonArray.length(); u++){
                        JSONObject jsonObject = jsonArray.getJSONObject(u);

                        String name = jsonObject.getString("name");

                        mLista.add(name);

                        Log.i("name", name);

                    }
                    minhaLista.setAdapter( new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            android.R.id.text1,
                            mLista
                    ));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.i("OPA","Error :" + error.toString());
            }
        });
        queue.add(getData);

    }


}



class Countries {
    String name;
    String callingCodes;
    String capital;
    String region;
    String subRegion;
    String population;
    String latlng;
    String nativeName;
    String cioc;

    public Countries(){}

    public Countries(String name, String callingCodes, String capital, String region, String subRegion, String population, String latlng, String nativeName, String cioc) {
        this.name = name;
        this.callingCodes = callingCodes;
        this.capital = capital;
        this.region = region;
        this.subRegion = subRegion;
        this.population = population;
        this.latlng = latlng;
        this.nativeName = nativeName;
        this.cioc = cioc;
    }
}

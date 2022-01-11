package com.example.bluedoll.views;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bluedoll.R;
import com.example.bluedoll.models.Maps;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class AboutUsFragment extends Fragment {

    GoogleMap map;
    private ArrayList<Maps> markerLocation = new ArrayList<>();
    private RequestQueue request;
    private String url = "https://raw.githubusercontent.com/acad600/JSONRepository/master/ISYS6203/O212-ISYS6203-SO02-00-BlueDoll.json";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng jakarta = new LatLng(-6.200000, 106.816666);
            googleMap.addMarker(new MarkerOptions().position(jakarta).title("Jakarta"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(jakarta));

            map = googleMap;

            MarkerOptions options = new MarkerOptions();

            for (int i = 0; i < markerLocation.size(); i++) {
                createMarker(markerLocation.get(i).getLat(), markerLocation.get(i).getLng(), markerLocation.get(i).getNamaLokasi());
            }
        }
    };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about_us, container, false);

        request = Volley.newRequestQueue(getActivity().getApplicationContext());
        SupportMapFragment sMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (sMapFragment != null) {
            sMapFragment.getMapAsync(callback);

        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONArray mapArray = obj.getJSONArray("markers");

                    for (int i = 0;i<mapArray.length();i++){
                        JSONObject mapsObjek = mapArray.getJSONObject(i);
                        Maps maps = new Maps(mapsObjek.getString("name"),mapsObjek.getDouble("lat"),mapsObjek.getDouble("lng") );
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

        request.add(stringRequest);

        return v;
    }


    private Marker createMarker(double ltd, double lng, String namePlace) {

        return map.addMarker(new MarkerOptions().position(new LatLng(ltd, lng)).title(namePlace));
    }

}
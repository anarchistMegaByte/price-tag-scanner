package com.carnot.testapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carnot.testapp.Views.CustomMapView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TestMapFragment extends Fragment
{
    public static TestMapFragment newInstance() {
        return new TestMapFragment();
    }

    public TestMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private GoogleMap map;
    private CustomMapView mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflatedView = inflater.inflate(R.layout.fragment_test_map, container, false);

        mView = (CustomMapView) inflatedView.findViewById(R.id.mapParked);
        mView.onCreate(savedInstanceState);
        mView.setFocusable(true);
        mView.setFocusableInTouchMode(true);

        mView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.clear();

                if (map != null)
                {
                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(0, 0))
                            .title("Marker"));

                    // Move the camera instantly to car_parked_location with a zoom of 10.
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 10));

                    // Zoom in, animating the camera and all such studapa.
                    if (map.getCameraPosition().zoom <= 14)
                        map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

                }
            }
        });
        mView.requestFocus();

        return inflatedView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mView.onDestroy();
    }
}

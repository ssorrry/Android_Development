package com.mirea.shiyanevskayaag.mireaproject;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.mirea.shiyanevskayaag.mireaproject.databinding.FragmentMapBinding;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapFragment extends Fragment {
    private MapView mapView = null;
    private FragmentMapBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(requireContext()));

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        mapView = binding.mapview;

        mapView.setZoomRounding(true);
        mapView.setMultiTouchControls(true);

        IMapController mapController = mapView.getController();
        mapController.setZoom(15.0);
        GeoPoint startPoint = new GeoPoint(55.794229, 37.700772);
        mapController.setCenter(startPoint);

        Marker marker_1 = new Marker(mapView);
        marker_1.setPosition(new GeoPoint(55.794229, 37.700772));
        marker_1.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(requireContext(),"ул. Стромынка, 20. РТУ МИРЭА. Кампус на Стромынке",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker_1);
        marker_1.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker_1.setTitle("Стромынка");

        Marker marker_2 = new Marker(mapView);
        marker_2.setPosition(new GeoPoint(55.6699, 37.4803));
        marker_2.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                Toast.makeText(requireContext(),"пр-т. Вернадского, 78с2. РТУ МИРЭА. Кампус на Вернадке",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mapView.getOverlays().add(marker_2);
        marker_2.setIcon(ResourcesCompat.getDrawable(getResources(), org.osmdroid.library.R.drawable.osm_ic_follow_me_on, null));
        marker_2.setTitle("Вернадка");

        // Логирование
        Log.d("MapFragment", "MapView is null: " + (mapView == null));
        Log.d("MapFragment", "Map overlays count: " + mapView.getOverlays().size());

        mapView.setBackgroundColor(Color.RED);

        Log.d(TAG, "onCreateView() вызван");

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() вызван");
        Configuration.getInstance().load(requireContext(),
                PreferenceManager.getDefaultSharedPreferences(requireContext()));
        mapView.setBackgroundColor(Color.RED);
        if (mapView != null) {
            mapView.onResume();
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() вызван");
        Configuration.getInstance().save(requireContext(),
                PreferenceManager.getDefaultSharedPreferences(requireContext()));
        if (mapView != null) {
            mapView.onPause();
        }
    }
}

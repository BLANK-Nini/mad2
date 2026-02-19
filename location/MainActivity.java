package com.example.location;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView latitude, longitude, addressText;
    Button getLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        addressText = findViewById(R.id.addressText);
        getLocation = findViewById(R.id.getLocation);

        fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);

        getLocation.setOnClickListener(v -> getLocationData());
    }

    private void getLocationData() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(location -> {

                        if (location != null) {

                            double lat = location.getLatitude();
                            double lon = location.getLongitude();

                            latitude.setText("Latitude: " + lat);
                            longitude.setText("Longitude: " + lon);

                            try {
                                Geocoder geocoder =
                                        new Geocoder(this, Locale.getDefault());

                                List<Address> addresses =
                                        geocoder.getFromLocation(lat, lon, 1);

                                if (addresses != null && !addresses.isEmpty()) {
                                    addressText.setText(
                                            "Address: " +
                                                    addresses.get(0).getAddressLine(0));
                                }

                            } catch (Exception e) {
                                Toast.makeText(this,
                                        "Unable to get address",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(this,
                                    "Location not available",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            getLocationData();
        }
    }
}

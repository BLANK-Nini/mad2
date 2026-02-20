import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Renamed to fl
    FusedLocationProviderClient fl;

    TextView latitude, longitude, address, city, country;
    Button getLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitude = findViewById(R.id.lattitude);
        longitude = findViewById(R.id.longitude);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        getLocation = findViewById(R.id.getlocation);

        // Initialize fl
        fl = LocationServices.getFusedLocationProviderClient(this);

        // Lambda click listener
        getLocation.setOnClickListener(v -> getLocation());
    }

    private void getLocation() {

        fl.getLastLocation().addOnSuccessListener(location -> {

            if (location == null) {
                Toast.makeText(this,
                        "Location not available",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            double lat = location.getLatitude();
            double lon = location.getLongitude();

            latitude.setText("Latitude: " + lat);
            longitude.setText("Longitude: " + lon);

            try {
                Geocoder geocoder =
                        new Geocoder(this, Locale.getDefault());

                List<Address> list =
                        geocoder.getFromLocation(lat, lon, 1);

                if (list != null && !list.isEmpty()) {

                    Address a = list.get(0);

                    address.setText("Address: " + a.getAddressLine(0));
                    city.setText("City: " + a.getLocality());
                    country.setText("Country: " + a.getCountryName());
                }

            } catch (Exception e) {
                Toast.makeText(this,
                        "Failed to get address",
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}
    }
}

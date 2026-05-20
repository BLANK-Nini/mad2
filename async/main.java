package com.example.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    Button imgdownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.ivshowimage);
        imgdownload = findViewById(R.id.imgdownload);

        imgdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ImageDownloader().execute(
                        "https://kct.ac.in/wp-content/uploads/2020/09/K00753-233x300.jpg"
                );
            }
        });
    }

    private class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {

        HttpURLConnection httpURLConnection;

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream =
                        new BufferedInputStream(httpURLConnection.getInputStream());
                publishProgress(50);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                publishProgress(100);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(MainActivity.this,
                    "Progress: " + values[0] + "%",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                iv.setImageBitmap(bitmap);
                Toast.makeText(MainActivity.this,
                        "Downloaded Successfully",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this,
                        "Download Failed",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}

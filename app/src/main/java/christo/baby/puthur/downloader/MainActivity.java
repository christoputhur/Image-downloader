package christo.baby.puthur.downloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.ImageReader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public void submitDownload(View view) {
            final ImageView iv1 = (ImageView) findViewById(R.id.iv);
            EditText urlField = (EditText) findViewById(R.id.url_field);
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = urlField.getText().toString();

            ImageRequest request = new ImageRequest(url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            iv1.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            iv1.setImageResource(R.drawable.image);
                        }
                    });
            queue.add(request);

            //MySingleton.getInstance(this).addToRequestQueue(request);

        }
    }





        //ImageView image = (ImageView) findViewById(R.id.iv);
        //String image_url = url;
        //ImageReader.OnImageAvailableListener






package christo.baby.puthur.downloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    Bitmap bm;
    String fileName;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }

        public void submitDownload(View view) {
            final ImageView iv1 = (ImageView) findViewById(R.id.iv);
            EditText urlField = (EditText) findViewById(R.id.urlField);
            final Button saveImageButton = (Button) findViewById(R.id.saveImageButton);
            RequestQueue queue = Volley.newRequestQueue(this);
            final String url = urlField.getText().toString();

            ImageRequest request = new ImageRequest(url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            iv1.setImageBitmap(bitmap);
                            saveImageButton.setVisibility(View.VISIBLE);
                            bm = bitmap;
                            fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            iv1.setImageResource(R.drawable.image);
                            saveImageButton.setVisibility(View.INVISIBLE);
                        }
                    });
            queue.add(request);
            //MySingleton.getInstance(this).addToRequestQueue(request);
        }

        public void saveImage(View view) throws FileNotFoundException {
            OutputStream outputStream = null;
            String storagePath = Environment.getExternalStorageDirectory().toString();
            File file = new File(storagePath, fileName);
            Context context = view.getContext();
            try {
                outputStream = new FileOutputStream(file);
                bm.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                Toast.makeText(context,"Image Saved at:" + storagePath, Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();

                Toast.makeText(context,"Error", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();

                Toast.makeText(context,"Error", Toast.LENGTH_LONG).show();
            }


        }
    }











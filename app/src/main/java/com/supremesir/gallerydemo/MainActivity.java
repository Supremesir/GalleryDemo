package com.supremesir.gallerydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.supremesir.gallerydemo.databinding.ActivityMainBinding;

/**
 * @author fang
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Error";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String url = "https://cdn.pixabay.com/photo/2020/04/03/15/27/flower-meadow-4999277_1280.jpg";
        RequestQueue queue = Volley.newRequestQueue(this);
        ImageLoader imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            // 设置容量为50的缓存
            // Lru - Last Recently Used
            private LruCache<String, Bitmap> cache = new LruCache<>(50);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
        binding.imageView.setImageUrl(url, imageLoader);

    }
}

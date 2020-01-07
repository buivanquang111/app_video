package com.example.app_video;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.app_video.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    String url="http://demo4855049.mockable.io/gethotvideo";
    String jsonarr="http://demo4855049.mockable.io/gethotvideo";
    ActivityMainBinding binding;


     AdapterVideo adapterVideo;
     ArrayList<Video> videolist;
     RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.imglogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Login());
            }
        });
        binding.imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Search());
            }
        });


//        //
//        binding.listTrangchu.setHasFixedSize(true);
//        binding.listTrangchu.setLayoutManager(new LinearLayoutManager(this));
//
//        videolist=new ArrayList<>();
//        requestQueue= Volley.newRequestQueue(this);
//
//        xuliJson(url);

        videolist = new ArrayList<>();
        new DoGetData(jsonarr);
        adapterVideo=new AdapterVideo(videolist);
        binding.listTrangchu.setAdapter(adapterVideo);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        binding.listTrangchu.setLayoutManager(layoutManager);


    }

    class DoGetData extends AsyncTask<Void, Void, Void>{
        String urlApi;
        String result="";

        public DoGetData(String urlApi) {
            this.urlApi = urlApi;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                URL url=new URL(urlApi);
                URLConnection connection=url.openConnection();
                InputStream is= connection.getInputStream();
                int bytecharacter;
                while((bytecharacter=is.read())!=-1){
                    result+=(char) bytecharacter;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                JSONArray jsonArray=new JSONArray(jsonarr);
                int length=jsonArray.length();
                for (int i=0;i<length;i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    String title=jsonObject.getString("title");
                    String avatar=jsonObject.getString("avatar");
                    videolist.add(new Video(avatar,title));
                }
               adapterVideo.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(aVoid);
        }
    }



//    private void xuliJson(final String json) {
//
//        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//                try {
//                    //JSONArray jsonArray=response.getJSONArray(json);
//                    JSONArray jsonArray=new JSONArray(json);
//                    for (int i=0; i<jsonArray.length();i++){
//                        JSONObject jsonObject=jsonArray.getJSONObject(i);
//                        String title=jsonObject.getString("title");
//                        String avatar=jsonObject.getString("avatar");
//
//                        videolist.add(new Video(avatar,title));
//
//                    }
//                    adapterVideo=new AdapterVideo(MainActivity.this,videolist);
//                    binding.listTrangchu.setAdapter(adapterVideo);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        requestQueue.add(request);
//    }







    public void getFragment(Fragment fragment){
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout,fragment)
                    .commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

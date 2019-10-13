package com.kamel.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private postsAdpater postsAdpater;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.RV_posts);
        posts=new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        postsAdpater = new postsAdpater(this,posts);

        AndroidNetworking.initialize(getApplicationContext());
        AndroidNetworking.get("http://192.168.1.13/tuto/api.php")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray postsj= response.getJSONArray("posts");
                            for (int i=0;i<postsj.length();i++){
                                post p=new post();
                                p.setTitle(postsj.getJSONObject(i).getString("title"));
                                p.setDescription(postsj.getJSONObject(i).getString("description"));
                                p.setImage(postsj.getJSONObject(i).getString("image"));
                                posts.add(p);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

///////////////

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postsAdpater);


    }
}

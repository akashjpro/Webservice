package com.example.aka.webserviceexample21032017;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button updateButton, editButton;
    EditText nameEditText, idEditText;

    RecyclerView mRecyclerView;
    ArrayList<Video> mListData;
    VideoAdapter mVideoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        eventListener();
        mListData = new ArrayList<>();
        mListData.add(new Video("001", "Dai Thien The Gioi", "hhhhhh"));

        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
        //mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(layoutManager);
        mVideoAdapter = new VideoAdapter(MainActivity.this, mListData);
        mRecyclerView.setAdapter(mVideoAdapter);

       runOnUiThread(new Runnable() {
           @Override
           public void run() {
              new LoadContent().execute("http://192.168.1.74/webservice041016/example.php");
           }
       });
    }

    private void eventListener() {
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void addControls() {
        updateButton = (Button) findViewById(R.id.buttonUpdate);
        editButton  = (Button) findViewById(R.id.buttonEdit);
        nameEditText = (EditText) findViewById(R.id.edtName);
        idEditText   = (EditText) findViewById(R.id.edtId);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

    }

    public class LoadContent extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray jsonArray = new JSONArray(s);
                String name;
                String id;
                String url;
                for (int i = 0; i< jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    name = jsonObject.getString("name");
                    id   = jsonObject.getString("id");
                    url  = jsonObject.getString("url");
                    mListData.add(new Video(id, name, url));
                   mVideoAdapter.notifyDataSetChanged();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    //Read content from url
    public static String readContentFromURL(String urlLink){
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlLink);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }


    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}

package shabalala.thamsanqa.tv_channel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private Toolbar toolbar;
    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Channel 808");
        toolbar.setSubtitle("Daily Schedule");

    }

    private void showSchedule(){

        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){

                JSONObject jo = result.getJSONObject(i);

                String id = jo.getString(Config.TAG_ID);
                String date = jo.getString(Config.TAG_DATE);
                String title = jo.getString(Config.TAG_TITLE);
                String time = jo.getString(Config.TAG_TIME);
                String description = jo.getString(Config.TAG_DESC);


                HashMap<String,String> show = new HashMap<>();
                show.put(Config.TAG_ID,id);
                show.put(Config.TAG_DATE,date);
                show.put(Config.TAG_TITLE,title);
                show.put(Config.TAG_TIME,time);
                show.put(Config.TAG_DESC,description);
                list.add(show);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                MainActivity.this, list, R.layout.mylist,
                new String[]{Config.TAG_TITLE,Config.TAG_DESC,Config.TAG_DATE,Config.TAG_TIME},
                new int[]{R.id.title,R.id.messages,R.id.poster,R.id.date});

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
        getJSON();
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Fetching Messages","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showSchedule();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Intent intent = new Intent(this, ViewEmployee.class);
        //  HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        //  String empId = map.get(Config.TAG_ID).toString();
        //  intent.putExtra(Config.SHOW_ID,empId);
        // startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.About:
                startActivity(new Intent(MainActivity.this,UploadActivity.class));
                Toast.makeText(this,"About Profile",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}

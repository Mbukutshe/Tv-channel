package shabalala.thamsanqa.tv_channel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;




public class UploadActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;

    private EditText Time;
    private EditText TimeEnd;
    private EditText Date;
    private EditText Title;
    private EditText Description;

    private Button btnDatePicker, btnTimePicker, btnTimePickerEnd, btnFinalUpload;

    private int mYear, mMonth, mDay, mHour, mMinute, eHour , eMinute;

        private  RequestQueue requestQueue;
        private  String insertUrl = "http://thammy202.comli.com/insertStudent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Channel 808");

        Time = (EditText)findViewById(R.id.Post_Time);
        Date = (EditText)findViewById(R.id.Post_Date);
        Title = (EditText)findViewById(R.id.Post_Title);
        TimeEnd = (EditText)findViewById(R.id.Post_Time_End);
        Description = (EditText)findViewById(R.id.Post_Description);

        btnTimePicker=(Button)findViewById(R.id.btn_time);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePickerEnd=(Button)findViewById(R.id.btn_time_end);
        btnFinalUpload=(Button)findViewById(R.id.btnUpload);

        btnTimePicker.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);
        btnFinalUpload.setOnClickListener(this);
        btnTimePickerEnd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            Date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            Time.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == btnTimePickerEnd) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            eHour = c.get(Calendar.HOUR_OF_DAY);
            eMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            TimeEnd.setText(hourOfDay + ":" + minute);
                        }
                    }, eHour, eMinute, false);
            timePickerDialog.show();
        }


        if (v == btnFinalUpload) {

            Toast.makeText(this,"Hour: " + mHour + "",Toast.LENGTH_LONG ).show();

            StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    System.out.println(response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parameters  = new HashMap<String, String>();
                    parameters.put("title",Title.getText().toString());
                    parameters.put("time",mHour+ ":"+ mMinute+"" +" - " +eHour+":" + eMinute+"");
                    parameters.put("description",Description.getText().toString());
                    parameters.put("date",mDay+"" + mMonth+"" + mYear+"");

                    return parameters;
                }
            };
            requestQueue.add(request);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_back:
                startActivity(new Intent(UploadActivity.this,MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }




}

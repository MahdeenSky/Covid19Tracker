package tech.classicaxle.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetAPIData().execute("https://api.covid19api.com/live/country/canada/status/confirmed");

        Button refresh = findViewById(R.id.refreshButton);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetAPIData().execute("https://api.covid19api.com/live/country/canada/status/confirmed");
            }
        });

    }


    class GetAPIData extends AsyncTask<String, Void,String> {

        @Override
        protected String doInBackground(String... urls) {

            Log.i("DiscoverAPI", " Async has started");

            String result ;
            URL url;
            HttpURLConnection connection ;

            try{

                url = new URL( urls[0]);

                connection = (HttpURLConnection) url.openConnection();



                connection.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();

                result = sb.toString();

                Log.i("DiscoverAPI", "Success. \n The result is \n" + result);

                return result;


            }
            catch (Exception e){
                Log.i("DiscoverAPI", "FAILED \n" + e.getMessage());
                return null;
            }


        }

        @SuppressLint("SetJavaScriptEnabled")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);



            String rec = "";
            String death = "";
            String active = "";
            String total = "";
            String date ="";

            try {

                Log.i("DiscoverAPI", "JSON OBJECT Interaction");

               // JSONObject object = new JSONObject(result);

                //JSONArray jsonArray = object.getJSONArray("top");

                JSONArray jsonArray = new JSONArray(result);

                Log.i("DiscoverAPI", "List Items are being called.");

                JSONObject lastObj = jsonArray.getJSONObject(jsonArray.length()-1);


                rec = lastObj.getString("Recovered");
                death= lastObj.getString("Deaths");
                active = lastObj.getString( "Active");
                total = lastObj.getString("Confirmed");
                date = lastObj.getString("Date");

                TextView r = findViewById(R.id.recoveredNum);
                TextView d = findViewById(R.id.deathsNum);
                TextView a = findViewById(R.id.activeNum);
                TextView t = findViewById(R.id.totalNum);
                TextView lastUpdate = findViewById(R.id.dateRecent);

                r.setText(rec);
                d.setText(death);

                a.setText(active);
                t.setText(total);

                Log.d("DateRecentAPI1", date);

                @SuppressLint("SimpleDateFormat") SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
                Date myDate = myFormat.parse(date);

                Log.d("DateRecentAPI", String.valueOf(myDate));

                String shortDate =   String.valueOf(myDate).substring(0, 10);

                lastUpdate.setText(shortDate);

                WebView news = findViewById(R.id.webview2);
                news.setWebViewClient(new WebViewClient());
                news.getSettings().setJavaScriptEnabled(true);
                news.loadUrl("https://www.who.int/news-room/news-updates");


                         }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        final Intent goLegal = new Intent(getApplicationContext(),Legal.class);

        if (id == R.id.world_menu){
            goLegal.putExtra("Source","World");
            startActivity(goLegal);
        }

        if (id == R.id.appointment_menu){
            goLegal.putExtra("Source","Appointment");
            startActivity(goLegal);

        }

        if (id == R.id.infoboard_menu){
            goLegal.putExtra("Source","InfoBoard");
            startActivity(goLegal);

        }



        return super.onOptionsItemSelected(item);
    }

}


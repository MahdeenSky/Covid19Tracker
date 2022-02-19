package tech.classicaxle.covid19tracker;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Legal extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal);

        String source = getIntent().getStringExtra( "Source");

        WebView  webView =  findViewById(R.id.legalWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        if(source.matches("World")){

            setTitle("World");
            webView.loadUrl("https://www.worldometers.info/coronavirus/");
        }else if (source.matches("Appointment")){
            setTitle("Book an Appointment");
            webView.loadUrl("https://covid-19.ontario.ca/book-vaccine/");

        } else {
            setTitle("Info Board");
            webView.loadUrl("https://www.cdc.gov/coronavirus/2019-ncov/your-health/about-covid-19.html");
        }
    }
}

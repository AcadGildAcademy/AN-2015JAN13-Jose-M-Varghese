package com.example.jose.intenet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jose on 2/24/2015.
 */

public class SettingActivity extends Activity {
Button search,developer,SpecficApp;
    EditText Esearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingactivitty);

        search = (Button)findViewById(R.id.btSearch);
        developer = (Button)findViewById(R.id.btDvlpr);
        SpecficApp = (Button)findViewById(R.id.btSpfic);
        Esearch = (EditText)findViewById(R.id.edSrch);
      SpecficApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent sApp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.facebook.katana"));
                startActivity(sApp);
            }
        });

          developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dvlp = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/FacebookIndia?brand_redir=1"));
                startActivity(dvlp);
            }
        });
 search.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent serch = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q="+Esearch.getText().toString()));
        startActivity(serch);
        // Toast.makeText(getApplicationContext(),Esearch.getText().toString(),Toast.LENGTH_LONG);
     }
 });
    }
}

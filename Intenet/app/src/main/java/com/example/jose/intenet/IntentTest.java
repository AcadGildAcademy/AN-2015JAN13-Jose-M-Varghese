package com.example.jose.intenet;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by jose on 2/24/2015.
 */


public class IntentTest extends Activity {

    Button Apps ;//=  (Button)(findViewById(R.id.btApps));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intentex);
      Apps=(Button)findViewById(R.id.btApps);

       Apps.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent TestIntent = new Intent(IntentTest.this,SettingActivity.class);//Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store"));
               startActivity(TestIntent);
           }
       });
    }


}

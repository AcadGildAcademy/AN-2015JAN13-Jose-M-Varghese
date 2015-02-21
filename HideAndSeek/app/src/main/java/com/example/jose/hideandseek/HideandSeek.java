package com.example.jose.hideandseek;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by manjusha on 2/20/2015.
 */
public class HideandSeek extends Activity {

//textView,button
    Button hide;
    TextView txt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hideandseek);
        hide=(Button)findViewById(R.id.buttonHideSeek);
        txt=(TextView)findViewById(R.id.txtData);
        //txt.setVisibility(View.INVISIBLE);
        int  flag  = txt.getVisibility();
        if(flag)
        {
            hide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt.setVisibility(View.GONE);
                    hide.setText("Show");
                }
            });
      }
        if(!flag)
        {
            hide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt.setVisibility(View.VISIBLE);
                    hide.setText("HiDe");
                }
            });
        }
    }
}

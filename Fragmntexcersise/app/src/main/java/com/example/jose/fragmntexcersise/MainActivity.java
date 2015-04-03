package com.example.jose.fragmntexcersise;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button  frg1,frg2,frg3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frg1=(Button)findViewById(R.id.bt1);
        frg1.setOnClickListener(this);
        frg2=(Button)findViewById(R.id.bt2);
        frg2.setOnClickListener(this);
        frg3=(Button)findViewById(R.id.bt3);
        frg3.setOnClickListener(this);

        /*frg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.frag1, Fragment2.newInstance());
                transaction.commit();
            }
        });
        frg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        frg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                          }
        });*/

    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            case R.id.bt1:
                Fragment1 fragmentOne = new Fragment1();
                fragmentTransaction.add(R.id.frag1, fragmentOne, "FIRST");
                fragmentTransaction.commit();
                break;
            case R.id.bt2:
                Fragment2 fragmentTwo = new Fragment2();
                fragmentTransaction.add(R.id.frag2,fragmentTwo,"SECOND");
                fragmentTransaction.commit();
                break;
            case  R.id.bt3:
                Fragment3 fragmentThree = new Fragment3();
                fragmentTransaction.add(R.id.frag3,fragmentThree,"THIRD");
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

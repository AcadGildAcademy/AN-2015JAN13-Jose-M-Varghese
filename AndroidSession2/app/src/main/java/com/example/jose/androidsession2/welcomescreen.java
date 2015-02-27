package com.example.jose.androidsession2;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.logging.Log;

import java.util.ArrayList;

/**
 * Created by jose on 2/26/2015.
 */
public class welcomescreen extends ActionBarActivity {
    TextView usr;
    ListView lstVw;
    ArrayList<String> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomescreen);
       /* usr=(TextView)findViewById(R.id.tvUser);
        Bundle Usrname = getIntent().getExtras();
        if(!Usrname.isEmpty())
        {
            usr.setText(Usrname.getString("username"));
        }*/
        getActionBar();
        contacts = new  ArrayList<String>();
        for(int i=0;i<10;i++)
        {
            contacts.add("test"+i);
        }
        lstVw=(ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        lstVw.setAdapter(adap);
        registerForContextMenu(lstVw);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(1,1,1,"1");//gpid,itmid,ordr,title
        menu.add(1,2,2,"2");
        menu.add(1,3,3,"3");
        menu.add(2,1,1,"1");
        menu.add(2,2,2,"2");
        menu.add(2,3,3,"3");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionsmenu,menu );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}

package com.example.jose.todo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Todo extends ActionBarActivity {

   String title;
   String descp;
   String datPi ;
   int day ;
   int month;
   int year;
   EditText setTitle;
   EditText  desc;
   DatePicker date;
   ListView lstView;
   ArrayList<String> contacts;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo);

        getActionBar();
      /*  lstView=(ListView) findViewById(R.id.listView);
        contacts=new ArrayList<String>();
      for(int i=0;i<10;i++) {
            contacts.add("item"+i);
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        lstView.setAdapter(adp);*/
       adapter = new MyAdapter(this, generateData());
        lstView=(ListView) findViewById(R.id.listView);
        lstView.setAdapter(adapter);

    }
private List<Details> generateData(){
    Database newDb = new Database(Todo.this);
    List<Details> dtls = newDb.getAllData();
    return dtls;
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        getMenuInflater().inflate(R.menu.add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity updateListin AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.Add:
                Context cntxt =this.getBaseContext();
                LinearLayout lyout = new LinearLayout(cntxt);
                lyout.setOrientation(LinearLayout.VERTICAL);

                final AlertDialog.Builder alertdlg= new AlertDialog.Builder(this);
                alertdlg.setTitle("Title");

                setTitle = new EditText(this);
                setTitle.setHint("Title");
                //alertdlg.setView(setTitle);
                lyout.addView(setTitle);
                  desc = new EditText(this);
                desc.setHint("Desc");
                //alertdlg.setView(desc);
                lyout.addView(desc);
                date =new DatePicker(this);
               // alertdlg.setView(date);
                lyout.addView(date);
                alertdlg.setView(lyout);
                alertdlg.setPositiveButton("Save",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String title=setTitle.getText().toString();
                        String descp = desc.getText().toString();
                        int day = date.getDayOfMonth();
                        int month = date.getMonth();
                        int year =  date.getYear();
                        String datPi =Integer.toString(day);
                        datPi=datPi+"/"+ Integer.toString(month);
                        datPi=datPi+"/"+Integer.toString(year);
                        Toast.makeText(getApplicationContext(), ""+datPi+descp+title, Toast.LENGTH_LONG).show();
                        Database newDb = new Database(Todo.this);
                       newDb.adDetails(new Details(title,descp,datPi));

                        List<Details> detail=newDb.getAllData();
                        adapter.updateList(detail);


                    }
                });

                alertdlg.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"clicked on add and cancel",Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    }
                });
                AlertDialog alrtdg= alertdlg.create();
                alrtdg.show();
               break;
            case R.id.Like:

                        Toast.makeText(getApplicationContext(),"clicked on add and cancel",Toast.LENGTH_LONG).show();

            //Toast.makeText(getApplicationContext(),"clicked on Like",Toast.LENGTH_LONG).show();
                break;

        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

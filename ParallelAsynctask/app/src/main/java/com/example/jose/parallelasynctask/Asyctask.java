package com.example.jose.parallelasynctask;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by JOSE on 3/3/2015.
 */
public class Asyctask extends Activity  implements View.OnClickListener{

    Button startDwnld;
    ProgressBar pb1,pb2,pb3,pb4;
    AsyTask as1,as2,as3,as4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asyctask);
        startDwnld = (Button)findViewById(R.id.btnStart);
        pb1 = (ProgressBar)findViewById(R.id.progressBar);
        pb2 = (ProgressBar)findViewById(R.id.progressBar2);
        pb3 = (ProgressBar)findViewById(R.id.progressBar3);
        pb4 = (ProgressBar)findViewById(R.id.progressBar4);
        startDwnld.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        as1 = new AsyTask(pb1);
        as1.execute();
        as2 = new AsyTask(pb2);
        as2.execute();
        as3 = new AsyTask(pb3);
        as3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        as4 = new AsyTask(pb4);
        as4.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

class AsyTask extends AsyncTask<String,Integer,String>{

    ProgressBar myProgressBar;
    AsyTask(ProgressBar target) {
        myProgressBar = target;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        for(int i=0; i<100; i++){
            publishProgress(i);
            try{
            Thread.sleep(200);}
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        myProgressBar.setProgress(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
   }
}

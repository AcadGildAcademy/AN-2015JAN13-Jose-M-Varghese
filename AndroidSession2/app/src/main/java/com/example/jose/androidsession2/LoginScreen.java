package com.example.jose.androidsession2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by manjusha on 2/26/2015.
 */
public class LoginScreen  extends Activity {
    Button signIn,cancel;
    EditText usrname,pswd;
    String user="jose";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        signIn=(Button)findViewById(R.id.btSignin);
        cancel=(Button)findViewById(R.id.btCancel);
        usrname = (EditText)findViewById(R.id.edUsrname);
        pswd=(EditText)findViewById(R.id.edPswd);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usrname.getText().toString()==""){
                    usrname.setError("Enter Your User Name  :) ");
                }
                else if(pswd.getText().toString()=="")
                {
                 pswd.setError("Enter yur password");
                }
                else
                {

                    if(usrname.getText().toString().equals(user) && pswd.getText().toString().equals(user))
                    {
                        Toast.makeText(LoginScreen.this,"Login Success",Toast.LENGTH_LONG).show();
                        Intent Wscrn= new Intent(LoginScreen.this,welcomescreen.class);
                        Bundle usname = new Bundle();
                        String name= usrname.getText().toString();
                        usname.putString("username",name);
                        Wscrn.putExtras(usname);
                        startActivity(Wscrn);
                        finish();

                    }
                    else
                    {
                        Toast.makeText(LoginScreen.this,"Login Failure .......",Toast.LENGTH_LONG).show();
                        usrname.setText("");
                        pswd.setText("");
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usrname.setText("");
                pswd.setText("");
            }
        });

    }
}

package com.extra.aroundtravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.extra.aroundtravel.db.SqlManager;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final EditText username = (EditText)findViewById(R.id.eusername);
        final EditText password = (EditText)findViewById(R.id.epassword);
        final Button login = (Button)findViewById(R.id.lbtn);
        final Button register = (Button)findViewById(R.id.sbtn);
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	String uname = username.getText().toString();
            	String pwd = password.getText().toString();
                if (uname == null || uname.equals("")){
                    Toast.makeText(MainActivity.this.getApplicationContext(),"Username Can not be empty£¡",Toast.LENGTH_LONG).show();
                }else if(pwd == null || pwd.equals("")){
                	Toast.makeText(MainActivity.this.getApplicationContext(),"Password Can not be empty£¡",Toast.LENGTH_LONG).show();
                }else{
                	SqlManager manager = new SqlManager(getApplicationContext());
                	boolean isExist = manager.getUserByUnamePwd(uname, pwd);
                	if(isExist){
                		Intent intent = new Intent();
                        intent.setClass(MainActivity.this, BaseMapActivity.class);
                        startActivity(intent);
                	}else{
                		Toast.makeText(MainActivity.this.getApplicationContext(),"username or password not correct£¡",Toast.LENGTH_LONG).show();
                	}
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

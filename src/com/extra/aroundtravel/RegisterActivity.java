package com.extra.aroundtravel;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.extra.aroundtravel.db.SqlManager;
import com.extra.aroundtravel.db.entity.User;

public class RegisterActivity extends Activity {
	private EditText rusername = null;
    private EditText rpassword = null;
    private EditText rrpassword = null;

    private Button submit = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		rusername = (EditText)findViewById(R.id.rusername);
        rpassword = (EditText)findViewById(R.id.rpassword);
        rrpassword = (EditText)findViewById(R.id.rrpassword);
        submit = (Button)findViewById(R.id.rbtn);
        
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            	String username = rusername.getText().toString();
            	String rpwd = rpassword.getText().toString();
            	String rrpwd = rrpassword.getText().toString();
                if(username == null || username.equals("")){
                    Toast.makeText(RegisterActivity.this.getApplicationContext(), "Username Can not be empty!", Toast.LENGTH_LONG).show();
                }else{
                	if(rpwd == null || rpwd.equals("")){
                        Toast.makeText(RegisterActivity.this.getApplicationContext(), "Password Can not be empty!", Toast.LENGTH_LONG).show();
                    }else{
                    	if(rrpwd == null || rrpwd.equals("")){
                            Toast.makeText(RegisterActivity.this.getApplicationContext(), "RePassword Can not be empty!", Toast.LENGTH_LONG).show();
                        }else if(!rrpwd.equals(rpwd)){
                        	Toast.makeText(RegisterActivity.this.getApplicationContext(), "RePassword not equal with password!", Toast.LENGTH_LONG).show();
                        }else{
                        	SqlManager sqlManager = new SqlManager(getApplicationContext());
                        	User user = new User();
                        	String up = username + rpwd;
                        	int id = up.hashCode();
                        	user.setId(id);
                        	user.setUsername(username);
                        	user.setPassword(rpwd);
                        	if(username.equals("admin")){
                        		user.setGroupid(1);
                        	}else{
                        		user.setGroupid(2);
                        	}
                        	sqlManager.save(user);
                        	Toast.makeText(RegisterActivity.this.getApplicationContext(), "success", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
	}
}

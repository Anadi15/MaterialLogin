package com.example.win8.materiallogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String USER_NAME = "USER_NAME";

    public static final String PASSWORD = "PASSWORD";

   // private static final String LOGIN_URL = "http://simplifiedcoding.16mb.com/UserRegistration/login.php";
    private static final String LOGIN_URL = "http://10.0.2.2/Educondb/login.php";
    private EditText editTextUserName;
    private EditText editTextPassword;
    private TextView text;

    private Button buttonLogin;
private TextView textViewerror;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUserName = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
textViewerror=(TextView) findViewById(R.id.textViewUserName);
        text=(TextView) findViewById(R.id.textViewAboutApp);
        text.setOnClickListener(this);
        buttonLogin = (Button) findViewById(R.id.buttonUserLogin);
//textView.setOnClickListener(this);


        buttonLogin.setOnClickListener(this);
    }


    private void login(){
        String username = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        userLogin(username,password);
    }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){
         //           Intent intent = new Intent(ActivityLogin.this,UserProfile.class);
           //         intent.putExtra(USER_NAME,username);
             //       startActivity(intent);
                    textViewerror.setText("You have sucessfully login");
                }else{
                    textViewerror.setText("*username or password is incorrect");
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(LOGIN_URL,data);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            login();
        }

    if(v == text)

    {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        //         intent.putExtra(USER_NAME,username);
             startActivity(intent);

    }
    }
}
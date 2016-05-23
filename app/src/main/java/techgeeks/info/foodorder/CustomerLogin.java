package techgeeks.info.foodorder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import techgeeks.info.foodorder.onlineDatabase.BackgroundWork;

public class CustomerLogin extends AppCompatActivity {

    EditText etName,etPass;

    String name,pass;
    private ProgressDialog pDialog;
    Button btnLogin,btnRegister;

    //BackgroundWork backgroundWork;


    Context context=this;

    CheckBox saveLoginCheckBox;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    public static String  PREFS_NAME="loginPrefs";
    public static String PREF_USERNAME="email";
    public static String PREF_PASSWORD="password";
    public static String PREF_CHECK= "saveLogin";

    private boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        setTitle("Delivery Guy Login ");


    etName = (EditText) findViewById(R.id.editTextEmail);
    etPass = (EditText) findViewById(R.id.editTextPassword);

        saveLoginCheckBox = (CheckBox) findViewById(R.id.checkBox);
        //loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        //loginPrefsEditor = loginPreferences.edit();

        //saveLogin = loginPreferences.getBoolean("saveLogin", false);


        btnLogin = (Button) findViewById(R.id.buttonLogin);
    btnRegister = (Button) findViewById(R.id.buttonSignUp);

    //backgroundWork = new BackgroundWork(context);
    //backgroundLogin =
        /*
        if (saveLogin) {
            etName.setText(loginPreferences.getString("contactEmail", ""));
            etPass.setText(loginPreferences.getString("contactPassword", ""));
            saveLoginCheckBox.setChecked(true);
        }
*/
    }

    @Override
    protected void onStart() {
        super.onStart();

        //read username and password from SharedPreferences
        getUser();
    }

    public void getUser(){
        //SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        loginPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        String username = loginPreferences.getString(PREF_USERNAME, null);
        String password = loginPreferences.getString(PREF_PASSWORD, null);

        if (username != null || password != null) {
            //directly show logout form
            //showLogout(username);
            Intent i = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(i);

        }
    }

    public void onLogin(View view) {

        switch (view.getId())
        {
            case R.id.buttonLogin:
                 name = etName.getText().toString();
                 pass = etPass.getText().toString();
                String type = "login";
                String result;

                //Toast.makeText(getApplicationContext(),"Button pressed",Toast.LENGTH_LONG).show();
                BackgroundLogin backgroundLogin = new BackgroundLogin();
                backgroundLogin.execute(type, name, pass);
                break;

            case R.id.buttonSignUp:
                Intent i = new Intent(getApplicationContext(),CustomerProfile.class);
                startActivity(i);
        }

    }

    private class BackgroundLogin extends AsyncTask<String,Void,String> {

        String result ="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CustomerLogin.this);
            pDialog.setMessage("Attempting login...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }


        /*public BackgroundLogin (Context ctx)
        {
            context = ctx;
        }
        */

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String iso = "iso-8059-1";

            //wamp
        /*String login_url = "http://10.0.2.2/login.php";
        String register_url = "http://10.0.2.2/register.php";
        String save_url = "http://10.0.2.2/save.php";
        String get_url = "http://10.0.2.2/get.php";
        */

            //online
            String login_url = "http://foodonline.comxa.com/login.php";
            //String register_url = "http://foodonline.comxa.com/register.php";




            //String login_url = "http://192.168.15.194/login.php";
            //String login_url = "http://foodonline.comxa.com/login.php";

            if(type.equals("login"))
                try {
                    //recieving username and pass from mainactivity
                    String user_name = params[1];
                    String password = params[2];
                    URL url = new URL(login_url);

                    //HttpURL
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    //sending data to php
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    //closing outputstream
                    outputStream.close();

                    //recieving response
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"),14);

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }



                    //closing input
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();

                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            return result;
        }

        @Override
        protected void onPostExecute(String aVoid) {

            /*alertDialog.setMessage(result);
            alertDialog.show();
            */

            if(aVoid.contains("login success") || aVoid.equals("login success")) {
                //alertDialog.hide();

                pDialog.setMessage("Login Success");
                pDialog.dismiss();
                Intent i = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(i);

                if (saveLoginCheckBox.isChecked()) {
                    /*
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("contactEmail", name);
                    loginPrefsEditor.putString("contactPassword", pass);
                    loginPrefsEditor.commit();*/

                    rememberMe(true,name,pass); //save username and password


                } /*else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }*/

                Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
            }
            else
            {
                etPass.setText("");
                pDialog.setMessage("Login Failed. Inavalid Email or Password");
                pDialog.setMax(10);

                pDialog.dismiss();

            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public void rememberMe(boolean check,String user, String password){
        //save username and password in SharedPreferences
        loginPrefsEditor = loginPreferences.edit();
        /*getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
                .edit()*/
        loginPrefsEditor.putBoolean(PREF_CHECK,check);
        loginPrefsEditor.putString(PREF_USERNAME,user);
        loginPrefsEditor.putString(PREF_PASSWORD,password);
        loginPrefsEditor.commit();
    }
}

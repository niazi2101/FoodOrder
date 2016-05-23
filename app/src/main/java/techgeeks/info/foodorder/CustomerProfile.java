package techgeeks.info.foodorder;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class CustomerProfile extends AppCompatActivity {

    private ProgressDialog pDialog;
    Button btnRegComplete;
    EditText textName, textEmail, textPassword, textPhone, textAddress;

    //BackgroundWork backgroundWork;
    BackgroundRegister register;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        setTitle("Create Profile");

        textName = (EditText) findViewById(R.id.editTextName);
        textEmail = (EditText) findViewById(R.id.editTextEmailReg);
        textPassword = (EditText) findViewById(R.id.editTextPasswordReg);
        textPhone = (EditText) findViewById(R.id.editTextPhone);
        textAddress = (EditText) findViewById(R.id.editTextAddress);
        //textRePassword = (EditText) findViewById(R.id.editTextRePassword);

        register = new BackgroundRegister();
        btnRegComplete = (Button) findViewById(R.id.buttonRegistered);
    }

    public void getRegister(View view) {
        String name = textName.getText().toString();
        String email = textEmail.getText().toString();
        String pass = textPassword.getText().toString();
        String address = textAddress.getText().toString();
        String phone = textPhone.getText().toString();

        String type = "register";

        if (name.equals("") || email.equals("") || pass.equals("")
                || address.equals("") || phone.equals("")) {
            Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
        }
        else
        {
            register.execute(type, name, phone, address, email, pass);
        }


    }

    public class BackgroundRegister extends AsyncTask<String, Void, String> {

        String result = "";
        String line = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CustomerProfile.this);
            pDialog.setMessage("Attempting Registation...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            String type = params[0];

            //online
            String login_url = "http://foodonline.comxa.com/login.php";
            String register_url = "http://foodonline.comxa.com/register.php";


            //String login_url = "http://192.168.15.194/login.php";
            //String login_url = "http://foodonline.comxa.com/login.php";

            if (type.equals("register")) {
                try {
                    String Name = params[1];
                    String Contact_No = params[2];
                    String Address = params[3];
                    String Email = params[4];

                    String Password = params[5];
                    URL url = new URL(register_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8") + "&"
                            + URLEncoder.encode("Contact_No", "UTF-8") + "=" + URLEncoder.encode(Contact_No, "UTF-8") + "&"

                            + URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(Address, "UTF-8") + "&"
                            + URLEncoder.encode("Email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8") + "&"
                            + URLEncoder.encode("Password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return result;
        }





        @Override
        protected void onPostExecute(String aVoid) {


        if(aVoid.contains("Insert Successfull") || aVoid.equals("Insert Successfull"))
        {
            pDialog.setMessage("Registration Successful");


            pDialog.dismiss();
            Intent i = new Intent(context, OneActivity.class);
            context.startActivity(i);
        }
            else
        {
            Toast.makeText(getApplicationContext(),"Insert Failed",Toast.LENGTH_SHORT).show();

        }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}

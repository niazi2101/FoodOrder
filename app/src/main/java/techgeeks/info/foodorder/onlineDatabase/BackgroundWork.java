package techgeeks.info.foodorder.onlineDatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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

import techgeeks.info.foodorder.TestActivity;

/**
 * Created by Hamza Khan Niaz on 5/21/2016.
 */
public class BackgroundWork extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    String result ="";


    String line = "";



    public BackgroundWork (Context ctx)
    {
        context = ctx;
    }

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
        String register_url = "http://foodonline.comxa.com/register.php";




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

        else if(type.equals("register"))
        {
            try
            {
                String Name = params[1];
                String Contact_No = params[2];
                String Address = params[3];
                String Email = params[4];

                String Password = params[5];
                URL url  = new URL(register_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Name", "UTF-8")+"="+URLEncoder.encode(Name, "UTF-8")+"&"
                        +URLEncoder.encode("Contact_No", "UTF-8")+"="+URLEncoder.encode(Contact_No, "UTF-8")+"&"

                        +URLEncoder.encode("Address", "UTF-8")+"="+URLEncoder.encode(Address, "UTF-8")+"&"
                        +URLEncoder.encode("Email", "UTF-8")+"="+URLEncoder.encode(Email, "UTF-8")+"&"
                        +URLEncoder.encode("Password", "UTF-8")+"="+URLEncoder.encode(Password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                while ((line = bufferedReader.readLine())!=null)
                {
                    result +=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {

        }


            return result;
    }


    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }


    @Override
    protected void onPostExecute(String aVoid) {

        alertDialog.setMessage(result);
        alertDialog.show();

        /*
        if(aVoid.contains("login success") || aVoid.equals("login success"))
        {
            alertDialog.hide();

            /*Intent i = new Intent(context, TestActivity.class);
            context.startActivity(i);*/


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

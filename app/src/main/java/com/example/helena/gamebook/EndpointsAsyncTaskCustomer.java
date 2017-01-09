package com.example.helena.gamebook;

import android.os.AsyncTask;
import android.util.Log;

import com.example.helena.myapplication.backend.customer1Api.Customer1Api;
import com.example.helena.myapplication.backend.customer1Api.model.Customer1;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stéphanie Pinto on 06.01.2017.
 */

public class EndpointsAsyncTaskCustomer extends AsyncTask<Void, Void, List<Customer1>> {
    private Customer1 customer1;
    private static final String TAG = EndpointsAsyncTaskCustomer.class.getName();
    private static Customer1Api customer1Api = null;

    EndpointsAsyncTaskCustomer(){}

    public EndpointsAsyncTaskCustomer(Customer1 customer1){
        this.customer1 = customer1;
    }

    @Override
    protected List<Customer1> doInBackground(Void... params) {

        if(customer1Api == null){
            // Only do this once
            Customer1Api.Builder builder = new Customer1Api.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    // if you deploy on the cloud backend, use your app name
                    // such as https://<your-app-id>.appspot.com
                    .setRootUrl("https://cloud-04012017.appspot.com/_ah/api/");
            customer1Api = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(customer1 != null){
                customer1Api.insert(customer1).execute();
                Log.i(TAG, "insert customer");
            }
            // and for instance return the list of all employees
            return customer1Api.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Customer1>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Customer1> result){

        if(result != null) {
            for (Customer1 customer1 : result) {
                Log.i(TAG, "CUSTOMER : Prenom: " + customer1.getPrenom() + " / Nom : "+ customer1.getNom() );
            }
        }
    }

}
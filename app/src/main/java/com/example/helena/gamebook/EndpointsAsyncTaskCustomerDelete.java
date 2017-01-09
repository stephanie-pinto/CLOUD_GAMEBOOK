package com.example.helena.gamebook;

import android.os.AsyncTask;
import android.util.Log;

import com.example.helena.myapplication.backend.customer1Api.Customer1Api;
import com.example.helena.myapplication.backend.customer1Api.model.Customer1;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Helena on 09.01.2017.
 */

public class EndpointsAsyncTaskCustomerDelete extends AsyncTask<Object, Object, List<Customer1>> {
        private static Customer1Api customer1Api = null;
        private static final String TAG = EndpointsAsyncTaskCustomer.class.getName();
        private Customer1 customer1;

        EndpointsAsyncTaskCustomerDelete(){}

        EndpointsAsyncTaskCustomerDelete(Customer1 customer1){
            this.customer1 = customer1;
        }

        @Override
        protected List<Customer1> doInBackground(Object... params) {

            if(customer1Api == null){
                // Only do this once
                Customer1Api.Builder builder = new Customer1Api.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        // if you deploy on the cloud backend, use your app name
                        // such as https://<your-app-id>.appspot.com
                        .setRootUrl("https://2-dot-truckdelivery2.appspot.com/_ah/api/");

                customer1Api = builder.build();
            }

            try{
                // Call here the wished methods on the Endpoints
                // For instance insert
                if(customer1 != null){
                    Log.i(TAG, "delete customer : "+ customer1.getId());

                    customer1Api.remove(customer1.getId()).execute();
                    Log.i(TAG, "delete customer : "+ customer1.getId());
                }
                // and for instance return the list of all customerss
                return customer1Api.list().execute().getItems();

            } catch (IOException e){
                Log.e(TAG, e.toString());
                return new ArrayList<>();
            }
        }

        //This method gets executed on the UI thread - The UI can be manipulated directly inside
        //of this method
        @Override
        protected void onPostExecute(List<Customer1> result){

            if(result != null) {
                for (Customer1 customer1 : result) {
                    Log.i(TAG, "First name: " + customer1.getPrenom() + " Last name: "
                            + customer1.getNom());
                }
            }
        }

    }

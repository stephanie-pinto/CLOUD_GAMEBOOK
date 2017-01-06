package com.example.helena.gamebook;

import android.content.Context;
import android.os.AsyncTask;
import android.os.AsyncTask;
import android.util.Log;

import android.util.Pair;
import android.widget.Toast;

import com.example.helena.*;
import com.example.helena.gamebook.db.object.Customer;
import com.example.helena.myapplication.backend.bookingApi.BookingApi;
import com.example.helena.myapplication.backend.bookingApi.model.Booking;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;


import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.util.List;

/**
 * Created by Helena on 03.01.2017.
 */

class EndpointsAsyncTaskBooking extends AsyncTask<Void, Void, List<Booking>> {
    private Booking booking;
    private static final String TAG = EndpointsAsyncTaskBooking.class.getName();
    private static BookingApi bookingApi = null;


    EndpointsAsyncTaskBooking(){};

    public EndpointsAsyncTaskBooking(Booking booking){
        this.booking = booking;
    }

    @Override
    protected List<Booking> doInBackground(Void... params) {
        if(bookingApi == null){
            // Only do this once
            BookingApi.Builder builder = new BookingApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    // if you deploy on the cloud backend, use your app name
                    // such as https://<your-app-id>.appspot.com
                    .setRootUrl("https://cloud-04012017.appspot.com/_ah/api/");
            bookingApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(booking != null){
                bookingApi.insert(booking).execute();
                Log.i(TAG, "insert booking");
            }
            // and for instance return the list of all employees
            return bookingApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Booking>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Booking> result){

        if(result != null) {
            for (Booking booking : result) {
                Log.i(TAG, "ID BOOKING: " + booking.getId());
            }
        }
    }
}
package com.example.helena.gamebook;

import android.os.AsyncTask;
import android.util.Log;


import com.example.helena.myapplication.backend.gameApi.GameApi;
import com.example.helena.myapplication.backend.gameApi.model.Game;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stéphanie Pinto on 06.01.2017.
 */

public class EndpointsAsyncTaskGame extends AsyncTask<Void, Void, List<Game>> {
    private Game game;
    private static final String TAG = EndpointsAsyncTaskGame.class.getName();
    private static GameApi gameApi = null;


    EndpointsAsyncTaskGame(){}

    public EndpointsAsyncTaskGame(Game game){
        this.game = game;
    }

    @Override
    protected List<Game> doInBackground(Void... params) {

        if(gameApi == null){
            // Only do this once
            GameApi.Builder builder = new GameApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    // if you deploy on the cloud backend, use your app name
                    // such as https://<your-app-id>.appspot.com
                    .setRootUrl("https://cloud-04012017.appspot.com/_ah/api/");
            gameApi = builder.build();
        }

        try{
            // Call here the wished methods on the Endpoints
            // For instance insert
            if(game != null){
                gameApi.insert(game).execute();
                Log.i(TAG, "insert game");
            }
            // and for instance return the list of all employees
            return gameApi.list().execute().getItems();

        } catch (IOException e){
            Log.e(TAG, e.toString());
            return new ArrayList<Game>();
        }
    }

    //This method gets executed on the UI thread - The UI can be manipulated directly inside
    //of this method
    @Override
    protected void onPostExecute(List<Game> result){

        if(result != null) {
            for (Game game : result) {
                Log.i(TAG, "Date: " + game.getDate() + " / Heure : "
                        + game.getHeure() + " / Résident : "
                        + game.getTeamRes() + " / Visiteur : "
                        + game.getTeamExt() + " / Quantité : "
                        + game.getQuantity());

            }
        }
    }

}
package com.ferganade.android_intech;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * helper methods.
 */
public class GetBeersServices extends IntentService {
    private final String TAG = GetBeersServices.class.getSimpleName();
    private static final String GET_ALL = "getAllBeers";

    public GetBeersServices() {
        super("GetBeersServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (GET_ALL.equals(action)) {
                handleActionBeers();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBeers() {
        URL url;

        try {
            url = new URL("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                copyInputStreamToFile(connection.getInputStream(), new File(getCacheDir(), "bieres.json"));
                Log.i(TAG, "Successfully got all beers");
                Log.i(TAG, getCacheDir().getAbsolutePath());
            }
        } catch(MalformedURLException exception) {
            exception.printStackTrace();
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    private void copyInputStreamToFile(InputStream input, File file){
        try {
            OutputStream output = new FileOutputStream(file);

            byte[] buff = new byte[1024];
            int len;

            while((len = input.read(buff)) > 0){
                output.write(buff, 0, len);
            }

            output.close();
            input.close();
        } catch(Exception exception){
            exception.printStackTrace();
        }
    }
}

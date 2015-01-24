package com.event.esport.personnal.esport_event;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * @Author François Hallereau
 * @Date 24/01/2015
 */
public class DownloadTask extends AsyncTask<String,Integer,Document> {

    private Context context;

    public DownloadTask(Context context) {
        this.context = context;
    }

    @Override
    protected Document doInBackground(String... params) {
        Document doc=null;
        if(isOnline()) {
            for (String param : params) {
                try {
                    doc = Jsoup.connect(param).get();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return doc;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

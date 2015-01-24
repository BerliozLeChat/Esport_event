package com.event.esport.personnal.esport_event;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * @Author François Hallereau
 * @Date 24/01/2015
 */
class DownloadTask extends AsyncTask<String,Integer,Document> {

    @Override
    protected Document doInBackground(String... params) {
        Document doc=null;
        for (String param : params){
            try {
                doc = Jsoup.connect(param).get();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return doc;
    }
}

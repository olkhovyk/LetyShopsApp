package com.olk.ilya.letyshopsapp.data;

import android.os.AsyncTask;
import android.view.View;

import com.olk.ilya.letyshopsapp.data.dagger.App;
import com.olk.ilya.letyshopsapp.domain.Currency;
import com.olk.ilya.letyshopsapp.domain.CurrencyMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;


public class ParserTask extends AsyncTask<Void, Void, Void> {

    private Logger LOGGER = Logger.getLogger(ParserTask.class.getName());

    @Inject Currency mCurrency;

    private HttpURLConnection urlConnection = null;
    private BufferedReader reader = null;
    private String resultJson = "";
    private Callback callback;
    private JSONArray jsonArray;

    @Inject
    public ParserTask(final Callback callback) {
       // App.getComponent().inject(this);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            createNewConnection();
            read(urlConnection);

            jsonArray = new JSONArray(resultJson);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                mCurrency = createNewCurrency(obj);
                CurrencyMap.getInstance().getCurrencyMap().put(mCurrency.getCcy(),mCurrency);
            }

        }catch (JSONException | IOException e) {
            LOGGER.log(Level.SEVERE, e.toString());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        if(callback!=null)
        callback.afterLoadCurrency(result);
    }

    public interface Callback
    {
        public void afterLoadCurrency(Void someResult);
    }

    private Currency createNewCurrency(JSONObject obj){
        Currency currency = new Currency();
        try{
        currency.setCcy(obj.getString("ccy"));
        currency.setBase_ccy(obj.getString("base_ccy"));
        currency.setBuy(obj.getDouble("buy"));
        currency.setSale(obj.getDouble("sale"));
        } catch (JSONException e){
            e.printStackTrace();
        }
        return currency;
    }

    private void createNewConnection(){
        URL url = null;
        try {
            url = new URL("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, e.toString());
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString());
            e.printStackTrace();
        }

    }

    private void read(HttpURLConnection urlConnection) throws IOException{
        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        resultJson = buffer.toString();
    }

}

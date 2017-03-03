package com.olk.ilya.letyshopsapp.data;

import android.os.AsyncTask;

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
import java.net.ProtocolException;
import java.net.URL;



public class ParserTask extends AsyncTask<Void, Void, Void> {

    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJson = "";
    private Callback callback;
    JSONArray jsonArray;

    public ParserTask(final Callback callback) {
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            createNewConnection();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            resultJson = buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            jsonArray = new JSONArray(resultJson);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Currency c = createNewCurrency(obj);
                CurrencyMap.getInstance().getCurrencyMap().put(c.getCcy(),c);
            }

        } catch (JSONException e) {
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
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

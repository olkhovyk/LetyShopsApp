package com.olk.ilya.letyshopsapp.data;

import android.content.Context;

import com.olk.ilya.letyshopsapp.domain.Currency;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;


public class SaveAndLoad {
    @Inject
    Currency c;

    private Logger LOGGER = Logger.getLogger(SaveAndLoad.class.getName());

    @Inject
    public SaveAndLoad() {
    }

    public void saveFile(Context context, String fileName, Map<String, Currency> map){
        String filePath = context.getFilesDir().getPath().toString() + fileName;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(new Date().toString() + "\n");
            writer.write("\n");
            for(Map.Entry entry : map.entrySet()){
                c = (Currency) entry.getValue();
                write(writer, c);
            }
        } catch (IOException e){
            LOGGER.log(Level.SEVERE, e.toString());
            e.printStackTrace();
        }
    }

    public String openFile(Context context, String fileName){
        String filePath = context.getFilesDir().getPath().toString() + fileName;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            if(reader != null){
                String line;
                StringBuilder sb = new StringBuilder();

                while ((line = reader.readLine()) != null){
                    sb.append(line + "\n");
                }

                return sb.toString();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString());
            e.printStackTrace();
        }
        return null;
    }

    protected void write(BufferedWriter writer, Currency c) throws IOException {
        writer.write("Currency : " + c.getCcy() + "\n");
        writer.write("Buy : " + c.getBuy() + "  Sale : " + c.getSale() + "\n");
        writer.write("\n");
    }

}

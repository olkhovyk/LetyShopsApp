package com.olk.ilya.letyshopsapp.data;

import android.content.Context;
import android.widget.TextView;


import com.olk.ilya.letyshopsapp.domain.Currency;
import com.olk.ilya.letyshopsapp.domain.CurrencyMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Map;
import static com.olk.ilya.letyshopsapp.data.Constants.FILENAME;


public class SaveAndLoad {
    public void saveFile(Context context, String fileName){
        String filePath = context.getFilesDir().getPath().toString() + fileName;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(new Date().toString() + "\n");
            writer.write("\n");
            for(Map.Entry entry : CurrencyMap.getInstance().getCurrencyMap().entrySet()){
                Currency c = (Currency) entry.getValue();
                writer.write("Currency : " + c.getCcy() + "\n");
                writer.write("Buy : " + c.getBuy() + "  Sale : " + c.getSale() + "\n");
                writer.write("\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void openFile(Context context, TextView textView, String fileName){
        String filePath = context.getFilesDir().getPath().toString() + fileName;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            if(reader != null){
                String line;
                StringBuilder sb = new StringBuilder();

                while ((line = reader.readLine()) != null){
                    sb.append(line + "\n");
                }
                reader.close();
                if(sb.length() > 0){
                    textView.setText(sb.toString());
                }
                else {
                    textView.setText("File is empty");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

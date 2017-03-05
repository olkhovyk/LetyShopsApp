package com.olk.ilya.letyshopsapp.data;

import com.olk.ilya.letyshopsapp.data.dagger.App;
import com.olk.ilya.letyshopsapp.domain.Currency;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by Илья on 05.03.2017.
 */
public class SaveAndLoadTest {

    Logger LOG = Logger.getLogger(SaveAndLoadTest.class.getName());

    File mFile;
    SaveAndLoad mSaveAndLoad;
    Currency c;

    @Before
    public void setUp() throws Exception {
        mFile = new File("TestFile");
        mSaveAndLoad = new SaveAndLoad();
        c = new Currency();
        c.setCcy("EUR");
        c.setBase_ccy("UAH");
        c.setBuy(20.15);
        c.setSale(22.10);
    }

    @Test
    public void write() throws Exception {
        if(!mFile.exists()){
            mFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(mFile));
        mSaveAndLoad.write(writer, c);
        writer.close();

        StringBuilder string = new StringBuilder("Currency : EUR\n" + "Buy : 20.15" + "  Sale : 22.1" +"\n\n");


        BufferedReader reader = new BufferedReader(new FileReader(mFile));

        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = reader.readLine()) != null){
            sb.append(line + "\n");
        }
        sb.toString();

        assertEquals(string.toString(), sb.toString());
    }

}
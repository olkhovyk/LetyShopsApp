package com.olk.ilya.letyshopsapp.data;



import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static com.olk.ilya.letyshopsapp.data.Constants.FILENAME;

/**
 * Created by Илья on 02.03.2017.
 */
public class SaveAndLoadTest {
    SaveAndLoadTest saveAndLoadTest;

    @Before
    public void setUp() throws Exception {
        saveAndLoadTest = new SaveAndLoadTest();

    }

    @Test
    public void saveFile() throws Exception {
        saveAndLoadTest.openFile();
    }

    @Test
    public void openFile() throws Exception {

    }

}
package com.olk.ilya.letyshopsapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Илья on 03.03.2017.
 */
public class CurrencyTest {
    Currency c = new Currency();
    @Before
    public void setUp() throws Exception {
        c.setCcy("UAH");
        c.setBase_ccy("EUR");
        c.setBuy(19.99);
        c.setSale(5.23);
    }

    @Test
    public void getCcy() throws Exception {
        assertEquals("UAH", c.getCcy());
    }

    @Test
    public void getBase_ccy() throws Exception {
        assertEquals("EUR", c.getBase_ccy());
    }

    @Test
    public void getBuy() throws Exception {
        assertEquals(19.99, c.getBuy(), 0.1);
    }

    @Test
    public void getSale() throws Exception {
        assertEquals(5.23, c.getSale(), 0.1);
    }

}
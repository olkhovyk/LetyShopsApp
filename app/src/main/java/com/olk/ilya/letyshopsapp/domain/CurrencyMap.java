package com.olk.ilya.letyshopsapp.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CurrencyMap {
    private static CurrencyMap ourInstance = new CurrencyMap();

    public static CurrencyMap getInstance() {
        return ourInstance;
    }

    private HashMap<String, Currency> mCurrencyMap = new HashMap<>();

    private CurrencyMap() {
    }

    public Map<String, Currency> getCurrencyMap() {
        return mCurrencyMap;
    }

    public void setCurrencyMap(HashMap<String, Currency> currencyMap) {
        mCurrencyMap = currencyMap;
    }
}

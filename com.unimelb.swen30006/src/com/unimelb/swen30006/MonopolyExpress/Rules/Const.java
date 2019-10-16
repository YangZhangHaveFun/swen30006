package com.unimelb.swen30006.MonopolyExpress.Rules;

import java.util.HashMap;

public class Const {
    public static HashMap<String, Integer> valueMap = new HashMap<>();

    static {
        valueMap.put("50", 600);
        valueMap.put("100", 1000);
        valueMap.put("150", 1500);
        valueMap.put("200", 1800);
        valueMap.put("250", 2200);
        valueMap.put("300", 2700);
        valueMap.put("400", 3000);
        valueMap.put("500", 3500);
        valueMap.put("Railroads", 2500);
        valueMap.put("Utilities", 800);
    }
}

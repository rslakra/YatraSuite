package com.rslakra.microservice.yatrasuite.yatrathymeleaf.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestHelpers {

    private static final Random RANDOM = new Random();

    /**
     * @return
     */
    public static Map<String, Object> createArbitraryMap() {
        Map<String, Object> map = new HashMap<>();
        int numElems = RANDOM.nextInt(5);

        if (numElems == 0) {
            map.put("innerMap", createArbitraryMap());
        } else {
            for (int i = 0; i < numElems; i++) {
                map.put("key" + i, "value" + i);
            }
        }

        return map;
    }
}

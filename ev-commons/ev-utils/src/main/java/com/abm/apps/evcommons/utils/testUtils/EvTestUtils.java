package com.abm.apps.evcommons.utils.testUtils;

import com.google.gson.Gson;

public class EvTestUtils {

    public static String toJson(Object o) {

        if (o != null) {
            return new Gson().toJson(o);
        }
        return null;
    }
}

package com.rsa.bingo.app.infrastructure.utils;

import org.json.JSONArray;

public final class CardValuesDecoder {

    private CardValuesDecoder() { }

    public static Integer[][] decode(String json) {
        var rows = new JSONArray(json);
        var values = new Integer[3][9];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                values[i][j] = rows.getJSONArray(i).optIntegerObject(j, null);
        return values;
    }
}

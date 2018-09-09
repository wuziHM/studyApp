package com.example.test.myapplicationtest.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonImpl extends Json {
    private Gson gson = new Gson ( );

    @Override
    public String toJson(Object src) {
        return gson.toJson ( src );
    }

    @Override
    public <T> T toObject(String json, Class<T> claxx) {
        return gson.fromJson ( json, claxx );
    }

    @Override
    public <T> T toObject(byte[] bytes, Class<T> claxx) {
        return gson.fromJson ( new String ( bytes ), claxx );
    }

    @Override
    public <T> List<T> toList(String json, Class<T> claxx) {
//        Type type = new TypeToken<ArrayList<T>> ( ) {
//        }.getType ( );
//        List<T> list = gson.fromJson ( json, type );
//        return list;

        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser ().parse(json).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, claxx));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}

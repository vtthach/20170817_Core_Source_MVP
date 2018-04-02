package com.igap.application.helper;


import com.google.gson.Gson;

public class GsonHelper {
    private final Gson gson;

    public GsonHelper(Gson gson) {
        this.gson = gson;
    }

    public String getJsonString(Object user) {
        return gson.toJson(user);
    }

    public <T> T toJsonObject(String strJson, Class<T> type) {
        return strJson != null ? gson.fromJson(strJson, type) : null;
    }
}

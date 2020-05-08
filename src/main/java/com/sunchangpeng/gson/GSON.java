package com.sunchangpeng.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GSON {
    private static final TypeToken<?> MAP_TYPE = new MapTypeToken();
    private static final TypeToken<?> LIST_TYPE = new ListTypeToken();

    public static final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    private GSON() {
    }

    public static String toJSONString(Object object) {
        return gson.toJson(object);
    }

    public static String toJSONString(Object object, boolean prettyFormat) {
        if (prettyFormat) {
            Gson prettyGson = gson.newBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(object);
        }
        return gson.toJson(object);
    }

    public static JsonElement parse(String text) {
        return gson.fromJson(text, JsonElement.class);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        return gson.fromJson(text, clazz);
    }

    public static <T> T parseObject(String text, Type typeOfT) {
        return gson.fromJson(text, typeOfT);
    }

    public static <T> T parseObject(String text, TypeToken typeToken) {
        return gson.fromJson(text, typeToken == null ? null : typeToken.getType());
    }

    public static <T> List<T> parseList(String text, Class<T> clazz) {
        return parseList(text, (Type) clazz);
    }

    public static <T> List<T> parseList(String text, Type typeOfT) {
        return gson.fromJson(assertJsonArrayString(text), TypeToken.getParameterized(ArrayList.class, typeOfT).getType());
    }

    public static <T> List<T> parseList(String text, TypeToken typeToken) {
        return parseList(text, typeToken == null ? null : typeToken.getType());
    }

    public static List<JsonElement> parseList(String text) {
        return gson.fromJson(assertJsonArrayString(text), LIST_TYPE.getType());
    }

    public static Map<String, JsonElement> parseMap(String text) {
        return gson.fromJson(assertJsonObjectString(text), MAP_TYPE.getType());
    }

    public static String assertJsonObjectString(String text) {
        String trimmed = (text != null) ? text.trim() : "";
        if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
            return trimmed;
        }
        throw new JsonSyntaxException("Expected JSON_OBJECT but was not.");
    }

    public static String assertJsonArrayString(String text) {
        String trimmed = (text != null) ? text.trim() : "";
        if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
            return trimmed;
        }
        throw new JsonSyntaxException("Expected JSON_ARRAY but was not.");
    }

    private static final class MapTypeToken extends TypeToken<Map<String, JsonElement>> {
    }

    private static final class ListTypeToken extends TypeToken<List<JsonElement>> {
    }
}
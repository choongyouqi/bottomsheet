package com.github.axzae.bottomsheet.pojo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Item {

    public Item(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @SerializedName("Name")
    public String name;

    @SerializedName("Value")
    public String value;

    @Override
    public String toString() {
        return (new Gson()).toJson(this);
    }
}
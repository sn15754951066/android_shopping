package com.example.android_shopping.interfaces;

public interface Callback<T> {

    void success(T data);

    void fail(String err);

}

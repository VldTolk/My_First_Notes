package com.example.myfirstnotes.main.domain;

public interface Callback<T>  {
    void onSuccess(T result);
    void onError(Throwable error);
}

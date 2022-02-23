package com.nytime.nytimes.client.request;

public class ApiResponse<T> {

    public int responseCode;
    private T data;


    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}

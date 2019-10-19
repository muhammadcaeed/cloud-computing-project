package com.onlinekaufen.springframework.dto;

/**
 * Created by Prashant on 3/8/17.
 *
 * Class for standardizing format for making request to the webservice.
 */
public class Request<T> {

    private int requestID;
    private T data;
    private String token;

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

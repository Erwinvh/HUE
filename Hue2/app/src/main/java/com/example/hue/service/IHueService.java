package com.example.hue.service;

import okhttp3.RequestBody;

interface IHueService {
    void getService(String service);
    boolean putService(String service, RequestBody body);
}

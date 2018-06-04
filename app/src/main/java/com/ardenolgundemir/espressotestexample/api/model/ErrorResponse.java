package com.ardenolgundemir.espressotestexample.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ardenolgundemir on 29/09/2017.
 */

public class ErrorResponse {

    @SerializedName("error")
    public String error;

    @SerializedName("message")
    public String message;
}

package com.ardenolgundemir.espressotestexample.controller.view;

/**
 * Created by ardenolgundemir on 01/06/2017.
 */

public interface BaseView {
    void showWait();
    void removeWait();
    void onFailure(String appErrorMessage);
    void onAlertMessage(String alertMessage);
}

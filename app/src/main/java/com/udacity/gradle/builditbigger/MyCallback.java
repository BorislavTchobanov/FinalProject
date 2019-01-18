package com.udacity.gradle.builditbigger;

import android.support.annotation.NonNull;

public interface MyCallback {
    void prepUI();
    void giveMeJoke(@NonNull String joke);
}
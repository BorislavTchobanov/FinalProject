package com.udacity.gradle.builditbigger;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Test;

public class EndpointsAsyncTaskTest {

    @Test
    public void asyncTaskTest() {
        EndpointsAsyncTask asyncTaskTest = new EndpointsAsyncTask(new MyCallback() {
            @Override
            public void prepUI() {

            }

            @Override
            public void giveMeJoke(@NonNull String joke) {
                Assert.assertNotNull(joke);
                Assert.assertTrue(!joke.isEmpty());
            }
        });
        asyncTaskTest.execute();
    }
}
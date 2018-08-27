package com.service.servicewithbinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Random;

public class ServiceUsingBinder extends Service {

    private final LocalBinder binder = new LocalBinder();
    private final Random random = new Random();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     * Binder to bind with client
     */
    class LocalBinder extends Binder {

        /**
         * Get Service
         *
         * @return ServiceUsingBinder
         */
        ServiceUsingBinder getService() {
            return ServiceUsingBinder.this;
        }
    }

    /**
     * Method for client
     * @return int
     */
    public int getRandomNumber() {
        return random.nextInt(100);
    }
}

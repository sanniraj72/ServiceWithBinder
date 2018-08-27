package com.service.servicewithbinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ServiceUsingBinder serviceUsingBinder;
    private boolean isBound = false;

    private TextView textView;

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int num = serviceUsingBinder.getRandomNumber();
            textView.setText(String.valueOf(num));
            handler.postDelayed(this, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ServiceUsingBinder.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        handler.removeCallbacks(runnable);
        isBound = false;
    }

    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                if (isBound) {
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, 2000);
                }
                break;
            case R.id.stop:
                handler.removeCallbacks(runnable);
                break;
            default:
                break;
        }
    }

    /**
     * Connection to bind with service
     */
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ServiceUsingBinder.LocalBinder localBinder = (ServiceUsingBinder.LocalBinder) iBinder;
            serviceUsingBinder = localBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}

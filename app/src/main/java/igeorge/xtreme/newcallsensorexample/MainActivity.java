package igeorge.xtreme.newcallsensorexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver telephonyReceiver;
    TextView tvHello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        callBroadCastReceiverToCheck();
        final Context context = this;
        tvHello = (TextView) findViewById(R.id.tvHelo);
        tvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TelephonyManager tlm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                if(tlm.getCallState() == TelephonyManager.CALL_STATE_RINGING){
                    Toast.makeText(context, "Ringing", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initTelephonyReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        //final CordovaInterface mycordova = this.cordova;
        this.telephonyReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                // If state has changed
                if ((intent != null) && intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
                    if (intent.hasExtra(TelephonyManager.EXTRA_STATE)) {
                        String extraData = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
                        if (extraData.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                            Log.i("TAG", "Telephone RINGING");
                            tvHello.setText("telephone ringing");
                        }
                        else if (extraData.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                            Log.i("TAG", "Telephone OFFHOOK");
                            tvHello.setText("telephone offhook");
                        }
                        else if (extraData.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                            Log.i("TAG", "Telephone IDLE");
                            tvHello.setText("telephone idle");
                        }
                    }
                }
            }
        };

        // Register the receiver
        registerReceiver(this.telephonyReceiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTelephonyReceiver();
//        callBroadCastReceiverToCheck();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(telephonyReceiver);
    }

    private void callBroadCastReceiverToCheck() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        registerReceiver(new CallReceiver(), intentFilter);
    }

}

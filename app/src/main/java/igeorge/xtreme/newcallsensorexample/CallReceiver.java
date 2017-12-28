package igeorge.xtreme.newcallsensorexample;

import android.content.Context;
import android.content.Intent;

import java.util.Date;

/**
 * Created by iGeorge on 28/12/17.
 */

public class CallReceiver extends PhoneCallReceiver {

    @Override
    protected void onIncomingCallStarted(Context context, String number, Date start) {
        super.onIncomingCallStarted(context, number, start);
//        Intent intent = new Intent(context, SensorIntentService.class);
//        context.startService(intent);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        super.onOutgoingCallStarted(ctx, number, start);
    }

    @Override
    protected void onIncomingCallEnded(Context ctx, String number, Date start, Date end) {
        super.onIncomingCallEnded(ctx, number, start, end);
    }

    @Override
    protected void onOutgoingCallEnded(Context ctx, String number, Date start, Date end) {
        super.onOutgoingCallEnded(ctx, number, start, end);
    }

    @Override
    protected void onMissedCall(Context ctx, String number, Date start) {
        super.onMissedCall(ctx, number, start);
    }
}

package hr.mfilipovic.inappnotiflayout.topnotification;

import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by marko on 31/12/16.
 */
public class TopNotificationListener implements ButtonsClickListener {

    @Override
    public void onOkClick() {
        Log.i(TAG, "onOkClick: override this method and remove call to super");
    }

    @Override
    public void onCancelClick() {
        Log.i(TAG, "onCancelClick: override this method and remove call to super");
    }

}

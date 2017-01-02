package hr.mfilipovic.inappnotiflayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Locale;

import hr.mfilipovic.inappnotiflayout.topnotification.TopNotification;
import hr.mfilipovic.inappnotiflayout.topnotification.TopNotificationListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int i = 0;

    public void showView(View view) {
        TopNotification.Builder builder3 = new TopNotification.Builder();
        builder3.withContext(this)
                .text(String.format(Locale.getDefault(), "%s%d", "this is notification ", i++))
                .buttons(TopNotification.BUTTONS_OK_CANCEL)
                .listener(new TopNotificationListener() {
                    @Override
                    public void onOkClick() {

                    }

                    @Override
                    public void onCancelClick() {

                    }
                })
                .show();
    }

}

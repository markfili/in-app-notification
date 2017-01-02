package hr.mfilipovic.inappnotiflayout.topnotification;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import hr.mfilipovic.inappnotiflayout.R;

/**
 * Created by marko on 30/12/16.
 */

public class TopNotification {

    public static final int STYLE_WHITE = 1;
    public static final int STYLE_BLACK = 2;

    public static final int BUTTONS_OK = 1;
    public static final int BUTTONS_OK_CANCEL = 2;

    public static class Builder {
        private Context context;

        private int buttons;
        private int background;

        private String text;
        private TopNotificationListener listener;

        static final Handler HANDLER = new Handler(Looper.getMainLooper());

        public Builder withContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder background(int background) {
            this.background = background;
            return this;
        }

        public Builder buttons(int buttons) {
            this.buttons = buttons;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder listener(TopNotificationListener listener) {
            this.listener = listener;
            return this;
        }

        public void show() {
            if (buttons != 0) {
                if (listener == null) {
                    throw new IllegalArgumentException("click listener not set, use listener() method and TopNotificationListener (or TopNotificationListenerImpl if both methods are not needed)");
                }
            }
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final LinearLayout notificationContainer = (LinearLayout) ((AppCompatActivity) context).findViewById(R.id.notification_linear_layout);
            final View notification = inflater.inflate(R.layout.notification_layout, notificationContainer, false);

            int backgroundColor;
            int textColor;
            switch (background) {
                case STYLE_WHITE:
                    backgroundColor = android.R.color.white;
                    textColor = android.R.color.black;
                    break;
                case STYLE_BLACK:
                    backgroundColor = android.R.color.black;
                    textColor = android.R.color.white;
                    break;
                default:
                    textColor = android.R.color.black;
                    backgroundColor = android.R.color.white;
                    break;
            }
            notification.setBackgroundColor(context.getResources().getColor(backgroundColor));
            TextView notificationText = (TextView) notification.findViewById(R.id.notification_text);
            notificationText.setText(text);
            notificationText.setTextColor(context.getResources().getColor(textColor));

            switch (buttons) {
                case BUTTONS_OK_CANCEL:
                    Button cancel = ((Button) notification.findViewById(R.id.notification_button_cancel));
                    cancel.setVisibility(View.VISIBLE);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onCancelClick();
                            notificationContainer.removeView(notification);
                        }
                    });
                case BUTTONS_OK:
                    Button ok = ((Button) notification.findViewById(R.id.notification_button_ok));
                    ok.setVisibility(View.VISIBLE);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onOkClick();
                            notificationContainer.removeView(notification);
                        }
                    });
                    break;
                default:
                    HANDLER.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            notificationContainer.removeView(notification);
                        }
                    }, 4000);
                    break;
            }

            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_in_top);

            notification.startAnimation(slideDown);
            notificationContainer.addView(notification);
        }
    }
}

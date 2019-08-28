package com.ashwin.android.slidingcarousel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "my_channel";
    private static final String CHANNEL_NAME = "My Channel";
    private static final String CHANNEL_DESCRIPTION = "This is a default channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void notify(View view) {
        createNotificationChannel();

        final int icon = R.mipmap.ic_launcher;
        final String title = "Sliding Carousel";
        final String text = "Expand this notification to view the carousel";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(icon);
        builder.setContentTitle(title);
        builder.setContentText(text);

        RemoteViews bigView = new RemoteViews(this.getPackageName(), R.layout.notification_layout);
        bigView.setImageViewResource(R.id.notification_icon, icon);
        bigView.setTextViewText(R.id.notification_title, title);
        bigView.setTextViewText(R.id.notification_text, text);

        builder.setStyle(new NotificationCompat.DecoratedCustomViewStyle());
        builder.setCustomBigContentView(bigView);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1024, builder.build());
    }
}

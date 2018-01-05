package com.ferganade.android_intech;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toasted = findViewById(R.id.clicker);
        toasted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, MainActivity.class.getSimpleName(), Toast.LENGTH_SHORT).show();
            }
        });

        Button dialog = findViewById(R.id.dialog);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Do you wish to continue")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, android.R.string.ok, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, android.R.string.cancel, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        Button notification = findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder Builder = new NotificationCompat.Builder(MainActivity.this)
                    .setContentTitle("New notification")
                    .setContentText("This is an example of notification")
                    .setSmallIcon(android.R.drawable.ic_dialog_info);

                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(001, Builder.build());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
            case R.id.home :
                Intent i1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i1);
                break;
            case R.id.toast:
                Intent i2 = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i2);
                break;
            case R.id.map:
                Intent i3 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londres"));
                startActivity(i3);
                break;
        }
        return(super.onOptionsItemSelected(item));
    }
}

package com.ferganade.android_intech;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final RecyclerView recycle = findViewById(R.id.recyclerList);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(new MyAdapter());

        Intent serviceIntent = new Intent(SecondActivity.this, GetBeersServices.class);
        serviceIntent.setAction("getAllBeers");
        startService(serviceIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.home:
            Intent i1 = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(i1);
            break;
        case R.id.toast:
            Intent i2 = new Intent(SecondActivity.this, SecondActivity.class);
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

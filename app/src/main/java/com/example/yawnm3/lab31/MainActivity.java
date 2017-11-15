package com.example.yawnm3.lab31;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMessage;
    private float max = 48, min = 8;
    private float currentSize;
    private float defaultSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        textViewMessage = (TextView)findViewById(R.id.textViewMessage);
        currentSize = textViewMessage.getTextSize() / getScreenDensity();
        defaultSize = currentSize;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_increase)
        {
            increaseSize();
        }

        if (id == R.id.action_decrease)
        {
            decreaseSize();
        }

        if (id == R.id.action_default)
        {
            defaultSize();
        }

        if (id == R.id.action_about)
        {
            setContentView(R.layout.about);
            WebView webViewAbout = (WebView)findViewById(R.id.webViewAbout);
            webViewAbout.loadUrl("https://www.google.com");
        }

        return super.onOptionsItemSelected(item);
    }

    public void increaseSize()
    {
        currentSize += 1;
        if(currentSize > max)
        {
            Toast.makeText(getApplicationContext(),"This is the max size.", Toast.LENGTH_SHORT).show();
            currentSize = max;
        }
        textViewMessage.setTextSize(currentSize);
    }

    public void decreaseSize()
    {
        currentSize -= 1;
        if(currentSize < min)
        {
            Toast.makeText(getApplicationContext(),"This is the min size.", Toast.LENGTH_SHORT).show();
            currentSize = min;
        }
        textViewMessage.setTextSize(currentSize);
    }

    public void defaultSize()
    {
        currentSize = defaultSize;
        textViewMessage.setTextSize(defaultSize);
    }

    public float getScreenDensity()
    {
        float sizeDensity = 0;

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        sizeDensity = metrics.density;

        return sizeDensity;
    }
}

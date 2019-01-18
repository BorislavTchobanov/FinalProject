package com.wheelandtire.bo.jokedisplaylib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class JokeMainActivity extends AppCompatActivity {
    public static final String EXTRA_JOKE = "extra_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.joke_activity_main);

        if (!getIntent().hasExtra(EXTRA_JOKE))
            finish();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String joke = getIntent().getStringExtra(EXTRA_JOKE);
        final TextView jokeTv = findViewById(R.id.joke_tv);
        jokeTv.setText(joke);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

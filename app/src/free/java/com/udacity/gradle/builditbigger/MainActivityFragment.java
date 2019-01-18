package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.wheelandtire.bo.jokedisplaylib.JokeMainActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final int REQUEST_CODE = 100;
    private Button jokeButton;
    private TextView textView;
    private ProgressBar spinner;
    private InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        jokeButton = root.findViewById(R.id.joke_button);
        textView = root.findViewById(R.id.instructions_text_view);
        spinner = root.findViewById(R.id.progress);

        jokeButton.setOnClickListener(v -> {
            final EndpointsAsyncTask task = new EndpointsAsyncTask(new MyCallback() {
                @Override
                public void prepUI() {
                    textView.setVisibility(View.GONE);
                    jokeButton.setVisibility(View.GONE);
                    spinner.setVisibility(View.VISIBLE);
                }

                @Override
                public void giveMeJoke(@NonNull String joke) {
                    initInterstitialAd(joke);
                }
            });
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        });


        return root;
    }

    private void hideProgressBar() {
        textView.setVisibility(View.VISIBLE);
        jokeButton.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.GONE);
    }

    private void startJokeActivity(String joke) {
        final Intent intent = new Intent(getContext(), JokeMainActivity.class);
        intent.putExtra(JokeMainActivity.EXTRA_JOKE, joke);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            hideProgressBar();
        }
    }

    private void initInterstitialAd(@NonNull String joke) {
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                startJokeActivity(joke);
            }

            @Override
            public void onAdClosed() {
                startJokeActivity(joke);
            }
        });
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}

package com.tuannv007.gplxb2;

import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.tuannv007.gplxb2.fragment.fragmenttest.FragmentTest;
import com.tuannv007.gplxb2.notification.AlarmReceiver;
import com.tuannv007.gplxb2.notification.NotificationScheduler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAdd();
    }

    private void initAdd() {
        MobileAds.initialize(this, getString(R.string.admod_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.adUnitId));
        AdRequest adRequest = new AdRequest.Builder()
            .build();
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                initView();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                initView();
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    private void initView() {
        addFragment(FragmentTest.newInstance(), R.id.frame_layout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void addFragment(Fragment frg, int layoutId) {
        String tag = frg.getClass().getSimpleName();
        getSupportFragmentManager().beginTransaction().replace(layoutId, frg, tag)
            .commit();
    }
}

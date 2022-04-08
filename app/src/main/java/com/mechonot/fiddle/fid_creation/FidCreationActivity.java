package com.mechonot.fiddle.fid_creation;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.mechonot.fiddle.FidDbHandler;
import com.mechonot.fiddle.R;
import com.mechonot.fiddle.fid.Fid;
import com.mechonot.fiddle.fid.FidFactory;
import com.mechonot.fiddle.fid.FidType;
import com.mechonot.fiddle.scrolling.FidScrollingActivity;

public class FidCreationActivity extends FragmentActivity {
    Fid fid;
    int stepNumber = 0;

    FragmentManager fragmentManager;
    FidCreationTextInputFragment textInputFragment;
    FidCreationDurationFragment durationFragment;
    FidCreationTypeFragment typeFragment;
    FidCreationPriorityFragment priorityFragment;
    FidCreationEndFragment endFragment;
    FidDbHandler fidManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fid_creation);
        fidManager = new FidDbHandler(this);
        fragmentManager = getSupportFragmentManager();
        fid = FidFactory.createEmptyFid();
        Intent intent = getIntent();
        if (Intent.ACTION_SEND.equals(intent.getAction())) {
            String url = intent.getStringExtra(Intent.EXTRA_TEXT);
            fid.setBody(url);
            if (url.contains("youtube") || url.contains("youtu.be")) {
                fid.setDescription("Watch that video");
                fid.setFidType(FidType.Video);
            }
            else {
                fid.setDescription("Check out that link");
                fid.setFidType(FidType.Article);
            }
        }
        startPickingType();
    }

    private void startPickingType() {
        typeFragment = FidCreationTypeFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, typeFragment)
                .addToBackStack(null)
                .commit();
    }

    private void startEnteringText() {
        textInputFragment = FidCreationTextInputFragment.newInstance(fid.getDescription(), fid.getBody());
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, textInputFragment)
                .addToBackStack(null)
                .commit();
    }

    private void startPickingDuration() {
        durationFragment = FidCreationDurationFragment.newInstance(fid.getDuration());
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, durationFragment)
                .addToBackStack(null)
                .commit();
    }

    private void startPickingPriorities() {
        priorityFragment = FidCreationPriorityFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, priorityFragment)
                .addToBackStack(null)
                .commit();
    }

    private void finishEnteringText() {
        fid.setDescription(textInputFragment.getDescription());
        fid.setBody(textInputFragment.getBody());
    }

    private void finishPickingDuration() {
        fid.setDuration(durationFragment.getDuration());
    }


    private void finishCreation() {
        endFragment = FidCreationEndFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, endFragment)
                .addToBackStack(null)
                .commit();
        new Handler().postDelayed(() -> {
            //This method will be executed once the timer is over
            // Start your app main activity
            Intent i = new Intent(FidCreationActivity.this, FidScrollingActivity.class);
            startActivity(i);
            // close this activity
            finish();
        }, 1000);
        fidManager.addNewFid(fid);
    }

    public void nextStep(View view) {
        switch (++stepNumber) {
            case 1: {
                // remove category
                startEnteringText();
                break;
            }
            case 2: {
                finishEnteringText();
                startPickingDuration();
                break;
            }
            case 3: {
                finishPickingDuration();
                startPickingPriorities();
                break;
            }
            case 4: {
                finishCreation();
                break;
            }
        }

    }

    public void prevStep(View view) {
        switch (--stepNumber) {
            case 0: {
                finishEnteringText();
                startPickingType();
                break;
            }
            case 1: {
                finishPickingDuration();
                startEnteringText();
                break;
            }
            case 2: {
                startPickingDuration();
                break;
            }
        }
    }

    public void setTypeVideo(View view){
        fid.setFidType(FidType.Video);
    }

    public void setTypeArticle(View view){
        fid.setFidType(FidType.Article);
    }

    public void setTypeTask(View view){
        fid.setFidType(FidType.Task);
    }

    public void setPriority1(View view){
        fid.setPriority(1);
    }

    public void setPriority2(View view){
        fid.setPriority(2);
    }

    public void setPriority3(View view){
        fid.setPriority(3);
    }

}
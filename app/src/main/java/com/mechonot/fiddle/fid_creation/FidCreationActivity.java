package com.mechonot.fiddle.fid_creation;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.mechonot.fiddle.R;
import com.mechonot.fiddle.fid.FauxFid;
import com.mechonot.fiddle.fid.Fid;
import com.mechonot.fiddle.fid.FidType;

public class FidCreationActivity extends FragmentActivity {
    Fid fid;
    int stepNumber = 0;

    FragmentManager fragmentManager;
    FidCreationTextInputFragment textInputFragment;
    FidCreationDurationFragment durationFragment;
    FidCreationTypeFragment typeFragment;
    FidCreationPriorityFragment priorityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fid_creation);
        fragmentManager = getSupportFragmentManager();
        fid = new FauxFid();
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
                //nice
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
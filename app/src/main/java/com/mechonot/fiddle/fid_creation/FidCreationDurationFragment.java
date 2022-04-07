package com.mechonot.fiddle.fid_creation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import com.mechonot.fiddle.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FidCreationDurationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FidCreationDurationFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String DURATION = "duration";

    private NumberPicker durationPicker;

    public FidCreationDurationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @param duration @return A new instance of fragment FidCreationFragment.
     */
    public static FidCreationDurationFragment newInstance(int duration) {
        FidCreationDurationFragment fragment = new FidCreationDurationFragment();
        Bundle args = new Bundle();
        args.putInt(DURATION, duration);
        fragment.setArguments(args);
        return fragment;
    }

    public int getDuration(){
        return durationPicker.getValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fid_creation_duration, container, false);
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        durationPicker = getView().findViewById(R.id.durationPicker);
        durationPicker.setMinValue(1);
        durationPicker.setMaxValue(99);
        durationPicker.setWrapSelectorWheel(false);
        if (getArguments() != null)
            durationPicker.setValue(getArguments().getInt(DURATION));
    }
}
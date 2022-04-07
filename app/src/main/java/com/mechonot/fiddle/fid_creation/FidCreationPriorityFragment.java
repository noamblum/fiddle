package com.mechonot.fiddle.fid_creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mechonot.fiddle.R;


public class FidCreationPriorityFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public FidCreationPriorityFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment FidCreationFragment.
     */
    public static FidCreationPriorityFragment newInstance() {
        return new FidCreationPriorityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fid_creation_priority, container, false);
    }
}
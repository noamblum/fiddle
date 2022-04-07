package com.mechonot.fiddle.fidCreation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mechonot.fiddle.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FidCreationDurationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FidCreationTextInputFragment extends Fragment {

    private static final String DESCRIPTION = "description";
    private static final String BODY = "body";

    EditText fidDescription;
    EditText fidBody;

    public FidCreationTextInputFragment() {}

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @param description, body @return A new instance of fragment FidCreationFragment.
     */
    public static FidCreationTextInputFragment newInstance(String description, String body) {
        FidCreationTextInputFragment fragment = new FidCreationTextInputFragment();
        Bundle args = new Bundle();
        args.putString(DESCRIPTION, description);
        args.putString(BODY, body);
        fragment.setArguments(args);
        return fragment;
    }

    public String getBody(){
        return fidBody.getText().toString();
    }

    public String getDescription(){
        return fidDescription.getText().toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fid_creation_text_input, container, false);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        fidDescription = getView().findViewById(R.id.FidDescription);
        fidBody = getView().findViewById(R.id.FidBody);
        if (getArguments() != null) {
            fidDescription.setText(getArguments().getString(DESCRIPTION));
            fidBody.setText(getArguments().getString(BODY));
        }
    }
}
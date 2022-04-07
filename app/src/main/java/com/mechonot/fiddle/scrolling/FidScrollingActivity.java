package com.mechonot.fiddle.scrolling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mechonot.fiddle.fid.FauxFid;
import com.mechonot.fiddle.R;

import java.util.ArrayList;
import java.util.List;

public class FidScrollingActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager lManager;
    private FidAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fid_scrolling);

        RecyclerView recycler = findViewById(R.id.recycler_view);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        List<FauxFid> fidList = new ArrayList<>();
        for (int ignored = 0; ignored < 10; ignored++){
            fidList.add(new FauxFid());
        }
        adapter = new FidAdapter(fidList);
        recycler.setAdapter(adapter);
    }
}
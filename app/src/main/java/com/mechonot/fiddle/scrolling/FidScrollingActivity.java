package com.mechonot.fiddle.scrolling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mechonot.fiddle.FidCreationActivity;
import com.mechonot.fiddle.FidDbHandler;
import com.mechonot.fiddle.R;
import com.mechonot.fiddle.fid.FauxFid;
import com.mechonot.fiddle.fid.Fid;

import java.util.ArrayList;
import java.util.List;

public class FidScrollingActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager lManager;
    private FidAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fid_scrolling);
        FidDbHandler fid_manager = new FidDbHandler(this);

        RecyclerView recycler = findViewById(R.id.recycler_view);

        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        List<Fid> fidList = fid_manager.read_fids();
        adapter = new FidAdapter(fidList);
        recycler.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback touchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Fid fid = adapter.getItemAtPosition(viewHolder.getAdapterPosition());
                switch (direction) {
                    case ItemTouchHelper.LEFT:
                        Toast.makeText(getBaseContext(), fid.getFidType().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case ItemTouchHelper.RIGHT:
                        Toast.makeText(getBaseContext(), fid.getBody(), Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                adapter.notifyItemChanged(viewHolder.getAdapterPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recycler);
    }
    public void moveToCreateFidScreen(View view){
        Intent intent = new Intent(this, FidCreationActivity.class);
        startActivity(intent);
    }

    public void sortByPriority(View view){
        adapter.sort_by("priority");
        adapter.notifyDataSetChanged();

    }

    public void chooseCategories(View view){
        Intent intent = new Intent(this, FidCreationActivity.class);
        startActivity(intent);
        adapter.notifyDataSetChanged();
    }

    public void sortByDuration(View view){
        adapter.sort_by("duration");
        adapter.notifyDataSetChanged();
    }
}
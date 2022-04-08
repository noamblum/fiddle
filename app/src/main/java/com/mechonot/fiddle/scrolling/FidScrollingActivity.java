package com.mechonot.fiddle.scrolling;

import com.mechonot.fiddle.fid.Fid;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mechonot.fiddle.fid_creation.FidCreationActivity;
import com.mechonot.fiddle.FidDbHandler;
import com.mechonot.fiddle.R;
import com.mechonot.fiddle.fid.Fid;
import com.mechonot.fiddle.fid.FidFactory;
import com.mechonot.fiddle.viewFidActivity;

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
        adapter = new FidAdapter(fidList, new OnFidClickListener() {
            @Override
            public void onItemClick(View view, Fid fid) {
                Toast.makeText(getBaseContext(), fid.getDescription(), Toast.LENGTH_LONG).show();
            }
        });
        recycler.setAdapter(adapter);


        ItemTouchHelper.SimpleCallback touchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Fid fid = adapter.getItemAtPosition(position);
                switch (direction) {
                    case ItemTouchHelper.RIGHT:
                        if (fid.done() == null)
                            fid_manager.markFidDone(fid.getId());
                        adapter.removeItemAtPosition(position);
                        break;
                    default:
                        break;
                }
                recycler.scrollToPosition(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recycler);
    }
    public void moveToCreateFidScreen(View view){
        Intent intent = new Intent(this, FidCreationActivity.class);
        startActivity(intent);
    }
    public void moveToRandomFid(View view){
        Intent intent = new Intent(this, viewFidActivity.class);
        startActivity(intent);
    }
    public void onButtonShowPopupWindowClick(View view) {
        PopupWindow popupWindow = new PopupWindow();
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popupwindow, null);

        // create the popup window
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
//        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
//        final PopupWindow popupWindow = new PopupWindow(popupView, (int)(width *.8), (int)(height * .6), focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
    public void sortByPriority(View view){
        adapter.sort_by("priority");
        adapter.setViewMode("priority");
        adapter.notifyDataSetChanged();

    }

    public void chooseCategories(View view){
        adapter.sort_by("categoty");
        adapter.setViewMode("category");
        adapter.notifyDataSetChanged();
    }

    public void sortByDuration(View view){
        adapter.sort_by("duration");
        adapter.setViewMode("duration");
        adapter.notifyDataSetChanged();
    }
}
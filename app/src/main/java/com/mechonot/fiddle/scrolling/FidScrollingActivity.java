package com.mechonot.fiddle.scrolling;

import com.mechonot.fiddle.fid.Fid;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
import com.mechonot.fiddle.viewFidActivity;

import java.util.List;

public class FidScrollingActivity extends AppCompatActivity {
    private RecyclerView.LayoutManager lManager;
    private FidAdapter adapter;
    private View currentyDisabledButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fid_scrolling);
        FidDbHandler fid_manager = new FidDbHandler(this);

        RecyclerView recycler = findViewById(R.id.recycler_view);
        currentyDisabledButton = findViewById(R.id.catButton);
        disableButton(currentyDisabledButton);


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
                if (direction == ItemTouchHelper.RIGHT) {
                    if (fid.done() == null)
                        fid_manager.markFidDone(fid.getId());
                    adapter.removeItemAtPosition(position);
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
        handleButtonsChange(view);

    }

    private void handleButtonsChange(View view) {
        disableButton(view);
        enableButton(currentyDisabledButton);
        currentyDisabledButton = view;
    }

    public void chooseCategories(View view){
        adapter.sort_by("categoty");
        adapter.setViewMode("category");
        adapter.notifyDataSetChanged();
        handleButtonsChange(view);
    }

    public void sortByDuration(View view){
        adapter.sort_by("duration");
        adapter.setViewMode("duration");
        adapter.notifyDataSetChanged();
        handleButtonsChange(view);
    }

    private void disableButton(View view) {
        if (!(view instanceof Button)) return;
        Button button = (Button) view;
        button.setEnabled(false);
        button.setBackgroundTintList(getBaseContext().getResources().getColorStateList(R.color.button_disabled));
        button.setTextColor(Color.parseColor("#FAEBDC"));
    }

    private void enableButton(View view) {
        if (!(view instanceof Button)) return;
        Button button = (Button) view;
        button.setEnabled(true);
        button.setBackgroundTintList(getBaseContext().getResources().getColorStateList(R.color.button_enabled
        ));

        button.setTextColor(Color.parseColor("#171717"));
    }
}
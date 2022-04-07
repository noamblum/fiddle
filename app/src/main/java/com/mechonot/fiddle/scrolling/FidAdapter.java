package com.mechonot.fiddle.scrolling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mechonot.fiddle.R;
import com.mechonot.fiddle.fid.FauxFid;
import com.mechonot.fiddle.fid.Fid;

import java.util.Comparator;
import java.util.List;

public class FidAdapter extends RecyclerView.Adapter<FidAdapter.FidViewHolder> {

    private List<Fid> items;

    public FidAdapter(List<Fid> items) {
        this.items = items;
    }

    public void sort_by(String type){
        if (type == "priority"){
            items.sort(Comparator.comparingInt(Fid::getPriority));
        }
        if (type=="duration"){
            items.sort(Comparator.comparingInt(Fid::getDuration));
        }
    }

    @NonNull
    @Override
    public FidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fid_row, parent, false);
        return new FidViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FidViewHolder holder, int position) {
        Fid fid = items.get(position);

        holder.title.setText(fid.getBody());
        holder.time.setText(String.valueOf(fid.getDuration()));
        holder.description.setText(fid.getBodyType().toString());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Fid getItemAtPosition(int i) {
        return items.get(i);
    }

    public static class FidViewHolder extends RecyclerView.ViewHolder {
        // Card fields
        public TextView title;
        public TextView time;
        public TextView description;

        public FidViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            time = v.findViewById(R.id.time);
            description = v.findViewById(R.id.description);
        }


    }
}

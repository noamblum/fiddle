package com.mechonot.fiddle.scrolling;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mechonot.fiddle.R;
import com.mechonot.fiddle.fid.BodyType;
import com.mechonot.fiddle.fid.FauxFid;
import com.mechonot.fiddle.fid.Fid;
import com.mechonot.fiddle.fid.FidType;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FidAdapter extends RecyclerView.Adapter<FidAdapter.FidViewHolder> {

    private String viewMode = "category";

    private List<Fid> items;

    public FidAdapter(List<Fid> items) {
        this.items = items;
    }

    public void setViewMode(String viewMode){
        this.viewMode = viewMode;
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

        holder.setFieldsFromFid(fid, viewMode);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Fid getItemAtPosition(int i) {
        return items.get(i);
    }

    public static class FidViewHolder extends RecyclerView.ViewHolder {

        private static final Map<FidType, Integer> TYPE_IMAGE_MAP = new EnumMap<>(FidType.class);
        private static final Map<Integer, Integer> PRIORITY_IMAGE_MAP = new HashMap<>();
        static {
            TYPE_IMAGE_MAP.put(FidType.Task, R.drawable.smiley);
            TYPE_IMAGE_MAP.put(FidType.Article, R.drawable.article);
            TYPE_IMAGE_MAP.put(FidType.Video, R.drawable.video);
            PRIORITY_IMAGE_MAP.put(3, R.drawable.three_circle_pink);
            PRIORITY_IMAGE_MAP.put(2, R.drawable.two_circles_pink);
            PRIORITY_IMAGE_MAP.put(1, R.drawable.circle_pink);
        }



        // Card fields
        public TextView title;
//        public TextView body;
        public ImageView image;
        public TextView textOnImage;

        public FidViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
//            body = v.findViewById(R.id.body);
            image = v.findViewById(R.id.corner_image);
            textOnImage = v.findViewById(R.id.over_image_text);

        }

        @SuppressWarnings("ConstantConditions")
        public void setFieldsFromFid(Fid fid, String viewMode){
            title.setText(fid.getDescription());
            switch (viewMode) {
                case "category":
                    textOnImage.setText("");
                    image.setImageResource(TYPE_IMAGE_MAP.get(fid.getFidType()));
                    break;
                case "priority":
                    textOnImage.setText("");
                    image.setImageResource(PRIORITY_IMAGE_MAP.get(fid.getPriority()));
                    break;
                case "duration":
                    textOnImage.setText(String.valueOf(fid.getDuration()));
                    image.setImageResource(R.drawable.empty_circle_pink);
                    break;
            }
        }
    }
}

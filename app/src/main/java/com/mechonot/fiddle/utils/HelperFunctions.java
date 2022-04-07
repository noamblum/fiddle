package com.mechonot.fiddle.utils;

import com.mechonot.fiddle.fid.Fid;
import com.mechonot.fiddle.fid.FidFactory;

import java.util.Random;

public class HelperFunctions {
    public static String generateTask() {
        Random random = new Random();
        int rand = random.nextInt(3);
        switch (rand){
            case 0:
                return "Call Grandma";
            case 1:
                return "20 push-ups";
            case 2:
                return "update your PlayList";
            default:
                return  "Meditate";
        }
    }


public void loadRandomFid(Fid fid, int width, int height) {

    Fid tempFid = fid;
    AlertDialog.Builder imageDialog = new AlertDialog.Builder(this);
    LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);

    View layout = inflater.inflate(R.layout.custom_fullimage_dialog,
    (ViewGroup) findViewById(R.id.layout_root));
    ImageView image = (ImageView) layout.findViewById(R.id.fullimage);
    image.setImageDrawable(tempImageView.getDrawable());
    imageDialog.setView(layout);
    imageDialog.setPositiveButton(resources.getString(R.string.ok_button), new DialogInterface.OnClickListener(){

public void onClick(DialogInterface dialog, int which) {
    dialog.dismiss();
    }

    });

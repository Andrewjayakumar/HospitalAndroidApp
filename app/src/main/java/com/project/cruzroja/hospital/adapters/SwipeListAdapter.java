package com.project.cruzroja.hospital.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.adapters.ArraySwipeAdapter;
import com.project.cruzroja.hospital.CustomDialog;
import com.project.cruzroja.hospital.R;
import com.project.cruzroja.hospital.items.DashboardItem;

import java.util.ArrayList;

/**
 * Created by Fabian Choi on 5/16/2017.
 */

public class SwipeListAdapter extends ArraySwipeAdapter<DashboardItem> {
    private final Context context;
    private ArrayList<DashboardItem> objects;
    private AlertDialog.Builder alertBuilder;
    private FragmentManager fragmentManager;

    public SwipeListAdapter(Context context, ArrayList<DashboardItem> objects, FragmentManager fm) {
        super(context, -1, objects);
        System.out.println("Inside ListAdapter Constructor");
        this.context = context;
        this.objects = objects;
        this.alertBuilder = new AlertDialog.Builder(this.context);
        this.fragmentManager = fm;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the object, and the type
        // Inflate the correct view based on the type and update the parts of the layout -> set onClicks

        System.out.println("Position: " + position);
        DashboardItem dashboardItem = objects.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);

        final View row;

        try {

            // Differentiate between two object types
            if (dashboardItem.getType().equals("Value")) {
                System.out.println("Adding Value to List");
                row = inflater.inflate(R.layout.list_item_value, parent, false);

                System.out.println("Grabbing Elements from Row");
                // Grab the elements of the Value ListItem
                TextView text = (TextView) row.findViewById(R.id.valueTextView);
                TextView value = (TextView) row.findViewById(R.id.valueData);

                System.out.println("Setting Elements in Row");
                // Set the elements of the ListItem
                text.setText(dashboardItem.getTitle());
                value.setText(dashboardItem.getValue());

                System.out.println("Setting row onClick Listener");
                row.findViewById(R.id.valueData).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Value ListItem onClick");

//                        // Set the title to the title of the ListItem
//                        alertBuilder.setTitle(((TextView) v.findViewById(R.id.valueTextView)).getText());
//                        alertBuilder.setMessage("How many units are available?");
//
//                        // Create the EditText
//                        final EditText valueText = new EditText(context);
//
//                        // Create the EditText LayoutParams
//                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
//                                (LinearLayout.LayoutParams.MATCH_PARENT,
//                                        LinearLayout.LayoutParams.MATCH_PARENT);
//                        valueText.setLayoutParams(params);
//
//                        // Check for a value already there
//                        if ( !((TextView)v.findViewById(R.id.valueData)).getText().equals("")) {
//                            //Set the value of the editText to the current value of the Data
//                            //Set the default value to the data value stored in the ListItem
//                            valueText.setText(((TextView) v.findViewById(R.id.valueData)).getText());
//                        }
//
//                        AlertDialog alert = alertBuilder.create();
//
//                        // Add the EditText to the AlertDialog
//                        //                    alert.setView(valueText);
//
//                        alert.show();

                        String title = ((TextView) row.findViewById(R.id.valueTextView)).getText().toString();
                        String message = "How many units are available?";
                        String type = "Value";
                        String data = ((TextView) row.findViewById(R.id.valueData)).getText().toString();

                        CustomDialog cd = CustomDialog.newInstance(title, message, type, data);
                        cd.show(fragmentManager, "value_dialog");
                        System.out.println("After Show----------------");

                        // Update to the new text value
//                        ((TextView) v.findViewById(R.id.valueData)).setText(cd.getUpdatedData());

                    }
                });

            } else if (dashboardItem.getType().equals("Toggle")) {
                System.out.println("Adding Toggle to List");
                row = inflater.inflate(R.layout.list_item_toggle, parent, false);

                // Grab the elements of the Toggle ListItem
                TextView text = (TextView) row.findViewById(R.id.toggleTextView);
                ImageView image = (ImageView) row.findViewById(R.id.toggleImage);

                // Set the elements of the ListItem
                text.setText(dashboardItem.getTitle());
                row.findViewById(R.id.toggleImage).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Toggle ListItem onClick");
//                        // Set the title to the title of the ListItem
//                        alertBuilder.setTitle(((TextView) v.findViewById(R.id.toggleTextView)).getText());
//                        alertBuilder.setMessage("Is this item available?");
//                        alertBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                System.out.println("Yes Button Clicked");
//                                dialog.dismiss();
//                            }
//                        });
//
//                        alertBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                System.out.println("No Button Clicked");
//                                dialog.dismiss();
//                            }
//                        });
//
//                        AlertDialog alert = alertBuilder.create();
//                        alert.show();



                        String title = ((TextView) row.findViewById(R.id.toggleTextView)).getText().toString();
                        String message = "Is this resource available?";
                        String type = "Toggle";
                        String data = "Yes";

                        CustomDialog cd = CustomDialog.newInstance(title, message, type, data);
                        cd.show(fragmentManager, "toggle_dialog");

                    }
                });


                image.setImageResource(R.drawable.apple);
            } else {
                return new View(context);
            }

        } catch (Exception e) {
            System.out.println("Caught an Exception");
            return new View(context);
        }

        return row;
    }  // end getView

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return 0;
    }
}
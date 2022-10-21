package com.example.projekt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private Map<String,List<String>> laboratoriumCollection;
    private List<String> labList;
    public MyExpandableListAdapter(Context context,List<String>labList, Map<String,List<String>>laboratoriumCollection ){
        this.context = context;
        this.laboratoriumCollection = laboratoriumCollection;
        this.labList = labList;

    }

    @Override
    public int getGroupCount() {
        return laboratoriumCollection.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return laboratoriumCollection.get(labList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return labList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return laboratoriumCollection.get(labList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
       String Labname = labList.get(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.group_item, null);
        }
        TextView item = convertView.findViewById(R.id.Laboratorium);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(Labname);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String model = laboratoriumCollection.get(labList.get(groupPosition)).get(childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_item, null);
        }
        TextView item = convertView.findViewById(R.id.number);
        ImageView open = convertView.findViewById(R.id.open);
        item.setText(model);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LabDetails.class);
                intent.putExtra("selectedExample",model);
                intent.putExtra("selectedLab",labList.get(groupPosition));//labList.get(childPosition)
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

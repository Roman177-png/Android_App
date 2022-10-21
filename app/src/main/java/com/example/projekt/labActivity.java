package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class labActivity extends AppCompatActivity {

    List<String> labList;
    List<String> childList;
    Map<String,List<String>> laboratoriumCollection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab);
        createGroupList();
        createCollection();
        ImageView open = findViewById(R.id.open);
        final ImageView backBtn = findViewById(R.id.backBtn);

        expandableListView = findViewById(R.id.Lab);
        expandableListAdapter = new MyExpandableListAdapter(this, labList, laboratoriumCollection);
        expandableListView.setAdapter(expandableListAdapter);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(labActivity.this,MainActivity.class));
                finish();
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition != -1 && i != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selected = expandableListAdapter.getChild(groupPosition,childPosition).toString();
                Toast.makeText(getApplicationContext(),"Wybrano: " + selected, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void createCollection() {
        String[] lab1Example = {"Example 1","Example 2","Example 3"};
        String[] lab2Example = {"Example 1","Example 2","Example 3","Example 4"};
        String[] lab3Example = {"Example 1","Example 2","Example 3","Example 4","Example 5"};
        String[] lab4Example = {"Example 1","Example 2","Example 3","Example 4"};
        String[] lab5Example = {"Example 1","Example 2","Example 3","Example 4"};
        String[] lab6Example = {"Example 1","Example 2","Example 3","Example 4"};
        laboratoriumCollection = new HashMap<String,List<String>>();
        for(String group: labList){
            if (group.equals("Laboratorium1")){
                loadChild(lab1Example);
            } else if (group.equals("Laboratorium2"))
                loadChild(lab2Example);
            else if (group.equals("Laboratorium3"))
                loadChild(lab3Example);
            else if (group.equals("Laboratorium4"))
                loadChild(lab4Example);
            else if (group.equals("Laboratorium5"))
                loadChild(lab5Example);
            else
                loadChild(lab6Example);
            laboratoriumCollection.put(group,childList);
        }
    }

    private void loadChild(String[] laboratoriumExample) {
        childList = new ArrayList<>();
        for (String model : laboratoriumExample){
            childList.add(model);
        }

    }

    private void createGroupList() {
        labList = new ArrayList<>();
        labList.add("Laboratorium1");
        labList.add("Laboratorium2");
        labList.add("Laboratorium3");
        labList.add("Laboratorium4");
        labList.add("Laboratorium5");
        labList.add("Laboratorium6");
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(labActivity.this,MainActivity.class));
        finish();
    }
}
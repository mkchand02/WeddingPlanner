package com.example.myapplication.adapters;
/*
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    List<ExpandableListItemModel> expandableList;
    Context context;

    public ExpandableListAdapter(List<ExpandableListItemModel> expandableList, Context context) {
        this.expandableList = expandableList;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return expandableList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandableList.get(groupPosition).getChildItems().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return expandableList.get(groupPosition).getParentTitle();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(expandableList.get(groupPosition).getChildItems() == null){
            return null;
        }
        return expandableList.get(groupPosition).getChildItems().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        if(expandableList.get(groupPosition).getChildItems() == null){
            return -1;
        }
        return Integer.parseInt(groupPosition+""+childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_header, null);
        }

        ImageView icon = convertView.findViewById(R.id.iv_headicon);
        TextView headText = convertView.findViewById(R.id.tv_headtitle);
        ImageView expandContract = convertView.findViewById(R.id.iv_expand);

        ExpandableListItemModel expandableListItem = expandableList.get(groupPosition);
        icon.setImageResource(expandableListItem.getIcon());
        headText.setText(expandableListItem.getParentTitle());


        if(expandableListItem.getChildItems().size() == 0){
            expandContract.setVisibility(View.INVISIBLE);
        }else{
            expandContract.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_child, null);
        }

        TextView expandableListChildText = convertView.findViewById(R.id.tv_expandablelist_child);
        expandableListChildText.setText(expandableList.get(groupPosition).getChildItems().get(childPosition));


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
*/
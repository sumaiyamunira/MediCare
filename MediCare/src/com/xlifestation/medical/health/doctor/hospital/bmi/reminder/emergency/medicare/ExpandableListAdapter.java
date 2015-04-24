package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import java.util.HashMap;
import java.util.List; 
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
 
public class ExpandableListAdapter extends BaseExpandableListAdapter {
 
    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Reminder>> _listDataChild;
 
    public ExpandableListAdapter(Context context, List<String> listDataHeader,
            HashMap<String, List<Reminder>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }
 
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
 
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
 
    @Override
    public View getChildView(int groupPosition, final int childPosition,
            boolean isLastChild, View convertView, ViewGroup parent) {
 
        final Reminder child = (Reminder) getChild(groupPosition, childPosition);
 
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child_item_reminder, null);
        }
 
        TextView txtListChild_name = (TextView) convertView.findViewById(R.id.textView1_ReminderDetails);
        txtListChild_name.setText(child.getName());
        
        TextView txtListChild_stDate = (TextView) convertView.findViewById(R.id.textView_startDate);
        
        
        TextView txtListChild_endDate = (TextView) convertView.findViewById(R.id.textView_endDate);
        
        
        TextView txtListChild_Details = (TextView) convertView.findViewById(R.id.textView_doesPerDay_or_time);
        
        
        if(child.getReminderType()==0)
        {  
        	txtListChild_Details.setText("Serial No: "+child.getSerialNo());
        	txtListChild_stDate.setText("Date\n"+child.getStartDate());
        	txtListChild_endDate.setText("Time\n"+ child.getTime());
        }else
        {
        	txtListChild_Details.setText("Does Per Day: "+ child.getDoesPerDay());
        	txtListChild_stDate.setText("Start Date\n"+child.getStartDate());
        	txtListChild_endDate.setText("End Date\n"+child.getEndDate());
        }
        
        return convertView;
    }
 
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }
 
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }
 
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }
 
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
 
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
            View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_reminder_group, null);
        }
 
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
 
        return convertView;
    }
 
    @Override
    public boolean hasStableIds() {
        return false;
    }
 
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
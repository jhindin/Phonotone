package com.jhindin.phonotone.instruments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.jhindin.phonotone.R;

public class InstrumentsAdapter extends BaseExpandableListAdapter {
    Context context;
    Family families[];
    LayoutInflater lInf;

    public InstrumentsAdapter(Context context, Family families[]) {
        this.context = context;
        this.families = families;

        lInf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return families.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return families[groupPosition].instruments.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return families[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return families[groupPosition].instruments[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return families[groupPosition].instruments[childPosition].pc;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {
        Family family = families[groupPosition];

        if (convertView == null) {
            convertView = lInf.inflate(R.layout.family_list_entry, parent, false);
        }

        TextView familiyNameView = (TextView) convertView.findViewById(R.id.family_name);
        familiyNameView.setText(family.name);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Instrument instrument = families[groupPosition].instruments[childPosition];

        if (convertView == null) {
            convertView = lInf.inflate(R.layout.instrument_list_entry, parent, false);
        }

        TextView instrumentNameView = (TextView) convertView.findViewById(R.id.instrument_name);
        instrumentNameView.setText(instrument.name);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

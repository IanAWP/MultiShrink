package io.github.ianawp.multishrink.common;


        import java.util.HashMap;
        import java.util.List;

        import android.content.Context;
        import android.graphics.Typeface;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseExpandableListAdapter;
        import android.widget.TextView;

        import io.github.ianawp.multishrink.R;
        import io.github.ianawp.multishrink.compress.Job;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<Job> jobs; // header titles


    public ExpandableListAdapter(Context context,List<Job> jobs) {
        this._context = context;
        this.jobs=jobs;

    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return jobs.get(groupPosition).getJobList().get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.job_content, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.jobs.get(groupPosition).getJobList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.jobs.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.jobs.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = jobs.get(groupPosition).getKey();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.job_header, null);
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
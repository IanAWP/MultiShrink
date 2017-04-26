package io.github.ianawp.multishrink.common;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by IanAWP on 25/04/2017.
 */

public class GridImageAdapter extends BaseAdapter {
String TAG = "GridImageAdapter";
    private final Context mContext;

    public List<Uri> getUriList() {
        return uriList;
    }

    private List<Uri> uriList;

    public GridImageAdapter(Context context, List<Uri> uris)
    {
        this.mContext=context;
        this.setUriList(uris);
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView mImageView;

        if (convertView == null) {
            mImageView = new ImageView(mContext);
            mImageView.setLayoutParams(new GridView.LayoutParams(130, 130));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(16, 16, 16, 16);
        } else {
            Log.d(TAG, "Recycling view");
            mImageView = (ImageView) convertView;
        }
        mImageView.setImageURI(getUriList().get(position));
        return mImageView;
    }


    public void setUriList(List<Uri> uriList) {
        this.uriList = uriList;
    }
}

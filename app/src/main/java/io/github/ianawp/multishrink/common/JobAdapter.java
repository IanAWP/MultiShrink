package io.github.ianawp.multishrink.common;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.github.ianawp.multishrink.R;
import io.github.ianawp.multishrink.store.Job;
import io.github.ianawp.multishrink.store.JobManager;

/**
 * Created by IanAWP on 2/05/2017.
 */

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private final JobManager jobManager;
    private final Activity context;

    private final String countTemplate;
    private final String countTemplateSingle;
    private final int dimension;

    final ForegroundColorSpan txtSpan;
    final ForegroundColorSpan fvSpan;

    @Inject
    public JobAdapter(JobManager jobmanager, Activity ctx){

        this.jobManager=jobmanager;
        this.context = ctx;

        countTemplate = context.getString(R.string.caption_job_size_template);
        countTemplateSingle = context.getString(R.string.caption_job_size_template_single);
        txtSpan = new ForegroundColorSpan(ctx.getResources().getColor(R.color.jvText));
        fvSpan = new ForegroundColorSpan(ctx.getResources().getColor(R.color.jvFlavour));

        Point size = new Point();
        ctx.getWindowManager().getDefaultDisplay().getSize(size);

        int  x = size.x;
        dimension =x/2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_view, parent, false);

        itemView.setMinimumHeight(dimension);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final JobAdapter.ViewHolder vh = holder;
        final Job job = jobManager.getJobs().get(position);
        Date date = job.getJobDescription().getTimeStamp();
        SpannableString s = getColorText(date);

        vh.txtTimeStamp.setText(s);

        int size = job.getAllImages().size();
        if(size==1){
            vh.txtNumImages.setText(String.format(countTemplateSingle, size));
        }
        else{
            vh.txtNumImages.setText(String.format(countTemplate, size));
        }

        if(size>0 && job.getAllImages().get(0).getIsComplete()){
            //"http://i.imgur.com/DvpvklR.png")//
            Picasso.with(context).load(job.getAllImages().get(0).getTargetUri())
                    .resize(dimension, dimension)
                    .centerCrop()
                    .into(vh.ivThumb);
            vh.txtTimeStamp.setText(job.getAllImages().get(0).getImageName());


        }
        vh.btnReDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jobManager.RemoveJob(job);
                notifyItemRemoved(vh.getAdapterPosition());
            }
        });

    }

    private SpannableString getColorText(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("d-MMM-yy");
        SimpleDateFormat tf = new SimpleDateFormat("h:mm a");
        String s = df.format(date).toUpperCase();
        String g = tf.format(date);




        SpannableString ss = new SpannableString(s+"  " + g);
        ss.setSpan(txtSpan, 0, s.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(fvSpan, s.length(),ss.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

       return ss;
    }


    @Override
    public int getItemCount() {
        return jobManager.getJobs().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTimeStamp;
        public TextView txtNumImages;
        public ImageView ivThumb;
        public ImageButton btnReDo;

        public ViewHolder(View view){
            super(view);
            txtNumImages = (TextView) view.findViewById(R.id.txtImageCount);
            txtTimeStamp= (TextView) view.findViewById(R.id.txtTimeStamp);
            ivThumb  = (ImageView) view.findViewById(R.id.ivThumb);
            btnReDo=(ImageButton) view.findViewById(R.id.btnReDo);
        }

    }
}

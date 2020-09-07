package com.ose4g.gadsproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LearnersListAdaptor extends RecyclerView.Adapter<LearnersListAdaptor.ViewHolder> {

    /*
    Recycler view adaptor for learners list
     */

    private Context mContext;
    private ArrayList<Learner> mLearners;
    private LayoutInflater mLayoutInflater;
    private int mHoursORScore;

    public LearnersListAdaptor(Context context, ArrayList<Learner> learners,int choose) {
        mContext = context;
        mLearners = learners;
        mLayoutInflater = LayoutInflater.from(mContext);
        mHoursORScore=choose;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //depending on the mode/id it inflates the right resource
        View view = mLayoutInflater
                .inflate((mHoursORScore==1?R.layout.learner_list_item:R.layout.iq_list_item)
                ,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnersListAdaptor.ViewHolder holder, int position) {
        Learner learner = mLearners.get(position);
//        Picasso.get().load((mHoursORScore==1?R.drawable.top_learner_icon:R.drawable.skill_iq_trimmed))
//                .into(holder.imageView);
        holder.imageView.setImageResource((mHoursORScore==1?R.drawable.top_learner_icon:R.drawable.skill_iq_trimmed));
        holder.learnerName.setText(learner.name);
        if (mHoursORScore==1)
            holder.learnerDescription.setText(learner.hours+" learning hours, "+learner.countries);

        else
            holder.learnerDescription.setText(learner.score+" skill IQ Score, "+learner.countries);
    }

    @Override
    public int getItemCount() {
        return mLearners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView learnerName;
        public TextView learnerDescription;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            learnerName = itemView.findViewById(R.id.learner_name);
            learnerDescription = itemView.findViewById(R.id.learner_description);
            imageView = itemView.findViewById(R.id.image_description);
        }
    }
}

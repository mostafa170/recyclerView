package com.kamel.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import java.util.List;

public class postsAdpater extends RecyclerView.Adapter<postsAdpater.ViewHolder> {


    private Context context;
    private List<post> posts;

    public postsAdpater(Context c, List<post> postList ){
        this.context=c;
        posts=postList;
    }

    @NonNull
    @Override
    public postsAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull postsAdpater.ViewHolder viewHolder, int i) {

        post p=posts.get(i);
        viewHolder.title.setText(p.getTitle());
        viewHolder.desc.setText(p.getDescription());

    }

    @Override
    public int getItemCount() {

        return posts.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=(TextView) itemView.findViewById(R.id.title);
            desc=(TextView) itemView.findViewById(R.id.desc);
        }
    }
}

//this is for professor Activity
package com.example.quizapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder> {
    Context context;
    String [] quiz_namelist;
    String [] quiz_sublist;
    int [] image_list;
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_name, description;
        ImageView images;
        public ViewHolder(View itemView){
            super(itemView);
            title_name = itemView.findViewById(R.id.textView3);
            description = itemView.findViewById(R.id.textView4);
            images = itemView.findViewById(R.id.imageView3);
        }
    }
    public ProgramAdapter(Context context,String [] quiz_namelist, String [] quiz_sublist, int [] image_list){
        this.context = context;
        this.quiz_namelist = quiz_namelist;
        this.quiz_sublist = quiz_sublist;
        this.image_list = image_list;
    }
    @NonNull
    @Override
    public ProgramAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.quiz_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramAdapter.ViewHolder holder, int position) {
        holder.title_name.setText(quiz_namelist[position]);
        holder.description.setText(quiz_sublist[position]);
        holder.images.setImageResource(image_list[position]);
    }

    @Override
    public int getItemCount() {
        return quiz_namelist.length;
    }


}

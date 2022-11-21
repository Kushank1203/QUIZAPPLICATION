package com.example.quizapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    Context context1;
    String [] quiz_namelist1;
    String [] quiz_sublist1;
    int [] image_list1;
    String [] start_quiz;
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title_name1, description1, starts;
        ImageView images;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_name1 = itemView.findViewById(R.id.textView5);
            description1 = itemView.findViewById(R.id.textView6);
            images = itemView.findViewById(R.id.imageView4);
            starts = itemView.findViewById(R.id.start);
        }
    }
    public MyListAdapter(Context context1,String [] quiz_namelist1, String [] quiz_sublist1, int [] image_list1,String [] start_quiz){
        this.context1 = context1;
        this.quiz_namelist1 = quiz_namelist1;
        this.quiz_sublist1 = quiz_sublist1;
        this.image_list1 = image_list1;
        this.start_quiz = start_quiz;

    }
    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater1 = LayoutInflater.from(context1);
        View view1 = inflater1.inflate(R.layout.quiz_item1,parent,false);
        ViewHolder viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title_name1.setText(quiz_namelist1[position]);
        holder.description1.setText(quiz_sublist1[position]);
        holder.images.setImageResource(image_list1[position]);
        holder.starts.setText(start_quiz[position]);
        //on click take quiz
        holder.starts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context1, "Hello "+start_quiz[position], Toast.LENGTH_SHORT).show();
                switch(position){
                    case 0:
                        Toast.makeText(context1, "hiii", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context1, "hiii1", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context1, "hiii2", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return quiz_namelist1.length;
    }
}

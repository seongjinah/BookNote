package com.example.booknote;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookIdxRVAdapter extends RecyclerView.Adapter<BookIdxRVAdapter.ViewHolder> {
    ArrayList<Book> BookList;
    OnItemClickListener mlistener;
    public BookIdxRVAdapter(ArrayList<Book> BookList) {
        this.BookList = BookList;
    }
    interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mlistener = onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.book_list_wrapper,parent,false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.title.setText(BookList.get(position).title);
        holder.author.setText(BookList.get(position).author);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.onItemClick(holder.itemView, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return BookList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title = itemView.findViewById(R.id.wrapper_title);
        TextView author = itemView.findViewById(R.id.wrapper_author);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
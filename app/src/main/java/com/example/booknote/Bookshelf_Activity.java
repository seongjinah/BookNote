package com.example.booknote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Bookshelf_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    BookIdxRVAdapter adapter;
    DatabaseReference dataRef_ing, dataRef_ed;
    ArrayList<Book> book_list_data = new ArrayList<>();

    FloatingActionButton bookshelf_fab;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookshelf);

        bookshelf_fab = findViewById(R.id.bookshelf_fab);
        bookshelf_fab.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bookshelf_Activity.this, Add_Book_Activity.class);
                startActivity(intent);
            }
        });

        dataRef_ing = FirebaseDatabase.getInstance().getReference("ing");
        dataRef_ed = FirebaseDatabase.getInstance().getReference("ed");
        recyclerView = findViewById(R.id.book_ing_index_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new BookIdxRVAdapter(book_list_data);
        adapter.setOnItemClickListener(new BookIdxRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //독서메모 띄우기
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        dataRef_ing.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                book_list_data.clear();
                for(DataSnapshot snap : snapshot.getChildren()){
                    book_list_data.add(snap.getValue(Book.class));
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

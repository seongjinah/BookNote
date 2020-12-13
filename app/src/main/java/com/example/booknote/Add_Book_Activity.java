package com.example.booknote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Add_Book_Activity extends AppCompatActivity {

    Button delete, save;
    EditText title, author;
    DatabaseReference dataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //Firebase 참조
        dataRef = FirebaseDatabase.getInstance().getReference("ing");

        delete = findViewById(R.id.addbook_delete);
        save = findViewById(R.id.addbook_save);

        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                title = findViewById(R.id.addbook_title);
                author = findViewById(R.id.addbook_author);

                dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        long size = snapshot.getChildrenCount();
                        Map<String, String> books = new HashMap<>();
                        books.put("title", title.getText().toString());
                        books.put("author", author.getText().toString());
                        dataRef.child(String.valueOf(size+1)).setValue(books);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Toast.makeText(getApplicationContext(), "책장에 "+title.getText().toString()+" 을 추가하였습니다!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

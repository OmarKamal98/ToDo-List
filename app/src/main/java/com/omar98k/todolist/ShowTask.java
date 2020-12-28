package com.omar98k.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TintInfo;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omar98k.todolist.adapters.TasksAdapter;
import com.omar98k.todolist.classes.TaskClass;

import java.util.ArrayList;

import static com.omar98k.todolist.Lists.currentNotebookId;

public class ShowTask extends AppCompatActivity {
    private static DatabaseReference mDatabase;
    public static ValueEventListener valueEventListener;

    //note tools
    private RecyclerView noteRecyclerView;
    static TasksAdapter noteAdapter;
    private RecyclerView.LayoutManager noteLayoutManager;
    static ArrayList<TaskClass> notes=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        //Initialize Realtime Reference.
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //recycler of notes
        noteRecyclerView = (RecyclerView) findViewById(R.id.note_recycler_view);
        noteRecyclerView.setHasFixedSize(true);
        noteLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        noteRecyclerView.setLayoutManager(noteLayoutManager);
        noteAdapter = new TasksAdapter(notes);
        noteRecyclerView.setAdapter(noteAdapter);
        //notes data
        initNoteData();
    }

    //add note in firebase database
    public static void writeNote(TaskClass task) {
        Log.d("FIREBASE", "Writing notebook");
        String userId =FirebaseAuth.getInstance().getUid();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase.child("User").child(userId).child("Task").child(task.idOfTask).setValue(task);
        notes.add(task);

    }

    //get notes from the fireBase database
    public static void initNoteData() {
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("Task")
                .addValueEventListener(valueEventListener=new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        notes.clear();
                        for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                            TaskClass note = snapshot.getValue(TaskClass.class);
                            if (note.ListsId.equals(currentNotebookId)) {
                                notes.add(note);
                            }
                            else if (currentNotebookId.equals("non")) {
                                notes.add(note);
                            }
                        }
                        noteAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    public void backtask(View view) {
        startActivity(new Intent(ShowTask.this,Lists.class));
        finish();
    }

    public void OnClickCreateNewtask(View view) {
        Intent intent=new Intent(ShowTask.this,AddTask.class);
        startActivity(intent);
        finish();
    }

    public void delete(View view) {
    }
}
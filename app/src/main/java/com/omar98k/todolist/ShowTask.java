package com.omar98k.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TintInfo;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omar98k.todolist.adapters.TasksAdapter;
import com.omar98k.todolist.classes.ListClass;
import com.omar98k.todolist.classes.TaskClass;

import java.util.ArrayList;
import java.util.List;

import static com.omar98k.todolist.Lists.currentNotebookId;
import static com.omar98k.todolist.Lists.nameOfList;

public class ShowTask extends AppCompatActivity {
    private static DatabaseReference mDatabase;
    public static ValueEventListener valueEventListener;
    TextView nameListt;
    //note tools
    private RecyclerView noteRecyclerView;
    static TasksAdapter noteAdapter;
    private RecyclerView.LayoutManager noteLayoutManager;
    public static ArrayList<TaskClass> notes=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);
        nameListt=findViewById(R.id.name_List);
        nameListt.setText(nameOfList);
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


            int a=0;
            final ListClass list=new ListClass(Lists.currentNotebookId, nameOfList);
            AlertDialog alertDialog = new AlertDialog.Builder(ShowTask.this)
//set icon
                    .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                    .setTitle("Delete List")
//set message
                    .setMessage("Are you sure the List '"+nameOfList+"' will be deleted? ")
//set positive button
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            String userId = FirebaseAuth.getInstance().getUid();
                            mDatabase= FirebaseDatabase.getInstance().getReference().child("User").child(userId).child("Lists").child(list.getName());
                            mDatabase.removeValue();

                            TaskClass task=new TaskClass(Lists.currentNotebookId,ShowTask.notes.get(getIntent().getIntExtra("task position",0)).idOfTask);
                            mDatabase=FirebaseDatabase.getInstance().getReference().child("User").child(userId).child("Task").child(task.idOfTask);
                            mDatabase.removeValue();

                            Intent intent=new Intent(ShowTask.this,Lists.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(),"The List  has been deleted",Toast.LENGTH_LONG).show();
                        }
                    })
//set negative button
                    .setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();


    }
}
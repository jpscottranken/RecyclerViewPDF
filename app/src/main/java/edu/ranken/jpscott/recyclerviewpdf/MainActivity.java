package edu.ranken.jpscott.recyclerviewpdf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);

        //  Get a handle for the floating action button (Fab).
        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = mWordList.size();
                // Add a new word to the wordList.
                mWordList.addLast("+ Word " + wordListSize);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

        //  Create an adapter and supply the data to display
        mAdapter = new WordListAdapter(this, mWordList);

        //  Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        //  Give the RecyclerVIew a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < 20; ++i) {
            mWordList.add("Word " + ((i < 10) ? "0" + String.valueOf(i) : String.valueOf(i)));
        }
    }
}
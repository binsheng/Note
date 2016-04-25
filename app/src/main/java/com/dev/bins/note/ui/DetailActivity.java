package com.dev.bins.note.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dev.bins.note.R;
import com.dev.bins.note.model.Note;

import org.litepal.crud.DataSupport;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener {


    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.content)
    TextView content;
    @InjectView(R.id.detail_toolbar)
    Toolbar detailToolbar;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = getIntent().getLongExtra("id", 1);
        ButterKnife.inject(this);
        initToolbar();
        initData();
    }

    private void initData() {
        Note note = DataSupport.find(Note.class, id);
        String titles = note.getTitle();
        if (titles.equals("无标题")) {
            title.setText(String.valueOf(note.getId()));
        } else {
            title.setText(titles);
        }
        content.setText(note.getContent());

    }

    private void initToolbar() {
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        detailToolbar.setNavigationOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit:
                Intent intent = new Intent(getApplicationContext(),AddNoteActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

                break;
            case R.id.del:
                ContentValues values = new ContentValues();
                values.put("isshow",false);
                DataSupport.update(Note.class, values, id);
                finish();
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}

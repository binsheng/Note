package com.dev.bins.note.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.bins.note.R;
import com.dev.bins.note.model.Category;
import com.dev.bins.note.model.Note;

import org.litepal.crud.DataSupport;

import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AddNoteActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.et_title)
    EditText etTitle;
    @InjectView(R.id.et_content)
    EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.inject(this);
        long id = getIntent().getLongExtra("id", 0);
        if (id != 0){
            initData(id);
        }
        initToolBar();

    }

    private void initData(long id) {
        Note note = DataSupport.find(Note.class, id);
        etTitle.setText(note.getTitle());
        etContent.setText(note.getContent());
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.abandon:
                finish();
                break;
            case R.id.save:
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content)) {
                    Snackbar.make(item.getActionView(), "标题或者内容不能为空", Snackbar.LENGTH_SHORT).show();
                } else {
                    Note note = new Note();
                    note.setTitle(title);
                    note.setContent(content);
                    note.setDate(new Date());
                    note.setShow(true);
                    note.save();

                    Category category = DataSupport.find(Category.class, Category.DEFAULT);
                    category.getNotes().add(note);
                    category.save();
                    Toast.makeText(AddNoteActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                    intent.putExtra("id", note.getId());
                    startActivity(intent);
                    finish();
                }


                break;

        }

        return true;
    }
}

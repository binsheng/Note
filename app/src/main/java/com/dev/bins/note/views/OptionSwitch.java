package com.dev.bins.note.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.dev.bins.note.R;

/**
 * Created by bin on 11/24/15.
 */
public class OptionSwitch extends RelativeLayout {

    private TextView title,desc;
    private Switch mSwitch;
    private String desc_on;
    private String desc_off;

    public OptionSwitch(Context context) {
        this(context, null);
    }

    public OptionSwitch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OptionSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.option_switch, this);
        initView();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OptionSwitch, defStyleAttr, 0);
        String title = typedArray.getString(R.styleable.OptionSwitch_show_title);
        desc_off = typedArray.getString(R.styleable.OptionSwitch_desc_off);
        desc_on = typedArray.getString(R.styleable.OptionSwitch_desc_on);
        setTitle(title);
        setDesc(desc_off);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        desc = (TextView) findViewById(R.id.desc);
        mSwitch = (Switch) findViewById(R.id.sw);
    }


    public void setTitle(String text){
        title.setText(text);
    }

    public void setDesc(String text){
        desc.setText(text);
    }

    public boolean isSwitch(){
        return mSwitch.isChecked();
    }
    public void setmSwitch(boolean option){
        if (option){
            desc.setText(desc_on);
        }else {
            desc.setText(desc_off);
        }
        mSwitch.setChecked(option);
    }


}

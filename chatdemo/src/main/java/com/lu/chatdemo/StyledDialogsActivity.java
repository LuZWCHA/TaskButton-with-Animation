package com.lu.chatdemo;

import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.lu.chatdemo.ChatBaseActivity.ChatDialogActivity;
import com.lu.chatdemo.Model.Dialog;
import com.lu.chatdemo.fixtures.DialogsFixtures;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import com.stfalcon.chatkit.utils.DateFormatter;

import java.util.Date;

public class StyledDialogsActivity extends ChatDialogActivity
        implements DateFormatter.Formatter {

    private DialogsList dialogsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styled_dialogs);

        dialogsList = (DialogsList) findViewById(R.id.dialogList);
        initAdapter();
    }

    @Override
    public void onDialogClick(Dialog dialog) {
        StyledMessagesActivity.open(this);
    }


    @Override
    public String format(Date date) {
        if (DateFormatter.isToday(date)) {
            return DateFormatter.format(date, DateFormatter.Template.TIME);
        } else if (DateFormatter.isYesterday(date)) {
            return getString(R.string.date_header_yesterday);
        } else if (DateFormatter.isCurrentYear(date)) {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH);
        } else {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH_YEAR);
        }
    }

    private void initAdapter(){
        try {
            super.dialogsAdapter = new DialogsListAdapter<>(super.imageLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.dialogsAdapter.setItems(DialogsFixtures.getDialogs());

        super.dialogsAdapter.setOnDialogClickListener(this);
        super.dialogsAdapter.setOnDialogLongClickListener(this);
        super.dialogsAdapter.setDatesFormatter(this);

        dialogsList.setAdapter(super.dialogsAdapter);
    }
}

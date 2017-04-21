package com.lu.indexpagedemo.view.simpleviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.ChatDialogActivity;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.Dialog;
import com.lu.indexpagedemo.view.simpleviews.fixtures.DialogsFixtures;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


//聊天功能过于庞大,这里仅提供静态界面,无数据交互暂时放于simple_view
public class DailogActivity extends ChatDialogActivity implements DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog> {

    private Toolbar mDialogToolbar;
    private DialogsList mMessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailog);
        initView();
        init();
    }

    void init() {
        dialogsAdapter.setItems(getFakeDialogs());
        dialogsAdapter.setOnDialogClickListener(this);
        dialogsAdapter.setOnDialogClickListener(this);
    }

    //制造假数据
    public List<Dialog> getFakeDialogs() {
        return DialogsFixtures.getDialogs();
    }

    @Override
    public void onDialogClick(Dialog dialog) {
        startActivity(new Intent(this, MessageActivity.class));
        EventBus.getDefault().postSticky(dialog);
    }

    @Override
    public void onDialogLongClick(Dialog dialog) {
        Utils.MakeTost(false, dialog.getDialogName());
    }

    private void initView() {
        mDialogToolbar = (Toolbar) findViewById(R.id.dialog_toolbar);
        mDialogToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mMessageList = (DialogsList) findViewById(R.id.dialogList);
        mMessageList.setAdapter(dialogsAdapter);
    }
}

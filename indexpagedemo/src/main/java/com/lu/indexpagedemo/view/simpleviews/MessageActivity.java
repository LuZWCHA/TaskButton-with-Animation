package com.lu.indexpagedemo.view.simpleviews;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.MyIfo;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.ChatMessageActivity;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.Dialog;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.Message;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;
import com.lu.indexpagedemo.view.simpleviews.fixtures.DialogsFixtures;
import com.lu.indexpagedemo.view.simpleviews.fixtures.MessagesFixtures;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MessageActivity extends ChatMessageActivity
        implements MessageInput.InputListener,
        MessageInput.AttachmentsListener {

    MessagesList messagesList;
    String dailogId;
    Dialog thisDialog;
    private Toolbar mMessageToolbar;
    private MessagesList mMessageList;
    private MessageInput mInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        senderId = MyIfo.getMyIfo().getSelf().getId();
        initView();
        initAdapter();
        EventBus.getDefault().register(this);
    }


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void CreateDialog(Dialog dialog) {
        dailogId = dialog.getId();
        thisDialog = dialog;
        setTitle(dialog.getDialogName());
    }

    private void initAdapter() {
        super.messagesAdapter = new MessagesListAdapter<>(senderId, super.imageLoader);
        super.messagesAdapter.enableSelectionMode(this);
        super.messagesAdapter.setLoadMoreListener(this);
        super.messagesAdapter.setDateHeadersFormatter(this);
        mMessageList.setAdapter(super.messagesAdapter);
    }

    @Override
    public boolean onSubmit(CharSequence input) {
        Message mymessage = MessagesFixtures.getTextMessage(input.toString());
        mymessage.setUser(MyIfo.getMyIfo().getSelf());
        super.messagesAdapter.addToStart(mymessage, true);
        return true;
    }


    @Override
    public void onAddAttachments() {

    }


    private void initView() {
        mMessageToolbar = (Toolbar) findViewById(R.id.message_toolbar);
        mMessageList = (MessagesList) findViewById(R.id.messageList);
        mInput = (MessageInput) findViewById(R.id.input);
        mInput.setInputListener(this);
        mInput.setAttachmentsListener(this);

        setSupportActionBar(mMessageToolbar);
        mMessageToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

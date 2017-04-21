package com.lu.chatdemo.ChatBaseActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.chatdemo.Model.Dialog;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;
import com.stfalcon.chatkit.utils.FrescoAutoFit;

public class ChatDialogActivity extends AppCompatActivity
        implements DialogsListAdapter.OnDialogClickListener<Dialog>,
        DialogsListAdapter.OnDialogLongClickListener<Dialog>{

    protected ImageLoader imageLoader;
    protected DialogsListAdapter<Dialog> dialogsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(SimpleDraweeView simpleDraweeView, String url,int maxWidth) {
                FrescoAutoFit.setControllerListener(simpleDraweeView,url,maxWidth);
            }

            @Override
            public void loadImage(SimpleDraweeView simpleDraweeView, String url) {
                FrescoAutoFit.setControllerListener(simpleDraweeView,url,0);
            }

            @Override
            public void loadImage(AppCompatImageView appCompatImageView, String url) {
            }

        };

    }

    @Override
    public void onDialogClick(Dialog dialog) {
        startActivity(new Intent(this,ChatMessageActivity.class));
    }


    @Override
    public void onDialogLongClick(Dialog dialog) {

    }
}

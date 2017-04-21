package com.lu.indexpagedemo.base.mvp.ChatBaseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.Dialog;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;
import com.stfalcon.chatkit.utils.DateFormatter;
import com.stfalcon.chatkit.utils.FrescoAutoFit;

import java.util.Date;

public class ChatDialogActivity extends AppCompatActivity implements
                                                DateFormatter.Formatter{

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

        try {
            dialogsAdapter = new DialogsListAdapter<Dialog>(imageLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String format(Date date) {
        if (DateFormatter.isToday(date)) {
            return DateFormatter.format(date, DateFormatter.Template.TIME);
        } else if (DateFormatter.isYesterday(date)) {
            return "昨天";
        } else if (DateFormatter.isCurrentYear(date)) {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH);
        } else {
            return DateFormatter.format(date, DateFormatter.Template.STRING_DAY_MONTH_YEAR);
        }
    }

}

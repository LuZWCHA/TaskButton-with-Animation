package com.lu.indexpagedemo.view.simpleviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.lu.indexpagedemo.base.mvp.ChatBaseActivity.Model.User;
import com.lu.indexpagedemo.view.adapters.MyRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class DesignerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private SimpleDraweeView mDesignerDetailsBackground;
    private SimpleDraweeView mDesignerDetailsAvatarPath;
    private TextView mDesignerDetailsUserName;
    /**
     * 210
     */
    private TextView mDesignerDetailsBeenFocus;
    /**
     * 3
     */
    private TextView mDesignerDetailsFocus;
    private LinearLayout mLinearLayout;
    /**
     * 关  注
     */
    private AppCompatButton mDesignerDetailsFocusButton;
    private MyRecyclerView mDesignerDetailsWorks;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.TRANSPARENT(getWindow());
        setContentView(R.layout.activity_designer_detials);
        initView();
        EventBus.getDefault().register(this);
    }

    @Subscribe(sticky = true)
    public void onUserGet(User user) {
        mUser = user;
        updateData(user);
    }

    private void updateData(User user){
        mDesignerDetailsBackground.setActualImageResource(R.drawable.details_background);
        Utils.load(Utils.AddBaseUrl(user.getAvatarPath()),mDesignerDetailsAvatarPath,Utils.dip2px(84),Utils.dip2px(84));
        mDesignerDetailsUserName.setText(user.getNickname());
    }

    private void initView() {
        mDesignerDetailsBackground = (SimpleDraweeView) findViewById(R.id.designer_details_background);
        mDesignerDetailsAvatarPath = (SimpleDraweeView) findViewById(R.id.designer_details_avatar_path);
        mDesignerDetailsUserName = (TextView) findViewById(R.id.designer_details_user_name);
        mDesignerDetailsBeenFocus = (TextView) findViewById(R.id.designer_details_been_focus);
        mDesignerDetailsFocus = (TextView) findViewById(R.id.designer_details_focus);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        mDesignerDetailsFocusButton = (AppCompatButton) findViewById(R.id.designer_details_focus_button);
        mDesignerDetailsFocusButton.setOnClickListener(this);
        mDesignerDetailsWorks = (MyRecyclerView) findViewById(R.id.designer_details_works);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.designer_details_focus_button:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

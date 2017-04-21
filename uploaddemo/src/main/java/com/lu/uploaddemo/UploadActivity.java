package com.lu.uploaddemo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener{

    private Button uploadButton;
    private EditText mTitleTextView;
    private EditText mContentTextView;
    String mTitle;
    String mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        uploadButton = (Button) findViewById(R.id.uploadbutton);

        mContentTextView = (EditText) findViewById(R.id.content_textview);
        mTitleTextView = (EditText) findViewById(R.id.title_textview);

        uploadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(!TextUtils.isEmpty(mTitleTextView.getText()) && !TextUtils.isEmpty(mContentTextView.getText()))
        {
            mTitle = mTitleTextView.getText().toString();
            mContent = mContentTextView.getText().toString();
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent,1);
        }else{
            Toast.makeText(this,"内容和标题不能为空",Toast.LENGTH_SHORT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(Activity.RESULT_OK == resultCode){
                Uri uri = data.getData();
                Cursor cursor = this.getContentResolver().query(uri, new String[]{"_data"},null, null, null);
                if(cursor.moveToFirst()){
                    String otherfile = cursor.getString(0);
                    uploadPic(otherfile);
                }
            }
        }
    }

    void uploadPic(String path)
    {
        File file = new File(path);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        RetrofitClient.getInstance().getApi().uploadImage(requestBody,mTitle,mContent)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        return requestBody.toString();
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Toast.makeText(getApplicationContext(),"开始上传",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String value) {
                        Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(),"上传失败",Toast.LENGTH_SHORT).show();
                        Log.e("failed",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplicationContext(),"上传完成",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

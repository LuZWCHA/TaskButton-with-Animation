package com.lu.indexpagedemo.view.activitys;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.lu.indexpagedemo.R;
import com.lu.indexpagedemo.base.Tools.Constant;
import com.lu.indexpagedemo.base.Tools.QSdkApp;
import com.lu.indexpagedemo.base.Tools.Utils;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;

public class TestActivity extends AppCompatActivity {

    private WebView webView;
    private android.webkit.WebView mWebView;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        frameLayout = (FrameLayout) findViewById(R.id.root_view);
//        webView = new WebView(this);
//        webView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        frameLayout.addView(webView);
        //QSdkApp.webViewInitiz(webView,new WebChromeClient(),"<p>这是王打算打大叔大叔大叔大叔大打双打的撒D啊盛大盛大盛大盛大盛大盛大盛大是搭搭撒撒大事大事大打双打大大的撒大苏打大叔啊盛大盛大盛大盛大盛大盛大盛大啊大叔大叔大叔大叔搭搭撒撒大事打死大大声大声答答答蓓蓓的jdlkdadasdadasdadasdasd893454108u3898hd90whef90wehf89qhf90weh8f9wqhef98whef-0qwhe-f-dhfios街道和都爱喊打喊杀滴哦哈啥都I啊活动I活动送爱好的果然个个热情而更为绕过去啊那是电脑上的ajsdjaklsdjaklsdjklajsdklajlkdjaldsjlaksjdklasjdlkasjsdlkajdklajdlkajsdlajdlkasj小号。woshi舒服撒上飞机快乐<img src=\"/zjutsrc/public/ueditor/php/upload/image/20170413/1492018667142349.jpg\" title=\"1492018667142349.jpg\" alt=\"下载.jpg\"/>还是独家发售</p>",Constant.screenwithdp);
        //QSdkApp.webViewInitiz(webView,,"https://demo.goodream.me/three.js/physics/shadow.html");

        mWebView = new android.webkit.WebView(this);
        mWebView.setWebChromeClient(new myWebViewClient());
        mWebView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        WebSettings webSettings = mWebView.getSettings();
        frameLayout.addView(mWebView);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.loadUrl("https://demo.goodream.me/three.js/physics/shadow.html");
    }

    public static class myWebViewClient extends android.webkit.WebChromeClient{

        @Override
        public void onPermissionRequestCanceled(PermissionRequest request) {
            super.onPermissionRequestCanceled(request);
            Utils.MakeTost(false,request.toString());

        }

        @Override
        public void onPermissionRequest(PermissionRequest request) {
            super.onPermissionRequest(request);
            Utils.MakeTost(false,request.toString());
            request.grant(request.getResources());
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        frameLayout.removeAllViews();
        //webView.clearCache(true);
        webView.destroy();
    }
}

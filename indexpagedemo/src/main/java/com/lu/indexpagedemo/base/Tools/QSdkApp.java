package com.lu.indexpagedemo.base.Tools;

import android.app.Application;
import android.util.Log;

import com.lu.indexpagedemo.base.retrofit.RetrofitClient;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * Created by 陆正威 on 2017/4/16.
 */

public class QSdkApp {
    public static void Init(Application application){
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(application,  cb);
    }

    public static void webViewInitiz(WebView mWebView, WebChromeClient webViewClient,String url)
    {
        WebSettings webSettings = mWebView.getSettings();
        initWebViewSettings(mWebView);

        //mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setWebChromeClient(webViewClient);
        mWebView.loadUrl(url);
    }

    public static void webViewInitiz(WebView mWebView, WebChromeClient webViewClient,String html,int screenWidth){
        html = html.replace("&lt","<");
        html = html.replace("&gt",">");
        html = html.replace("<img", "<img style='width:100%; height:auto;'");
        html = "<meta name=\"viewport\" content=\"width="+ String.valueOf(screenWidth) +",initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no\" />" +
                "<head>\n" +
                "<style type=\"text/css\">\n" +
                "body {word-wrap:break-word; overflow:hidden;font-size:100%;margin-left:5%;margin-right:5%}\n" +
                "img {margin-top:5%;margin-bottom:5%}\n"+
                "p {font-size: 1em}\n" +
                "</style>\n" +
                "</head><html><body>" + html + "</body></html>";

        WebSettings webSettings = mWebView.getSettings();
        initWebViewSettings(mWebView);

        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setWebChromeClient(webViewClient);
        mWebView.getX5WebViewExtension().setScrollBarFadingEnabled(false);

        mWebView.loadDataWithBaseURL(RetrofitClient.BASE_URL,html, "text/html", "utf-8",null);
    }

    public static void initWebViewSettings(WebView mWebView) {
        WebSettings webSetting = mWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setSupportZoom(true);
        //webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(true);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        //webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // this.getSettingsExtension().setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);//extension
        // settings 的设计
    }
}

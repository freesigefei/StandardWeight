package com.test.standardweight;
/**
 @author:freesigefei
 定义一个新的activity来展现web view网页页面，默认显示为百度
 **/
import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

    private WebView mWebView; //Web网页视图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //调用创建视图的函数
        creadView();
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); // Android默认的处理方式
                handler.proceed();  // 接受所有网站的证书
                //handleMessage(Message msg); // 进行其他处理
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDefaultTextEncodingName("gb2312");
        mWebView.loadUrl("https://www.baidu.com");
    }

    //定义创建视图的函数
    public void creadView(){
        mWebView=(WebView)findViewById(R.id.mWebView);
    }
}

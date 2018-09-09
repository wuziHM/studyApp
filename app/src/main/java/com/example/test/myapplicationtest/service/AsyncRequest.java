package com.example.test.myapplicationtest.service;

import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.ksoap2.serialization.SoapObject;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.test.myapplicationtest.utils.PhoneUtils;
import com.example.test.myapplicationtest.utils.StringUtils;


public class AsyncRequest extends
        AsyncTask<Object, Integer, String> {

    private String title;

    private String message = "正在获取信息...";

    /**
     * 是否显示进度条，默认显示
     */
    private boolean isShowProgress;

    private WeakReference<Context> mWeakContext;

    private ProgressDialog progressDialog;

    private BaseService webService;

    private OnAsyncRequestCompledted onAsyncRequestCompledted;

    private Handler handler = null;

    public static final int EXCEPTION_WEB_SERVICE = 0x9999;

    private static final Executor DEFAULT_EXECUTOR = Executors.newCachedThreadPool();

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case EXCEPTION_WEB_SERVICE: {
                    String text = (String) msg.obj;
                    if (!StringUtils.isBlank(text)) {
                        Toast.makeText(mWeakContext.get().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        }
    };


    /**
     * @param message
     * @param mContext
     * @param webService
     */
    public AsyncRequest(String message, Context mContext, BaseService webService) {
        this(null, message, mContext, webService);
    }

    /**
     * @param title
     * @param message
     * @param context
     * @param webService
     */
    public AsyncRequest(String title, String message, Context context, BaseService webService) {
        super();
        this.title = title;
        this.message = message;
        this.mWeakContext = new WeakReference<Context>(context);
        this.webService = webService;

        webService.setContext(mWeakContext.get());
        setShowProgress(true);
    }

    @Override
    protected void onPreExecute() {
        if (isShowProgress()) {
            progressDialog = new ProgressDialog(mWeakContext.get());
            if (null != getTitle()) {
                progressDialog.setTitle(getTitle());
            }
            progressDialog.setMessage(getMessage());
            try {
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... params) {
        String result = null;
        try {
            if (params[1] instanceof String) {
                String param = (String) params[1];

                if (params.length == 3) {
                    result = webService.httpRequest((String) params[0], param,
                            convertTimeout(params[2]));
                    Log.i("map", "验证码：result1---"+result);
                } else {
                    result = webService.httpRequest((String) params[0], param);
                    Log.i("map", "验证码：result2---"+result);
                }
            } else if (params[1] instanceof SoapObject) {
                SoapObject sobj = (SoapObject) params[1];

                if (params.length == 3) {
                    result = webService.httpRequest((String) params[0], sobj,
                            convertTimeout(params[2]));
                    Log.i("map", "验证码：result3---"+result);
                } else {
                    result = webService.httpRequest((String) params[0], sobj);
                    Log.i("map", "验证码：result4---"+result);
                }
            }

        } catch (WebServiceException e) {
            e.printStackTrace();
            Message msg = new Message();
            msg.what = EXCEPTION_WEB_SERVICE;
            msg.obj = e.getMessage();
            Message outMsg = Message.obtain(msg);
            mHandler.sendMessage(msg);
            if(handler != null){
                handler.sendMessage(outMsg);
            }
        }
        return result;
    }

    /* (non-Javadoc)
     * @see android.os.AsyncTask#execute(Params[])
     */
    @SuppressLint("NewApi")
    public final AsyncTask<Object, Integer, String> request(Object...params) {
        if (PhoneUtils.hasHoneycomb()) {
            return executeOnExecutor(DEFAULT_EXECUTOR, params);
        } else {
            return execute(params);
        }

    }

    /**
     * 得到超时时间
     * @param obj 单位为秒
     * @return 返回毫秒
     */
    private int convertTimeout(Object obj) {
        int result = 60000;

        if (null != obj) {
            result = Integer.parseInt((String) obj);
            result *= 1000;
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        if (null != onAsyncRequestCompledted && null != result) {
            onAsyncRequestCompledted.onCompleted(result);
        }

        if (null != progressDialog && progressDialog.isShowing()) {
//			String className = mWeakContext.get().getClass().getName();
//			if (CommonUtils.isTopActivity(mWeakContext.get(), className)) {
//				progressDialog.dismiss();
//			} // OrderOperation back OrderMgmtActivity

            progressDialog.dismiss();
        }

        super.onPostExecute(result);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOnAsyncRequestCompledted(OnAsyncRequestCompledted onAsyncRequestCompledted) {
        this.onAsyncRequestCompledted = onAsyncRequestCompledted;
    }

    public boolean isShowProgress() {
        return isShowProgress;
    }

    public void setShowProgress(boolean isShowProgress) {
        this.isShowProgress = isShowProgress;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public interface OnAsyncRequestCompledted {
        public boolean onCompleted(String result) ;
    }

}

package com.example.test.myapplicationtest.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.service.dto.PdaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;

public abstract class BaseService {
    private static final int SHOW_MESSAGE = 0x9000;

    protected static final String NAMESPACE = "http://sundatou.iok.la:18134/train/front/";

//    public RemoteService remoteService;

    private Context context;  // static, 则AsyncRequest会throw badtoken exception

    /**
     * 通信的请求的数据
     */
    protected SoapObject soapObject;

    /**
     * 调用具体的WebService接口
     */
    private String soapAction;

    /**
     * 异步请求任务
     */
    protected AsyncRequest asyncRequest;

    /**
     * 主要用于在外部处理通信结果，传递消息
     */
    private Handler handler;

    private Handler showMessageHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_MESSAGE: {
                    String text = (String) msg.obj;
                    Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    break;
                }
                default: {
                    break;
                }
            }

        }
    };

//    public BaseService() {
//        this.remoteService = new RemoteService();
//    }

    /**
     * 无参数的实例化方法
     */
    public BaseService(Context context) {
        this.context = context;
    }

    private static Gson gson = null;

    /**
     * 获取json实例
     * @return
     */
    public Gson getGsonInstance() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }


    /**
     * 与WebService接口交互
     * @param methodName 方法名
     * @param request json的请求格式
     * @return
     * @throws WebServiceException
     */
    public String httpRequest(String methodName, String request) throws WebServiceException {
        soapObject = new SoapObject(getNamespace(), methodName);
        soapObject.addProperty("arg0", request);
        soapAction = getNamespace() + methodName;
        String response = getResponse();
        return response;
    }

    /**
     * @param methodName
     * @param request
     * @param timeout
     * @return
     * @throws WebServiceException
     */
    public String httpRequest(String methodName, String request,
                              int timeout) throws WebServiceException {
        soapObject = new SoapObject(getNamespace(), methodName);
        soapObject.addProperty("arg0", request);
        soapAction = getNamespace() + methodName;
        String response = getResponse(timeout);
        return response;
    }

    /**
     * 与WebService接口交互
     * @param methodName 请求的方法名
     * @param request webservice的ksoap请求对象，可设置参数的个数
     * @throws WebServiceException
     */
    public String httpRequest(String methodName, SoapObject request) throws WebServiceException {
        soapObject = request;
        soapAction = getNamespace() + methodName;
        String response = getResponse();
        return response;
    }

    /**
     * @param methodName
     * @param request
     * @param timeout
     * @return
     * @throws WebServiceException
     */
    public String httpRequest(String methodName, SoapObject request,
                              int timeout) throws WebServiceException {
        soapObject = request;
        soapAction = getNamespace() + methodName;
        String response = getResponse(timeout);
        return response;
    }

    /**
     * 与webservice接口进行交互
     * @return
     * @throws WebServiceException 网络交互异常
     */
    private String getResponse() throws WebServiceException {
         return getResponse(120000);
    }

    /**
     * @param timeout 超时时间
     * @return
     * @throws WebServiceException
     */
    private String getResponse(int timeout) throws WebServiceException {
        String response = "";

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope( SoapEnvelope.VER10);
        envelope.dotNet = false;
//        envelope.encodingStyle.
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE transport = new HttpTransportSE(getServiceUri(), timeout);

        try {
            transport.call(soapAction, envelope);
            if (envelope.bodyIn instanceof SoapFault) {
                SoapFault fault = (SoapFault) envelope.bodyIn;
                throw new WebServiceException(fault.getMessage());
            } else {
                response = (String) envelope.getResponse();
            }

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new WebServiceException(context.getString( R.string.exception_socket_timeout));
        } catch (IOException e) {
            e.printStackTrace();
            throw new WebServiceException(context.getString(R.string.exception_web_service));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            throw new WebServiceException(context.getString(R.string.exception_web_service));
        }

        return response;
    }

    /**
     * 获取webservice的命名空间
     * @return
     */
    protected abstract String getNamespace();

    /**
     * 获取webservice的接口地址
     * @return
     */
    protected abstract String getServiceUri();

    /**
     * 将json字符转成对象
     * @param result
     * @param typeOf
     * @return
     */
    public <T> PdaResponse<T> fromJson(String result, Type typeOf) {
        PdaResponse<T> response = new PdaResponse<T>();
        try {
            response = getGsonInstance().fromJson(result, typeOf);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * 发送带参数的消息进行处理
     * @param what 消息处理的标识
     * @param obj 需要处理的对象
     */
    protected void sendHandleMessage(int what, Object obj) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = obj;
        if (null != getHandler()) {
            getHandler().sendMessage(msg);
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * 转成json字符
     * @return
     */
    public String toJson(Object obj) {
        String request = getGsonInstance().toJson(obj);
//		LoggerUtils.d(LoggerUtils.makeLogTag(BaseWebService.class),
//				"request: " + request);

        return request;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 显示提示信息
     * @param msg
     */
    public void showMessage(String msg) {
        Message message = new Message();
        message.what = SHOW_MESSAGE;
        message.obj = msg;
        showMessageHandler.sendMessage(message);
    }
}

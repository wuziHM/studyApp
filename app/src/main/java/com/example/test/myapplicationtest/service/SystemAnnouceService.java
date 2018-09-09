package com.example.test.myapplicationtest.service;

import android.content.Context;
import android.util.Log;

import com.example.test.myapplicationtest.R;
import com.example.test.myapplicationtest.dto.NoticeDetailDto;
import com.example.test.myapplicationtest.dto.RefreshListDto;
import com.example.test.myapplicationtest.dto.SystemNoticeListDto;
import com.example.test.myapplicationtest.dto.WarningDetailDto;
import com.example.test.myapplicationtest.dto.WarningListDto;
import com.example.test.myapplicationtest.service.dto.PdaResponse;
import com.example.test.myapplicationtest.service.dto.ResponseDto;
import com.example.test.myapplicationtest.utils.GsonImpl;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemAnnouceService extends BaseService {

    public static final String url = "http://sundatou.iok.la:18134/train/front/";//http://sundatou.iok.la:18134/train/front/

    public static final String nameSpace = "http://sundatou.iok.la:18134/train/front/";//http://ws.webxml.com.cn/WebServices/WeatherWS.asmx

    //公告列表
    public static final String MethodgetNoticeList = "getNoticeList";

    //安全信息列表
    public static final String MethodgetSafetyList = "getSafetyList"; //getSafetyList

    //警示信息
    public static final String MethodgetWarningList = "getWarningList";

    //系统详情
    public static final String MethodgetNoticeMessage = "getNoticeMessage";

    //安全信息详情
    public static final String MethodgetSafetyMessage = "getSafetyMessage";

    //警告信息详情
    public static final String MethodgetWarningMessage = "getWarningMessage";


    public static final int GETSAFETYLIST = 0;

    public static final int GETNOTICELIST = 1;

    public static final int GETWARNINGLIST = 2; //ctrl shift u

    public static final int GETNOTICEMESSAGE = 3;

    public static final int GETWARNINGMESSAGE = 4;


    /**
     * 无参数的实例化方法
     *
     * @param context
     */
    public SystemAnnouceService(Context context) {
        super ( context );
    }

    @Override
    protected String getNamespace() {
        return nameSpace;
    }

    @Override
    protected String getServiceUri() {
        return url;
    }

    public void getWarningMessage(Map<String, Integer> paramsMap) {
        try {
            String baseUrl = url + MethodgetWarningMessage +"?memberId="+paramsMap.get ( "memberId" );
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode(paramsMap.get(key).toString (),"utf-8")));
                pos++;
            }
            String params =tempParams.toString();
            // 请求的参数转换为byte数组
            byte[] postData = params.getBytes();
            // 新建一个URL对象
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(15 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(15 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                JSONObject jsonObject = new JSONObject(result);
                Object str = jsonObject.opt ( "data" );
                int success = jsonObject.optInt ( "result" );
                List<WarningDetailDto> response = new ArrayList<WarningDetailDto> (  );
                response =  GsonImpl.get().toList(str.toString (),WarningDetailDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(GETWARNINGMESSAGE, response);
                }
//                PdaResponse<List<RefreshListDto>> response = new PdaResponse<List<RefreshListDto>> ();
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }
    }
    /**
     * 安全信息详情
     * @param paramsMap
     */
    public void getSafetyMessage(Map<String, Integer> paramsMap) {
        try {
            String baseUrl = url + MethodgetSafetyMessage +"?id="+paramsMap.get ( "id" );
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode(paramsMap.get(key).toString (),"utf-8")));
                pos++;
            }
            String params =tempParams.toString();
            // 请求的参数转换为byte数组
            byte[] postData = params.getBytes();
            // 新建一个URL对象
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(15 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(15 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                JSONObject jsonObject = new JSONObject(result);
                Object str = jsonObject.opt ( "data" );
                int success = jsonObject.optInt ( "result" );
                NoticeDetailDto response = new NoticeDetailDto ();
                response =  GsonImpl.get().toObject(str.toString (),NoticeDetailDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(GETNOTICEMESSAGE, response);
                }
//                PdaResponse<List<RefreshListDto>> response = new PdaResponse<List<RefreshListDto>> ();
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }
    }

    /**
     * 系统信息详情
     * @param paramsMap
     */
    public void getNoticeMessage(Map<String, Integer> paramsMap) {
        try {
            String baseUrl = url + MethodgetNoticeMessage +"?id="+paramsMap.get ( "id" );
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode(paramsMap.get(key).toString (),"utf-8")));
                pos++;
            }
            String params =tempParams.toString();
            // 请求的参数转换为byte数组
            byte[] postData = params.getBytes();
            // 新建一个URL对象
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(15 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(15 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                JSONObject jsonObject = new JSONObject(result);
                Object str = jsonObject.opt ( "data" );
                int success = jsonObject.optInt ( "result" );
                NoticeDetailDto response = new NoticeDetailDto ();
                response =  GsonImpl.get().toObject(str.toString (),NoticeDetailDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(GETNOTICEMESSAGE, response);
                }
//                PdaResponse<List<RefreshListDto>> response = new PdaResponse<List<RefreshListDto>> ();
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }
    }

    /**
     * 获取安全知识列表
     * @param paramsMap
     */
    public void getSafetyList(Map<String, String> paramsMap) {
        try {
            String baseUrl = url + MethodgetSafetyList;
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode(paramsMap.get(key),"utf-8")));
                pos++;
            }
            String params =tempParams.toString();
            // 请求的参数转换为byte数组
            byte[] postData = params.getBytes();
            // 新建一个URL对象
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(15 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(15 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                ResponseDto response = new ResponseDto();
                response =  GsonImpl.get().toObject(result,ResponseDto.class);
                if(response.getResult ().intValue () == 1){
                    //成功
                    sendHandleMessage(GETSAFETYLIST, response);
                }
//                PdaResponse<List<RefreshListDto>> response = new PdaResponse<List<RefreshListDto>> ();
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }
    }

    /**
     * 获取系统消息列表
     * @param paramsMap
     */
    public void getNoticeList(Map<String, String> paramsMap) {
        try {
            String baseUrl = url + MethodgetNoticeList;
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode(paramsMap.get(key),"utf-8")));
                pos++;
            }
            String params =tempParams.toString();
            // 请求的参数转换为byte数组
            byte[] postData = params.getBytes();
            // 新建一个URL对象
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(15 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(15 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                JSONObject jsonObject = new JSONObject(result);
                Object str = jsonObject.opt ( "data" );
                int success = jsonObject.optInt ( "result" );
                SystemNoticeListDto response = new SystemNoticeListDto ();
                response =  GsonImpl.get().toObject(str.toString (),SystemNoticeListDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(GETNOTICELIST, response);
                }
//                PdaResponse<List<RefreshListDto>> response = new PdaResponse<List<RefreshListDto>> ();
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }
    }


    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
    public String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e("tag", e.toString());
            return null;
        }
    }

    public void getWarningList(Map<String, String> paramsMap) {
        try {
            String baseUrl = url + MethodgetWarningList;
            //合成参数
            StringBuilder tempParams = new StringBuilder();
            int pos = 0;
            for (String key : paramsMap.keySet()) {
                if (pos > 0) {
                    tempParams.append("&");
                }
                tempParams.append(String.format("%s=%s", key,  URLEncoder.encode(paramsMap.get(key),"utf-8")));
                pos++;
            }
            String params =tempParams.toString();
            // 请求的参数转换为byte数组
            byte[] postData = params.getBytes();
            // 新建一个URL对象
            URL url = new URL(baseUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接超时时间
            urlConn.setConnectTimeout(15 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(15 * 1000);
            // Post请求必须设置允许输出 默认false
            urlConn.setDoOutput(true);
            //设置请求允许输入 默认是true
            urlConn.setDoInput(true);
            // Post请求不能使用缓存
            urlConn.setUseCaches(false);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
            //设置本次连接是否自动处理重定向
            urlConn.setInstanceFollowRedirects(true);
            // 配置请求Content-Type
            urlConn.setRequestProperty("Content-Type", "application/json");
            // 开始连接
            urlConn.connect();
            // 发送请求参数
            DataOutputStream dos = new DataOutputStream(urlConn.getOutputStream());
            dos.write(postData);
            dos.flush();
            dos.close();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 获取返回的数据
                String result = streamToString(urlConn.getInputStream());
                JSONObject jsonObject = new JSONObject(result);
                Object str = jsonObject.opt ( "data" );
                int success = jsonObject.optInt ( "result" );
                WarningListDto response = new WarningListDto ();
                response =  GsonImpl.get().toObject(str.toString (),WarningListDto.class);
                if(success == 1){
                    //成功
                    sendHandleMessage(GETWARNINGLIST, response);
                }
                Log.e("tag", "Post方式请求成功，result--->" + result);
            } else {
                Log.e("tag", "Post方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e("tag", e.toString());
        }
    }
//---------------------------------------------------------------------------------------------------------------

//    public void getNoticeList(String requestGson, boolean isProgress) {
//        asyncRequest = new AsyncRequest(getContext().getResources()
//                .getString( R.string.querying_info), getContext(), this);
//        asyncRequest.setShowProgress(isProgress);
//        asyncRequest
//                .setOnAsyncRequestCompledted(new AsyncRequest.OnAsyncRequestCompledted () {
//
//                    @Override
//                    public boolean onCompleted(String result) {
////                        PdaResponse<List<ResourceCommonDto>> response = new PdaResponse<List<ResourceCommonDto>>();
////                        Type type = new TypeToken<PdaResponse<List<ResourceCommonDto>>>() {
////                        }.getType();
////                        response = getGsonInstance().fromJson(result, type);
////                        if (response.isSuccess()) {
////                            sendHandleMessage(LIST_RESOURCE, response);
////                        } else {
////                            showMessage(response.getMessage());
////                        }
//                        return false;
//                    }
//                });
//
//        asyncRequest.request(MethodgetNoticeList, requestGson);
//    }
//
//
//    public void getSafetyList(String requestGson, boolean isProgress) {
//        asyncRequest = new AsyncRequest(getContext().getResources()
//                .getString( R.string.querying_info), getContext(), this);
//        asyncRequest.setShowProgress(isProgress);
//        asyncRequest
//                .setOnAsyncRequestCompledted(new AsyncRequest.OnAsyncRequestCompledted () {
//
//                    @Override
//                    public boolean onCompleted(String result) {
//                        PdaResponse<List<RefreshListDto>> response = new PdaResponse<List<RefreshListDto>> ();
//                        Type type = new TypeToken<PdaResponse<List<RefreshListDto>>> () {
//                        }.getType();
//                        response = getGsonInstance().fromJson(result, type);
//                        if (response.isSuccess()) {
////                            sendHandleMessage(LIST_RESOURCE, response);
//                        } else {
//                            showMessage(response.getMessage());
//                        }
//                        return false;
//                    }
//                });
//
//        asyncRequest.request(MethodgetSafetyList, requestGson);
//    }
}

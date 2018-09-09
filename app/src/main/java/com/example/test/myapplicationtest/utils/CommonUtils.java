package com.example.test.myapplicationtest.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.kobjects.base64.Base64;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CommonUtils {

    /**
     * 唤醒锁缓存。整个应用程序应当只有一把唤醒锁。如果系统已经获取了该锁，则没有必要再次申请。
     */
    private static WakeLock wakeLock= null;


    /**
     * 判断字符串是否为空
     * @param value 待判断的字符串
     * @return 如果是空指针或空白字符串，返回true，否则返回false
     */
    public static boolean isBlank(CharSequence value) {
        return value == null || value.length() == 0;
    }

    /**
     * 判断字符串是否为空
     * @param value 待判断的字符串
     * @return 如果是空指针或空白字符串，返回true，否则返回false
     */
    public static boolean isBlank(String value) {
        return value == null || value.equals("");
    }

    /**
     * 将CharSequence转换为String，如果是空指针，则返回“”。
     * @return
     */
    public static String turn2String(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * 将CharSequence转换为String，如果是空指针，则返回null。
     * @param chars
     * @return
     */
    public static String charSequence2String(CharSequence chars) {
        if (chars == null) {
            return null;
        } else {
            return chars.toString();
        }
    }

    /**
     * 将CharSequence转换为String，如果是空指针，则返回null。
     * @return
     */
    public static String object2String(Object obj) {
        if (obj == null) {
            return null;
        } else {
            return obj.toString();
        }
    }

    /**
     * 获取两点(经纬度表示)间的距离
     * @param lon1 第一点经度值
     * @param lat1 第一点纬度值
     * @param lon2 第二点经度值
     * @param lat2 第二点纬度值
     * @return 返回两点间距离
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        float[] results=new float[1];
        Location.distanceBetween(lat1, lon1, lat2, lon2, results);
        return results[0];
    }

    public static int toInt(String source) {
        int result = 0;
        if (source == null || source == "") {
            result = 0;
        } else {
            try {
                result = Integer.parseInt(source);
            } catch (NumberFormatException e) {
                Log.e("CommonUtils", "转换数字失败", e);
            }
        }
        return result;
    }


    public static int toInt(Long source) {
        int result = 0;
        try {
            result = Integer.parseInt(String.valueOf(source));
        } catch (NumberFormatException e) {
            Log.e("CommonUtils", "转换数字失败", e);
        }
        return result;
    }

    public static long toLong(String source) {
        long result = 0;
        if (source == null || source == "") {
            result = 0;
        } else {
            try {
                result = Long.parseLong(source);
            } catch (NumberFormatException e) {
                Log.e("CommonUtils", "转换数字失败", e);
            }
        }
        return result;
    }

    public static double toDouble(String source) {
        double result = 0;
        if (source == null || source == "") {
            result = 0;
        } else {
            try {
                result = Double.parseDouble(source);
            } catch (NumberFormatException e) {
                Log.e("CommonUtils", "转换数字失败", e);
            }
        }
        return result;
    }

    /**
     * 字符串转浮点
     * @author wangchangsen
     * @date 2012-11-8 上午10:23:17
     * @param source 浮点数的字符串
     * @param validBit 有效位
     * @return
     */
    public static double toDouble(String source, int validBit) {
        double result = 0;
        if (source == null || source.startsWith("0.0000")) {
            result = 0;
        } else {
            try {
                String tmp = source;
                if (source.length() >= validBit) {
                    tmp = source.substring(0, validBit);
                }
                result = Double.parseDouble(tmp);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                result = 0;
            }
        }
        return result;
    }

    /**
     * 检查文件是否存在
     * @param filePath
     * @return
     */
    public static boolean checkFileIsExist(String filePath) {
        if (filePath == null) {
            return false;
        }
        File file = new File(filePath);
        if (file != null && file.isFile()) {
            return true;
        }
        return false;
    }

    /**
     * 检查文件是否存在
     * @return
     */
    public static boolean checkFileIsExist(File file) {
        if (file != null && file.isFile()) {
            return true;
        }
        return false;
    }

    /**
     * 检查文件列表中是否有实体文件
     * @author wangchangsen
     * @date 2012-9-3 上午11:38:55
     * @param listImagePath
     * @return
     */
    public static boolean checkHasFileExist(ArrayList<String> listImagePath) {

        if (listImagePath == null) {
            return false;
        }

        for (String path : listImagePath) {
            if (checkFileIsExist(path)) {
                return true;
            }
        }
        return false;
    }

//	/**
//	 * 创建图片的缩放版本
//	 * @param file
//	 * @return
//	 */
//	public static File createThumbnail(String oldFilePath, String newFilePath) throws FileNotFoundException, Exception {
//		File file = new File(newFilePath);
//		file.getParentFile().mkdirs();
//		Bitmap bitmap = null;
//
//		// 压缩文件并将其转换为bitmap
//		bitmap = BitmapFactory.decodeFile(oldFilePath);
//		bitmap = Bitmap.createScaledBitmap(bitmap, 600, 800, true);
//
//		// 创建生成后的图片文件
//		BufferedOutputStream bos = null;
//		bos = new BufferedOutputStream(new FileOutputStream(file));
//		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//		bos.flush();
//		bos.close();
//		bitmap.recycle();
//
//		try {
//			// 删除拍照后产生的临时文件
//			if (CommonUtils.checkFileIsExist(oldFilePath)) {
//				new File(oldFilePath).delete();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return file;
//	}


    /**
     * 计算的样本大小
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    /**
     * 计算初始化样本大小
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 获取文件字符串
     * @param file
     * @return
     */
    public static String getEncodeString(File file) {
        String imageStr = "";
        FileInputStream fin = null;
        ByteArrayOutputStream output = null;
        try {
            byte[] buffer = new byte[1024];
            fin = new FileInputStream(file);
            output = new ByteArrayOutputStream();

            int bytesread = fin.read(buffer, 0, buffer.length);
            while (bytesread > 0) {
                output.write(buffer);
                bytesread = fin.read(buffer, 0, buffer.length);
            }
            imageStr = Base64.encode(output.toByteArray());
            fin.close();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return imageStr;
    }

    /**
     * 添加水印
     * @return
     */
    public static void addPicDateCanvas(Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E");
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(24);
        canvas.drawText(fmt.format(new Date()), bitmap.getWidth() - 400, bitmap.getHeight() - 20, paint);
    }

    /**
     * 把boolean值变成字符串“true”,"false"
     * @return
     */
    public static String turnBool2String (Boolean flag) {
        if (flag == null) {
            return "false";
        }
        return flag == true ? "true" : "false";
    }

    public static String[] getImageNames(String folderPath) {
        File file01 = new File(folderPath);

        String[] files01 = file01.list();

        int imageFileNums = 0;
        for (int i = 0; i < files01.length; i++) {
            File file02 = new File(folderPath + "/" + files01[i]);

            if (!file02.isDirectory()) {

                if (isImageFile(file02.getName())) {

                    imageFileNums++;
                }
            }
        }

        String[] files02 = new String[imageFileNums];

        int j = 0;
        for (int i = 0; i < files01.length; i++) {
            File file02 = new File(folderPath + "/" + files01[i]);

            if (!file02.isDirectory()) {

                if (isImageFile(file02.getName())) {
                    files02[j] = file02.getName();
                    j++;
                }
            }
        }
        return files02;
    }

    private static boolean isImageFile(String fileName) {
        String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1,
                fileName.length());
        if (fileEnd.equalsIgnoreCase("jpg")) {
            return true;
        } else if (fileEnd.equalsIgnoreCase("png")) {
            return true;
        } else if (fileEnd.equalsIgnoreCase("bmp")) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 申请唤醒锁，确保系统不进入休眠状态。
     * 2012-8-18 下午6:13:00 wangqiang添加此方法
     * @param context 申请唤醒锁的上下文，比如Acitivty实例。
     * @param mode 唤醒模式。参看android的API说明。
     */
    public static void acquireWakeLock(Context context, int mode) {
        boolean wasHeld = false;
        if (CommonUtils.wakeLock != null) {
            if (CommonUtils.wakeLock.isHeld()) {
                wasHeld = true;
                CommonUtils.wakeLock.release();
            }
            CommonUtils.wakeLock = null;
        }

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        CommonUtils.wakeLock = pm.newWakeLock(mode | PowerManager.ON_AFTER_RELEASE, context.getClass().getName());
        CommonUtils.wakeLock.setReferenceCounted(false);
        if (wasHeld) {
            CommonUtils.wakeLock.acquire();
        }
    }

    /**
     * 释放唤醒锁。
     * 2012-8-18 下午6:19:35 wangqiang添加此方法
     * @param context
     */
    public static void releaseWakeLock(Context context) {
        if (CommonUtils.wakeLock != null) {
            if (CommonUtils.wakeLock.isHeld()) {
                CommonUtils.wakeLock.release();
            }
            CommonUtils.wakeLock = null;
        }
    }

    /**
     * 判断服务是否在运行
     * @author wangchangsen
     * @date 2012-9-27 上午9:47:59
     * @param context
     * @param className
     * @return
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager actManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> listService = actManager.getRunningServices(50);
        if (!(listService.size() > 0)) {
            return false;
        }

        for (int i=0; i<listService.size(); i++) {
            if (listService.get(i).service.getClassName().equals(className)) {
                isRunning = true;
                break;
            }
        }

        return isRunning;
    }

    /**
     * 根据图片路径删除本地图片文件
     * @author wangchangsen
     * @date 2012-8-30 上午9:29:53
     * @param path
     */
    public static void delLocalFile(String path) {
        if (CommonUtils.checkFileIsExist(path)) {
            File file = new File(path);
            file.delete();
        }
    }

    /**
     * 判断activity在栈顶
     * @return
     */
    public static boolean isTopActivity(Context context, String className) {
        boolean isTop = false;
        ActivityManager actManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> listTaskInfo = actManager.getRunningTasks(1);
        String tempName = null;
        if (null != listTaskInfo) {
            tempName = listTaskInfo.get(0).topActivity.getClassName();
//    		Log.i("thom", "name " + tempName);
        }
        if (null != tempName) {
            isTop = tempName.equals(className);
        }

        return isTop;
    }

    /**
     * 获得标准的时间
     * @return
     */
    public static String getStandardTime(){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date.format(new Date());
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 测量控件的尺寸
     *
     * @param view
     */
    public static void calcViewMeasure(View view) {
        int width = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int expandSpec = View.MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
    }


    /**
     * 获取控件的高度，如果获取的高度为0，则重新计算尺寸后再返回高度
     *
     * @param view
     * @return
     */
    public static int getViewMeasuredHeight(View view) {
        calcViewMeasure(view);
        return view.getMeasuredHeight();
    }

    /**
     * 通过附件路径集合转成附件集合，不包含图片实体信息
     * @param attasPath
     * @return
     */
//    public static List<AttachmentDto> parsePathToImageDtosWithoutValues(
//            List<String> attasPath) {
//        List<AttachmentDto> attachments = new ArrayList<AttachmentDto>();
//        if (null != attasPath && attasPath.size() > 0) {
//            for (String path : attasPath) {
//                AttachmentDto attachmentDto = new AttachmentDto();
//                File file = new File(path);
//                attachmentDto.setName(file.getName());
//                attachmentDto.setPdaPath(file.getAbsolutePath());
//                attachments.add(attachmentDto);
//            }
//        }
//        return attachments;
//    }

    /**
     * 通过附件路径集合转成附件集合，包含图片实体信息
     * @param attasPath
     * @return
     */
//    public static List<AttachmentDto> parsePathToImageDtosWithValues(
//            List<String> attasPath) {
//        List<AttachmentDto> attachments = new ArrayList<AttachmentDto>();
//        if (null != attasPath && attasPath.size() > 0) {
//            for (String path : attasPath) {
//                AttachmentDto attachmentDto = new AttachmentDto();
//                File file = new File(path);
//                attachmentDto.setName(file.getName());
//                attachmentDto.setValue(CommonUtils.getEncodeString(file));
//                attachmentDto.setPdaPath(file.getAbsolutePath());
//                attachments.add(attachmentDto);
//            }
//        }
//        return attachments;
//    }

    /**
     * 改变文字颜色
     * @param tv
     * @param num
     */
    public static void changeTextViewTextColor(TextView tv,int num){
        tv.append(Html.fromHtml("<font color = \"#0099FF\">"+num+"</font>"));
    }

}

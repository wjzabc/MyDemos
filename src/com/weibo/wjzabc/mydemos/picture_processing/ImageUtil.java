package com.weibo.wjzabc.mydemos.picture_processing;

import com.weibo.wjzabc.mydemos.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.util.ByteArrayBuffer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class ImageUtil {
	
	public static final String TAG = ImageUtil.class.getSimpleName();

	public static final long maxBitmapSize = 5 * 1024 * 1024;// 5M

	public static final int screenWidth = 480;

	public static final int screenHeight = 800;
	
	public static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.RGB_565;

	public static Bitmap decodeByteArray(byte[] byteArray) {
		if (byteArray == null || byteArray.length == 0) {
			return null;
		}

		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inSampleSize = 1;
		opt.inJustDecodeBounds = true;
		opt.inPreferredConfig = BITMAP_CONFIG;
		BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, opt);
		opt.inSampleSize = 1;
		opt.inJustDecodeBounds = false;
		opt.inInputShareable = true;
		opt.inPurgeable = true;
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
					byteArray.length, opt);
		} catch (OutOfMemoryError e) {

		}
		return bitmap;
	}

	@SuppressLint("NewApi")
    public static Bitmap decodeFile(String pathName) {
		if (pathName == null || pathName.isEmpty()) {
			return null;
		}

		// MemoryUtil.trace();
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inSampleSize = 1;
		opt.inJustDecodeBounds = true;
		opt.inPreferredConfig = BITMAP_CONFIG;
		BitmapFactory.decodeFile(pathName, opt);
		opt.inSampleSize = 1;
		opt.inJustDecodeBounds = false;
		opt.inInputShareable = true;
		opt.inPurgeable = true;
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeFile(pathName, opt);
		} catch (OutOfMemoryError e) {
			
		}
		return bitmap;
	}
	
	/**
	 * 以最省内存的方式读取本地资源的图�?	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap getNativeBitMap(Context context, int resId) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = BITMAP_CONFIG;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(is, null, opt);
		} catch (OutOfMemoryError e) {
			
		}
		return bitmap;
	}

	// Add sample size calculate when decode bitmap
	public static int computeSampleByScreenBudget(
			BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);
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

	private static int computeInitialSampleSize(BitmapFactory.Options options,

	int minSideLength, int maxNumOfPixels) {

		double w = options.outWidth;

		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 :

		(int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));

		int upperBound = (minSideLength == -1) ? 128 :

		(int) Math.min(Math.floor(w / minSideLength),

		Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {

			// return the larger one when there is no overlapping zone.

			return lowerBound;

		}

		if ((maxNumOfPixels == -1) &&

		(minSideLength == -1)) {

			return 1;

		} else if (minSideLength == -1) {

			return lowerBound;

		} else {

			return upperBound;

		}

	}

	public static Bitmap getBitmap(String file) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			Log.d(TAG, e.toString());
		}
		return bitmap;
	}

	public static Bitmap getBitmapByUrl(String url , AsyncTask<Void, Void, Bitmap> task) throws Exception {
		byte[] byteArray = getByteArrayByUrl(url,task);
		if (byteArray == null)
			return null;
		Bitmap bitmap = decodeByteArray(byteArray);
		return bitmap;
	}

	public static byte[] getByteArrayByUrl(String url , AsyncTask<Void, Void, Bitmap> task) throws Exception {
		URL requestURL;
		HttpURLConnection conn = null;
		InputStream is = null;
		ByteArrayBuffer buffer = null;
		try {
			requestURL = new URL(url);
			conn = (HttpURLConnection) requestURL.openConnection();
			conn.setDoInput(true);
			conn.connect();
			int length = conn.getContentLength();

			if (length > 0) {
				is = conn.getInputStream();
				buffer = new ByteArrayBuffer(length);
				int value = -1;
				while ((value = is.read()) != -1) {
					if (task.isCancelled()) {
						return null;
					}
					buffer.append(value);
				}
				return buffer.buffer();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Bitmap.CompressFormat getCompressFormat(String fileName) {
		if (fileName.endsWith("jpg") || fileName.endsWith("jpeg")) {
			return CompressFormat.PNG;
		} else {
			return CompressFormat.PNG;
		}
	}

	public static Bitmap.CompressFormat getCompressFormat(File file) {
		String name = file.getName();
		return getCompressFormat(name);
	}

	public static Uri getImageUri(String paramString) {
		return Uri.fromFile(new File(paramString));
	}

	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		if (bitmap == null)
			return null;
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Bitmap.Config config = Bitmap.Config.ARGB_8888;
		Bitmap newBitmap = Bitmap.createBitmap(w, h, config);
		Canvas canvas = new Canvas(newBitmap);
		Paint paint = new Paint();
		int k = newBitmap.getWidth();
		int m = newBitmap.getHeight();
		Rect rect = new Rect(0, 0, k, m);
		RectF rectF = new RectF(rect);
		float f = Math.min(k, m) * 45;// SNSSettings.getInstance().getHeadRadius();
		paint.setAntiAlias(true);
		paint.setColor(-12434878);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, f, f, paint);
		PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
		PorterDuffXfermode Xfermode = new PorterDuffXfermode(mode);
		paint.setXfermode(Xfermode);
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return newBitmap;
	}

	public static Bitmap rotate(Bitmap bitmap, int paramInt) {
		Matrix matrix;
		Bitmap roteBitmap = bitmap;
		if ((paramInt != 0) && (bitmap != null)) {
			matrix = new Matrix();
			float f1 = paramInt;
			float f2 = bitmap.getWidth() / 2.0f;
			float f3 = bitmap.getHeight() / 2.0f;
			matrix.setRotate(f1, f2, f3);
			int w = bitmap.getWidth();
			int h = bitmap.getHeight();
			roteBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);
		}
		return roteBitmap;
	}

	public static Bitmap scaleBitmapTo(Bitmap bitmap, int scale) {
		if (bitmap == null) {
			return null;
		}
		Bitmap scaleBitmap = null;
		int scaledW = scale;// (int) (bitmap.getWidth() * (scale / 100f));
		int scaledH = scale;// (int) (bitmap.getHeight() * (scale Util./ 100f));
		scaleBitmap = Bitmap.createScaledBitmap(bitmap, scaledW, scaledH, true);
		return scaleBitmap;
	}

	public static Bitmap scaleBitmapTo(Bitmap bitmap, int dstWidth,
			int dstHeight, boolean filter) {
		if (bitmap == null) {
			return null;
		}
		return Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, filter);
	}
	
    public static Bitmap scaleBitmap(Context context, Bitmap src, int width, int height){
        final int bitmapWidth = src.getWidth();
        final int bitmapHeight = src.getHeight();
        Bitmap scaledBitmap = src;
        if(bitmapWidth != width || bitmapHeight != height){
        	float scaleWidth = ((float) width) / bitmapWidth;
            float scaleHeight = ((float) height) / bitmapHeight;
            Matrix matrix = new Matrix();  
            matrix.postScale(scaleWidth, scaleHeight);            
            scaledBitmap = Bitmap.createBitmap(src, 0, 0,   
            		bitmapWidth, bitmapHeight, matrix, true);
        }
        return scaledBitmap;
    }
    
    public static Bitmap drawableToBitmap(Drawable drawable){
		
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888: Bitmap.Config.RGB_565;
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		return bitmap;
	}
    public static Bitmap createIconBitmap(Context context){
        Resources res = context.getResources();
        Bitmap bitmap = Bitmap.createBitmap(200, 200, Config.ARGB_8888);
        Canvas canvas=new Canvas();
        canvas.setBitmap(bitmap);
        canvas.drawARGB(255, 255, 0, 0);
        Drawable icon;
        icon= res.getDrawable(R.drawable.wer234);
        icon.setBounds(0, 0,icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        icon.draw(canvas);
        icon= res.getDrawable(R.drawable.single_icon_mask);
        BitmapDrawable bicon=(BitmapDrawable) icon;
        Paint paint=new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN)); 
        canvas.drawBitmap(bicon.getBitmap(),new Matrix(),paint);
        return bitmap;
    }
    public static Bitmap createIconBitmap(Bitmap srcIcon, Context context, boolean singleItem) {
        Bitmap bitmap=srcIcon;

        Resources res = context.getResources();
		Bitmap icon = null;
		
		Drawable iconBg = null;
		if(singleItem){
			iconBg = res.getDrawable(R.drawable.single_icon_bg);
		}else{
			iconBg = res.getDrawable(R.drawable.single_icon_bg);
		}
		
		int desIconHeight = iconBg.getIntrinsicHeight();
		int desIconWidth = iconBg.getIntrinsicWidth();
		Bitmap scaleBitmap = scaleBitmap(context, srcIcon, desIconWidth, desIconHeight);
		icon = scaleBitmap;
//		//1
//		bitmap=icon;
//        if(bitmap!=null){
//            Log.d("tttt", "getIntrinsicWidth="+desIconWidth);
//            Log.d("tttt", "bitmap.getWidth()="+bitmap.getWidth());
//            return bitmap; 
//        }
//        Log.d("tttt", "2 bitmap.getWidth()="+bitmap.getWidth());

		Bitmap mask = null;
		if (singleItem) {
			mask = BitmapFactory.decodeResource(context.getResources(), R.drawable.single_icon_mask);
		}else{
			mask = BitmapFactory.decodeResource(context.getResources(), R.drawable.single_icon_mask);
		}
		
    	Rect sOldBounds = new Rect();
        int iconBgSize[] = new int[2];
        iconBgSize[0] = iconBg.getIntrinsicWidth();
        iconBgSize[1] = iconBg.getIntrinsicHeight();
        
        int iconSize[] = new int[2];
        iconSize[0] = icon.getWidth();
        iconSize[1] = icon.getHeight();
        
        Bitmap maskedBitmap = Bitmap.createBitmap(iconBgSize[0],iconBgSize[1], Bitmap.Config.ARGB_8888);           
		final Canvas maskCanvas = new Canvas();
		maskCanvas.setBitmap(maskedBitmap);
		
//      //2
//    bitmap=maskedBitmap;
//      if(bitmap!=null){
//          Log.d("tttt", "getIntrinsicWidth="+desIconWidth);
//          Log.d("tttt", "bitmap.getWidth()="+bitmap.getWidth());
//          return bitmap; 
//      }
//      Log.d("tttt", "2 bitmap.getWidth()="+bitmap.getWidth());
		
		float iconWidthScale = 1.0f;
		iconWidthScale = mask.getWidth()*1.0f/ iconSize[0];
		if (iconWidthScale *iconSize[1] > mask.getHeight()) {
			iconWidthScale = mask.getHeight()*1.0f/ iconSize[1];
		}
		
		int targetWidht = (int) (iconWidthScale * iconSize[0]);
		int targetHeight = (int) (iconWidthScale * iconSize[1]);
		int left = (int) ((iconBgSize[0] - targetWidht)/2);
		int top = (int) ((iconBgSize[1] - targetHeight)/2);
		Matrix matrix = new Matrix();  
		Paint paint = new Paint(); 
		paint.setAntiAlias(true);
		matrix.postScale(iconWidthScale, iconWidthScale);
		matrix.postTranslate(left, top);
		maskCanvas.drawBitmap(icon, matrix, paint);
		
//	      //3
//	    bitmap=maskedBitmap;
//	      if(bitmap!=null){
//	          Log.d("tttt", "getIntrinsicWidth="+desIconWidth);
//	          Log.d("tttt", "bitmap.getWidth()="+bitmap.getWidth());
//	          return bitmap; 
//	      }
//	      Log.d("tttt", "2 bitmap.getWidth()="+bitmap.getWidth());
		
		
//		sOldBounds.set(icon.getBounds());
//		icon.setBounds(left, top, left + iconSize[0], top + iconSize[1]);
//		icon.draw(maskCanvas);
//		icon.setBounds(sOldBounds);
		
		float scaleWidth = ((float) iconBgSize[0]) / mask.getWidth();   
		float scaleHeight = ((float) iconBgSize[1]) / mask.getHeight();
		matrix.reset();
		matrix.postScale(scaleWidth, scaleHeight);            
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN)); 
		maskCanvas.drawBitmap(mask, matrix, paint);
		
//        //4
//        bitmap=maskedBitmap;
//          if(bitmap!=null){
//              Log.d("tttt", "getIntrinsicWidth="+desIconWidth);
//              Log.d("tttt", "bitmap.getWidth()="+bitmap.getWidth());
//              return bitmap; 
//          }
//          Log.d("tttt", "2 bitmap.getWidth()="+bitmap.getWidth());
		
		final Canvas canvas = new Canvas();
		bitmap = Bitmap.createBitmap(iconBgSize[0],iconBgSize[1],Bitmap.Config.ARGB_8888);
		canvas.setBitmap(bitmap);
		
//        //5
//        //bitmap=maskedBitmap;
//          if(bitmap!=null){
//              Log.d("tttt", "getIntrinsicWidth="+desIconWidth);
//              Log.d("tttt", "bitmap.getWidth()="+bitmap.getWidth());
//              return bitmap; 
//          }
//          Log.d("tttt", "2 bitmap.getWidth()="+bitmap.getWidth());
		
        sOldBounds.set(iconBg.getBounds());
        iconBg.setBounds(left, top,left+ iconBgSize[0], top + iconBgSize[0]);
        iconBg.draw(canvas);
        
//        //6
//        //bitmap=maskedBitmap;
//          if(bitmap!=null){
//              Log.d("tttt", "getIntrinsicWidth="+desIconWidth);
//              Log.d("tttt", "bitmap.getWidth()="+bitmap.getWidth());
//              return bitmap; 
//          }
//          Log.d("tttt", "2 bitmap.getWidth()="+bitmap.getWidth());
        
        iconBg.setBounds(sOldBounds);
        canvas.drawBitmap(maskedBitmap, left, top, null);
        
//      //7
////      bitmap=maskedBitmap;
//        if(bitmap!=null){
//            Log.d("tttt", "getIntrinsicWidth="+desIconWidth);
//            Log.d("tttt", "bitmap.getWidth()="+bitmap.getWidth());
//            return bitmap; 
//        }
//        Log.d("tttt", "2 bitmap.getWidth()="+bitmap.getWidth());
        
        Drawable iconBorder = context.getResources().getDrawable(R.drawable.icon_cover);      
        
        left = (iconBgSize[0] - iconBorder.getIntrinsicWidth()) / 2 ;
        top = (iconBgSize[1] - iconBorder.getIntrinsicHeight()) / 2 ;
        iconBorder.setBounds(left, top, left+iconBgSize[0], top+iconBgSize[0]);
        iconBorder.draw(canvas);
        
      //8
//    bitmap=maskedBitmap;
      if(bitmap!=null){
          Log.d("tttt", "getIntrinsicWidth="+desIconWidth);
          Log.d("tttt", "bitmap.getWidth()="+bitmap.getWidth());
          return bitmap; 
      }
      Log.d("tttt", "2 bitmap.getWidth()="+bitmap.getWidth());
        
        maskedBitmap.recycle();
        mask.recycle();
        
        return bitmap;
    } 
    
    public static Bitmap createBitmap(Bitmap source, int x, int y, int width, int height,
            Matrix m, boolean filter) {
        if (x + width > source.getWidth()) {
            throw new IllegalArgumentException("x + width must be <= bitmap.width()");
        }
        if (y + height > source.getHeight()) {
            throw new IllegalArgumentException("y + height must be <= bitmap.height()");
        }

        int neww = width;
        int newh = height;
        Canvas canvas = new Canvas();
        Bitmap bitmap;
        Paint paint;

        Rect srcR = new Rect(x, y, x + width, y + height);
        RectF dstR = new RectF(0, 0, width, height);

        if (m == null || m.isIdentity()) {
            bitmap = Bitmap.createBitmap(neww, newh,
                    source.hasAlpha() ? Config.ARGB_8888 : Config.RGB_565);
            paint = null;   // not needed
        } else {
            /*  the dst should have alpha if the src does, or if our matrix
                doesn't preserve rectness
            */
            boolean hasAlpha = source.hasAlpha() || !m.rectStaysRect();
            RectF deviceR = new RectF();
            m.mapRect(deviceR, dstR);
            neww = Math.round(deviceR.width());
            newh = Math.round(deviceR.height());
            bitmap = Bitmap.createBitmap(neww, newh, hasAlpha ? Config.ARGB_8888 : Config.RGB_565);
            if (hasAlpha) {
                bitmap.eraseColor(0);
            }
            canvas.translate(-deviceR.left, -deviceR.top);
            canvas.concat(m);
            paint = new Paint();
            paint.setFilterBitmap(filter);
            if (!m.rectStaysRect()) {
                paint.setAntiAlias(true);
            }
        }
        
        // The new bitmap was created from a known bitmap source so assume that
        // they use the same density
        bitmap.setDensity(source.getDensity());
        
        canvas.setBitmap(bitmap);
        canvas.drawBitmap(source, srcR, dstR, paint);

        return bitmap;
    }
    
}
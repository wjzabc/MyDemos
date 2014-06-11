
package com.weibo.wjzabc.mydemos.others;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CheckApkSignHelper {

    /**
     * check if installed apk and uninstalled apk have the same Signatures.
     * @param context
     * @param packageName installed apk package's name
     * @param archiveFilePath uninstalled apk fill full path
     * @return
     */
    public static boolean isSignatureSame(Context context, String packageName,
            String archiveFilePath) {
        return IsSignaturesSame(getInstalledPackageSignatures(context, packageName),
                getUninstallAPKSignatures(archiveFilePath));
    }

    /**
     * 获取已安装apk的签名
     * 
     * @param context
     * @return
     */
    private static Signature[] getInstalledPackageSignatures(Context context, String packageName) {
        Signature[] signatures = null;
        PackageManager pm = context.getPackageManager();
        try {
            signatures = pm.getPackageInfo(packageName, PackageManager.GET_SIGNATURES).signatures;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.d("aaaa", "getInstalledPackageSignatures "+signatures[0].toString());
        return signatures;
    }
    
    private static Signature[] getUninstallAPKSignatures(String apkPath) { 
        String PATH_PackageParser = "android.content.pm.PackageParser"; 
        try { 
            // apk包的文件路径 
            // 这是一个Package 解释器, 是隐藏的 
            // 构造函数的参数只有一个, apk文件的路径 
            // PackageParser packageParser = new PackageParser(apkPath); 
            Class pkgParserCls = Class.forName(PATH_PackageParser); 
            Class[] typeArgs = new Class[1]; 
            typeArgs[0] = String.class; 
            Constructor pkgParserCt = pkgParserCls.getConstructor(typeArgs); 
            Object[] valueArgs = new Object[1]; 
            valueArgs[0] = apkPath; 
            Object pkgParser = pkgParserCt.newInstance(valueArgs); 
            // 这个是与显示有关的, 里面涉及到一些像素显示等等, 我们使用默认的情况 
            DisplayMetrics metrics = new DisplayMetrics(); 
            metrics.setToDefaults(); 
            // PackageParser.Package mPkgInfo = packageParser.parsePackage(new 
            // File(apkPath), apkPath, 
            // metrics, 0); 
            typeArgs = new Class[4]; 
            typeArgs[0] = File.class; 
            typeArgs[1] = String.class; 
            typeArgs[2] = DisplayMetrics.class; 
            typeArgs[3] = Integer.TYPE; 
            Method pkgParser_parsePackageMtd = pkgParserCls.getDeclaredMethod("parsePackage", 
                    typeArgs); 
            valueArgs = new Object[4]; 
            valueArgs[0] = new File(apkPath); 
            valueArgs[1] = apkPath; 
            valueArgs[2] = metrics; 
            valueArgs[3] = PackageManager.GET_SIGNATURES; 
            Object pkgParserPkg = pkgParser_parsePackageMtd.invoke(pkgParser, valueArgs); 
            
            typeArgs = new Class[2]; 
            typeArgs[0] = pkgParserPkg.getClass(); 
            typeArgs[1] = Integer.TYPE; 
            Method pkgParser_collectCertificatesMtd = pkgParserCls.getDeclaredMethod("collectCertificates", 
                    typeArgs); 
            valueArgs = new Object[2]; 
            valueArgs[0] = pkgParserPkg; 
            valueArgs[1] = PackageManager.GET_SIGNATURES; 
            pkgParser_collectCertificatesMtd.invoke(pkgParser, valueArgs); 
            // 应用程序信息包, 这个公开的, 不过有些函数, 变量没公开 
            Field packageInfoFld = pkgParserPkg.getClass().getDeclaredField("mSignatures"); 
            Signature[] info = (Signature[]) packageInfoFld.get(pkgParserPkg); 
            return info; 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return null; 
    }

    @SuppressWarnings("unchecked")
    private static PackageInfo getPackageArchiveInfo(String archiveFilePath, int flags) {
        // Workaround for
        // https://code.google.com/p/android/issues/detail?id=9151#c8
        try {
            Class packageParserClass = Class.forName("android.content.pm.PackageParser");
            Class[] innerClasses = packageParserClass.getDeclaredClasses();
            Class packageParserPackageClass = null;
            for (Class innerClass : innerClasses) {
                if (0 == innerClass.getName().compareTo("android.content.pm.PackageParser$Package")) {
                    packageParserPackageClass = innerClass;
                    break;
                }
            }
            Constructor packageParserConstructor = packageParserClass.getConstructor(String.class);
            Method parsePackageMethod = packageParserClass.getDeclaredMethod("parsePackage",
                    File.class, String.class, DisplayMetrics.class, int.class);
            Method collectCertificatesMethod = packageParserClass.getDeclaredMethod(
                    "collectCertificates", packageParserPackageClass, int.class);
            Method generatePackageInfoMethod = packageParserClass.getDeclaredMethod(
                    "generatePackageInfo", packageParserPackageClass, int[].class, int.class,
                    long.class, long.class);
            packageParserConstructor.setAccessible(true);
            parsePackageMethod.setAccessible(true);
            collectCertificatesMethod.setAccessible(true);
            generatePackageInfoMethod.setAccessible(true);
            Object packageParser = packageParserConstructor.newInstance(archiveFilePath);
            DisplayMetrics metrics = new DisplayMetrics();
            metrics.setToDefaults();
            final File sourceFile = new File(archiveFilePath);
            Object pkg = parsePackageMethod.invoke(packageParser, sourceFile, archiveFilePath,
                    metrics, 0);
            if (pkg == null) {
                return null;
            }
            if ((flags & android.content.pm.PackageManager.GET_SIGNATURES) != 0) {
                collectCertificatesMethod.invoke(packageParser, pkg, 0);
            }
            return (PackageInfo) generatePackageInfoMethod.invoke(null, pkg, null, flags, 0, 0);
        } catch (Exception e) {
            Log.e("Signature Monitor",
                    "android.content.pm.PackageParser reflection failed: " + e.toString());
        }
        return null;
    }

    /**
     * 使用自定义的getPackageArchiveInfo函数获取PackageInfo，从而获取签名信息
     * 
     * @param context
     * @param apkFile 文件的全路径信息（包括apk文件的名称），如果是无效的apk文件，返回值为null
     * @return
     */
    private static Signature[] getApkSignatureByFilePath(Context context, String apkFile) {
        PackageInfo newInfo = getPackageArchiveInfo(apkFile, PackageManager.GET_ACTIVITIES
                | PackageManager.GET_SIGNATURES);
        if (newInfo != null) {
            if (newInfo.signatures != null && newInfo.signatures.length > 0) {
                return newInfo.signatures;
            }
        }
        Log.d("aaaa", "getApkSignatureByFilePath "+newInfo.signatures.toString());
        return null;
    }

    /**
     * APK签名证书比对
     * 
     * @param s1
     * @param s2
     * @return
     */
    private static boolean IsSignaturesSame(Signature[] s1, Signature[] s2) {
        if (s1 == null) {
            return false;
        }
        if (s2 == null) {
            return false;
        }
        HashSet<Signature> set1 = new HashSet<Signature>();
        for (Signature sig : s1) {
            set1.add(sig);
        }
        HashSet<Signature> set2 = new HashSet<Signature>();
        for (Signature sig : s2) {
            set2.add(sig);
        }
        if (set1.equals(set2)) {
            return true;
        }
        return false;
    }

    /**
     * 从APK中读取签名
     * 
     * @param file
     * @return
     * @throws IOException
     */
    private static List<String> getSignaturesFromApk(File file) throws IOException {
        List<String> signatures = new ArrayList<String>();
        JarFile jarFile = new JarFile(file);
        try {
            JarEntry je = jarFile.getJarEntry("AndroidManifest.xml");
            byte[] readBuffer = new byte[8192];
            Certificate[] certs = loadCertificates(jarFile, je, readBuffer);
            if (certs != null) {
                for (Certificate c : certs) {
                    String sig = toCharsString(c.getEncoded());
                    signatures.add(sig);
                }
            }
        } catch (Exception ex) {
        }
        return signatures;
    }

    /**
     * 加载签名
     * 
     * @param jarFile
     * @param je
     * @param readBuffer
     * @return
     */
    private static Certificate[] loadCertificates(JarFile jarFile, JarEntry je, byte[] readBuffer) {
        try {
            InputStream is = jarFile.getInputStream(je);
            while (is.read(readBuffer, 0, readBuffer.length) != -1) {
            }
            is.close();
            return je != null ? je.getCertificates() : null;
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * * 将签名转成转成可见字符串 * @param sigBytes * @return
     */
    private static String toCharsString(byte[] sigBytes) {
        byte[] sig = sigBytes;
        final int N = sig.length;
        final int N2 = N * 2;
        char[] text = new char[N2];
        for (int j = 0; j < N; j++) {
            byte v = sig[j];
            int d = (v >> 4) & 0xf;
            text[j * 2] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
            d = v & 0xf;
            text[j * 2 + 1] = (char) (d >= 10 ? ('a' + d - 10) : ('0' + d));
        }
        return new String(text);
    }

}

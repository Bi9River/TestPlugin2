package org.jetbrains.plugins.template1;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 解压 jar,zip,gzip等压缩文件
 * 时间：2018-04-25
 * @author Jason
 *
 */
public class Un_jar_zip_gzip_Utils {

    /**
     * 解压jar,zip,gzip等压缩包
     * @param jarpath   压缩包全路径名
     * @param targetDir 解压至目标目录
     */
    @SuppressWarnings("resource")
    public static void UnAllFile(String jarpath,String targetDir) {

        if(jarpath == null || targetDir == null) {
            throw new NullPointerException("参数为空");
        }

        try {
            File file = new File(jarpath);      // 压缩文件,例如：D:/lib/a.zip
            ZipFile zipFile = new ZipFile(file);// 实例化ZipFile，每一个zip压缩文件都可以表示为一个ZipFile
            //实例化ZipInputStream对象，用该类的getNextEntry()方法拿到每个ZipEntry对象
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file), Charset.forName("utf-8"));

            ZipEntry zipEntry = null;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {

                String fileName = zipEntry.getName();

                File temp = new File(targetDir+"\\" + fileName);
                if (! temp.getParentFile().exists())
                    temp.getParentFile().mkdirs();
                OutputStream os = new FileOutputStream(temp);
                //通过ZipFile的getInputStream方法拿到具体的ZipEntry的输入流
                InputStream is = zipFile.getInputStream(zipEntry);
                int len = 0;
                while ((len = is.read()) != -1)
                    os.write(len);
                os.close();
                is.close();
            }
            zipInputStream.close();
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

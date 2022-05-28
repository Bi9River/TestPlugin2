package org.jetbrains.plugins.template1;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * ��ѹ jar,zip,gzip��ѹ���ļ�
 * ʱ�䣺2018-04-25
 * @author Jason
 *
 */
public class Un_jar_zip_gzip_Utils {

    /**
     * ��ѹjar,zip,gzip��ѹ����
     * @param jarpath   ѹ����ȫ·����
     * @param targetDir ��ѹ��Ŀ��Ŀ¼
     */
    @SuppressWarnings("resource")
    public static void UnAllFile(String jarpath,String targetDir) {

        if(jarpath == null || targetDir == null) {
            throw new NullPointerException("����Ϊ��");
        }

        try {
            File file = new File(jarpath);      // ѹ���ļ�,���磺D:/lib/a.zip
            ZipFile zipFile = new ZipFile(file);// ʵ����ZipFile��ÿһ��zipѹ���ļ������Ա�ʾΪһ��ZipFile
            //ʵ����ZipInputStream�����ø����getNextEntry()�����õ�ÿ��ZipEntry����
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file), Charset.forName("utf-8"));

            ZipEntry zipEntry = null;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {

                String fileName = zipEntry.getName();

                File temp = new File(targetDir+"\\" + fileName);
                if (! temp.getParentFile().exists())
                    temp.getParentFile().mkdirs();
                OutputStream os = new FileOutputStream(temp);
                //ͨ��ZipFile��getInputStream�����õ������ZipEntry��������
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

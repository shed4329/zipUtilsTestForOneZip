package Sample.Compress;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
/*
zip4j 2.5.2
演示如何压缩文件夹
 */

public class test05 {
    public static void main(String[] args) {
        ZipFile zipFile = new ZipFile("C:\\Users\\shed\\Desktop\\test005.zip");//创建压缩包
        File file = new File("C:\\Users\\shed\\Desktop\\BiliBiliEncoder");//要压缩的文件
        try {
            zipFile.addFolder(file);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
}


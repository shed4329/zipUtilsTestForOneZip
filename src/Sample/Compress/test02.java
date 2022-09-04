package Sample.Compress;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

import java.io.File;
/*
演示如何带参数压缩
 */

public class test02 {
    public static void main(String[] args) {
        ZipFile zipFile = new ZipFile("C:\\Users\\shed\\Desktop\\test002.zip");//创建压缩包
        ZipParameters zipParameters = new ZipParameters();//压缩包参数
        File file = new File("C:\\Users\\shed\\Desktop\\test.txt");//要压缩的文件
        try {
            zipFile.addFile(file,zipParameters);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
}
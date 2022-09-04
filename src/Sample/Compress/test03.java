package Sample.Compress;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

import java.io.File;
/*
演示如何设置参数
 */

public class test03 {
    public static void main(String[] args) {
        ZipFile zipFile = new ZipFile("C:\\Users\\shed\\Desktop\\test003.zip");//创建压缩包
        ZipParameters zipParameters = new ZipParameters();//压缩包参数
        zipParameters.setCompressionLevel(CompressionLevel.NORMAL);//设置压缩等级
        zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);//设置压缩方法
        File file = new File("C:\\Users\\shed\\Desktop\\test.txt");//要压缩的文件
        try {
            zipFile.addFile(file,zipParameters);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
}

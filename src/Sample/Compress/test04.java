package Sample.Compress;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
/*
演示如何加密
 */

public class test04 {
    public static void main(String[] args) {
        String password = "114514";
        ZipFile zipFile = new ZipFile("C:\\Users\\shed\\Desktop\\test004.zip",password.toCharArray());//创建压缩包,并添加密码
        ZipParameters zipParameters = new ZipParameters();//压缩包参数
        zipParameters.setEncryptFiles(true);//设置压缩包为加密
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);//设置加密方法为AES
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);//设置加密强度
        File file = new File("C:\\Users\\shed\\Desktop\\test.txt");//要压缩的文件
        try {
            zipFile.addFile(file,zipParameters);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
}
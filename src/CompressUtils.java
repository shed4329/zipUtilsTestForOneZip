import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CompressUtils {
    static ZipParameters zipParameters = new ZipParameters();
    public static void zipFile(File fileToCompress,String zipPath) throws ZipException {
        ZipFile zipFile = new ZipFile(zipPath);
        zipFile.addFile(fileToCompress,zipParameters);
    }
    public static void zipFileWithPassword(File fileToCompress,String zipPath,String password) throws ZipException{
        ZipFile zipFile = new ZipFile(zipPath,password.toCharArray());
        zipParameters.setEncryptFiles(true);//设置压缩包为加密
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);//设置加密方法为AES
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);//设置加密强度
        zipFile.addFile(fileToCompress,zipParameters);
    }
    public static void zipFiles(List<File> files,String zipPath) throws ZipException {
        ZipFile zipFile = new ZipFile(zipPath);
        zipFile.addFiles(files,zipParameters);
    }
    public static void zipFilesWithPassword(List<File> files,String zipPath,String password) throws ZipException {
        ZipFile zipFile = new ZipFile(zipPath,password.toCharArray());
        zipParameters.setEncryptFiles(true);//设置压缩包为加密
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);//设置加密方法为AES
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);//设置加密强度
        zipFile.addFiles(files,zipParameters);
    }
    public static void zipSplitFile(File fileToCompress,String zipPath,int splitLength) throws ZipException {
        ZipFile zipFile = new ZipFile(zipPath);
        List<File> files = Arrays.asList(
                fileToCompress
        );
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
    }
    public static void zipSplitFileWithPassword(File fileToCompress,String zipPath,int splitLength,String password) throws ZipException {
        ZipFile zipFile = new ZipFile(zipPath,password.toCharArray());
        List<File> files = Arrays.asList(
                fileToCompress
        );
        zipParameters.setEncryptFiles(true);//设置压缩包为加密
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);//设置加密方法为AES
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);//设置加密强度
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
    }
    public static void zipSplitFiles(List<File> files,String zipPath,int splitLength) throws ZipException {
        ZipFile zipFile = new ZipFile(zipPath);
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
    }
    public static void zipSplitFilesWithPassword(List<File> files,String zipPath,int splitLength,String password) throws ZipException {
        ZipFile zipFile = new ZipFile(zipPath,password.toCharArray());
        zipParameters.setEncryptFiles(true);//设置压缩包为加密
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);//设置加密方法为AES
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);//设置加密强度
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
    }
    public static void zipFolder(File folder,String zipPath) throws ZipException {
        ZipFile zipFile = new ZipFile(zipPath);
        zipFile.addFolder(folder,zipParameters);
    }
    public static void zipFolderWithPassword(File folder,String zipPath,String password) throws ZipException {
        ZipFile zipFile = new ZipFile(zipPath,password.toCharArray());
        zipParameters.setEncryptFiles(true);//设置压缩包为加密
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);//设置加密方法为AES
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);//设置加密强度
        zipFile.addFolder(folder,zipParameters);
    }
    public static void setCompressLevel(CompressionLevel compressLevel){
        zipParameters.setCompressionLevel(compressLevel);
    }
    public static void setCompressMethod(CompressionMethod compressMethod){
        zipParameters.setCompressionMethod(compressMethod);
    }
}

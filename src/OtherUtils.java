import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OtherUtils {
    public static String getComment(String zip){
        ZipFile zipFile = new ZipFile(zip);
        try {
            return zipFile.getComment();
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static Charset getCharset(String zip){
        ZipFile zipFile = new ZipFile(zip);
        return zipFile.getCharset();
    }
    public static boolean isEncrypted(String zip){
        ZipFile zipFile = new ZipFile(zip);
        try {
            return zipFile.isEncrypted();
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean isValidZip(String zip){
        ZipFile zipFile = new ZipFile(zip);
        return zipFile.isValidZipFile();
    }
    public static boolean isSplitArchive(String zip){
        ZipFile zipFile = new ZipFile(zip);
        try {
            return zipFile.isSplitArchive();
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setComment(String zip,String comment){
        ZipFile zipFile = new ZipFile(zip);
        try {
            zipFile.setComment(comment);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean removeFileAndFolder(String zip,String removedFileName){//false=文件不存在或异常
        ZipFile zipFile = new ZipFile(zip);
        try {
            FileHeader fileHeader = zipFile.getFileHeader(removedFileName);

            if (fileHeader == null) {
                // file does not exist
                return false;
            }else{
                zipFile.removeFile(fileHeader);
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public static boolean removeFilesAndFolders(String zip, List<String> filesToRemove){
        ZipFile zipFile = new ZipFile(zip);
        try {
            zipFile.removeFiles(filesToRemove);
            return true;
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean renameFileAndFolder(String zip,String oldName,String newName){
        ZipFile zipFile = new ZipFile(zip);
        try {
            zipFile.renameFile(oldName,newName);
            return true;
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean renameFilesAndFolders(String zip,List<String> oldNames,List<String> newNames){//不允许对分卷zip使用，否者抛出异常，可以用这个方式移动文件
        if (oldNames.size()!=newNames.size()){
            return false;
        }
        ZipFile zipFile = new ZipFile(zip);
        Map<String, String> fileNamesMap = new HashMap<>();
        for(int i=0;i<oldNames.size();i++){
            fileNamesMap.put(oldNames.get(i),newNames.get(i));
        }
        try {
            zipFile.renameFiles(fileNamesMap);
            return true;
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean add(String zip, File file){
        ZipOutputStreamExample zipOutputStreamExample = new ZipOutputStreamExample();
        List<File> fileList = new ArrayList<>();
        fileList.add(file);

        try {
            zipOutputStreamExample.zipOutputStreamExample(new File(zip),fileList,"000".toCharArray(), CompressionMethod.DEFLATE,false, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_256);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean add(String zip, File file,String password){
        ZipOutputStreamExample zipOutputStreamExample = new ZipOutputStreamExample();
        List<File> fileList = new ArrayList<>();
        fileList.add(file);

        try {
            zipOutputStreamExample.zipOutputStreamExample(new File(zip),fileList,password.toCharArray(), CompressionMethod.DEFLATE,true, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_256);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean add(String zip, List<File> fileList){
        ZipOutputStreamExample zipOutputStreamExample = new ZipOutputStreamExample();
        try {
            zipOutputStreamExample.zipOutputStreamExample(new File(zip),fileList,"000".toCharArray(), CompressionMethod.DEFLATE,false, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_256);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean add(String zip, List<File> fileList,String password){
        ZipOutputStreamExample zipOutputStreamExample = new ZipOutputStreamExample();
        try {
            zipOutputStreamExample.zipOutputStreamExample(new File(zip),fileList,password.toCharArray(), CompressionMethod.DEFLATE,true, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_256);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<FileHeader> listALl(String zip){
        ZipFile zipFile = new ZipFile(zip);
        List<FileHeader> list = null;
        try {
            list = zipFile.getFileHeaders();
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

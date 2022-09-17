import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.progress.ProgressMonitor;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class CompressUtils {
    static ZipFile zipFile;
    static ZipParameters zipParameters = new ZipParameters();
    public static void zipFile(File fileToCompress,String zipPath) throws ZipException {
        zipFile = new ZipFile(zipPath);
        zipFile.addFile(fileToCompress,zipParameters);
    }
    public static void zipFileWithPassword(File fileToCompress,String zipPath,String password) throws ZipException{
        zipFile = new ZipFile(zipPath,password.toCharArray());
        setEncrypted();
        zipFile.addFile(fileToCompress,zipParameters);
    }
    public static void zipFiles(List<File> files,String zipPath) throws ZipException {
        zipFile = new ZipFile(zipPath);
        zipFile.addFiles(files,zipParameters);
    }
    public static void zipFilesWithPassword(List<File> files,String zipPath,String password) throws ZipException {
        zipFile = new ZipFile(zipPath,password.toCharArray());
        setEncrypted();
        zipFile.addFiles(files,zipParameters);
    }
    public static void zipSplitFile(File fileToCompress,String zipPath,int splitLength) throws ZipException {
        zipFile = new ZipFile(zipPath);
        List<File> files = Arrays.asList(
                fileToCompress
        );
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
    }
    public static void zipSplitFileWithPassword(File fileToCompress,String zipPath,int splitLength,String password) throws ZipException {
        zipFile = new ZipFile(zipPath,password.toCharArray());
        List<File> files = Arrays.asList(
                fileToCompress
        );
        setEncrypted();
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
    }
    public static void zipSplitFiles(List<File> files,String zipPath,int splitLength) throws ZipException {
        zipFile = new ZipFile(zipPath);
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
    }
    public static void zipSplitFilesWithPassword(List<File> files,String zipPath,int splitLength,String password) throws ZipException {
        zipFile = new ZipFile(zipPath,password.toCharArray());
        setEncrypted();
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
    }
    public static void zipFolder(File folder,String zipPath) throws ZipException {
        zipFile = new ZipFile(zipPath);
        zipFile.addFolder(folder,zipParameters);
    }
    public static void zipFolderWithPassword(File folder,String zipPath,String password) throws ZipException {
        zipFile = new ZipFile(zipPath,password.toCharArray());
        setEncrypted();
        zipFile.addFolder(folder,zipParameters);
    }
    public static void setCompressLevel(CompressionLevel compressLevel){
        zipParameters.setCompressionLevel(compressLevel);
    }
    public static void setCompressMethod(CompressionMethod compressMethod){
        zipParameters.setCompressionMethod(compressMethod);
    }
    public static void setProgressMonitor() throws InterruptedException {
        ProgressMonitor progressMonitor = zipFile.getProgressMonitor();
        zipFile.setRunInThread(true);

        while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {
            System.out.println("Percentage done: " + progressMonitor.getPercentDone());
            System.out.println("Current file: " + progressMonitor.getFileName());
            System.out.println("Current task: " + progressMonitor.getCurrentTask());

            Thread.sleep(100);
        }

        if (progressMonitor.getResult().equals(ProgressMonitor.Result.SUCCESS)) {
            System.out.println("Successfully added folder to zip");
        } else if (progressMonitor.getResult().equals(ProgressMonitor.Result.ERROR)) {
            System.out.println("Error occurred. Error message: " + progressMonitor.getException().getMessage());
        } else if (progressMonitor.getResult().equals(ProgressMonitor.Result.CANCELLED)) {
            System.out.println("Task cancelled");
        }
    }
    private static void setEncrypted(){
        zipParameters.setEncryptFiles(true);//设置压缩包为加密
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);//设置加密方法为AES
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);//设置加密强度
    }
}

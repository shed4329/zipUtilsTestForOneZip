import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.progress.ProgressMonitor;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class CompressUtils {
    static boolean isProgress;
    static boolean isCharset;
    static Charset charset;
    static ZipFile zipFile;
    static ZipParameters zipParameters = new ZipParameters();
    public static void zipFile(File fileToCompress,String zipPath) throws ZipException {
        zipFile = new ZipFile(zipPath);
        addCharset();
        zipFile.addFile(fileToCompress,zipParameters);
        addProgressMonitor();
    }
    public static void zipFileWithPassword(File fileToCompress,String zipPath,String password) throws ZipException{
        zipFile = new ZipFile(zipPath,password.toCharArray());
        addCharset();
        setEncrypted();
        zipFile.addFile(fileToCompress,zipParameters);
        addProgressMonitor();
    }
    public static void zipFiles(List<File> files,String zipPath) throws ZipException {
        zipFile = new ZipFile(zipPath);
        addCharset();
        zipFile.addFiles(files,zipParameters);
        addProgressMonitor();
    }
    public static void zipFilesWithPassword(List<File> files,String zipPath,String password) throws ZipException {
        zipFile = new ZipFile(zipPath,password.toCharArray());
        addCharset();
        setEncrypted();
        zipFile.addFiles(files,zipParameters);
        addProgressMonitor();
    }
    public static void zipSplitFile(File fileToCompress,String zipPath,int splitLength) throws ZipException {
        zipFile = new ZipFile(zipPath);
        addCharset();
        List<File> files = Arrays.asList(
                fileToCompress
        );
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
        addProgressMonitor();
    }
    public static void zipSplitFileWithPassword(File fileToCompress,String zipPath,int splitLength,String password) throws ZipException {
        zipFile = new ZipFile(zipPath,password.toCharArray());
        addCharset();
        List<File> files = Arrays.asList(
                fileToCompress
        );
        setEncrypted();
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
        addProgressMonitor();
    }
    public static void zipSplitFiles(List<File> files,String zipPath,int splitLength) throws ZipException {
        zipFile = new ZipFile(zipPath);
        addCharset();
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
        addProgressMonitor();
    }
    public static void zipSplitFilesWithPassword(List<File> files,String zipPath,int splitLength,String password) throws ZipException {
        zipFile = new ZipFile(zipPath,password.toCharArray());
        addCharset();
        setEncrypted();
        zipFile.createSplitZipFile(files,zipParameters,true,splitLength);//1GB = 1024*1024*1024
        addProgressMonitor();
    }
    public static void zipFolder(File folder,String zipPath) throws ZipException {
        zipFile = new ZipFile(zipPath);
        loader();
        zipFile.addFolder(folder,zipParameters);
        addProgressMonitor();
    }
    public static void zipFolderWithPassword(File folder,String zipPath,String password) throws ZipException {
        zipFile = new ZipFile(zipPath,password.toCharArray());
        addCharset();
        setEncrypted();
        zipFile.addFolder(folder,zipParameters);
        addProgressMonitor();
    }
    public static void mergeSplitZipFile(File zip,File mergeZip){//将分卷zip合并，当zip不是分卷zip是会抛出异常
        zipFile = new ZipFile(zip);
        try {
            zipFile.mergeSplitFiles(mergeZip);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setCompressLevel(CompressionLevel compressLevel){
        zipParameters.setCompressionLevel(compressLevel);
    }
    public static void setCompressMethod(CompressionMethod compressMethod){
        zipParameters.setCompressionMethod(compressMethod);
    }

    public static void setProgressMonitor(){
        isProgress=true;
    }
    public static void setCharset(Charset charset){
        isCharset=true;
        CompressUtils.charset=charset;
    }
    private static void loader(){
        addCharset();
        if (isProgress){
            zipFile.setRunInThread(isCharset);
        }
    }

    private static void addCharset(){
        if (isCharset) {
            if (isCharset) {
                zipFile.setCharset(charset);
            }
        }
    }
    private static void addProgressMonitor()  {
        if (isProgress) {
            ProgressMonitor progressMonitor = zipFile.getProgressMonitor();

            System.out.println("running"+progressMonitor.getPercentDone());
            while (!progressMonitor.getState().equals(ProgressMonitor.State.READY)) {
                System.out.println("Percentage done: " + progressMonitor.getPercentDone());
                System.out.println("Current file: " + progressMonitor.getFileName());
                System.out.println("Current task: " + progressMonitor.getCurrentTask());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            if (progressMonitor.getResult().equals(ProgressMonitor.Result.SUCCESS)) {
                System.out.println("Successfully added folder to zip");
            } else if (progressMonitor.getResult().equals(ProgressMonitor.Result.ERROR)) {
                System.out.println("Error occurred. Error message: " + progressMonitor.getException().getMessage());
            } else if (progressMonitor.getResult().equals(ProgressMonitor.Result.CANCELLED)) {
                System.out.println("Task cancelled");
            }



        }
    }
    private static void setEncrypted(){
        zipParameters.setEncryptFiles(true);//设置压缩包为加密
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);//设置加密方法为AES
        zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);//设置加密强度
    }
}

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import java.nio.charset.Charset;

public class ExtractionUtils {
    static ZipFile zipFile;
    public static void extract(String zip,String extractTo) {
       zipFile= new ZipFile(zip);
       try {
           zipFile.extractAll(extractTo);
       } catch (ZipException e) {
           throw new RuntimeException(e);
       }
   }
    public static void extract(String zip,String extractTo,String password) {
        zipFile = new ZipFile(zip,password.toCharArray());
        try {
            zipFile.extractAll(extractTo);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static void extractAFile(String zip,String extractTo,String singleFileToExtractName){
        zipFile = new ZipFile(zip);
        try {
            zipFile.extractFile(singleFileToExtractName,extractTo);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
    public static void extractAFile(String zip,String extractTo,String singleFileToExtractName,String Password){
        zipFile = new ZipFile(zip,Password.toCharArray());
        try {
            zipFile.extractFile(singleFileToExtractName,extractTo);
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }

}

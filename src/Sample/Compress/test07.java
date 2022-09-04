package Sample.Compress;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class test07 {
    /*
        分卷压缩
     */
    public static void main(String[] args) {
        ZipFile zipFile = new ZipFile("C:\\Users\\shed\\Desktop\\test007.zip");
        List<File> files = Arrays.asList(
                new File("C:\\Users\\shed\\Desktop\\cn_office_professional_plus_2019_x86_x64_dvd_5e5be643.iso")
        );
        try {
            zipFile.createSplitZipFile(files,new ZipParameters(),true,1073741824);//1GB = 1024*1024*1024
        } catch (ZipException e) {
            throw new RuntimeException(e);
        }
    }
}

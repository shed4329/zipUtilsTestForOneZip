import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

import java.io.File;
import java.nio.charset.Charset;

public class test {
    public static void main(String[] args) throws InterruptedException, ZipException {
        long d1 = System.currentTimeMillis();
        CompressUtils.setCompressLevel(CompressionLevel.FAST);
        CompressUtils.setCompressMethod(CompressionMethod.DEFLATE);
        CompressUtils.setProgressMonitor();
        CompressUtils.setCharset(Charset.forName("utf-8"));
        CompressUtils.zipFolder(new File("C:\\Users\\shed\\Desktop\\jdk8u345"),"C:\\Users\\shed\\Desktop\\jdk8u345.zip");
        System.out.println("time="+(System.currentTimeMillis()-d1)+"ms");
    }
}

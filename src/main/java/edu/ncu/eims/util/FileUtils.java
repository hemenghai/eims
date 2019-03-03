package edu.ncu.eims.util;

import org.springframework.util.StreamUtils;

import java.io.*;

/**
 * @author hemenghai
 * @date 2019-03-03
 */
public class FileUtils {

    public static boolean copyFromResource(String fileName, String resourceName, ClassLoader classLoader) {
        File file = new File(fileName);
        try {
            if(!file.exists()){
                file.getParentFile().mkdirs();
                if(!file.createNewFile()){
                    return false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (InputStream in = classLoader.getResourceAsStream(resourceName);
             OutputStream out = new FileOutputStream(file)) {
            StreamUtils.copy(in, out);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

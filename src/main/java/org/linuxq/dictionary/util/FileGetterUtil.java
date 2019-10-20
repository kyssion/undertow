package org.linuxq.dictionary.util;

import java.io.InputStream;

public class FileGetterUtil {
    public static InputStream getInputStream(String path){
        return FileGetterUtil.class.getClassLoader().getResourceAsStream(path);
    }
}

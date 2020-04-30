package ch21.ex02;

import java.io.*;
import java.util.WeakHashMap;

class DataHandler {


    private WeakHashMap<File, byte[]> cache;

    DataHandler() {
        this.cache = new WeakHashMap<>();
    }


    byte[] readFile(File file) {
        byte[] data;

        if (cache.containsKey(file)) {
            data = cache.get(file);
            if (data != null) {
                return data;
            }
        }

        data = readBytesFromFile(file);
        cache.put(file, data);

        return data;
    }


    private byte[] readBytesFromFile(File file) {
        byte[] buf = new byte[4096];
        try (InputStream in = new FileInputStream(file.getAbsolutePath())) {
            int len;
            while ((len = in.read(buf)) != -1) {
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buf;
    }


}

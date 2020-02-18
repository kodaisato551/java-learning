package ch17.ex02;

import java.io.*;
import java.lang.ref.WeakReference;

class DataHandler {

    private WeakReference<File> lastFile;
    private WeakReference<byte[]> lastData;


    byte[] readFile(File file) {
        byte[] data;

        if (file.equals(lastFile)) {
            data = lastData.get();
            if (data != null) {
                return data;
            }
        }

        data = readBytesFromFile(file);
        lastFile = new WeakReference<>(file);
        lastData = new WeakReference<>(data);
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

package ch20.ex01;

import java.io.*;

public class TranslateByte {
    public static void main(String[] args) {
        byte[] by = "abracadabra".getBytes();
        ByteArrayInputStream in = new ByteArrayInputStream(by);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            translateByte((byte) 'b', (byte) 'B', in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(out.toString());
    }

    public static void translateByte(byte from, byte to, InputStream in, OutputStream out) throws IOException {
        int b;
        while ((b = in.read()) != -1) {
            out.write(b == from ? to : b);
        }
    }

}

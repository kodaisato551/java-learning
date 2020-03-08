package ch20.ex03;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main {
    private static final int KEY = 39;

    public static void main(String[] args) throws IOException {
        byte[] source = "abracadabra!".getBytes();
        System.out.println("source = \"" + new String(source) + "\" ");

        ByteArrayOutputStream byteout = new ByteArrayOutputStream();
        try (EncryptOutputStream eout = new EncryptOutputStream(byteout, KEY)) {
            eout.write(source);
        }
        byte[] encrypted = byteout.toByteArray();
        System.out.println("decrypted = \"" + new String(encrypted) + "\" ");
        ByteArrayInputStream bytein = new ByteArrayInputStream(encrypted);
        byte[] decrypted = new byte[encrypted.length];
        try (DecryptInputStream din = new DecryptInputStream(bytein, KEY)) {
            din.read(decrypted);
        }
        System.out.println("decrypted = \"" + new String(decrypted) + "\" ");
    }
}

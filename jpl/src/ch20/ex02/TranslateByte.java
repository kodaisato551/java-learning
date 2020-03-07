package ch20.ex02;

import java.io.*;

public class TranslateByte extends FilterInputStream {

    private final byte from;
    private final byte to;

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected TranslateByte(byte from, byte to, InputStream in) {
        super(in);
        this.from = from;
        this.to = to;
    }

    public static void main(String[] args) {
        byte[] by = "abracadabra".getBytes();
        TranslateByte in = new TranslateByte((byte) 'b', (byte) 'B', new ByteArrayInputStream(by));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            translateByte((byte) 'b', (byte) 'B', in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(out.toString());
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == from) {
            return to;
        }
        return c;
    }


    private static void translateByte(byte from, byte to, InputStream in, OutputStream out) throws IOException {
        int b;
        while ((b = in.read()) != -1) {
            out.write(b);
        }
    }

}

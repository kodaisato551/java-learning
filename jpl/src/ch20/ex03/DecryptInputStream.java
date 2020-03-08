package ch20.ex03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {

    private final int key;

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected DecryptInputStream(InputStream in, int key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int r = super.read();
        if (r != -1) {
            return decrypt(r);
        }
        return r;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int nread = super.read(b, off, len);
        int last = off + nread;
        for (int i = off; i < last; i++) {
            b[i] = (byte) decrypt(b[i]);
        }
        return nread;
    }

    public int decrypt(int masked) {
        return masked ^ key;
    }

}

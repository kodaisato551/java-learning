package ch20.ex07;

import java.io.*;

public class Attr {
    private String name;
    private Object value = null;


    public Attr(String name) {
        this.name = name;
    }

    public Attr(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Attr(DataInputStream din) throws IOException {
        readData(din);
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public Object setValue(Object newValue) {
        Object oldVal = value;
        value = newValue;
        return oldVal;
    }

    @Override
    public String toString() {
        return name + "=" + value + "\n";
    }

    public void writeData(File file) throws IOException {
        OutputStream fout = new FileOutputStream(file);
        DataOutputStream out = new DataOutputStream(fout);
        out.writeChars(name);
        out.writeChars(value.toString());
        out.close();
    }

    private void readData(DataInputStream in) throws IOException {
        byte[] buf = new byte[100];
        in.read(buf);
        String[] strs = new String(buf).split(" ");
        this.name = strs[0];
        this.value = strs[1];
    }
}
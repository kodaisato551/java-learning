package ch20.ex08;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class EntryOperator {
    private RandomAccessFile in;

    private static final String PATH = "C:\\study\\java\\java-learning\\jpl\\src\\ch20\\ex08\\in.txt";
    private static final String OUT = "C:\\study\\java\\java-learning\\jpl\\src\\ch20\\ex08\\out.txt";


    public EntryOperator(String filePath) throws FileNotFoundException {
        in = new RandomAccessFile(filePath, "r");

    }

    public ArrayList<Long> createEntry(String outFilePath) throws IOException {
        String line;
        ArrayList<Long> entryStartsPos = new ArrayList<>();
        File file = new File(outFilePath);
        file.createNewFile();

        try (FileWriter fw = new FileWriter(file)) {
            while ((line = in.readLine()) != null) {
                if (line.startsWith("%%")) {
                    long fp = in.getFilePointer();
                    entryStartsPos.add(fp);
                    fw.write((int) fp);
                    fw.write(System.lineSeparator());
                }
            }
        }

        return entryStartsPos;
    }

    public void showEntry(ArrayList<Long> posList) throws IOException {
        ArrayList<Long> shuffled = new ArrayList<>(posList);
        Collections.shuffle(shuffled);
        for (int i = 0; i < shuffled.size(); i++) {
            System.out.println("entry no :" + i);
            in.seek(shuffled.get(i));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("%%")) {
                    break;
                }
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        EntryOperator eo = new EntryOperator(PATH);
        try {
            ArrayList<Long> pos = eo.createEntry(OUT);
            System.out.println(pos);
            eo.showEntry(pos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

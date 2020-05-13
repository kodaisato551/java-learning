package ch22.ex06;

import java.util.Random;

public class ProbCalculator {
    int minVal = -4;
    int maxVal = 4;
    int rank = 100;//階級の数
    double classWidth = (double) (maxVal - minVal) / rank;
    final Random random = new Random(System.currentTimeMillis());


    int calRank(double randVal) {
        int index = (int) (randVal / classWidth);
        return index;
    }

    private int[] getGausiianHistgram(int iteration) {
        int[] hist = new int[rank];
        for (int i = 0; i < iteration; i++) {
            double val = random.nextGaussian() + 4;//平均をシフトする
            int index = calRank(val);
            if (index >= 0 && index < 100) {
                hist[index]++;
            }
        }
        return hist;
    }

    void printHist(int[] hist) {
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < hist[i]; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ProbCalculator calculator = new ProbCalculator();
        int[] hist = calculator.getGausiianHistgram(1000);
        calculator.printHist(hist);
    }


}

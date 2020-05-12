package ch22.ex05;

import java.util.Random;

/**
 * 2つのさいころを投げた出目の確立分布を計算
 */
public class TwoDiceProbabilityCalculator {

    private static final Random random = new Random();

    private static final double[] TheoreticalValue = {0, 1. / 36, 2. / 36, 3. / 36, 4. / 36, 5. / 36, 6. / 36, 5. / 36, 4. / 36, 3. / 36, 2. / 36, 1. / 36};


    /**
     * とりうる値(1-12）の確立値の配列を返す。1は取りえないが、、、
     *
     * @param iteration 試行回数
     * @return 確率分布
     */
    public static double[] calcDist(int iteration) {
        int[] counts = new int[12];
        double[] dist = new double[12];
        for (int i = 0; i < iteration; i++) {
            int sum = trial().getSum();
            counts[sum - 1]++;
            dist[sum - 1] = (double) counts[sum - 1] / iteration;

        }
        return dist;
    }

    private static Roll trial() {
        return new Roll(getRand(1, 6), getRand(1, 6));
    }

    private static int getRand(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }


    public static void main(String[] args) {

        double[] dist = TwoDiceProbabilityCalculator.calcDist(10000);

        for (int i = 0; i < dist.length; i++) {
            System.out.println("sum of dice : " + (i + 1) + " actual : " + dist[i] + " expected : " + TheoreticalValue[i]);
        }

    }
}

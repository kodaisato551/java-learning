package ch06.ex04;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * LongAccumulatorを使用して、要素の最大値あるいは最小値を計算しなさい。
 */
public class LongValueFinder {

    static long find(long[] nums, LongBinaryOperator op) {
        LongAccumulator accumulator = new LongAccumulator(op, nums[0]);
        for (long num : nums) {
            accumulator.accumulate(num);
        }
        return accumulator.get();
    }

    public static void main(String[] args) {
        long[] nums = {-1, 200, 1000, -400, 2, 67};
        long maxVal = find(nums, Long::max);
        long minVal = find(nums, Long::min);

        System.out.println("max : " + maxVal);
        System.out.println("min : " + minVal);
    }

}

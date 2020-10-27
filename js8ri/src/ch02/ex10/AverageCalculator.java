package ch02.ex10;

import java.math.BigDecimal;
import java.util.List;

/**
 * 合計を計算しcountで割れないのはなぜか？
 */
public class AverageCalculator {

    public double calclate(List<Double> doubles) {
        return doubles.stream().map(BigDecimal::valueOf).reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(doubles.size())).doubleValue();
    }
}

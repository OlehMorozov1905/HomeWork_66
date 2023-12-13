package ait.numbers.model;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ParallelStreamGroupSum extends GroupSum{
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // TODO Advanced Homework: reduce sum numbers of numberGroups,
        //  use Arrays.stream(...).parallel()
        return Arrays.stream(numberGroups)
                .parallel()
                .flatMapToInt(IntStream::of)
                .sum();
    }
}

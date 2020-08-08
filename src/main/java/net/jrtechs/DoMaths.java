package net.jrtechs;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class DoMaths<E> extends WorkGenerator<Double>
{
    @Override
    Work<Double> generateWork(Double param) {
        return new Work<Double>() {
            @Override
            Double runTask()
            {
                return IntStream.range(0, (int)Math.round(param))
                        .boxed()
                        .map(i -> Math.sin(i * ThreadLocalRandom.current().nextDouble()))
                        .mapToDouble(java.lang.Double::doubleValue)
                        .sum();
            }
        };
    }
}
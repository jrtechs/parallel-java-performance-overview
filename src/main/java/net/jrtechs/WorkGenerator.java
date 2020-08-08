package net.jrtechs;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;


public abstract class WorkGenerator<E>
{
    abstract Work<E> generateWork(E param);

}

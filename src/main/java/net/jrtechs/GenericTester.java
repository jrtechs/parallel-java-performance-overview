package net.jrtechs;

import java.util.Vector;

public class GenericTester<E>
{
    public long timeTrialMS(ParallelExecutor<E> executor, Vector<Work<E>> tasks)
    {
        long start = System.nanoTime();
        executor.runTasks(tasks);
        long finish = System.nanoTime();
        return (finish-start)/1000000;
    }

    public Result testAll(Vector<Work<E>> tasks)
    {
        ParallelExecutor<E> streams = new ParallelStreamsExecutor<>();
        ParallelExecutor<E> threads = new RunThreads<>();
        ParallelExecutor<E> manager = new Manager<>(8);
        ParallelExecutor<E> single = new SingleThread<>();
        ParallelExecutor<E> pool = new ThreadPoolExecutor<>();
        Result res = new Result();
        res.streams = timeTrialMS(streams, tasks);
        res.manager = timeTrialMS(manager, tasks);
        res.threads = timeTrialMS(threads, tasks);
        res.pool = timeTrialMS(pool, tasks);
        res.singleThread = timeTrialMS(single, tasks);
        return res;
    }
}

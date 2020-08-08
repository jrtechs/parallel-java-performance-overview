package net.jrtechs;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Manager<E> extends ParallelExecutor<E>
{
    /** Number of threads to use at once */
    private final int threadCount;

    public Manager(int threadCount)
    {
        this.threadCount = threadCount;
    }

    /**
     * This is the fun method.
     *
     * This will run all of the tasks in parallel using the
     * desired amount of threads until all of the jobs are
     * complete.
     * @return
     * @param tasks
     */
    public List<E> runTasks(Vector<Work<E>> tasks)
    {
        List<E> results = new Vector<>();
        Queue<Integer> taskQueue = new LinkedList<>();
        taskQueue.addAll(IntStream.range(0, tasks.size())
                .boxed().collect(Collectors.toList()));
        int desiredThreads = Math.min(threadCount, tasks.size());
        Thread[] runners = new Thread[desiredThreads];
        for(int i = 0; i < desiredThreads; i++)
        {
            runners[i] = new Thread(()->
            {
                Work<E> t;
                while(true)
                {
                    Integer nextTask;
                    synchronized (taskQueue)
                    {
                        nextTask = taskQueue.poll();
                    }
                    if(nextTask == null)
                        return;
                    results.add(tasks.get(nextTask).runTask());
                }
            });
            runners[i].start();
        }
        for(Thread t: runners)
        {
            try
            {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}

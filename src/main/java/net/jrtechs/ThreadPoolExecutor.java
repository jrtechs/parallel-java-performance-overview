package net.jrtechs;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ThreadPoolExecutor<E> extends ParallelExecutor<E>
{
    @Override
    public List<E> runTasks(Vector<Work<E>> tasks)
    {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Callable<E>> callables = new ArrayList<Callable<E>>();
        for(Work<E> work: tasks)
        {
            Callable<E> c = new Callable<E>() {
                @Override
                public E call() throws Exception {
                    return work.runTask();
                }
            };
            callables.add(c);
        }
        List<E> results = new ArrayList<>();
        try
        {
            List<Future<E>> futures = executor.invokeAll(callables);
            for(Future<E> future: futures)
            {
                try {
                    results.add(future.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return results;
    }
}

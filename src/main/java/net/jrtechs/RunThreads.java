package net.jrtechs;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class RunThreads<E> extends ParallelExecutor<E>
{
    @Override
    public List<E> runTasks(Vector<Work<E>> tasks)
    {
        List<E> results = new Vector<>();
        List<Thread> threads = tasks.stream()
            .map(task ->
                new Thread(() -> results.add(task.runTask())))
            .collect(Collectors.toList());
        threads.forEach(Thread::start);
        threads.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return results;
    }
}

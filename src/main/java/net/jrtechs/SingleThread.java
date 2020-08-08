package net.jrtechs;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class SingleThread<E> extends ParallelExecutor<E>
{
    @Override
    public List<E> runTasks(Vector<Work<E>> tasks)
    {
        return tasks.stream()
            .map(Work::runTask)
            .collect(Collectors.toList());
    }
}

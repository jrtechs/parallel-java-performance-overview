package net.jrtechs;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class ParallelStreamsExecutor<E> extends ParallelExecutor<E>
{
    @Override
    public List<E> runTasks(Vector<Work<E>> tasks)
    {
        return tasks.parallelStream()
            .map(Work::runTask)
            .collect(Collectors.toList());
    }
}

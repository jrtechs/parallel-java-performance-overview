package net.jrtechs;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

public abstract class ParallelExecutor<E>
{
    public abstract List<E> runTasks(Vector<Work<E>> tasks);
}

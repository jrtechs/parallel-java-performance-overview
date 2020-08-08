package net.jrtechs;

import java.util.ArrayList;
import java.util.List;

public class PythonGraphingConversion
{
    List<Long> single;
    List<Long> threaded;
    List<Long> manager;
    List<Long> streams;
    List<Integer> size;

    public PythonGraphingConversion()
    {
        single = new ArrayList<>();
        threaded = new ArrayList<>();
        manager = new ArrayList<>();
        streams = new ArrayList<>();
        size = new ArrayList<>();
    }

    public void addPoint(Result res, int i)
    {
        size.add(i);
        single.add(res.singleThread);
        threaded.add(res.threads);
        manager.add(res.manager);
        streams.add(res.streams);
    }


    public void printPythonCode(String title)
    {
        System.out.println(String.format("single = %s", this.single.toString()));
        System.out.println(String.format("threads = %s", this.threaded.toString()));
        System.out.println(String.format("manager = %s", this.manager.toString()));
        System.out.println(String.format("streams = %s", this.streams.toString()));
        System.out.println(String.format("sizes = %s", this.size.toString()));
        System.out.println(String.format("plot_result(single, threads, manager, streams, sizes, title='%s')", title));
    }
}

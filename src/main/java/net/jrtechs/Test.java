package net.jrtechs;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test
{

    public static void runDoNothingTest(int max, int incrementer)
    {
        WorkGenerator<Object> generator = new DoNothing<>();
        PythonGraphingConversion result = new PythonGraphingConversion();

        Vector<Work<Object>> workAll = new Vector<>();
        workAll.addAll(
            IntStream.range(0, max)
                .boxed()
                .map(i -> generator.generateWork(i))
                .collect(Collectors.toList())
        );
        GenericTester<Object> genObjectTester = new GenericTester<>();
        for(int i = 1; i <= max; i+= incrementer)
        {
            Vector<Work<Object>> work = new Vector<>(workAll.subList(0, i));
            Result res = genObjectTester.testAll(work);
            result.addPoint(res, i);
        }
        result.printPythonCode("Operational Overhead");
    }


    public static void sleepTest(int max, int incrementer)
    {
        WorkGenerator<Object> generator = new SleepWork<>();
        PythonGraphingConversion result = new PythonGraphingConversion();

        Vector<Work<Object>> workAll = new Vector<>();
        workAll.addAll(
                IntStream.range(0, max)
                        .boxed()
                        .map(i -> generator.generateWork(i))
                        .collect(Collectors.toList())
        );
        GenericTester<Object> genObjectTester = new GenericTester<>();
        for(int i = 1; i <= max; i+= incrementer)
        {
            Vector<Work<Object>> work = new Vector<>(workAll.subList(0, i));
            Result res = genObjectTester.testAll(work);
            result.addPoint(res, i);
        }
        result.printPythonCode("Sleeping Tasks");
    }

    public static void arithmeticWork(int max, int incrementer)
    {
        WorkGenerator<Double> generator = new DoMaths<>();
        PythonGraphingConversion result = new PythonGraphingConversion();

        Vector<Work<Double>> workAll = new Vector<>();
        workAll.addAll(
                IntStream.range(0, max)
                        .boxed()
                        .map(i -> generator.generateWork(i*1.0))
                        .collect(Collectors.toList())
        );
        GenericTester<Double> doubleGenericTester = new GenericTester<>();
        for(int i = 1; i <= max; i+= incrementer)
        {
            Vector<Work<Double>> work = new Vector<>(workAll.subList(0, i));
            Result res = doubleGenericTester.testAll(work);
            result.addPoint(res, i);
        }
        result.printPythonCode("Complex Maths");
    }

    public  static void main(String[] arguments)
    {
        //sleepTest(50, 5);
        arithmeticWork(10000, 20);
//        runDoNothingTest(10000, 100);
//        Vector<Work<Object>> work = new Vector<>();
//        work.addAll(
//            IntStream.range(0, 100000).boxed()
//                .map(i -> new Work<Object>() {
//                        @Override
//                        Object runTask() {
//                            //System.out.println("running task");
//                            return i;
//                        }
//                    }
//                ).collect(Collectors.toList())
//        );
//        System.out.println(testAll(work));

//
//        Vector<Work<Object>> work = new Vector<>();
//        work.addAll(
//            IntStream.range(0, 10000).boxed()
//                .map(i -> new Work<Object>() {
//                        @Override
//                        Object runTask() {
//                            for(int z = 0; z < 10000; z++)
//                            {
//                                Object o = Math.sin(z * ThreadLocalRandom.current().nextDouble());
//                            }
//                            return ThreadLocalRandom.current().nextDouble() * Math.PI;
//                            //return i;
//                        }
//                    }
//                ).collect(Collectors.toList())
//        );
//        GenericTester<Object> genericTester = new GenericTester<>();
//        System.out.println(genericTester.testAll(work));



//        Vector<Work<Object>> work = new Vector<>();
//        work.addAll(
//            IntStream.range(0, 10).boxed()
//                .map(i -> new Work<Object>() {
//                        @Override
//                        Object runTask() {
//                            try {
//                                Thread.sleep(500);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            return ThreadLocalRandom.current().nextDouble() * Math.PI;
//                            //return i;
//                        }
//                    }
//                ).collect(Collectors.toList())
//        );
//        System.out.println(testAllThree(work));
    }
}

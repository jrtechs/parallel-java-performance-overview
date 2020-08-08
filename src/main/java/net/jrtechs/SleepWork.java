package net.jrtechs;

public class SleepWork<E> extends WorkGenerator<E>
{
    @Override
    Work<E> generateWork(E param) {
        return new Work<E>() {
            @Override
            E runTask() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return param;
            }
        };
    }
}
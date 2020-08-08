package net.jrtechs;

public class DoNothing<E> extends WorkGenerator<E>
{
    @Override
    Work<E> generateWork(E param) {
        return new Work<E>() {
            @Override
            E runTask() {
                return param;
            }
        };
    }
}
package net.jrtechs;

public class Result
{
    long streams;
    long threads;
    long manager;
    long singleThread;
    long pool;

    @Override
    public String toString() {
        return "Result{" +
                "streams=" + streams +
                ", threads=" + threads +
                ", manager=" + manager +
                ", singleThread=" + singleThread +
                ", pool=" + pool +
                '}';
    }
}

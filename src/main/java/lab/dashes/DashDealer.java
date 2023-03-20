package lab.dashes;

import java.util.function.Consumer;

public class DashDealer implements Runnable {
    private final String output;
    private final Consumer<String> consumer;

    public DashDealer(String output, Consumer<String> consumer) {
        this.output = output;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            consumer.accept(output);
        }
    }
}

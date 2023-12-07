package com.hello;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Client client = new Client(args[0]);
        while (true) {
            client.tick();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            };
        }
    }
}
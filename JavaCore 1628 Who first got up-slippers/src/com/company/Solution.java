package com.company;

/*

1628 Who first got up - that and slippers

1. Understand what the program does.
1.1. Each thread should read words from the console. Use the ready-made static BufferedReader reader.
1.2. Use the AtomicInteger countReadStrings to calculate how many words have already been read from the console with all the threads.
2. Implement the logic of the run method:
2.1. Until the thread is interrupted (! IsInterrupted) read the words from the console and add them to the List <String> result field.
2.2. Use countReadStrings to count words already read from the console.

Requirements:
1. The run method should work until the thread is interrupted (! IsInterrupted).
2. The run method should NOT create its InputStreamReaders or BufferedReaders.
3. The run method should read the words from the reader and add them to the result list.
4. The run method should increase the counter of read lines countReadStrings by 1 after each read.
5. The program should output the data read by each thread.


 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static volatile AtomicInteger countReadStrings = new AtomicInteger(0);
    public static volatile BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        //read count of strings
        int count = Integer.parseInt(reader.readLine());

        //init threads
        ReaderThread consolReader1 = new ReaderThread();
        ReaderThread consolReader2 = new ReaderThread();
        ReaderThread consolReader3 = new ReaderThread();

        consolReader1.start();
        consolReader2.start();
        consolReader3.start();

        while (count > countReadStrings.get()) {
        }

        consolReader1.interrupt();
        consolReader2.interrupt();
        consolReader3.interrupt();
        System.out.println("#1:" + consolReader1);
        System.out.println("#2:" + consolReader2);
        System.out.println("#3:" + consolReader3);

        reader.close();
    }

    public static class ReaderThread extends Thread {
        private List<String> result = new ArrayList<String>();

        public void run() {
            //add your code here - добавьте код тут
            while (!isInterrupted()) {
                try {
                    if (reader.ready()) {
                        result.add(reader.readLine());
                        countReadStrings.incrementAndGet();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public String toString() {
            return result.toString();
        }
    }
}





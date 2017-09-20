package com.sma.lang.thread.tutorial.part3;
// ProdCons2.java


public class ProdCons2 {
  public static void main(String[] args) {
    Shared s = new Shared();
    new Producer(s).start();
    new Consumer(s).start();
  }
}


class Shared {
  private char c = '\u0000';
  private boolean writeable = true;

  synchronized void produceSharedChar(char c) {
    while (!writeable)
      try {
        wait();
      } catch (InterruptedException e) {}

    this.c = c;
    writeable = false;
    notify();
  }

  synchronized char consumeSharedChar() {
    while (writeable)
      try {
        wait();
      } catch (InterruptedException e) { }

    writeable = true;
    notify();

    return c;
  }
}


class Producer extends Thread {
  private Shared s;

  Producer(Shared s) {
    this.s = s;
  }

  public void run() {
    for (char ch = 'A'; ch <= 'Z'; ch++) {
      try {
        Thread.sleep((int) (Math.random() * 1000));
      } catch (InterruptedException e) {}

      s.produceSharedChar(ch);
      System.out.println(ch + " produced by producer.");
    }
  }
}


class Consumer extends Thread {
  private Shared s;

  Consumer(Shared s) {
    this.s = s;
  }

  public void run() {
    char ch;

    do {
      try {
        Thread.sleep((int) (Math.random() * 500));
      } catch (InterruptedException e) {}

      ch = s.consumeSharedChar();
      System.out.println(ch + " consumed by consumer.");
    } while (ch != 'Z');
  }
}

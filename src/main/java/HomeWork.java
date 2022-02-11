public class HomeWork {
    public static class Task1 {
        static volatile char c = 'A';
         static Object mon = new Object();

        public static void main(String[] args) {
            System.out.println("Task1");
            new Thread(new WaitNotifyClass('A', 'B')).start();
            new Thread(new WaitNotifyClass('B', 'C')).start();
            new Thread(new WaitNotifyClass('C', 'A')).start();
        }

     static class WaitNotifyClass implements Runnable {
            private char currentLetter;
            private char nextLetter;

            public WaitNotifyClass(char currentLetter, char nextLetter) {
                this.currentLetter = currentLetter;
                this.nextLetter = nextLetter;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (mon) {
                        try {
                            while (c != currentLetter)
                                mon.wait();
                            System.out.print(currentLetter);
                            c = nextLetter;
                            mon.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

    }
//Task2 - применение ExecutorService считаю пока необязательным, так как это у нас в чате лишь один поток.
// Но, в случае перехода с одного потока на несколько потоков, и снижения нагрузки на систему - тогда да, мне кажется, что его стоит добавить.

}

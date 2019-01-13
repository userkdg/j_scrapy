package cn.kindg.jscrapy.io;

import java.io.*;

public class PipedStream {
    public static void main(String[] args) {

        PipedOutputStream pos = new PipedOutputStream();
        try {
            PipedInputStream pis = new PipedInputStream(pos);
            new Thread(new InputStreamRunnable(pis)).start();
            new Thread(new OutputStreamRunnable(pos)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class InputStreamRunnable implements Runnable{
        private PipedInputStream pis = null;
        public InputStreamRunnable(PipedInputStream pis){
            this.pis = pis;
        }
        @Override
        public void run() {
            BufferedReader sr = new BufferedReader(new InputStreamReader(pis));
            try {
                System.out.println("读取到的内容:"+sr.readLine());
                sr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static class OutputStreamRunnable implements Runnable{
        private PipedOutputStream pos = null;
        public OutputStreamRunnable(PipedOutputStream pos){
            this.pos = pos;
        }
        @Override
        public void run(){
            try {
                pos.write("Hello World!".getBytes());
                pos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

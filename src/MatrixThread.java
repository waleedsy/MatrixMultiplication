import java.util.Random;

public class MatrixThread {

    final static int WIDTH = 100;
    final static int HEIGHT = 100;

    private static class WorkerThread extends Thread
    {
        int[][] firstMatrix;
        int[][] secondMatrix;
        int[][] finalMatrix;
        int start;
        int stop;

        public WorkerThread(int [][] firstMatrix, int [][] secondMatrix, int start, int stop)
        {
            this.firstMatrix = firstMatrix;
            this.secondMatrix = secondMatrix;
            this.finalMatrix = new int[HEIGHT][WIDTH];
            this.start = start;
            this.stop = stop;
        }

        public void run()
        {
           for (int i = start; i < stop; i++)
           {
               for (int j = 0; j < WIDTH; j++)
               {
                   for (int k = 0; k < WIDTH; k++)
                   {
                        finalMatrix [i][j] += firstMatrix[i][j] * secondMatrix[k][j];
                   }
               }
           }

        }

        public int[][] getFinalMatrix() {
            return finalMatrix;
        }

    }

    public static int [][] BigMatrix ()
    {
        int [][] array_tot = new int [WIDTH][HEIGHT];
        Random rand = new Random();

        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 0; j < HEIGHT; j++)
            {
                array_tot [i][j] = rand.nextInt();
            }
        }

        return array_tot;
    }

    public static void main (String [] args) throws InterruptedException
    {
        WorkerThread[] t = new WorkerThread[10];
        int [][] fistMatrix = BigMatrix();
        int [][] secondMatrix = BigMatrix();
        int [][] finalMatrix = new int [WIDTH] [HEIGHT];

       for (int i = 0; i < 10; i++)
       {
           t[i] = new WorkerThread(fistMatrix, secondMatrix, i * 10, i * 10 + 10);
           t[i].start();
       }

       for (int i = 0; i < 10; i++)
       {
           t[i].join();
       }

        for (int i = 0; i < 10; i++)
        {
            for(int j = i * 10; j < i * 10 + 10; j++) {
                for (int k = 0; k < WIDTH; k++) {
                    finalMatrix[j][k] = t[i].getFinalMatrix()[j][k];
                }
            }
        }

       for (int i = 0; i  < 100; i++)
       {
           for (int j = 0; j < 100; j++)
           {
               System.out.print(finalMatrix[i][j] + " ");
           }
           System.out.println(" ");
       }

    }
}

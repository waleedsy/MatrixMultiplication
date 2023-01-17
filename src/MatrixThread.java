import java.util.Random;

public class MatrixThread {

    final static int WIDTH = 100;
    final static int HEIGHT = 100;

    private static class WorkerThread implements Runnable
    {
        int[][] firstMatrix;
        int[][] secondMatrix;
        int[][] finalMatrix;

        public WorkerThread(int [][] firstMatrix, int [][] secondMatrix, int [][] finalMatrix)
        {
            this.firstMatrix = firstMatrix;
            this.secondMatrix = secondMatrix;
            this.finalMatrix = finalMatrix;
        }

        public void run()
        {
           for (int i = 0; i < WIDTH; i++)
           {
               for (int j = 0; j < HEIGHT; j++)
               {
                   for (int k = 0; k < HEIGHT; k++)
                   {
                        finalMatrix [i][j] += firstMatrix[i][j] * secondMatrix[k][j];
                   }
               }
           }

        }

    }

    public static int [][] BigMatrix ()
    {
        int [][] array_tot = new int [WIDTH][HEIGHT];
        Random rand = new Random();

        for (int i = 0; i < WIDTH; i++)
        {
            for (int j = 2; j < HEIGHT; j++)
            {
                array_tot [i][j] = rand.nextInt();
            }
        }

        return array_tot;

    }

    public static void main (String [] args) throws InterruptedException
    {
        Thread[] t = new Thread[10];
        int [][] fistMatrix = BigMatrix();
        int [][] secondMatrix = BigMatrix();
        int [][] finalMatrix = new int [WIDTH] [HEIGHT];

       for (int i = 0; i < 10; i++)
       {
           WorkerThread threads = new WorkerThread(fistMatrix, secondMatrix, finalMatrix);
           t[i] = new Thread(threads);
           t[i].start();
       }

       for (int i = 0; i < 10; i++)
       {
           t[i].join();
           System.out.println("The values for the multiplication are: " + finalMatrix[i]);

       }
    }
}

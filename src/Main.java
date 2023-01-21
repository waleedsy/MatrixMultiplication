public class Main {

    public static void main(String[] args) throws InterruptedException {
        WorkerThread[] t = new WorkerThread[10];
        int[][] fistMatrix = MatrixThread.BigMatrix();
        int[][] secondMatrix = MatrixThread.BigMatrix();
        int[][] finalMatrix = new int[MatrixThread.WIDTH][MatrixThread.HEIGHT];

        for (int i = 0; i < 10; i++) {
            t[i] = new WorkerThread(fistMatrix, secondMatrix, i * 10, i * 10 + 10);
            t[i].start();
        }

        for (int i = 0; i < 10; i++) {
            t[i].join();
        }

        for (int i = 0; i < 10; i++) {
            for (int j = i * 10; j < i * 10 + 10; j++) {
                for (int k = 0; k < MatrixThread.WIDTH; k++) {
                    finalMatrix[j][k] = t[i].getFinalMatrix()[j][k];
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.print(finalMatrix[i][j] + " ");
            }
            System.out.println(" ");
        }

    }


}

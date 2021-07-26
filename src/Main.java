import java.util.Scanner;

public class Main
{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        int banyakData = 4;
        int banyakX = 2;
        float learningRate = 0.1f;
        float toleransi = 0.05f;

        int[][] x = {
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1}
        };

        int[] target = {1, -1, -1, -1};

        Adaline adaline = new Adaline(learningRate,toleransi,x,target);
        adaline.learning();

        float [] weight = adaline.getWeight();
        float bias = adaline.getBias();
        int[] xTesting = new int[x[0].length];

        System.out.println("Testing");
        System.out.println("========");
        for (int i = 0; i < banyakX; i++)
        {
            System.out.print("Masukkan X" + (i + 1) + " = ");
            xTesting[i] = scanner.nextInt();
        }

        float hasilSummary = adaline.calculateSummaryFunction(x[0].length,weight,xTesting,bias);
        System.out.println("Hasil fungsi summary adalah = " + hasilSummary);
        System.out.println("Output yang diperoleh adalah Y = " + ((hasilSummary >= 0) ? 1 : -1));
    }
}

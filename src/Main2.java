import java.util.Scanner;

public class Main2
{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        int banyakData;
        int banyakX;
        float learningRate;
        float toleransi;

        System.out.print("Input banyak data : ");
        banyakData = scanner.nextInt();
        System.out.print("Input banyak x : ");
        banyakX = scanner.nextInt();
        System.out.print("Input nilai batas toleransi : ");
        toleransi = scanner.nextFloat();
        System.out.print("Input nilai learning rate : ");
        learningRate = scanner.nextFloat();
        System.out.println();

        int[][] x = new int[banyakData][banyakX];
        int[] target = new int[banyakData];

        for (int i = 0; i < banyakData; i++)
        {
            for (int j = 0; j < banyakX; j++)
            {
                System.out.print("Masukkan X-" + (j + 1) + " data ke-" + (i + 1) + "= ");
                x[i][j] = scanner.nextInt();
            }
            System.out.print("Masukkan T data ke-" + (i + 1 + "= "));
            target[i] = scanner.nextInt();
            System.out.println();
        }

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

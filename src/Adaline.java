public class Adaline
{
    private final float learningRate;
    private final float toleranceLimit;
    private final float[] weight;
    private final float[] deltaWeight;
    private float bias;
    private final int[][] x;
    private final int[] target;

    public Adaline(float learningRate, float toleranceLimit, int[][] x, int[] target)
    {
        this.learningRate = learningRate;
        this.toleranceLimit = toleranceLimit;
        this.x = x;
        this.target = target;
        this.weight = new float[x[0].length];
        this.deltaWeight = new float[x.length];
    }

    public float[] getWeight()
    {
        return weight;
    }

    public float getBias()
    {
        return bias;
    }


    public void learning()
    {
        boolean deltaWSmaller = false;
        int epoch = 1;
        float deltaBias;
        int totalData = x.length;
        int totalX = x[0].length;

        System.out.println("\nLearning");
        System.out.println("===========");
        do
        {
            header(epoch);
            float maxDeltaW = 0.0f;

            for (int i = 0; i < totalData; i++)
            {
                float summaryFunction = calculateSummaryFunction(i);
                int y = (summaryFunction >= 0) ? 1 : -1;
                int tMinY = target[i] - y;
                deltaBias = (learningRate * tMinY);


                for (int j = 0; j < totalX; j++)
                {
                    System.out.printf("%4d", x[i][j]);
                }

                System.out.printf("%8d%8.1f%8d%8d", target[i], summaryFunction, y, tMinY);

                printDW(i, tMinY);
                for (int j = 0; j < totalX; j++)
                {
                    if (maxDeltaW < deltaWeight[j])
                    {
                        maxDeltaW = deltaWeight[j];
                    }
                }

                System.out.printf("%8.1f", deltaBias);

                printW(tMinY);

                printBias(tMinY, deltaBias);
            }

            finalResult();

            epoch++;
            if (maxDeltaW < toleranceLimit)
            {
                deltaWSmaller = true;
            }

        } while (!deltaWSmaller);
    }

    private void finalResult()
    {
        int totalX = x[0].length;

        System.out.println();
        for (int i = 0; i < totalX; i++)
        {
            System.out.println("W" + (i + 1) + "= " + weight[i]);
        }
        System.out.println("B = " + bias + "\n");
    }

    private void printBias(int tMinY, float deltaBias)
    {
        if (tMinY != 0)
        {
            bias += deltaBias;
        }
        System.out.printf("%8.1f%n", bias);
    }

    private void header(int epoch)
    {
        int totalX = x[0].length;

        System.out.println("Epoch ke-" + epoch);
        for (int i = 0; i < totalX; i++)
        {
            System.out.printf("%4s", ("X" + (i + 1)));
        }

        System.out.printf("%8s", "T");
        System.out.printf("%8s", "F(X)");
        System.out.printf("%8s", "Y");
        System.out.printf("%8s", "T-Y");

        for (int i = 0; i < totalX; i++)
        {
            System.out.printf("%8s", ("DW" + (i + 1)));
        }

        System.out.printf("%8s", "DB");

        for (int i = 0; i < totalX; i++)
        {
            System.out.printf("%8s", "W" + (i + 1));
        }

        System.out.printf("%8s%n", "b");
    }


    private float calculateSummaryFunction(int i)
    {
        int totalX = x[0].length;
        float nilaiFx = 0;

        for (int j = 0; j < totalX; j++) // 1
        {
            nilaiFx += weight[j] * x[i][j]; // 0 + 0 * 1 + 0 * 1
        }

        return nilaiFx + bias;
    }

    public float calculateSummaryFunction(int banyakX, float[]weight, int[]xTesting, float bias)
    {
        float fungsiSummary = 0;

        for(int i=0; i<banyakX; i++)
        {
            fungsiSummary +=xTesting[i] * weight[i];
        }

        return fungsiSummary + bias;
    }


    private void printDW(int i, int tMinY)
    {
        int totalX = x[0].length;

        for (int j = 0; j < totalX; j++)
        {
            deltaWeight[j] = (learningRate * tMinY * x[i][j]);
            System.out.printf("%8.1f", deltaWeight[j]);
        }
    }

    private void printW(int tMinY)
    {
        int totalX = x[0].length;

        for (int j = 0; j < totalX; j++)
        {
            if (tMinY != 0)
            {
                weight[j] += deltaWeight[j];
            }
            System.out.printf("%8.1f", weight[j]);
        }
    }

}

package goodtrailer.quizgen.problem;

import java.util.Arrays;

public abstract class AbstractWeightedProblemFactory implements IProblemFactory
{
    private WeightedFactory[] wFactories = getWeightedFactories();
    private int[] thresholds = new int[wFactories.length];
    private int weightSum = 0;

    public AbstractWeightedProblemFactory()
    {
        for (int i = 0; i < wFactories.length; i++)
        {
            thresholds[i] = weightSum;
            weightSum += wFactories[i].weight;
        }
    }

    @Override
    public final IProblem get()
    {
        if (thresholds.length == 0)
            throw new IllegalStateException("no weighted problem factories");

        int x = (int) (Math.random() * weightSum);
        int i = Arrays.binarySearch(thresholds, x);
        if (i < 0)
            i = -i - 2;
        return wFactories[i].problemFactory.get();
    }

    protected abstract WeightedFactory[] getWeightedFactories();

    protected record WeightedFactory(IProblemFactory problemFactory, int weight)
    {
    };
}

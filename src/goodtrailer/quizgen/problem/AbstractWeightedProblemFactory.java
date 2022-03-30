package goodtrailer.quizgen.problem;

public abstract class AbstractWeightedProblemFactory implements IProblemFactory
{
    private WeightedFactory[] wFactories;
    private int[] thresholds;
    private int weightSum = 0;

    public AbstractWeightedProblemFactory()
    {
        wFactories = getWeightedFactories().clone();
        thresholds = new int[wFactories.length];
        weightSum = 0;
        for (int i = 0; i < wFactories.length; i++)
        {
            thresholds[i] = weightSum;
            weightSum += wFactories[i].weight;
        }
    }

    @Override
    public final IProblem get()
    {
        int x = (int) (Math.random() * weightSum);
        for (int i = thresholds.length - 1; i >= 0; i--)
            if (x >= thresholds[i])
                return wFactories[i].problemFactory.get();
        throw new IllegalStateException("no weighted problem factories");
    }

    protected abstract WeightedFactory[] getWeightedFactories();

    protected record WeightedFactory(IProblemFactory problemFactory, int weight)
    {
    };
}

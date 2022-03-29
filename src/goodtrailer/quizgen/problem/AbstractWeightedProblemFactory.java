package goodtrailer.quizgen.problem;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeightedProblemFactory implements IProblemFactory
{
    private ArrayList<WeightedFactory> wFactories;
    private int[] thresholds;
    private int weightSum = 0;
    
    public AbstractWeightedProblemFactory()
    {
        wFactories = new ArrayList<WeightedFactory>(GetWeightedFactories());
        thresholds = new int[wFactories.size()];
        weightSum = 0;
        for (var i = 0; i < wFactories.size(); i++)
        {
            thresholds[i] = weightSum;
            weightSum += wFactories.get(i).Weight;
        }
    }
    
    @Override
    public final IProblem Generate()
    {
        var x = (int)(Math.random() * weightSum);
        for (var i = thresholds.length - 1; i >= 0; i--)
            if (x >= thresholds[i])
                return wFactories.get(i).ProblemFactory.Generate();
        throw new IllegalStateException("no weighted problem factories");
    }
    
    protected abstract List<WeightedFactory> GetWeightedFactories();
    
    protected class WeightedFactory
    {
        public IProblemFactory ProblemFactory;
        public int Weight;
        
        public WeightedFactory(IProblemFactory problemFactory, int weight)
        {
            this.ProblemFactory = problemFactory;
            this.Weight = weight;
        }
    }
}

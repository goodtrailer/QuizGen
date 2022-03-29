package goodtrailer.quizgen.problem;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRandomProblemFactory implements IProblemFactory
{
    private ArrayList<IProblemFactory> factories;
    
    public AbstractRandomProblemFactory()
    {
        factories = new ArrayList<IProblemFactory>(GetFactories());
    }
    
    @Override
    public final IProblem Generate()
    {
        return factories.get((int)(Math.random() * factories.size())).Generate();
    }
    
    protected abstract List<IProblemFactory> GetFactories();
}

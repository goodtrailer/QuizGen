package goodtrailer.quizgen.problem;

public abstract class AbstractRandomProblemFactory implements IProblemFactory
{
    private IProblemFactory[] factories;
    
    public AbstractRandomProblemFactory()
    {
        factories = getFactories().clone();
    }
    
    @Override
    public final IProblem get()
    {
        return factories[(int)(Math.random() * factories.length)].get();
    }
    
    protected abstract IProblemFactory[] getFactories();
}

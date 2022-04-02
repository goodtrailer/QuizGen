package goodtrailer.quizgen.problem;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Supplier;

public class ChapterProblemFactory implements IProblemFactory
{
    private IProblemFactory factory;
    private static final HashMap<Integer, Supplier<IProblemFactory>> factories;

    static
    {
        factories = new HashMap<Integer, Supplier<IProblemFactory>>();
        factories.put(6, Ch6ProblemFactory::new);
        factories.put(10, Ch10ProblemFactory::new);
    }

    public ChapterProblemFactory(int chapter)
    {
        factory = factories.get(chapter).get();
    }

    @Override
    public IProblem get()
    {
        return factory.get();
    }
    
    public static Set<Integer> chapters()
    {
        return factories.keySet();
    }
}

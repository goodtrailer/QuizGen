package goodtrailer.quizgen.app.problem;

import java.util.HashMap;
import java.util.Set;
import java.util.function.Supplier;

import goodtrailer.quizgen.app.problem.chapter10.Chapter10ProblemFactory;
import goodtrailer.quizgen.app.problem.chapter6.Chapter6ProblemFactory;
import goodtrailer.quizgen.problem.IProblem;
import goodtrailer.quizgen.problem.IProblemFactory;

public class ChapterProblemFactory implements IProblemFactory
{
    private IProblemFactory factory;
    private static final HashMap<Integer, Supplier<IProblemFactory>> factories;

    static
    {
        factories = new HashMap<Integer, Supplier<IProblemFactory>>();
        factories.put(6, Chapter6ProblemFactory::new);
        factories.put(10, Chapter10ProblemFactory::new);
    }

    public ChapterProblemFactory(int chapter)
    { factory = factories.get(chapter).get(); }

    @Override
    public IProblem get()
    { return factory.get(); }

    public static Set<Integer> chapters()
    { return factories.keySet(); }
}

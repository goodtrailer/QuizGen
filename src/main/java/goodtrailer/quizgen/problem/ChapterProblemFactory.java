package goodtrailer.quizgen.problem;

public class ChapterProblemFactory implements IProblemFactory
{
    private IProblemFactory factory;

    public ChapterProblemFactory(int chapter)
    {
        factory = switch (chapter)
        {
        case 6 -> new Ch6ProblemFactory();
        case 10 -> new Ch10ProblemFactory();
        default -> throw new IllegalArgumentException("unsupported chapter");
        };
    }

    @Override
    public IProblem get()
    {
        return factory.get();
    }
}

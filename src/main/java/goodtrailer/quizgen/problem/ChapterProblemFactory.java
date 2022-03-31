package goodtrailer.quizgen.problem;

public class ChapterProblemFactory implements IProblemFactory
{
    private IProblemFactory factory;
    
    public ChapterProblemFactory(int chapter)
    {
        switch (chapter)
        {
        case 10 -> factory = new Ch10ProblemFactory();
        default -> throw new IllegalArgumentException("unsupported chapter");
        }
    }
    
    @Override
    public IProblem get()
    {
        return factory.get();
    }
}

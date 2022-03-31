package goodtrailer.quizgen.problem;

public class Ch10ProblemFactory extends AbstractWeightedProblemFactory
{
    private static final WeightedFactory[] weighted_factories = new WeightedFactory[] {
            new WeightedFactory(LineIntersectionProblem::new, 2),
            new WeightedFactory(LineDistanceProblem::new, 1),
    };

    @Override
    protected WeightedFactory[] getWeightedFactories()
    {
        return weighted_factories;
    }
}
package goodtrailer.quizgen.problem;

public class Ch10ProblemFactory extends AbstractWeightedProblemFactory
{
    @Override
    protected WeightedFactory[] getWeightedFactories()
    {
        return new WeightedFactory[] {
                new WeightedFactory(LineIntersectionProblem::new, 2),
                new WeightedFactory(LineDistanceProblem::new, 1),
        };
    }
}

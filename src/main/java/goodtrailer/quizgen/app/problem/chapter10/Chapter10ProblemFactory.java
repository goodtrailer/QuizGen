package goodtrailer.quizgen.app.problem.chapter10;

import goodtrailer.quizgen.problem.AbstractWeightedProblemFactory;

public class Chapter10ProblemFactory extends AbstractWeightedProblemFactory
{
    private static final WeightedFactory[] weighted_factories = new WeightedFactory[] {
            new WeightedFactory(TransversalAngleProblem::new, 1),
            new WeightedFactory(PointLineDistanceProblem::new, 2),
            new WeightedFactory(ParallelLineDistanceProblem::new, 1),
    };

    @Override
    protected WeightedFactory[] getWeightedFactories()
    {
        return weighted_factories;
    }
}

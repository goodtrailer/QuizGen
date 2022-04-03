package goodtrailer.quizgen.app.problem.chapter6;

import goodtrailer.quizgen.problem.AbstractWeightedProblemFactory;

public class Chapter6ProblemFactory extends AbstractWeightedProblemFactory
{
    private static final WeightedFactory[] weighted_factories = new WeightedFactory[] {
            // new WeightedFactory(ExponentialDomainRangeProblem::new, 2),
            new WeightedFactory(ExponentialTableAdditionProblem::new, 1),
            new WeightedFactory(ExponentialGrowthTypeProblem::new, 1),
            new WeightedFactory(ExponentialRateOfChangeProblem::new, 1),
            new WeightedFactory(ExponentialIntersectionProblem::new, 3),
    };

    @Override
    protected WeightedFactory[] getWeightedFactories()
    {
        return weighted_factories;
    }
}
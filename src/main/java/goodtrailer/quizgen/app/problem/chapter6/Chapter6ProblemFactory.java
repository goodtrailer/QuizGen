package goodtrailer.quizgen.app.problem.chapter6;

import java.util.List;

import goodtrailer.quizgen.problem.AbstractWeightedProblemFactory;

public class Chapter6ProblemFactory extends AbstractWeightedProblemFactory
{
    private static final List<WeightedFactory> weighted_factories = List.of(
            new WeightedFactory(ExponentialDomainProblem::new, 1),
            new WeightedFactory(ExponentialRangeProblem::new, 1),
            new WeightedFactory(ExponentialTableAdditionProblem::new, 1),
            new WeightedFactory(ExponentialGrowthTypeProblem::new, 1),
            new WeightedFactory(ExponentialRateOfChangeProblem::new, 1),
            new WeightedFactory(ExponentialIntersectionProblem::new, 3));

    @Override
    protected List<WeightedFactory> getWeightedFactories()
    { return weighted_factories; }
}

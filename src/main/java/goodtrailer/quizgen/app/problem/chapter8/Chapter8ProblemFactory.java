package goodtrailer.quizgen.app.problem.chapter8;

import java.util.List;

import goodtrailer.quizgen.problem.AbstractWeightedProblemFactory;

public class Chapter8ProblemFactory extends AbstractWeightedProblemFactory
{
    private static final List<WeightedFactory> weighted_factories = List.of(
            new WeightedFactory(SupplementaryAngleProblem::new, 1));
    
    @Override
    protected List<WeightedFactory> getWeightedFactories()
    {
        return weighted_factories;
    }
}

package goodtrailer.quizgen.app.problem.chapter10;

import goodtrailer.quizgen.problem.AbstractWeightedProblemFactory;

public class Chapter10ProblemFactory extends AbstractWeightedProblemFactory
{
    private static final WeightedFactory[] weighted_factories = new WeightedFactory[] {
            new WeightedFactory(TransversalAngleProblem::new, 1),
            // TODO change to nearest point problem, the problem isn't supposed to give you the
            // two lines you're working with. You're meant to come up with the second line yourself.
            new WeightedFactory(LineIntersectionProblem::new, 2),
            new WeightedFactory(LineDistanceProblem::new, 1),
    };

    @Override
    protected WeightedFactory[] getWeightedFactories()
    {
        return weighted_factories;
    }
}

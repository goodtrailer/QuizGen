package goodtrailer.quizgen.problem;

import java.util.Arrays;
import java.util.List;

public class Ch10ProblemFactory extends AbstractWeightedProblemFactory
{
    @Override
    protected List<WeightedFactory> GetWeightedFactories()
    {
        return Arrays.asList(new WeightedFactory[] {
                new WeightedFactory(LineIntersectionProblem::new, 2),
                new WeightedFactory(LineDistanceProblem::new, 1),
        });
    }
}

package goodtrailer.quizgen.problem;

import goodtrailer.quizgen.util.Line;
import goodtrailer.quizgen.util.MathUtils;

public class LineDistanceProblem extends AbstractFrqProblem
{
    private Line line0;
    private Line line1;
    private double distance;

    @Override
    protected void initialize()
    {
        line0 = Line.random();
        line1 = Line.randomParallel(line0);
        distance = line0.distance(line1);
    }

    @Override
    protected String getPrompt()
    {
        return String.format("Find the minimum distance between the lines { y\u2080 = %s } and { y\u2081 = %s }.",
                line0.toString(), line1.toString());
    }

    @Override
    protected Result checkInput(String input)
    {
        double inDistance;
        try
        {
            inDistance = MathUtils.parseFraction(input);
        }
        catch (NumberFormatException nfe)
        {
            return Result.INVALID;
        }

        return Result.from(MathUtils.areEqual(inDistance, distance));
    }
}

package goodtrailer.quizgen.app.problem.chapter10;

import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;
import goodtrailer.quizgen.util.Linear;
import goodtrailer.quizgen.util.MathUtils;

class ParallelLineDistanceProblem extends AbstractFrqProblem
{
    private Linear line0;
    private Linear line1;
    private double distance;

    @Override
    protected void initialize()
    {
        line0 = Linear.random();
        line1 = Linear.random().withM(line0.m());
        distance = line0.distance(line1);
    }

    @Override
    protected String getPrompt()
    {
        String message = "Find the minimum distance between the lines { y\u2080 = %s } and "
            + "{ y\u2081 = %s }.";
        return String.format(message, line0.toString(), line1.toString());
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

package goodtrailer.quizgen.app.problem.chapter6;

import goodtrailer.quizgen.math.IMathUtils;
import goodtrailer.quizgen.math.function.Exponential;
import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;

class ExponentialRateOfChangeProblem extends AbstractFrqProblem
{
    private Exponential exponential;

    @Override
    protected void initialize()
    { exponential = Exponential.random(); }

    @Override
    protected String getPrompt()
    {
        return String.format("What is the percentage rate of change of the equation { y = %s }?",
                exponential.toString());
    }

    @Override
    protected Result checkInput(String input)
    {
        double inRate;
        try
        {
            inRate = IMathUtils.parsePercentage(input);
        }
        catch (NumberFormatException nfe)
        {
            return Result.INVALID;
        }
        return Result.from(IMathUtils.areEqual(inRate, exponential.b(), 5));
    }
}

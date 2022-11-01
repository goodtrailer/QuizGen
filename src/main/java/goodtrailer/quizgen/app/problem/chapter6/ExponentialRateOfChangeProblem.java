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
    {
        exponential = Exponential.random();
        while (exponential.isConstant())
            exponential = Exponential.random();
    }

    @Override
    protected String getPrompt()
    {
        String message = "Find the percentage rate of change of the exponential equation:\n\n"
                + "y = %s";
        
        return String.format(message, exponential.toString());
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

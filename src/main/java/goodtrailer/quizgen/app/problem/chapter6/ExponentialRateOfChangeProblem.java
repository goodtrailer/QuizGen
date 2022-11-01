package goodtrailer.quizgen.app.problem.chapter6;

import goodtrailer.quizgen.math.IMathUtils;
import goodtrailer.quizgen.math.function.Exponential;
import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;

class ExponentialRateOfChangeProblem extends AbstractFrqProblem
{
    private static final double max_a = 5.0;
    private static final double max_b = 3.0;
    
    private Exponential exponential;

    @Override
    protected void initialize()
    {
        double b = IMathUtils.randomDouble(0, max_b);
        exponential = new Exponential(max_a, b, 1, 0);
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
        
        return Result.from(IMathUtils.areEqual(inRate, exponential.b() - 1));
    }
}

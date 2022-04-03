package goodtrailer.quizgen.problem;

import goodtrailer.quizgen.util.Exponential;
import goodtrailer.quizgen.util.MathUtils;

class ExponentialRateOfChangeProblem extends AbstractFrqProblem
{
    private Exponential exponential;
    
    @Override
    protected void initialize()
    {
        exponential = Exponential.random();
    }

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
            inRate = MathUtils.parsePercentage(input);
        }
        catch (NumberFormatException nfe)
        {
            return Result.INVALID;
        }
        return Result.from(MathUtils.areEqual(inRate, exponential.b(), 5));
    }
}

package goodtrailer.quizgen.app.problem.chapter6;

import java.util.ArrayList;

import goodtrailer.quizgen.math.function.Exponential;
import goodtrailer.quizgen.math.function.ExponentialGrowthType;
import goodtrailer.quizgen.problem.AbstractMcqProblem;

class ExponentialGrowthTypeProblem extends AbstractMcqProblem
{
    private Exponential exponential;

    @Override
    protected void initialize()
    { exponential = Exponential.random(); }

    @Override
    protected String getPrompt()
    {
        String message = "Describe the growth type of the equation:\n\n"
                + "y = %s";
        
        return String.format(message, exponential.toString());
    }

    @Override
    protected Choices getChoices()
    {
        var descriptions = new ArrayList<String>();
        int correctIndex = -1;
        var type = exponential.growthType();
        for (var t : ExponentialGrowthType.values())
        {
            if (t == type)
                correctIndex = descriptions.size();
            descriptions.add(t.toString());
        }
        return new Choices(descriptions, correctIndex);
    }
}

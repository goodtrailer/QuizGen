package goodtrailer.quizgen.problem;

import java.util.ArrayList;

import goodtrailer.quizgen.util.Exponential;

public class ExponentialGrowthTypeProblem extends AbstractMcqProblem
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
        return String.format("What is the growth type of the exponential equation { y = %s }?",
                exponential.toString());
    }

    @Override
    protected Choices getChoices()
    {
        var descriptions = new ArrayList<String>();
        int correctIndex = -1;
        var type = exponential.growthType();
        for (var t : Exponential.GrowthType.values())
        {
            if (t == type)
                correctIndex = descriptions.size();
            descriptions.add(t.toString());
        }
        return new Choices(descriptions, correctIndex);
    }
}

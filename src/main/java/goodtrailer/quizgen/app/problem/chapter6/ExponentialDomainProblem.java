package goodtrailer.quizgen.app.problem.chapter6;

import java.util.ArrayList;
import java.util.List;

import goodtrailer.quizgen.math.Interval;
import goodtrailer.quizgen.math.function.Exponential;
import goodtrailer.quizgen.problem.AbstractMcqProblem;

class ExponentialDomainProblem extends AbstractMcqProblem
{
    private Exponential exponential;

    @Override
    protected void initialize()
    { exponential = Exponential.random(); }

    @Override
    protected String getPrompt()
    {
        String message = "Describe the domain of the exponential equation:\n\n"
                + "y = %s";
        
        return String.format(message, exponential.toString());
    }

    @Override
    protected Choices getChoices()
    {
        // domain of an exponential function is always (-inf, inf)
        int correctIndex = 0;
        var intervals = new ArrayList<Interval>(List.of(
                Interval.real(),
                Interval.real().withLower(0),
                Interval.real().withUpper(0)));
        intervals.add(Interval.point(exponential.evaluate(0)));

        var descriptions = new ArrayList<String>();
        for (var i : intervals)
            descriptions.add(i.toString());

        return new Choices(descriptions, correctIndex);
    }
}

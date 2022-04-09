package goodtrailer.quizgen.app.problem.chapter6;

import java.util.ArrayList;
import java.util.List;

import goodtrailer.quizgen.math.Interval;
import goodtrailer.quizgen.math.function.Exponential;
import goodtrailer.quizgen.problem.AbstractMcqProblem;

class ExponentialRangeProblem extends AbstractMcqProblem
{
    private Exponential exponential;

    @Override
    protected void initialize()
    { exponential = Exponential.random(); }

    @Override
    protected String getPrompt()
    { return String.format("Describe the range of { y = %s }.", exponential.toString()); }

    @Override
    protected Choices getChoices()
    {
        int correctIndex = -1;
        var intervals = new ArrayList<Interval>(List.of(
                Interval.real(),
                Interval.real().withLower(0),
                Interval.real().withUpper(0)));
        intervals.add(Interval.point(exponential.evaluate(0)));

        var range = exponential.range().get(0);
        for (int i = 0; i < intervals.size(); i++)
            if (range.equals(intervals.get(i)))
                correctIndex = i;

        var descriptions = new ArrayList<String>();
        for (var i : intervals)
            descriptions.add(i.toString());

        return new Choices(descriptions, correctIndex);
    }
}

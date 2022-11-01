package goodtrailer.quizgen.app.problem.chapter8;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

import goodtrailer.quizgen.math.Point;
import goodtrailer.quizgen.math.function.Linear;
import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;
import goodtrailer.quizgen.util.IResourceUtils;

public class ComplementaryAngleProblem extends AbstractFrqProblem
{
    private Linear angleA, angleB;
    private Point solution;

    @Override
    protected void initialize()
    {
        angleA = Linear.random();
        while (angleA.isConstant())
            angleA = Linear.random();
        
        angleB = Linear.random();
        while (angleB.isConstant())
            angleB = Linear.random();
        
        var angleSum = angleA.add(angleB);
        double x = angleSum.solution(new Linear(0, 90)).point().x();
        solution = new Point(angleA.evaluate(x), angleB.evaluate(x));
    }

    @Override
    protected String getPrompt()
    {
        String message = "In the diagram below, A and B are complementary angles. The diagram is "
                + "not to scale.\n\n"
                + "m\u2220A = a\u00B0 = (%s)\u00B0\n"
                + "m\u2220B = b\u00B0 = (%s)\u00B0\n\n"
                + "Find the values of a and b. Give your solution in the form of a point (a, b).";
        return String.format(message, angleA.toString("x"), angleB.toString("x"));
    }

    @Override
    protected void addComponents(List<JComponent> components)
    {
        super.addComponents(components);

        String filename = "ComplementaryAngle.png";
        var label = new JLabel(IResourceUtils.getImage(this, filename));
        components.addAll(components.size() - 2, List.of(label, createFiller()));
    }
    
    @Override
    protected Result checkInput(String input)
    {
        input = input.trim();

        Point inPoint;
        try
        {
            inPoint = Point.parse(input);
        }
        catch (NumberFormatException nfe)
        {
            return Result.INVALID;
        }

        if (!inPoint.is2d())
            return Result.INVALID;

        return Result.from(solution.equals(inPoint));
    }
}

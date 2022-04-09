package goodtrailer.quizgen.app.problem.chapter10;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

import goodtrailer.quizgen.math.IMathUtils;
import goodtrailer.quizgen.math.Point;
import goodtrailer.quizgen.math.function.Linear;
import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;
import goodtrailer.quizgen.util.IResourceUtils;

class TransversalAngleProblem extends AbstractFrqProblem
{
    private static final int min_angle_c = 1;
    private static final int max_angle_c = 179;
    private static final int variants_count = 4;

    private Linear angleA, angleB;
    private int angleC;
    private int variant;
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

        angleC = IMathUtils.randomInt(min_angle_c, max_angle_c);
        variant = (int) (Math.random() * variants_count);

        double x, y;
        switch (variant)
        {
        // that moment when you realize every transversal problem is the same...
        case 0:
        case 2:
        case 3:
            x = angleA.solution(new Linear(0, 180 - angleC)).point().x();
            y = angleB.solution(new Linear(0, angleC)).point().x();
            break;
        case 1:
            x = angleA.solution(new Linear(0, angleC)).point().x();
            y = angleB.solution(new Linear(0, 180 - angleC)).point().x();
            break;
        default:
            throw new IllegalStateException("invalid problem variant");
        }
        solution = new Point(x, y);
    }

    @Override
    protected String getPrompt()
    {
        String message = "In the transversal below, m\u2220C = %d\u00B0; m\u2220A = (%s)\u00B0; "
                + "m\u2220B = (%s)\u00B0. Find the values of x and y. Give your solution in the "
                + "form of a point (x, y).";
        return String.format(message, angleC, angleA.toString(), angleB.toString("y"));
    }

    @Override
    protected void addComponents(List<JComponent> components)
    {
        super.addComponents(components);

        String filename = String.format("Transversal%d.png", variant);
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

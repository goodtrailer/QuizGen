package goodtrailer.quizgen.app.problem.chapter8;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

import goodtrailer.quizgen.math.IMathUtils;
import goodtrailer.quizgen.math.Point;
import goodtrailer.quizgen.math.function.Linear;
import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;
import goodtrailer.quizgen.util.IResourceUtils;

public class AdjacentAngleProblem extends AbstractFrqProblem
{
    private static final int min_abc = 90;
    private static final int max_abc = 315;
    private static final int variants_count = 3;
    
    private int angleABC;
    private Linear angleABD;
    private Linear angleCBD;
    private Point solution;
    
    private int variant;
    
    @Override
    protected void initialize()
    {
        variant = (int) (Math.random() * variants_count);
        
        angleABC = IMathUtils.randomInt(min_abc, max_abc);
        
        angleABD = Linear.random();
        while (angleABD.isConstant())
            angleABD = Linear.random();
        
        angleCBD = Linear.random();
        while (angleCBD.isConstant())
            angleCBD = Linear.random();
        
        var angleSum = angleABD.add(angleCBD);
        double x = angleSum.solution(new Linear(0, angleABC)).point().x();
        solution = new Point(angleABD.evaluate(x), angleCBD.evaluate(x));
    }
    
    @Override
    protected String getPrompt()
    {
        String message = "In the diagram below, \u2220ABD and \u2220CBD are adjacent angles. The "
                + "diagram is not to scale.\n\n"
                + "m\u2220ABC = %d\u00B0\n"
                + "m\u2220ABD = a\u00B0 = (%s)\u00B0\n"
                + "m\u2220CBD = c\u00B0 = (%s)\u00B0\n\n"
                + "Find the values of a and c. Give your solution in the form of a point (a, c).";
        return String.format(message, angleABC, angleABD.toString("x"), angleCBD.toString("x"));
    }

    @Override
    protected void addComponents(List<JComponent> components)
    {
        super.addComponents(components);

        String filename = String.format("AdjacentAngle%d.png", variant);
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

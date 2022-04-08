package goodtrailer.quizgen.app.problem.chapter6;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import goodtrailer.quizgen.math.IMathUtils;
import goodtrailer.quizgen.math.Point;
import goodtrailer.quizgen.math.function.Exponential;
import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;

class ExponentialTableAdditionProblem extends AbstractFrqProblem
{
    private static final int max_x = 2;

    private Exponential exponential;
    private Point point0;
    private Point point1;

    @Override
    protected void initialize()
    {
        exponential = Exponential.random().withM(IMathUtils.randomInt(1));
        while (exponential.isConstant())
            exponential = Exponential.random().withM(IMathUtils.randomInt(1));

        int x0 = 0, x1 = 0;
        while (x0 == x1)
        {
            x0 = IMathUtils.randomInt(max_x);
            x1 = IMathUtils.randomInt(max_x);
        }
        point0 = new Point(x0, exponential.evaluate(x0));
        point1 = new Point(x1, exponential.evaluate(x1));
    }

    @Override
    protected void addComponents(List<JComponent> components)
    {
        super.addComponents(components);

        var headers = new String[] { "x", "f(x)" };
        var data = new String[2 * max_x + 1][2];
        for (int i = 0, x = -max_x; x <= max_x; i++, x++)
        {
            data[i][0] = IMathUtils.toString(x);
            data[i][1] = IMathUtils.toString(exponential.evaluate(x));
        }
        var table = new JTable(data, headers);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        var scrollPane = new JScrollPane(table);
        scrollPane.setMaximumSize(new Dimension(300, 0));

        components.addAll(components.size() - 2, List.of(scrollPane, createFiller()));
    }

    @Override
    protected String getPrompt()
    {
        return String.format("What is the value of { f(%s) + f(%s) }?",
                IMathUtils.toString(point0.x()),
                IMathUtils.toString(point1.x()));
    }

    @Override
    protected Result checkInput(String string)
    {
        double input;
        try
        {
            input = IMathUtils.parseFraction(string);
        }
        catch (NumberFormatException nfe)
        {
            return Result.INVALID;
        }

        return Result.from(IMathUtils.areEqual(input, point0.y() + point1.y()));
    }
}

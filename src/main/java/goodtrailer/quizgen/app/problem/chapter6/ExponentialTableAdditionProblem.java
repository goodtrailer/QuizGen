package goodtrailer.quizgen.app.problem.chapter6;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;
import goodtrailer.quizgen.util.Exponential;
import goodtrailer.quizgen.util.MathUtils;
import goodtrailer.quizgen.util.Point;

class ExponentialTableAdditionProblem extends AbstractFrqProblem
{
    private static final int max_x = 2;

    private Exponential exponential;
    private Point point0;
    private Point point1;

    @Override
    protected void initialize()
    {
        exponential = Exponential.random().withM(MathUtils.randomInt(1));
        while (exponential.isConstant())
            exponential = Exponential.random().withM(MathUtils.randomInt(1));

        int x0 = 0, x1 = 0;
        while (x0 == x1)
        {
            x0 = MathUtils.randomInt(max_x);
            x1 = MathUtils.randomInt(max_x);
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
            data[i][0] = MathUtils.toString(x);
            data[i][1] = MathUtils.toString(exponential.evaluate(x));
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
                MathUtils.toString(point0.x()),
                MathUtils.toString(point1.x()));
    }

    @Override
    protected Result checkInput(String string)
    {
        double input;
        try
        {
            input = MathUtils.parseFraction(string);
        }
        catch (NumberFormatException nfe)
        {
            return Result.INVALID;
        }

        return Result.from(MathUtils.areEqual(input, point0.y() + point1.y()));
    }
}

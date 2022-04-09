package goodtrailer.quizgen.app.problem.chapter6;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import goodtrailer.quizgen.math.IFunction;
import goodtrailer.quizgen.math.IMathUtils;
import goodtrailer.quizgen.math.function.Exponential;
import goodtrailer.quizgen.math.function.Linear;
import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;
import goodtrailer.quizgen.util.IBooleanUtils;

class ExponentialTableBaseProblem extends AbstractFrqProblem
{
    private static final int max_x = 2;

    private IFunction function;
    private double base;

    @Override
    protected void initialize()
    {
        boolean isExponential = IBooleanUtils.random();

        function = IFunction.zero();
        while (function.isConstant())
        {
            function = isExponential
                    ? Exponential.random().withM(IMathUtils.randomInt(1))
                    : Linear.random();
        }
        base = isExponential ? ((Exponential) function).b() : 0;
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
            data[i][1] = IMathUtils.toString(function.evaluate(x));
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
        return "Is the following table's function exponential? If so, write its base (reciprocals "
                + "are valid). Otherwise, write 0.";
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

        boolean isCorrect = IMathUtils.areEqual(input, base)
                || IMathUtils.areEqual(input, 1.0 / base);
        
        return Result.from(isCorrect);
    }
}

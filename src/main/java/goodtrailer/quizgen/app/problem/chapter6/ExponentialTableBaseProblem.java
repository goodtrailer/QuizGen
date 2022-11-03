package goodtrailer.quizgen.app.problem.chapter6;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import goodtrailer.quizgen.math.IFunction;
import goodtrailer.quizgen.math.IMathConstants;
import goodtrailer.quizgen.math.IMathUtils;
import goodtrailer.quizgen.math.function.Exponential;
import goodtrailer.quizgen.math.function.Linear;
import goodtrailer.quizgen.problem.AbstractFrqProblem;
import goodtrailer.quizgen.problem.Result;
import goodtrailer.quizgen.util.IBooleanUtils;

class ExponentialTableBaseProblem extends AbstractFrqProblem
{
    private static final int max_x = 2;
    
    private static final int max_a = 3;
    private static final int max_b = 5;
    private static final int max_m = 1;
    private static final int max_c = 3;
    
    private static final int table_places = 12;

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
                    ? Exponential.random(max_a, max_b, max_m, max_c)
                    : Linear.random();
        }
        base = isExponential ? ((Exponential) function).b() : 0;
    }

    @Override
    protected void addComponents(List<JComponent> components)
    {
        super.addComponents(components);

        String input = IMathConstants.DEFAULT_VARIABLE;
        String output = String.format("f(%s)", IMathConstants.DEFAULT_VARIABLE);
        var headers = new String[] { input, output };
        
        var data = new String[2 * max_x + 1][2];
        for (int i = 0, x = -max_x; x <= max_x; i++, x++)
        {
            data[i][0] = IMathUtils.toString(x);
            data[i][1] = IMathUtils.toString(function.evaluate(x), table_places);
        }
        
        var table = new JTable(data, headers);
        int fontHeight = table.getFontMetrics(table.getFont()).getHeight();
        table.setRowHeight(fontHeight);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        
        var scrollPane = new JScrollPane(table);
        scrollPane.setMaximumSize(new Dimension(300, 0));

        components.addAll(components.size() - 2, List.of(scrollPane, createFiller()));
    }

    @Override
    protected String getPrompt()
    {
        String message = "Is the following table's function exponential?\n"
                + "If so, find its base (reciprocals are valid).\n"
                + "Otherwise, write 0.";
        
        return message;
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

        return Result.from(IMathUtils.equals(input, base) || IMathUtils.equals(input, 1.0 / base));
    }
}

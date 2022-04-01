package goodtrailer.quizgen.problem;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTextArea;

public abstract class AbstractFrqProblem extends AbstractProblem
{
    private JTextArea inputText;

    @Override
    public final Result submit()
    {
        var result = checkInput(inputText.getText());
        inputText.setBackground(result.toColor());
        return result;
    }

    @Override
    protected List<JComponent> getComponents()
    {
        inputText = new JTextArea();
        inputText.setColumns(COLUMNS);
        inputText.setLineWrap(true);
        return new ArrayList<JComponent>(List.of(inputText));
    }

    protected abstract Result checkInput(String input);
}

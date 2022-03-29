package goodtrailer.quizgen.problem;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class AbstractFrqProblem implements IProblem
{
    public static final Color  COLOR_CORRECT = new Color(0xD7F4D2);
    public static final Color COLOR_WRONG = new Color(0xFFA9A9);
    public static final int COLUMNS = 20;
    
    private JPanel panel = new JPanel();
    private JTextArea promptText = new JTextArea("void");
    private JTextArea inputText = new JTextArea();
    
    public AbstractFrqProblem()
    {
        promptText.setLineWrap(true);
        promptText.setEditable(false);
        promptText.setColumns(COLUMNS);
        inputText.setLineWrap(true);
        inputText.setColumns(COLUMNS);
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(promptText);
        panel.add(inputText);
    }
    
    public final boolean Submit()
    {
        boolean isCorrect = CheckInput(inputText.getText());
        inputText.setBackground(isCorrect ? COLOR_CORRECT : COLOR_WRONG);
        return isCorrect;
    }
    
    protected void SetPrompt(String prompt)
    {
        promptText.setText(prompt);
    }
    
    protected abstract boolean CheckInput(String input);
    
    @Override
    public JPanel GetPanel()
    {
        return panel;
    }
}

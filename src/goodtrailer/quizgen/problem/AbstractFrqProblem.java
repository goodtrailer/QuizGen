package goodtrailer.quizgen.problem;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class AbstractFrqProblem implements IProblem
{
    public static final Color  COLOR_CORRECT = new Color(0xD7F4D2);
    public static final Color COLOR_WRONG = new Color(0xFFA9A9);
    public static final int COLUMNS = 20;
    public static final int PADDING = 10;
    
    private JPanel panel = new JPanel();
    private JTextArea promptText = new JTextArea("void");
    private JTextArea inputText = new JTextArea();
    
    public AbstractFrqProblem()
    {
        promptText.setColumns(COLUMNS);
        promptText.setEditable(false);
        promptText.setLineWrap(true);
        promptText.setOpaque(false);
        inputText.setColumns(COLUMNS);
        inputText.setLineWrap(true);
        
        initialize();
        promptText.setText(getPrompt());
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        panel.add(promptText);
        panel.add(Box.createRigidArea(new Dimension(0, PADDING)));
        panel.add(inputText);
    }
    
    @Override
    public final boolean submit()
    {
        boolean isCorrect = checkInput(inputText.getText());
        inputText.setBackground(isCorrect ? COLOR_CORRECT : COLOR_WRONG);
        return isCorrect;
    }
    
    @Override
    public JPanel getPanel()
    {
        return panel;
    }
    
    protected abstract void initialize();
    protected abstract boolean checkInput(String input);
    protected abstract String getPrompt();
}

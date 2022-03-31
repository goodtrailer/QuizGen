package goodtrailer.quizgen.problem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class AbstractFrqProblem implements IProblem
{
    public static final Color COLOR_CORRECT = new Color(0xD7F4D2);
    public static final Color COLOR_INCORRECT = new Color(0xFFA9A9);
    public static final Color COLOR_INVALID = new Color(0xFEC98F);
    public static final int COLUMNS = 20;
    public static final int PADDING = 10;

    private JPanel panel = new JPanel();
    private JTextArea promptText = new JTextArea("void");
    private JLabel pictureLabel = new JLabel();
    private JTextArea inputText = new JTextArea();

    public AbstractFrqProblem()
    {
        initialize();

        promptText.setColumns(COLUMNS);
        promptText.setEditable(false);
        promptText.setLineWrap(true);
        promptText.setOpaque(false);
        promptText.setText(getPrompt());

        pictureLabel.setIcon(getImage());

        inputText.setColumns(COLUMNS);
        inputText.setLineWrap(true);

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        panel.add(promptText);
        panel.add(Box.createRigidArea(new Dimension(0, PADDING)));
        panel.add(pictureLabel);
        panel.add(Box.createRigidArea(new Dimension(0, PADDING)));
        panel.add(inputText);
        
        for (var c : panel.getComponents())
            if (c instanceof JComponent jc)
                jc.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    @Override
    public final Result submit()
    {
        var result = checkInput(inputText.getText());
        inputText.setBackground(switch (result)
        {
        case INCORRECT -> COLOR_INCORRECT;
        case CORRECT -> COLOR_CORRECT;
        case INVALID -> COLOR_INVALID;
        });
        return result;
    }

    @Override
    public JPanel getPanel()
    {
        return panel;
    }

    protected abstract Result checkInput(String input);

    protected abstract String getPrompt();

    protected abstract Icon getImage();

    protected abstract void initialize();
}

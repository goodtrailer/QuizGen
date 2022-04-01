package goodtrailer.quizgen.problem;

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class AbstractProblem implements IProblem
{
    public static final int COLUMNS = 20;
    public static final int PADDING = 10;
    
    private JPanel panel = new JPanel();
    private JTextArea promptText = new JTextArea("void");

    public AbstractProblem()
    {
        initialize();

        promptText.setColumns(COLUMNS);
        promptText.setEditable(false);
        promptText.setLineWrap(true);
        promptText.setOpaque(false);
        promptText.setText(getPrompt());

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        panel.add(promptText);
        panel.add(Box.createRigidArea(new Dimension(0, PADDING)));
        var components = getComponents();
        if (components != null)
            for (var c : components)
            {
                panel.add(c);
                panel.add(Box.createRigidArea(new Dimension(0, PADDING)));
            }

        for (var c : panel.getComponents())
            if (c instanceof JComponent jc)
                jc.setAlignmentX(Component.LEFT_ALIGNMENT);
    }
    
    @Override
    public final JComponent getRootComponent()
    {
        return panel;
    }

    protected abstract void initialize();

    protected abstract List<JComponent> getComponents();

    protected abstract String getPrompt();
}

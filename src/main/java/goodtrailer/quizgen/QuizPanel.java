package goodtrailer.quizgen;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import goodtrailer.quizgen.problem.IProblem;
import goodtrailer.quizgen.problem.IProblemFactory;
import goodtrailer.quizgen.problem.Result;

public class QuizPanel extends JPanel
{
    public static final int SCROLL_INCREMENT = 5;
    public static final int PADDING = 5;

    private static final long serialVersionUID = 4126538549451781876L;

    private Box problemsBox = new Box(BoxLayout.PAGE_AXIS);
    private Box bottomBox = new Box(BoxLayout.LINE_AXIS);
    private JLabel correctLabel = new JLabel("\u2013/\u2013");
    private JButton submitButton = new JButton("Submit");
    private JScrollPane scrollPane = new JScrollPane(problemsBox);
    private IProblem[] problems;
    
    public QuizPanel()
    {
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        
        submitButton.addActionListener((ActionEvent ae) -> submitAll());
        bottomBox.add(correctLabel);
        bottomBox.add(Box.createHorizontalGlue());
        bottomBox.add(submitButton);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        add(scrollPane);
        add(Box.createRigidArea(new Dimension(0, PADDING)));
        add(bottomBox);
        
        for (var c : getComponents())
            if (c instanceof JComponent jc)
                jc.setAlignmentX(Component.RIGHT_ALIGNMENT);
    }

    public void generate(IProblemFactory factory, int count)
    {
        problems = new IProblem[count];
        problemsBox.removeAll();
        
        {
            var p = factory.get();
            problems[count - 1] = p;
            problemsBox.add(p.getRootComponent(), 0);
        }
        
        for (int i = count - 2; i >= 0; i--)
        {
            var p = factory.get();
            problems[i] = p;
            problemsBox.add(new JSeparator(SwingConstants.HORIZONTAL), 0);
            problemsBox.add(p.getRootComponent(), 0);
        }
    }

    public int submitAll()
    {
        int correct = 0;
        for (var question : problems)
            if (question.submit() == Result.CORRECT)
                correct++;
        correctLabel.setText(String.format("%d/%d", correct, problems.length));
        return correct;
    }

    public int count()
    {
        return problems.length;
    }
}

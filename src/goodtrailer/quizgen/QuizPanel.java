package goodtrailer.quizgen;

import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import goodtrailer.quizgen.problem.IProblem;
import goodtrailer.quizgen.problem.IProblemFactory;
import goodtrailer.quizgen.problem.Result;

public class QuizPanel extends JPanel
{
    public static final int SCROLL_INCREMENT = 5;

    private static final long serialVersionUID = 4126538549451781876L;

    private Box problemsBox;
    private JButton submitButton;
    private JScrollPane scrollPane;
    private IProblem[] problems;

    {
        problemsBox = new Box(BoxLayout.PAGE_AXIS);
        submitButton = new JButton("Submit");
        scrollPane = new JScrollPane(problemsBox);
    }
    
    public QuizPanel()
    {
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        submitButton.addActionListener((ActionEvent ae) ->
        {
            submitAll();
        });

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(scrollPane);
        add(submitButton);
    }

    public void generate(IProblemFactory factory, int count)
    {
        problems = new IProblem[count];
        problemsBox.removeAll();
        for (int i = count - 1; i >= 0; i--)
        {
            var p = factory.get();
            problems[i] = p;
            problemsBox.add(new JSeparator(SwingConstants.HORIZONTAL), 0);
            problemsBox.add(p.getPanel(), 0);
        }
    }

    public int submitAll()
    {
        int correct = 0;
        for (var question : problems)
            if (question.submit() == Result.CORRECT)
                correct++;
        return correct;
    }

    public int count()
    {
        return problems.length;
    }
}

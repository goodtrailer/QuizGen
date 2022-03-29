package goodtrailer.quizgen;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import goodtrailer.quizgen.problem.IProblem;
import goodtrailer.quizgen.problem.IProblemFactory;

public class QuizPanel extends JPanel
{
    public static final int SCROLL_INCREMENT = 5;

    private static final long serialVersionUID = 4126538549451781876L;

    private Box problemsBox = new Box(BoxLayout.Y_AXIS);
    private JButton submitButton = new JButton("Submit");
    private JScrollPane scrollPane = new JScrollPane(problemsBox);
    private ArrayList<IProblem> problems = new ArrayList<IProblem>();

    public QuizPanel()
    {
        scrollPane.getVerticalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(SCROLL_INCREMENT);
        submitButton.addActionListener((ActionEvent ae) ->
        {
            SubmitAll();
        });

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(scrollPane);
        add(submitButton);
    }

    public void Generate(IProblemFactory factory, int count)
    {
        problems.clear();
        problemsBox.removeAll();
        for (int i = 0; i < count; i++)
        {
            var p = factory.Generate();
            problems.add(p);
            problemsBox.add(new JSeparator(SwingConstants.HORIZONTAL), 0);
            problemsBox.add(p.GetPanel(), 0);
        }
    }

    public int SubmitAll()
    {
        int correct = 0;
        for (var question : problems)
            if (question.Submit())
                correct++;
        return correct;
    }

    public int GetCount()
    {
        return problems.size();
    }
}

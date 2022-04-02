package goodtrailer.quizgen;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

import goodtrailer.quizgen.problem.IProblem;
import goodtrailer.quizgen.problem.IProblemFactory;
import goodtrailer.quizgen.problem.Result;

public class QuizPanel extends JPanel
{
    private static final long serialVersionUID = 4126538549451781876L;

    private ScrollableBox problemsBox = new ScrollableBox(BoxLayout.PAGE_AXIS);
    private JScrollPane scrollPane = new JScrollPane(problemsBox);
    private IProblem[] problems;

    public QuizPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(scrollPane);
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
        return correct;
    }

    public int count()
    {
        return problems.length;
    }

    private class ScrollableBox extends Box implements Scrollable
    {
        public static final int SCROLL_INCREMENT = 20;
        
        private static final long serialVersionUID = 8642183969100983700L;
        
        public ScrollableBox(int axis)
        {
            super(axis);
        }

        @Override
        public Dimension getPreferredScrollableViewportSize()
        {
            return getPreferredSize();
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle visible, int orientation, int direction)
        {
            return SCROLL_INCREMENT;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle visible, int orientation, int direction)
        {
            return SCROLL_INCREMENT;
        }

        @Override
        public boolean getScrollableTracksViewportWidth()
        {
            return true;
        }

        @Override
        public boolean getScrollableTracksViewportHeight()
        {
            return false;
        }
    }
}

package goodtrailer.quizgen;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import goodtrailer.quizgen.problem.ChapterProblemFactory;

public class Main
{
    public static final String NAME = "QuizGen";
    public static final Dimension DEFAULT_SIZE = new Dimension(600, 400);
    public static final FontUIResource UI_FONT = new FontUIResource("Georgia", Font.PLAIN, 14);
    
    public static void main(String[] vargs)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.err.println("Failed to set Look & Feel.");
        }

        try
        {
            Iterable<?> keys = () -> UIManager.getDefaults().keys().asIterator();
            for (var key : keys)
                if (UIManager.get(key) instanceof FontUIResource)
                    UIManager.put(key, UI_FONT);
        }
        catch (Exception e)
        {
            System.err.println("Failed to set font.");
        }

        SwingUtilities.invokeLater(Main::run);
    }

    private static void run()
    {
        var frame = new JFrame(NAME);
        var quizPanel = new QuizPanel();
        quizPanel.generate(new ChapterProblemFactory(10), 30);
        quizPanel.setPreferredSize(DEFAULT_SIZE);

        frame.add(quizPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

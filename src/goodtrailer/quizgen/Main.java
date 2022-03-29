package goodtrailer.quizgen;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import goodtrailer.quizgen.problem.Ch10ProblemFactory;

public class Main
{
    public static void main(String[] vargs)
    {
        SwingUtilities.invokeLater(Main::run);
    }
    
    private static void run()
    {
        JFrame frame = new JFrame("QuizGen");
        QuizPanel quizPanel = new QuizPanel();
        quizPanel.Generate(new Ch10ProblemFactory(), 10);
        
        frame.add(quizPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

package goodtrailer.quizgen.problem;

import javax.swing.JPanel;

public interface IProblem
{
    Result submit();
    JPanel getPanel();
}

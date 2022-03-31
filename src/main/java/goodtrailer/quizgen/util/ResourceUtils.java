package goodtrailer.quizgen.util;

import javax.swing.ImageIcon;

public abstract class ResourceUtils
{
    public static final ImageIcon loadImage(String path)
    {
        if (path.isBlank())
            return null;
        
        return new ImageIcon(ResourceUtils.class.getResource(path));
    }
}

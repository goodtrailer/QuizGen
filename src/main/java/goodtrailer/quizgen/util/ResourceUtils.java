package goodtrailer.quizgen.util;

import java.io.InputStream;

import javax.swing.ImageIcon;

public final class ResourceUtils
{
    private ResourceUtils()
    {
    }

    public static final ImageIcon getImage(Class<?> origin, String path)
    {
        var resource = origin.getResource(path);
        if (resource == null)
            return null;
        return new ImageIcon(resource);
    }

    public static final ImageIcon getImage(Object origin, String path)
    {
        return getImage(origin.getClass(), path);
    }

    public static final InputStream getFile(Class<?> origin, String path)
    {
        return origin.getResourceAsStream(path);
    }
    
    public static final InputStream getFile(Object origin, String path)
    {
        return getFile(origin.getClass(), path);
    }
}

package us.codecraft.forger.property.format;

/**
 * @author code4crafter@gmail.com
 */
public interface ObjectFormatter<T> {

    T format(String raw);

    T format(String raw, String[] extra);

    Class<T> clazz();

}

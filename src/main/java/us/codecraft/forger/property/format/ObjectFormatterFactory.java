package us.codecraft.forger.property.format;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public class ObjectFormatterFactory {

    private Map<Class, ObjectFormatter> formatterMap = new ConcurrentHashMap<Class, ObjectFormatter>();

    public ObjectFormatterFactory() {
        initFormatterMap();
    }

    private void initFormatterMap() {
        for (Class<? extends ObjectFormatter> basicTypeFormatter : BasicTypeFormatter.basicTypeFormatters) {
            put(basicTypeFormatter);
        }
        put(DateFormatter.class);
    }

    public void put(Class<? extends ObjectFormatter> objectFormatterClazz) {
        try {
            ObjectFormatter objectFormatter = objectFormatterClazz.newInstance();
            formatterMap.put(objectFormatter.clazz(), objectFormatter);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ObjectFormatter get(Class<?> clazz) {
        return formatterMap.get(clazz);
    }
}

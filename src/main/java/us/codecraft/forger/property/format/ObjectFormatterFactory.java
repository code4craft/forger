package us.codecraft.forger.property.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author code4crafter@gmail.com
 * @since 0.3.2
 */
public class ObjectFormatterFactory {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<Class, ObjectFormatter> objectFormatterMapWithPropertyAsKey = new ConcurrentHashMap<Class, ObjectFormatter>();

    private Map<Class, ObjectFormatter> objectFormatterMapWithClassAsKey = new ConcurrentHashMap<Class, ObjectFormatter>();

    public ObjectFormatterFactory() {
        initFormatterMap();
    }

    private void initFormatterMap() {
        for (Class<? extends ObjectFormatter> basicTypeFormatter : BasicTypeFormatter.basicTypeFormatters) {
            put(basicTypeFormatter);
        }
        put(DateFormatter.class);
    }

    public synchronized void put(Class<? extends ObjectFormatter> objectFormatterClazz) {
        try {
            ObjectFormatter objectFormatter = objectFormatterClazz.newInstance();
            if (objectFormatter.clazz() != null) {
                objectFormatterMapWithPropertyAsKey.put(objectFormatter.clazz(), objectFormatter);
            }
            objectFormatterMapWithClassAsKey.put(objectFormatterClazz, objectFormatter);
        } catch (InstantiationException e) {
            logger.error("Init objectFormatter error", e);
        } catch (IllegalAccessException e) {
            logger.error("Init objectFormatter error", e);
        }
    }

    public ObjectFormatter get(Class<?> clazz) {
        return objectFormatterMapWithPropertyAsKey.get(clazz);
    }
}

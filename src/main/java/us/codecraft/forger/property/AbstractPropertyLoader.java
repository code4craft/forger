package us.codecraft.forger.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.forger.property.format.BasicTypeFormatter;
import us.codecraft.forger.property.format.Formatter;
import us.codecraft.forger.property.format.ObjectFormatter;
import us.codecraft.forger.property.format.ObjectFormatterFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 */
public abstract class AbstractPropertyLoader implements PropertyLoader {

    private ObjectFormatterFactory objectFormatterFactory = new ObjectFormatterFactory();

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected ObjectFormatterFactory getObjectFormatterFactory() {
        return objectFormatterFactory;
    }

    @Override
    public <T> T load(T object, Map<String, Object> propertyConfigs) {
        List<Property> properties = getProperties(object.getClass());
        for (Property property : properties) {
            Object value = propertyConfigs.get(property.getName());
            if (value == null) {
                throw new IllegalArgumentException("Config for property " + property.getName() + " is missing!");
            }
            switch (property.getType()) {
                case PropertyString:
                    ObjectFormatter objectFormatter = property.getObjectFormatter();
                    Object fieldValue = objectFormatter.format(String.valueOf(value), property.getExtras());
                    try {
                        property.getField().set(object, fieldValue);
                    } catch (IllegalAccessException e) {
                        logger.warn("Set field " + property.getField() + " error!", e);
                    }
                    break;
                case PropertyList:
                    if (!List.class.isAssignableFrom(object.getClass())) {
                        throw new IllegalArgumentException("Config for property " + property.getName() + " should be subclass of List!");
                    }

                    break;
                //TODO other type
            }
        }
        return object;
    }

    protected ObjectFormatter getObjectFormatter(Field field) {
        if (field.isAnnotationPresent(Formatter.class)) {
            Formatter formatter = field.getAnnotation(Formatter.class);
            if (formatter.formatter() != null) {
                ObjectFormatter objectFormatter = objectFormatterFactory.get(formatter.formatter());
                if (objectFormatter != null) {
                    return objectFormatter;
                }
                objectFormatterFactory.put(formatter.formatter());
                return objectFormatterFactory.get(formatter.formatter());
            }
            if (!formatter.subClazz().equals(Void.class)) {
                ObjectFormatter objectFormatter = objectFormatterFactory.get(formatter.subClazz());
                if (objectFormatter == null) {
                    throw new IllegalArgumentException("No objectFormatter for class " + formatter.subClazz());
                }
                return objectFormatter;
            }
        }
        return getObjectFormatterFactory().get(BasicTypeFormatter.detectBasicClass(field.getType()));
    }

}

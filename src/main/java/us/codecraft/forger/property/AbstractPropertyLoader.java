package us.codecraft.forger.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.forger.property.format.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 */
public abstract class AbstractPropertyLoader implements PropertyLoader {

    private TypeFormatterFactory typeFormatterFactory = new TypeFormatterFactory();

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected TypeFormatterFactory getTypeFormatterFactory() {
        return typeFormatterFactory;
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
                    ObjectFormatter typeFormatter = property.getObjectFormatter();
                    Object fieldValue = typeFormatter.format(String.valueOf(value));
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

    protected ObjectFormatter prepareParam(TypeFormatter objectFormatter, String[] params) {
        if (params == null) {
            return objectFormatter;
        }
        return new ObjectFormatterWithParams().setTypeFormatter(objectFormatter).setParams(params);
    }

    protected ObjectFormatter getObjectFormatter(Field field) {
        if (field.isAnnotationPresent(Formatter.class)) {
            Formatter formatter = field.getAnnotation(Formatter.class);
            if (formatter.formatter() != null) {
                TypeFormatter typeFormatter = typeFormatterFactory.get(formatter.formatter());
                if (typeFormatter != null) {
                    return typeFormatter;
                }
                typeFormatterFactory.put(formatter.formatter());
                return prepareParam(typeFormatterFactory.get(formatter.formatter()), formatter.value());
            } else if (!formatter.subClazz().equals(Void.class)) {
                TypeFormatter typeFormatter = typeFormatterFactory.get(formatter.subClazz());
                if (typeFormatter == null) {
                    throw new IllegalArgumentException("No typeFormatter for class " + formatter.subClazz());
                }
                return prepareParam(typeFormatterFactory.get(formatter.formatter()), formatter.value());
            }
        }
        return getTypeFormatterFactory().get(BasicTypeFormatter.detectBasicClass(field.getType()));
    }

}

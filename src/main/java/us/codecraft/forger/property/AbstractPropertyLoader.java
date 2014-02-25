package us.codecraft.forger.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.forger.property.format.BasicTypeFormatter;
import us.codecraft.forger.property.format.ObjectFormatter;
import us.codecraft.forger.property.format.ObjectFormatterFactory;

import java.util.List;
import java.util.Map;

/**
 * @author yihua.huang@dianping.com
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
                    ObjectFormatter objectFormatter = getObjectFormatterFactory().get(BasicTypeFormatter.detectBasicClass(property.getField().getType()));
                    Object fieldValue = objectFormatter.format(String.valueOf(value), property.getExtras());
                    try {
                        property.getField().set(object, fieldValue);
                    } catch (IllegalAccessException e) {
                        logger.warn("Set field " + property.getField() + " error!", e);
                    }
                    break;
                //TODO other type
            }
        }
        return object;
    }

}

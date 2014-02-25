package us.codecraft.forger.property;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihua.huang@dianping.com
 */
public class SimpleFieldPropertyLoader extends AbstractPropertyLoader {

    @Override
    public List<Property> getProperties(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Property> properties = new ArrayList<Property>(fields.length);
        for (Field field : fields) {
            if (!field.isAccessible()){
                field.setAccessible(true);
            }
            properties.add(Property.fromField(field));
        }
        return properties;
    }
}

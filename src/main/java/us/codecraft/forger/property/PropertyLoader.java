package us.codecraft.forger.property;

import java.util.List;
import java.util.Map;

/**
 * @author yihua.huang@dianping.com
 */
public interface PropertyLoader {

    public <T> T load(T object, Map<String, Object> propertyConfigs);

    public List<Property> getProperties(Class clazz);

}

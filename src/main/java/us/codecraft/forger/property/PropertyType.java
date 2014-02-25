package us.codecraft.forger.property;

import java.util.Map;

/**
 * @author yihua.huang@dianping.com
 */
public enum PropertyType {

    PropertyString,PropertyMap,PropertyList;

    public static PropertyType from(Class clazz){
        if (clazz.isAssignableFrom(Map.class)){
            return PropertyMap;
        }
        if (clazz.isAssignableFrom(Iterable.class)){
            return PropertyList;
        }
        return PropertyString;
    }

}

package us.codecraft.forger.property;

import java.lang.reflect.Field;

/**
 * @author yihua.huang@dianping.com
 */
public class Property {

    private String name;

    private PropertyType type;

    private Field field;

    private String[] extras;

    public Property(String name, PropertyType type, Field field) {
        this.name = name;
        this.type = type;
        this.field = field;
    }

    public Property(String name, PropertyType type, Field field, String[] extras) {
        this.name = name;
        this.type = type;
        this.field = field;
        this.extras = extras;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String[] getExtras() {
        return extras;
    }

    public void setExtras(String[] extras) {
        this.extras = extras;
    }

    public static Property fromField(Field field) {
        return new Property(field.getName(), PropertyType.from(field.getType()), field);
    }
}

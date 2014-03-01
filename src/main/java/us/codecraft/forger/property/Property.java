package us.codecraft.forger.property;

import java.lang.reflect.Field;

/**
 * @author code4crafter@gmail.com
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

    public Property setName(String name) {
        this.name = name;
        return this;
    }

    public PropertyType getType() {
        return type;
    }

    public Property setType(PropertyType type) {
        this.type = type;
        return this;
    }

    public Field getField() {
        return field;
    }

    public Property setField(Field field) {
        this.field = field;
        return this;
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

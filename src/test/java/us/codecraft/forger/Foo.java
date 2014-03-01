package us.codecraft.forger;

import us.codecraft.forger.property.Inject;
import us.codecraft.forger.property.format.Formatter;

/**
 * @author code4crafter@gmail.com
 */
public class Foo {

    @Formatter("")
    @Inject("fooa")
    private String foo;

    public String getFoo() {
        return foo;
    }

}

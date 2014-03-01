package us.codecraft.forger;

import us.codecraft.forger.property.Inject;

/**
 * @author code4crafter@gmail.com
 */
public class Foo {

    @Inject("fooa")
    private String foo;

    public String getFoo() {
        return foo;
    }

}

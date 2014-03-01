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

    public static final String SOURCE_CODE = "import us.codecraft.forger.property.Inject;\n" +
            "import us.codecraft.forger.property.format.Formatter;\n" +
            "\n" +
            "/**\n" +
            " * @author code4crafter@gmail.com\n" +
            " */\n" +
            "public class Foo {\n" +
            "\n" +
            "    @Formatter(\"\")\n" +
            "    @Inject(\"fooa\")\n" +
            "    private String foo;\n" +
            "\n" +
            "    public String getFoo() {\n" +
            "        return foo;\n" +
            "    }\n" +
            "\n" +
            "}";

}

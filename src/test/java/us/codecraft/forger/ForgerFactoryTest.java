package us.codecraft.forger;

import com.google.common.collect.ImmutableMap;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import us.codecraft.forger.property.SimpleFieldPropertyLoader;

/**
 * @author code4crafter@gmail.com
 */
public class ForgerFactoryTest {

    @Test
    public void testForgerCreateByClass() throws Exception {
        ForgerFactory forgerFactory = new ForgerFactory(new SimpleFieldPropertyLoader(), null);
        Forger<Foo> forger = forgerFactory.<Foo>create(Foo.class);
        Foo foo = forger.forge(ImmutableMap.<String, Object>of("foo", "test"));
        Assertions.assertThat(foo.getFoo()).isEqualTo("test");
    }
}

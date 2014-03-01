package us.codecraft.forger;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import us.codecraft.forger.property.AnnotationPropertyLoader;
import us.codecraft.forger.property.SimpleFieldPropertyLoader;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafter@gmail.com
 */
public class ForgerFactoryTest {

    @Test
    public void testForgerCreateByClassProperty() throws Exception {
        ForgerFactory forgerFactory = new ForgerFactory(new SimpleFieldPropertyLoader(), null);
        Forger<Foo> forger = forgerFactory.<Foo>create(Foo.class);
        Foo foo = forger.forge(ImmutableMap.<String, Object>of("foo", "test"));
        assertThat(foo.getFoo()).isEqualTo("test");
    }

    @Test
    public void testForgerCreateByClassAnnotation() throws Exception {
        ForgerFactory forgerFactory = new ForgerFactory(new AnnotationPropertyLoader(), null);
        Forger<Foo> forger = forgerFactory.<Foo>create(Foo.class);
        Foo foo = forger.forge(ImmutableMap.<String, Object>of("fooa", "test"));
        assertThat(foo.getFoo()).isEqualTo("test");
    }
}

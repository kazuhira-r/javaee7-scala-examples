package org.littlewings.javaee7.cdi;

import static org.assertj.core.api.Assertions.*;

import javax.enterprise.inject.spi.CDI;

import org.jboss.weld.environment.se.Weld;

import org.junit.Test;

public class ConstructorInjectionTest {
    @Test
    public void testCdiConstructorInjection() {
        Weld weld = new Weld();
        weld.initialize();

        DecorationService decorator = CDI.current().select(DecorationService.class).get();

        assertThat(decorator.join("*** ", " ***", "Hello", "World"))
            .isEqualTo("*** Hello, World ***");

        weld.shutdown();
    }
}

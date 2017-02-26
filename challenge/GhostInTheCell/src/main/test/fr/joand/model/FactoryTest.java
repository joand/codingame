package fr.joand.model;

import fr.joand.App;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

public class FactoryTest {

    @Test
    public void getFactory() {
        Factory a = new Factory(1, null, 0, 0);
        Factory b = new Factory(2, null, 0, 0);
        Factory c = new Factory(3, null, 0, 0);
        List<Factory> factories = new ArrayList<>();
        factories.add(a);
        factories.add(b);
        factories.add(c);

        Factory factory = App.getFactory(factories, 2);
        assertEquals(factory, b);

        assertNotEquals(factory, a);
        assertNotEquals(factory, c);

        Factory nullFactory = App.getFactory(factories, 5);
        assertNull(nullFactory);
    }
}

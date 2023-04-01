package org.ww.adt.tests;

import org.ww.adt.data.DAOEngine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest
{

    @Test
    public void testCanBuildDB()
    {
        new DAOEngine();
        assertEquals(true, true);
    }
}

package pl.devdmin.core.util;

import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.deepEquals;
import static org.junit.Assert.assertEquals;

public class UtilsClassTest {
    private String[][] array;
    @Before
    public void setUp(){
        array = new String[][]{{"A","B","C"},{"D","E","F"},{"G","H","I"}, {"J","K","L"}, {"M","N","O"} };
    }

    @Test
    public void testCopyArray(){
        String[][] copiedArray = UtilsClass.cloneArray(array, 0, array.length);
        deepEquals(array, copiedArray);

     }

    @Test
    public void testCopyofRangeArray(){
        int from = 2;
        int to = 4;
        String[][] copiedArray = UtilsClass.cloneArray(array, from,to);
        for(int i = 0; i < (from-to); i++)
            for(int j = 0; j < copiedArray[0].length ; j++)
                assertEquals(array[i+from][j],copiedArray[i][j]);
    }

}

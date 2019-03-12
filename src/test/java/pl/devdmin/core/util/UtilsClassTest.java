package pl.devdmin.core.util;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static java.util.Arrays.deepEquals;
import static java.util.Arrays.parallelPrefix;
import static org.junit.Assert.assertEquals;

public class UtilsClassTest {
    private String[][] array;
    private String[] data;
    @Before
    public void setUp(){
        array = new String[][]{{"A","B","C"},{"D","E","F"},{"G","H","I"}, {"J","K","L"}, {"M","N","O"} };
        data = new String[]{"hello world", "text", "example"};
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

    @Test
    public void testInsertBottomRowToArray(){
        String[][] newArray = UtilsClass.insertRowToArray(data, array, RowInsertion.BOTTOM);
        for(int o = 0; o < data.length; o++)
            assertEquals(data[o],newArray[(array.length)][o]);

    }

    @Test
    public void testInsertTopRowToArray(){
        String[][] newArray = UtilsClass.insertRowToArray(data, array, RowInsertion.TOP);
        for(int o = 0; o < data.length; o++)
            assertEquals(data[o],newArray[0][o]);

    }

}

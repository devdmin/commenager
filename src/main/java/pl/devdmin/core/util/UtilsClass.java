package pl.devdmin.core.util;

import java.util.Arrays;

public class UtilsClass {
    public static String[][] cloneArray(String[][] src, int from, int to) {
        String[][] target = new String[to-from][src[0].length];

        for(int i=0; i<(to-from); i++){
            for(int j=0; j<src[0].length; j++) {
                target[i][j] = src[i+from][j];
            }
        }
        return target;
    }

    public static String[][] insertRowToArray(String[] data, String[][] array, RowInsertion insertion){
        if(data.length == array[0].length) {
            String[][] newArray = new String[array.length + 1][array[0].length];
            switch (insertion) {
                case TOP:
                    for(int i=0; i<array.length; i++)
                        for (int j = 0; j < newArray[i].length; j++)
                            newArray[i + 1][j] = array[i][j];

                    for (int i = 0; i < data.length; i++)
                        newArray[0][i] = data[i];

                    break;
                case BOTTOM:
                    for(int i=0; i<array.length; i++)
                        for(int j=0; j< array[i].length; j++)
                            newArray[i][j]=array[i][j];

                    for (int i = 0; i < data.length; i++)
                        newArray[array.length][i] = data[i];
                    break;
            }
            return newArray;
        }else{
            throw new RuntimeException();
        }
    }
}

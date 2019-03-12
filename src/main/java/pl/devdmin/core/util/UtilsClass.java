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
            switch (insertion) {
                case TOP:
                    break;
                case BOTTOM:
                    array = Arrays.copyOf(array, array.length + 2);
                    for (int i = 0; i < data.length; i++) {
                        array[array.length][i] = data[i];
                    }
                    break;
            }
            return array;
        }else{
            throw new RuntimeException();
        }
    }
}

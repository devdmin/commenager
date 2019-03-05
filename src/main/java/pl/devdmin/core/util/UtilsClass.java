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
}

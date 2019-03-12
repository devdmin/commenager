package pl.devdmin.core.acquisition.pdf;

import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.pdf.AbstractPDFBuilder;
import pl.devdmin.pdf.PDFBuilder;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class PDFAcqusitionBuilder extends AbstractPDFBuilder<Acquisition>{
    private final static String HEADING_TEXT = "ACQUSITUION RAPORT";

    public PDFAcqusitionBuilder() {
        super.HEADING_TEXT = HEADING_TEXT;
    }

    @Override
    protected String[][] getArrayFromSet(Set<Acquisition> modelSet) {
        String[][] array = new String[modelSet.size()+1][4];

        array[0][0] = "Product";
        array[0][1] = "Amount";
        array[0][2] = "Price";
        array[0][3] = "Total Price";

        int i = 1;
        for(Acquisition acquisition : modelSet){
            array[i][0] = acquisition.getProduct().getName();
            array[i][1] = String.valueOf(acquisition.getAmount());
            array[i][2] = acquisition.getPrice().toString();
            array[i][3] = acquisition.getTotalPrice().toString();
            i++;
        }
        return array;
    }

    @Override
    protected BigDecimal getSumTotalPrice(Set<Acquisition> modelSet) {
        return null;
    }

    @Override
    public File getFile() throws IOException {
        return File.createTempFile(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"acquisition.pdf");
    }
}

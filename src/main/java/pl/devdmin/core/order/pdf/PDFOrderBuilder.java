package pl.devdmin.core.order.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import pl.devdmin.core.order.Order;
import pl.devdmin.pdf.AbstractPDFBuilder;
import pl.devdmin.pdf.PDFBuilder;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class PDFOrderBuilder extends AbstractPDFBuilder<Order> {
    private final static String HEADING_TEXT = "ORDER RAPORT";

    public PDFOrderBuilder() {
        super.HEADING_TEXT = HEADING_TEXT;
    }

    @Override
    public File getFile() throws IOException {
        return File.createTempFile(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"order.pdf");
    }

    @Override
    protected String[][] getArrayFromSet(Set<Order> modelSet) {
        String[][] array = new String[modelSet.size()+1][8];

        array[0][0] = "Date";
        array[0][1] = "Product";
        array[0][2] = "Amount";
        array[0][3] = "Price";
        array[0][4] = "VAT";
        array[0][5] = "Shipping Cost";
        array[0][6] = "Total Price";
        array[0][7] = "Address";


        int i = 1;
        for(Order order : modelSet){
            array[i][0] = order.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            array[i][1] = order.getProduct().getName();
            array[i][2] = String.valueOf(order.getAmount());
            array[i][3] = order.getPrice().toString();
            array[i][4] = String.valueOf(order.getVatValue());
            array[i][5] = order.getShippingCost().toString();
            array[i][6] = order.getTotalPrice().toString();
            array[i][7] = order.getAddress();
            i++;
        }
        return array;
    }
}

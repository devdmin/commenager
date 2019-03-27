package pl.devdmin.pdf.builder;

import org.apache.pdfbox.pdmodel.PDDocument;
import pl.devdmin.core.order.Order;
import pl.devdmin.pdf.AbstractPDFBuilder;
import pl.devdmin.pdf.PDFBuilder;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
        String[][] array = new String[modelSet.size()+2][8];


        array[0][0] = "Date";
        array[0][1] = "Address";
        array[0][2] = "Product";
        array[0][3] = "Amount";
        array[0][4] = "Price";
        array[0][5] = "VAT";
        array[0][6] = "Shipping Cost";
        array[0][7] = "Total Price";



        int i = 1;
        for(Order order : modelSet){
            array[i][0] = order.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            array[i][1] = order.getAddress();
            array[i][2] = order.getProduct().getName();
            array[i][3] = String.valueOf(order.getAmount());
            array[i][4] = order.getPrice().toString();
            array[i][5] = String.valueOf(order.getVatValue());
            array[i][6] = order.getShippingCost().toString();
            array[i][7] = order.getTotalPrice().toString();
            i++;
        }
        for(int k = 0; k < 7; k++) {
            array[i][k] = " ";
        }
        array[i][7] = getSumTotalPrice(modelSet).toString();
        return array;
    }

    protected BigDecimal getSumTotalPrice(Set<Order> orders) {
        BigDecimal sumTotalPrice = new BigDecimal("0");
        for (Order order : orders) {
            sumTotalPrice.add(order.getTotalPrice());
        }
        return sumTotalPrice;
    }

}

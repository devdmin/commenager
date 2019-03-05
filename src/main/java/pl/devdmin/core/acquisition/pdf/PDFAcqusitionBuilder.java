package pl.devdmin.core.acquisition.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.core.util.UtilsClass;
import pl.devdmin.pdf.PDFBuilder;

import java.io.File;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;

public class PDFAcqusitionBuilder implements PDFBuilder<Acquisition> {
    private Set<Acquisition> acquisitionSet;
    private PDDocument document;
    private final static String HEADING_TEXT = "ACQUSITUION RAPORT";

    public PDDocument build(Set<Acquisition> acquisitionSet) {
        this.acquisitionSet = acquisitionSet;
        this.document = createDocument();
        return document;
    }

    private PDDocument createDocument(){
        document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        createHeading(document, page);
        createBody(document, page, acquisitionSet);
        return document;
    }

    private void createHeading(PDDocument document, PDPage page) {
        try {

            PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true, true);
            printHeadingText(contentStream);
            printGeneratingDate(contentStream);
            contentStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void printHeadingText(PDPageContentStream stream) throws IOException {
        stream.setFont(PDType1Font.TIMES_ROMAN, 14);
        stream.beginText();
        stream.newLineAtOffset(200.0f,800.0f);
        stream.showText(HEADING_TEXT);
        stream.endText();
    }

    private void printGeneratingDate(PDPageContentStream stream) throws IOException{
        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        stream.setFont(PDType1Font.TIMES_ROMAN, 10);
        stream.beginText();
        stream.newLineAtOffset(450.0f,800.0f);
        stream.showText("Generated: " + formattedDate);
        stream.endText();
    }
    private void createBody(PDDocument document, PDPage page, Set<Acquisition> acquisitionSet){
        createTable(document, page, acquisitionSet);
    }


    private void createTable(PDDocument document, PDPage page, Set<Acquisition> acquisitionSet) {
       String[][] dataArray = getArrayFromSet(acquisitionSet);
        PDPage pdPage;
       for(int i = 0; i < dataArray.length; i = i + 36){
           if (i == 0) {
                pdPage = page;
           }else{
               pdPage = new PDPage(PDRectangle.A4);
               document.addPage(pdPage);
           }
           if(i+36 > dataArray.length){
               createTableOnPage(document,pdPage, UtilsClass.cloneArray(dataArray, i, dataArray.length), 750);
           }else {
               createTableOnPage(document,pdPage, UtilsClass.cloneArray(dataArray, i, i + 36), 750);
           }
        }
    }
    private void createTableOnPage(PDDocument document,PDPage page, String[][] dataArray, float y) {
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, true, true, true);
            drawTable(page, contentStream, y, 10, dataArray);
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[][] getArrayFromSet(Set<Acquisition> acquisitionSet){
        String[][] array = new String[acquisitionSet.size()+1][4];

        array[0][0] = "Product";
        array[0][1] = "Amount";
        array[0][2] = "Price";
        array[0][3] = "Total Price";

        int i = 1;
        for(Acquisition acquisition : acquisitionSet){
           array[i][0] = acquisition.getProduct().getName();
           array[i][1] = String.valueOf(acquisition.getAmount());
           array[i][2] = acquisition.getPrice().toString();
           array[i][3] = acquisition.getTotalPrice().toString();
            i++;
        }
        return array;
    }

    public File getFile() throws IOException {
        return File.createTempFile(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),"acquisition.pdf");
    }

    public static void drawTable(PDPage page, PDPageContentStream contentStream,
                                 float y, float margin,
                                 String[][] content) throws IOException {
        final int rows = content.length;
        final int cols = content[0].length;
        final float rowHeight = 20f;
        final float tableWidth = page.getMediaBox().getWidth()-(2*margin);
        final float tableHeight = rowHeight * rows;
        final float colWidth = tableWidth/(float)cols;
        final float cellMargin=5f;


        //draw the rows
        float nexty = y ;
        for (int i = 0; i <= rows; i++) {
            contentStream.drawLine(margin,nexty,margin+tableWidth,nexty);
            nexty-= rowHeight;
        }

        //draw the columns
        float nextx = margin;
        for (int i = 0; i <= cols; i++) {
            contentStream.drawLine(nextx,y,nextx,y-tableHeight);
            nextx += colWidth;
        }

        //now add the text
        contentStream.setFont(PDType1Font.HELVETICA_BOLD,8);

        float textx = margin+cellMargin;
        float texty = y-15;

        for(int i = 0; i < content.length; i++){
            for(int j = 0 ; j < content[i].length; j++){
                String text = content[i][j];
                contentStream.beginText();
                contentStream.newLineAtOffset(textx,texty);
                contentStream.showText(text);
                contentStream.endText();
                textx += colWidth;
            }

            texty-=rowHeight;
            textx = margin+cellMargin;
        }
    }

}

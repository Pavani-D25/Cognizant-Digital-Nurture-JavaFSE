public class FactoryMethodTest {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Test ===\n");

        // create factory objects
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        // use factories to create documents
        Document wordDoc = wordFactory.createDocument();
        Document pdfDoc = pdfFactory.createDocument();
        Document excelDoc = excelFactory.createDocument();

        // test Word document
        System.out.println("Document Type: " + wordDoc.getType());
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();

        // test PDF document
        System.out.println("\nDocument Type: " + pdfDoc.getType());
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();

        // test Excel document
        System.out.println("\nDocument Type: " + excelDoc.getType());
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();

        // test template method
        System.out.println("\n--- Using processDocument method ---");
        Document processedDoc = wordFactory.processDocument();
        System.out.println("Processed document type: " + processedDoc.getType());
    }
}

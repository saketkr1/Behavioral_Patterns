
/*
    * Template pattern is a behavioral design pattern that defines the program skeleton of an algorithm in the superclass
    *  but lets subclasses override specific steps of the algorithm without changing its structure.
    * The Template pattern is used when you want to let the subclasses implement the specific steps of an algorithm.
    * The Template pattern is used when you want to avoid code duplication by moving common code to a superclass.
 */

abstract class ReportGenerator  {
    protected abstract String collectData();
    protected abstract String processData(String data);
    protected abstract String formatReport(String processedData);

    private void printReport(String report) {
        System.out.println(report);
    }

    public void generateReport() {
        String data = collectData();
        String processedData = processData(data);
        String report = formatReport(processedData);
        printReport(report);
    }
}

class PdfReportGenerator extends ReportGenerator {
    @Override
    protected String collectData() {
        return "PDF Data";
    }

    @Override
    protected String processData(String data) {
        return "PDF Processed Data";
    }

    @Override
    protected String formatReport(String processedData) {
        return "PDF Report";
    }
}
public class Template {
    public static void main(String[] args) {
        ReportGenerator pdfReport = new PdfReportGenerator();
        pdfReport.generateReport();
    }
}

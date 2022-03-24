import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class createPdf {
    static String savepath = "C:/Users/Dell/Desktop/all.pdf";

    public static void main(String[] args) {
        System.out.println("start " + new Date());
        List<String> fileList = getFiles();
        morePdfTopdf(fileList, savepath);
    }

    public static List<String> getFiles() {
        List<String> fileList = new ArrayList<String>();
        for (int i = 1; i < 4; ++i) {
            fileList.add("C:/Users/Dell/Desktop/0" + i + ".pdf");
        }
        return fileList;
    }

    public static void morePdfTopdf(List<String> fileList, String savepath) {
        Document document = null;
        try {
            document = new Document(new PdfReader(fileList.get(0)).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(savepath));
            document.open();
            for (int i = 0; i < fileList.size(); i++) {
                PdfReader reader = new PdfReader(fileList.get(i));
                int n = reader.getNumberOfPages();// 获得总页码
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);// 从当前Pdf,获取第j页
                    copy.addPage(page);
                }
                System.out.println(i);
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
            System.out.println("finish " + new Date());
        }
    }
}

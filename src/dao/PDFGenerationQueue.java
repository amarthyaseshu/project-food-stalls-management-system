package dao;

import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import utility.ConnectionManager;

public class PDFGenerationQueue {

	public static void generateQueue(int stall_id) {

		try {
			String file_name = "F:\\FACE PREP\\proshow-2\\PDF_Generation\\queue1.pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file_name));
			document.open();

			PreparedStatement ps = null;
			ResultSet rs = null;

			String sql = "select * from orders where stall_id=?";

			ps = ConnectionManager.getConnection().prepareStatement(sql);

			ps.setInt(1, stall_id);

			rs = ps.executeQuery();
			Paragraph para0 = new Paragraph(
					"Order ID" + "  " + "Employee ID" + "  " + "Stall ID" + "  " + "Item ID" + "  " + "Order Status");
			document.add(para0);
			while (rs.next()) {
				Paragraph para = new Paragraph(
						rs.getInt("id") + "               " + rs.getInt("employee_id") + "                "
								+ rs.getInt("stall_id") + "          "
								+ rs.getInt("item_id")
								+ "           "
								+ rs.getString("order_status"));
				document.add(para);
				document.add(new Paragraph("  "));
			}
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.ymkj.cms.web.boss.controller.channel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class FillFormSpecialChars2 {
	public static final String SRC = "resources/pdfs/form.pdf";
    public static final String DEST = "results/acroforms/form_special_chars.pdf";
    public static final String FONT = "resources/fonts/FreeSans.ttf";
 
    public static void main(String[] args) throws DocumentException, IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new FillFormSpecialChars2().manipulatePdf("E://bhxt2_apply.pdf", "D://ZDCF02001.pdf");
    }
 
    public void manipulatePdf(String src, String dest) throws DocumentException, IOException {
        PdfReader reader = new PdfReader(src);
    	PdfStamper stamper = new PdfStamper(reader, new ByteArrayOutputStream());
        AcroFields fields = stamper.getAcroFields();
        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, false, null, null, false);
        fields.setFieldProperty("Name", "textfont", bf, null);
        fields.setField("Name", "\u04e711111");
        stamper.setFormFlattening(true);
        stamper.close();
    }
}
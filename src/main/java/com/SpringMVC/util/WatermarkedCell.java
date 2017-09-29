package com.SpringMVC.util;

import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;

public class WatermarkedCell implements PdfPCellEvent {
    String watermark;
    public WatermarkedCell(String watermark) {
        this.watermark = watermark;
    }
    public void cellLayout(PdfPCell cell, Rectangle position,
        PdfContentByte[] canvases) {    	
        PdfContentByte canvas = canvases[PdfPTable.TEXTCANVAS];
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
            new Phrase(watermark, FontFactory.getFont(FontFactory.HELVETICA, 10)),
            (position.getLeft() + position.getRight()) / 2,
            (position.getBottom() + position.getTop()) / 2, 0);
    }
}
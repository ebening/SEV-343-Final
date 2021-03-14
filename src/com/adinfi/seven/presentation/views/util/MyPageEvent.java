package com.adinfi.seven.presentation.views.util;

 


import java.awt.Color;
import java.io.FileOutputStream;


 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
 

public class MyPageEvent extends PdfPageEventHelper  {
	  protected Phrase header = new Phrase("header");
	 
	  protected ResourceBundleManager bundleManager = new ResourceBundleManager();
	  protected String leyenda=null;
	  protected String dir;
	  protected int numHojasFolleto;

	  protected PdfPTable footer = new PdfPTable(4);

	  public MyPageEvent( String dir , int numHojasFolleto  ) {
	    footer.setTotalWidth(300);
	    this.dir=dir;
	    this.numHojasFolleto=numHojasFolleto;
	    /*
	    footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	    footer.addCell(new Phrase(new Chunk("First").setAction(new PdfAction(PdfAction.FIRSTPAGE))));
	    footer.addCell(new Phrase(new Chunk("Prev").setAction(new PdfAction(PdfAction.PREVPAGE))));
	    footer.addCell(new Phrase(new Chunk("Next").setAction(new PdfAction(PdfAction.NEXTPAGE))));
	    footer.addCell(new Phrase(new Chunk("Last").setAction(new PdfAction(PdfAction.LASTPAGE))));
	    pdfDocInfo.setNumPages(0);
	    leyenda = bundleManager.getValue("LEYENDA_PDF"); */
	     
	  }

	   
	  public void onStartPage(PdfWriter writer, Document document){
		  
		  try{
	      	  
		 // drawWaterMark(writer,document);
			  if( this.numHojasFolleto>=1 ){
			    drawHeader(writer,document);
			  }
		  
		  }catch(Exception e){
			  e.printStackTrace();
		  } 
	  }
	 
	  
	  public void drawHeader(PdfWriter writer, Document document)
		        throws DocumentException {
		  
		  PdfContentByte cb = writer.getDirectContent();
		 		 		  
		  cb.saveState();
	      PdfGState state = new PdfGState();
	      
	      
	        cb.setGState(state);
	        /*
	        cb.setRGBColorFill(0xFF, 0xFF, 0xFF);
	        cb.setLineWidth(0.5f);
	     
	        cb.rectangle(30, 745+10 , 535 , 78);
	        
	        cb.moveTo(390, 745+26);
	        cb.lineTo(390+175, 745 + 26 );
	        
	        cb.moveTo(468-27  , 745+10);
	        cb.lineTo(468-27 , 745 + 32 +10);
	        
	       // cb.rectangle(415, 745+10 , 150 , 32);
	        cb.rectangle(390, 745+10 , 175 , 32);
	        cb.fillStroke();
	        */
	        cb.restoreState(); 
	       // String file=dir + "/resources/images/ENCABEZADO1.jpg";
	        String file=dir + "/resources/images/";
	        if( writer.getCurrentPageNumber()>=3 ){
	        	file+="header_3.png";
	        }else{
	        	file+="header_"+ writer.getCurrentPageNumber()+".png";
	        }
	        
	        
	        
	        System.out.println("file header :"+ file );
	        
	        try{
	           //Image image = Image.getInstance( "c:/jmpa/folleto_header.jpg" );
	        	 Image image = Image.getInstance( file );
	           
	           image.setAbsolutePosition(0f, 712f+10);  
	            
	          
	  		                        	                   
	            image.scaleAbsolute(612 , 70);
               cb.addImage(image);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
		  
	        
	      //Clasificacion
		  
      }
		        
		    
 
	  
	  public void onEndPage(PdfWriter writer, Document document) {
		 if( this.numHojasFolleto>=1 ){
		    putFooter(writer,document);
		 }  
			  if( writer.getCurrentPageNumber()==1 ){
				//putPortada(writer,document, pdfDocInfo.getNombreDoc() ); 
			  }
			 
	
		  
		 
	  }
	  
	  
	 

	 
	  public void putFooter(PdfWriter writer, Document document ){
		  
	       // String file=dir + "/resources/images/PIE1.jpg";
		  
	        String file=dir + "/resources/images/";
	        if( writer.getCurrentPageNumber()>=4 ){
	        	file+="footer_4.png";
	        }else{
	        	file+="footer_"+ writer.getCurrentPageNumber()+".png";
	        }		  
		  
	        System.out.println("file header :"+ file );
	        
	        try{
	        	PdfContentByte cb = writer.getDirectContent();
	           //Image image = Image.getInstance( "c:/jmpa/folleto_header.jpg" );
	        	 Image image = Image.getInstance( file );
	           
	           image.setAbsolutePosition(0f, 1f);  
	            
	          
	  		                        	                   
	            image.scaleAbsolute(612 , 80);
             cb.addImage(image);
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	        
		  
	  }
	  
	 
	  
	  public void drawWaterMark(PdfWriter writer , Document document){
		  try{		  
			 
		    Image image = Image.getInstance( "c:/jmpa/logo_3.png" );
            image.setAbsolutePosition(135f, 390f);  
            
            PdfContentByte cb = writer.getDirectContentUnder();
  		                        	                   
            image.scaleAbsolute(400 , 200);
            document.add(image);          
		  }catch(Exception e){
			  e.printStackTrace();
		  }
	  }
	  
	  
	 
	  
	 
     

	  
	}
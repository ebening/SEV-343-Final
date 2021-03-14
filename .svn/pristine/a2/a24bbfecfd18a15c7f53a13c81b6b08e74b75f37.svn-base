package com.adinfi.seven.presentation.views.util;

 

 
import com.adinfi.catalogs.bos.AttrSearch;
import com.adinfi.defines.GlobalDefines;
import com.adinfi.seven.business.domain.CatRegValues;
import com.adinfi.seven.business.domain.CatRegs;
import com.adinfi.seven.business.domain.TblArticulosHoja;
import com.adinfi.seven.business.domain.TblFolleto;
import com.adinfi.seven.business.domain.TblFolletoHojas;
import com.adinfi.seven.business.domain.TblTemplate;
import com.adinfi.seven.business.domain.TblTemplateSegments;
import com.adinfi.seven.business.services.ServiceDynamicCatalogs;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfTable;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdfDocument {
	
	ServiceDynamicCatalogs serviceDynamicCatalogs;

    /**
     * A font that is used in the calendar
     */
    protected Font normal;
    /**
     * A font that is used in the calendar
     */
    protected Font small;
    /**
     * A font that is used in the calendar
     */
    protected Font bold;

    private static final Integer MARGIN_CELL_DESCRIPTION = 100;
    private static final Float CELL_FIXED_HEIGHT = 20f;
    private static final Integer LEFT_RIGHT_CELL_MARGIN = 15;
    private static final Logger LOG = Logger.getLogger(PdfDocument.class.getName());
    private static final Rectangle PAGE_SIZE = PageSize.LETTER;
    private String dirImages=null;
    
    /**
     * Initializes the fonts and collections.
     *
     * @throws DocumentException
     * @throws IOException
     */
    public PdfDocument(String dirImages , ServiceDynamicCatalogs serviceDynamicCatalogs  ) throws DocumentException, IOException {
       this.dirImages=dirImages;
       this.serviceDynamicCatalogs=serviceDynamicCatalogs;
    }

    
 
	
	//protected static Map<Integer , TblFolletoHojas  > getMapSegmentsMain(  )
    //num hoja -- mapa --segment_parent_id ->hoja
	protected  Map<Integer, Map<Integer , TblFolletoHojas>   >  processInfoHojasFolleto(List<TblFolletoHojas> hojasFolleto, Integer idSistVenta ){
		//HashMap<Integer,List<TblFolletoHojas>> copias = new HashMap<Integer,List<TblFolletoHojas>>(); 
		 Map<Integer, Map<Integer , TblFolletoHojas>   >  mapHojas=new TreeMap<>();
		//Tree< num_hoja , Tree< sistema_venta , Tree< id_hoja  
		Integer sistemaVenta=null;
		
		//Map<Integer, Map<Integer , TblFolletoHojas>  > mapHojasxSistVenta=null;
		Map<Integer , TblFolletoHojas> mapHojasxSegPar=null;
		Integer idHoja;
		Integer idSegPar; 
		
		for(TblFolletoHojas infoHoja :hojasFolleto){
			Integer numHoja = Integer.valueOf(infoHoja.getNumHoja());
			sistemaVenta=Integer.valueOf(infoHoja.getIdSistemaVenta());
			if(sistemaVenta==null || sistemaVenta<=0 || sistemaVenta.equals(idSistVenta)==false ){
				continue;
			}
			
			
			sistemaVenta=infoHoja.getIdSistemaVenta();
			mapHojasxSegPar= mapHojas.get(numHoja);
			if( mapHojasxSegPar==null ){
				mapHojasxSegPar=new TreeMap<>();
				mapHojas.put(numHoja, mapHojasxSegPar);
			}
			
			
			idHoja=    infoHoja.getIdHoja();
			idSegPar = infoHoja.getHojaParentSegId();
			if(idSegPar==null){
				idSegPar=0;
			}
			mapHojasxSegPar.put(idSegPar, infoHoja);
			
			 
		}
		
		return mapHojas;
	}
	
	
	
	protected  Map<Integer , List<TblFolletoHojas>   >  getInfoHojasFolleto(List<TblFolletoHojas> hojasFolleto, Integer idSistVenta ){
		//HashMap<Integer,List<TblFolletoHojas>> copias = new HashMap<Integer,List<TblFolletoHojas>>(); 
		 Map<Integer , List<TblFolletoHojas>   >   mapHojas=new TreeMap<>();
		//Tree< num_hoja , Tree< sistema_venta , Tree< id_hoja  
		Integer sistemaVenta=null;
		
		//Map<Integer, Map<Integer , TblFolletoHojas>  > mapHojasxSistVenta=null;
		//Map<Integer , TblFolletoHojas> mapHojasxSegPar=null;
		Integer idHoja;
		Integer idHojaPar; 
		
		List<TblFolletoHojas> lstFolletoHojas;
		for(TblFolletoHojas infoHoja :hojasFolleto){
			Integer numHoja = Integer.valueOf(infoHoja.getNumHoja());
			sistemaVenta=Integer.valueOf(infoHoja.getIdSistemaVenta());
			if(sistemaVenta==null || sistemaVenta<=0 || sistemaVenta.equals(idSistVenta)==false ){
				continue;
			}
						
			sistemaVenta=infoHoja.getIdSistemaVenta();
			
			idHojaPar = infoHoja.getIdHojaPadre();
			if(idHojaPar==null || idHojaPar<=0  ){
				continue;
			}			
			 						
			idHoja=    infoHoja.getIdHoja();
			lstFolletoHojas=mapHojas.get(numHoja);
			if(lstFolletoHojas==null){
				lstFolletoHojas=new ArrayList<TblFolletoHojas>();
				mapHojas.put(numHoja, lstFolletoHojas);
			}
			lstFolletoHojas.add(infoHoja);
		  
			
			 
		}
		
		return mapHojas;
	}
	
	
	
	protected Map<String,String> getMapRegArt( String idArt ){
		Map<String,String> result=null;
	    Set<CatRegValues> setReg = null;
	    
	    try{
	    ArrayList<AttrSearch> lstSearchAttrs = new ArrayList<AttrSearch>();
	    AttrSearch attSearch = new AttrSearch();
		attSearch.setAttrName("ID");
						
		List<CatRegs> regs = null;	   
		Map<String,String> mapRegArt;
		CatRegs reg=null;

        attSearch.setValue( idArt );
    	lstSearchAttrs.add(attSearch);   
    	
    	regs = serviceDynamicCatalogs.getRegs("CAT_ARTICULO", lstSearchAttrs);  
    			
    	if(regs==null || regs.size()<=0){
    		return null;
    	}
    	reg=regs.get(0);
        result=reg.getValuesAsMap();
        
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return result;
		
	}
	
    
    
    
    public void createPdfDocument(HttpServletRequest request,
    	      HttpServletResponse response,TblFolleto folleto , int idSistemaVenta , List<TblFolletoHojas> hojasFolleto  ) throws IOException,
            DocumentException {

        if (folleto == null) {
            return ;
        }

        int numHojas=  folleto.getHojas();
        System.out.println("Numero de hojas :"+  numHojas);
         
        
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //PdfWriter.getInstance(document, baos);        
        
                
        Rectangle pagesize = new Rectangle(PAGE_SIZE);
        Document document = new Document(pagesize, 15f, 15f, 70f, 15f);
        
        PdfWriter writer = PdfWriter.getInstance(document, /*new FileOutputStream(fileName)*/  baos );
        String path= request.getRealPath("/");
        System.out.println("path :"+ path );
        
        MyPageEvent pageEvent= new MyPageEvent(path,numHojas);

        writer.setPageEvent(pageEvent);
        
        crearHeaderFooter(document, writer);
        document.open();
        PdfPTable table;
        Boolean first = false;
        
        
        String documentName = "";// docBO.getDocumentName();
        PdfPTable tableChild = getDocumentTitle(documentName);
        if (tableChild != null) {
          //  document.add(tableChild);
          //  document.newPage();
            first = true;
        }

        
        
        
        int numeroTotalHojas = folleto.getHojas();
      //  Map<Integer, Map<Integer , TblFolletoHojas>   >  hojas =processInfoHojasFolleto(hojasFolleto,  idSistemaVenta  );
        Map<Integer, List<  TblFolletoHojas>   >   hojas= getInfoHojasFolleto(hojasFolleto,  idSistemaVenta );
        
        
        
        Set set= hojas.keySet();
        Iterator it = set.iterator();
       // Map<Integer , TblFolletoHojas> mapHojasxSegPar;
        Integer numHoja = null;
        TblFolletoHojas hoja;
        
        int i = 0;
        List<TblFolletoHojas> lstSpacesxHoja=null;
        int numSpaces=0;
        while (it.hasNext()) {
        	
        	
            
            numHoja = (Integer) it.next();
            lstSpacesxHoja = hojas.get(numHoja);
            if (lstSpacesxHoja == null) {
                continue;
            }
            numSpaces=lstSpacesxHoja.size();
            table = new PdfPTable(1);
            table.setSkipFirstHeader(true);
            table.setSkipLastFooter(true);
            //table.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table.getDefaultCell().setFixedHeight(652/numSpaces);
            
            /*
            Phrase p = new Phrase(   "titulo"  );
            PdfPCell cell=new PdfPCell(p);
            //cell.setBorder( Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP );
             cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);
            */
             
            
            table.setWidthPercentage(100f);
            
            //TODO nuevo para errores
            table.setSplitLate(false);
            table.setSplitRows(false);
            table.setExtendLastRow(false);

            
            if (first == false && i != numeroTotalHojas) {
                document.newPage();
            }
            first = false;

            //TblFolletoHojas hojaParent= mapHojasxSegPar.get(0);
            //TblTemplate template=hojaParent.getTemplate();      
            
            for(TblFolletoHojas folletoHoja:    lstSpacesxHoja  ){
            	
            	TblTemplate template=folletoHoja.getTemplate(); 
            	if(template==null){
            		continue;
            	}
	            TblTemplateSegments rootSegment=template.getMapSegmentsById().get(template.getRootSegmentId()) ;
	            if(rootSegment==null){
	            	continue;
	            }
	            addLayout(table,   folletoHoja , rootSegment , null );	   
            
            }
            document.add(table);
            
            
            i++;
        }
        document.close();
        
        
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");

        response.setContentType("application/pdf");

        response.setContentLength(baos.size());

        ServletOutputStream out = response.getOutputStream();
        baos.writeTo(out);
        out.flush();
        
        
         
    }

    protected void crearHeaderFooter(Document document, PdfWriter writer) {
        try {
            //writer.setBoxSize("art", new Rectangle(5, 5, 945, 55));
            HeaderFooter event = new HeaderFooter();
            writer.setPageEvent(event);
        } catch (Exception ex) {
            LOG.log(Level.ALL, ex.getMessage());
        }
    }

  
    protected void displayArticuloPDF(PdfPTable tableParent, TblTemplate template ,  TblTemplateSegments section,  TblArticulosHoja articulo ) {
        PdfPTable tableChild = new PdfPTable(1);
       // tableChild.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
        Map<String,String> mapRegArt;
        TblTemplateSegments segParent;
        //TODO nuevo para errores
        tableChild.setSplitLate(false);
        tableChild.setSplitRows(false);
        tableChild.setExtendLastRow(false);
       // tableParent.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
        tableParent.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        try {
            tableChild.setWidths(new float[]{Float.parseFloat(String.valueOf(section.getWidth()))});
           // String fileName = objectInfoBO.getFile();

            // Add titulo
            Font fTit = UtileriasPDF.fontTitulo;
            fTit.setColor(UtileriasPDF.fgColorTitulo);
            /*
            Phrase p = new Phrase(   "titulo"  , fTit);

            PdfPCell cellTitulo = new PdfPCell(p);
            cellTitulo.setBackgroundColor(UtileriasPDF.bgColorTitulo);
            cellTitulo.setBorder(Rectangle.NO_BORDER);
            cellTitulo.setVerticalAlignment(Element.ALIGN_TOP);
            cellTitulo.setFixedHeight(CELL_FIXED_HEIGHT);
            tableChild.addCell(cellTitulo);   */

            // Add subtitulo 
            Font fSub = UtileriasPDF.fontSubtitulo;
            fSub.setColor(UtileriasPDF.fgColorSubtitulo);
           // Phrase p1 = new Phrase(objectInfoBO.getSubTitulo() == null || objectInfoBO.getSubTitulo().isEmpty() ? "" : objectInfoBO.getSubTitulo(), fSub);
            /*
            PdfPCell cellSubtitulo = new PdfPCell(p1);
            cellSubtitulo.setBackgroundColor(UtileriasPDF.bgColorSubtitulo);
            cellSubtitulo.setBorder(Rectangle.NO_BORDER);
            cellSubtitulo.setFixedHeight(CELL_FIXED_HEIGHT);
            tableChild.addCell(cellSubtitulo); */
            String fileName=this.dirImages+ "/" + articulo.getTblImageArticulo().getTblImagenes().getPathFile();
           // fileName="C:\\jmpa\\admaster\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\seven\\resources\\images\\fotos_articulos/140618124000728_72814.jpg";
           // articulo.getTblImageArticulo().
            // Add image            
            mapRegArt=getMapRegArt(articulo.getIdArticulo() );
            String	descrip="";
            String   precioBase="";
            String priceFormat="";
            String abonoFormat="";
			if( mapRegArt!=null ){
				descrip=mapRegArt.get("DESCRIP");
				precioBase=mapRegArt.get("COST");
				if( precioBase!=null ){
					
					try{
						NumberFormat formatter = NumberFormat.getCurrencyInstance();
						formatter.setMinimumFractionDigits(0);
						formatter.setMaximumFractionDigits(0);
						//formatter.set
						priceFormat = formatter.format(Double.valueOf(precioBase));
						abonoFormat = formatter.format(Double.valueOf(precioBase)/100);
						
					}catch(Exception e){}
					
					
				}
				
				
				
			}
		 
		  /*
			if (art == null)
				continue; */
            
            
            
            Image image1 = Image.getInstance(fileName);
            image1.setTransparency(new int[]{ 0x00 , 0x10 });
            image1.setScaleToFitLineWhenOverflow(true);
            
            


        
            if (image1 != null) {
                float ancho = image1.getWidth();
                float alto = image1.getHeight();

                float maxWidth = section.getWidth() - LEFT_RIGHT_CELL_MARGIN;
                float maxHeight = section.getHeight() - MARGIN_CELL_DESCRIPTION;
                
                if( maxHeight<=0 ){
                	segParent=template.getMapSegmentsById().get( section.getSegmentParentId() );
                	if( segParent!=null ){
                		maxHeight= segParent.getHeight() - MARGIN_CELL_DESCRIPTION;
                	}
                	 
                	
                }

                float old_x = ancho;
                float old_y = alto;

                float new_w = maxWidth;
                float new_h = maxHeight;

                float ratio1 = old_x / new_w;
                float ratio2 = old_y / new_h;

                float thumb_w = 0, thumb_h = 0;
                if (ratio1 > ratio2) {
                    thumb_w = new_w;
                    thumb_h = old_y / ratio1;
                } else {
                    thumb_h = new_h;
                    thumb_w = old_x / ratio2;
                }
               
                
                if(thumb_h<60){
                	thumb_h=60;                	
                }
                
                if(thumb_w<60){
                   thumb_w=60;	
                }
                
                image1.scaleToFit((float) Math.ceil(thumb_w), (float) Math.ceil(thumb_h));
               // image1.set
               // image1.setTransparency(new int[]{ 0x00 , 0x10 });
                image1.setTransparency(new int[]{ 255 , 255 });
                PdfPCell cellImage = new PdfPCell(image1);
                cellImage.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cellImage.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellImage.setBackgroundColor(UtileriasPDF.bgColorImagen);
                cellImage.setBorder(Rectangle.NO_BORDER);

                //cellImage.setFixedHeight(section.getHeight() - MARGIN_CELL_DESCRIPTION);
                
                //cellImage.setFixedHeight(maxHeight);

                tableChild.addCell(cellImage);
            }

            // Add comments
            Font fCom = UtileriasPDF.fontDescripcion;
            fCom.setColor(UtileriasPDF.fgColorDescripcion);
            //Font fDesc=FontFactory.getFont(FontFactory.HELVETICA , 10, Font.NORMAL , BaseColor.BLACK );
            //Phrase p2 = new Phrase( new Chunk( descrip , fDesc  ) );
            
            Font fDesc=FontFactory.getFont(FontFactory.HELVETICA , 6.7f , Font.NORMAL , BaseColor.BLACK );            
            Phrase p2 = new Phrase( new Chunk(  descrip   , fDesc ) );


            PdfPCell cellDescripcion = new PdfPCell(p2);
            
         //   cellDescripcion.setBackgroundColor(UtileriasPDF.bgColorDescripcion);
            cellDescripcion.setBorder(Rectangle.NO_BORDER);
            cellDescripcion.setNoWrap(false);
            //cellDescripcion.setFixedHeight(CELL_FIXED_HEIGHT);
            cellDescripcion.setVerticalAlignment(Element.ALIGN_TOP);
            cellDescripcion.setHorizontalAlignment(Element.ALIGN_LEFT);
            tableChild.addCell(cellDescripcion); 
            tableParent.addCell(tableChild);
            
            PdfPTable tablePrice = new PdfPTable(4);
            tablePrice.setWidths(new float[]{1,4,4,1} );
            //tablePrice.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
           // tablePrice.setWidths(new float[]{50,50});
            tablePrice.setHorizontalAlignment(PdfTable.ALIGN_CENTER);
           
            tablePrice.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            //tablePrice.getDefaultCell().setBorder(10);
            //tablePrice.getDefaultCell().setPadding(0);
            
            tablePrice.getDefaultCell().setFixedHeight(10);            
            tablePrice.addCell(" ");            
             
                        
            Font fPrice=FontFactory.getFont(FontFactory.HELVETICA , 14, Font.BOLD , BaseColor.BLACK );
            Phrase p1 = new Phrase( new Chunk(  priceFormat  , fPrice ) );
            
          //  p1.setFont(f);
            PdfPCell cellSubtitulo = new PdfPCell(p1);
            
             
         //   cellSubtitulo.setBackgroundColor(UtileriasPDF.bgColorSubtitulo);
            cellSubtitulo.setBorder(Rectangle.NO_BORDER); 
            cellSubtitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cellSubtitulo.setFixedHeight(CELL_FIXED_HEIGHT);
           // tableChild.addCell(cellSubtitulo);  
            tablePrice.addCell(cellSubtitulo);
            
            
            p1 = new Phrase( new Chunk(  abonoFormat   , fPrice ) );
            cellSubtitulo = new PdfPCell(p1);
            cellSubtitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellSubtitulo.setBorder(Rectangle.NO_BORDER); 
            tablePrice.addCell(cellSubtitulo);
            
            tablePrice.addCell(" ");
            tablePrice.addCell(" ");
            
            Font fLey=FontFactory.getFont(FontFactory.HELVETICA , 6.7f , Font.NORMAL , BaseColor.BLACK );            
            p1 = new Phrase( new Chunk(  "Contado"   , fLey ) );
            cellSubtitulo = new PdfPCell(p1);
            cellSubtitulo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellSubtitulo.setBorder(Rectangle.NO_BORDER); 
            tablePrice.addCell(cellSubtitulo);            
            
            p1 = new Phrase( new Chunk(  "Semanales"   , fLey ) );
            cellSubtitulo = new PdfPCell(p1);
            cellSubtitulo.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellSubtitulo.setBorder(Rectangle.NO_BORDER); 
            tablePrice.addCell(cellSubtitulo);            
            tablePrice.addCell(" ");
             
            PdfPCell cell=new PdfPCell(tablePrice);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setFixedHeight(50f);
            //cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
             
            tableParent.addCell(cell);
            
           

        } catch (Exception ex) {
            //Utilerias.logger(getClass()).error(ex);
        	ex.printStackTrace();
        }
    }

    
    protected void displayObjects(PdfPTable tableParent, TblArticulosHoja articulo) {
        if (articulo == null) {
            return;
        }
        
        
        PdfPCell cell = null;

        try {
            

        } catch (Exception ex) {
            LOG.log(Level.ALL, ex.getMessage());
        }
    }
    
    protected PdfPTable getDocumentTitle(String documentName) {
        PdfPTable tableChild = new PdfPTable(1);
        try {
            tableChild.setWidthPercentage(100f);
            tableChild.setSkipFirstHeader(true);
            tableChild.setSkipLastFooter(true);
            tableChild.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            tableChild.setSplitLate(false);
            tableChild.setSplitRows(false);
            tableChild.setExtendLastRow(false);

            // Add titulo
            Font fTit = UtileriasPDF.fontTituloPDF;
            fTit.setColor(UtileriasPDF.fgColorTituloPDF);
            Phrase p = new Phrase(UtileriasPDF.TITULO_DOCUMENTO, fTit);
            PdfPCell cellTitulo = new PdfPCell(p);
            cellTitulo.setBackgroundColor(UtileriasPDF.bgColorTitulo);
            cellTitulo.setBorder(Rectangle.NO_BORDER);
            cellTitulo.setVerticalAlignment(Element.ALIGN_TOP);
            cellTitulo.setColspan(1);
            cellTitulo.setPaddingTop(5);
            cellTitulo.setPaddingBottom(5);
            tableChild.addCell(cellTitulo);

            // Add tema 
            Font fSub = UtileriasPDF.fontTemaPDF;
            fSub.setColor(BaseColor.BLACK);
            Phrase p1 = new Phrase(String.format(UtileriasPDF.TEMA_PDF, documentName), fSub);
            PdfPCell cellSubtitulo = new PdfPCell(p1);
            cellSubtitulo.setBackgroundColor(UtileriasPDF.bgColorSubtitulo);
            cellSubtitulo.setBorder(Rectangle.NO_BORDER);
            cellSubtitulo.setPaddingTop(5);
            cellSubtitulo.setPaddingBottom(5);
            tableChild.addCell(cellSubtitulo);
            tableChild.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            tableChild.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
        } catch (Exception ex) {
            tableChild = null;
           // Utilerias.logger(getClass()).error(ex);
        }
        return tableChild;
    }

    private void addLayout(PdfPTable tableParent, TblFolletoHojas hoja ,  TblTemplateSegments section, Map<Integer , TblFolletoHojas> mapHojasxSegPar) {

    	TblTemplate template= hoja.getTemplate();
    	TblFolletoHojas hojaChild;
    	TblTemplate templateChild;
    	TblTemplateSegments sectionChild;
        List<TblTemplateSegments> sectionsChilds = template.getMapSegmentsByParent().get(section.getSegmentId()); 
        int numCols = 1;
        float[] widths = null;
        Map<Integer, TblArticulosHoja> map_articulosBySegment=hoja.getArticulosBySegment();
        TblArticulosHoja articulo;
        /*
        try{
            Image image2 = Image.getInstance("C:\\jmpa\\admaster\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\seven\\resources\\images\\fotos_articulos\\140618124000728_72814.jpg");
            tableParent.addCell(image2);
            }catch(Exception e){ e.printStackTrace(); } */
       // tableParent.addCell(""+section.getSegmentId()+ " " +section.getTipo() );
        if (section.getTipo()=='N') {
        	
        	 
        	 
        	
        	/*
            if (parentSection.getChildSectionsAsList() == null || parentSection.getChildSectionsAsList().isEmpty()) {
                SectionInfoBO secInfo = new SectionInfoBO();
                secInfo.setSectionName(section.getSectionName());
                displayObjects(tableParent, section);
            } else { */
                //Iteración para hijos de parent.
        	
        	    articulo=map_articulosBySegment.get(section.getSegmentId());
        	    if( articulo==null ){
        	    	//No tiene articulo verificamos si tiene template asociado
        	    	if( mapHojasxSegPar!=null ){
	        	    	hojaChild=mapHojasxSegPar.get(section.getSegmentId());
	        	    	if(hojaChild!=null){
	        	    		templateChild=hojaChild.getTemplate();
	        	    		if( templateChild!=null ){
	        	    		   sectionChild=templateChild.getRootSection();
	        	    		 //  tableParent.addCell("agregando template child ");
	        	    		    addLayout(tableParent, hojaChild  , sectionChild, mapHojasxSegPar);
	        	    		
	        	    		}else{
	        	    			return;
	        	    		}
	        	    	}else{
	        	    		return;
	        	    	}
        	    	}
        	    	
        	    	
        	    }else{
        	    	//tiene aritculo
                    PdfPTable child = new PdfPTable(1);      
                    //child.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
                    child.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                     
                  //  child.addCell("kflksdklfsdfs");
                    //displayArticuloPDF(PdfPTable tableParent, TblTemplateSegments section,  TblArticulosHoja articulo ) 
                    displayArticuloPDF( child, template , section ,  articulo );
                    tableParent.addCell(child);
                  //  tableParent.addCell("nombre del articulo ....");
                   
                    
                    
                 

        	    	
        	    }
           // }
            return;
        }
        
           if( GlobalDefines.SEC_TYPE_ROW.equals(  Character.toString(section.getTipo())    )   ){
                widths = new float[1];
                widths[0] = section.getWidth();
                
           }else  if ( GlobalDefines.SEC_TYPE_COL.equals(Character.toString( section.getTipo()) )  ){
                if (sectionsChilds != null && sectionsChilds.size() > 0) {
                    widths = new float[sectionsChilds.size()];
                    int i = 0;
                    for (TblTemplateSegments secTmp : sectionsChilds) {
                        if (secTmp == null) {
                            continue;
                        }
                        widths[i++] = secTmp.getWidth();
                    }
                }
	            if (sectionsChilds != null && sectionsChilds.size() > 0) {
	                numCols = sectionsChilds.size();
	            }    
        
           }
        
        PdfPTable content = null;
        PdfPTable child = null;
        if (sectionsChilds != null) {
            content = new PdfPTable(numCols);
           // content.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
            content.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            try {
                content.setWidths(widths);

            } catch (DocumentException ex) {
                LOG.log(Level.ALL, ex.getMessage());
            }

            for (TblTemplateSegments secTmp : sectionsChilds) {
                if (secTmp == null) {
                    continue;
                }
                child = new PdfPTable(1);
               // child.getDefaultCell().setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
                child.getDefaultCell().setBorder(Rectangle.NO_BORDER);
               // child.addCell("otra pruebaaaa" + secTmp.getSegmentId() );
               // child.addCell("xxxxxxxxxxxxxx");
                   
                addLayout(child, hoja ,  secTmp, mapHojasxSegPar);
                
                PdfPCell cell = new PdfPCell(child);
                //cell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
                cell.setBorder(Rectangle.NO_BORDER);
               
                if ( GlobalDefines.SEC_TYPE_ROW.equals(Character.toString( section.getTipo()) )  ) {                    
                      //  cell.setFixedHeight(secTmp.getHeight());
                      //  break;
                }else if(  GlobalDefines.SEC_TYPE_COL.equals(Character.toString( section.getTipo())  )  ){
                	  //  cell.setFixedHeight(section.getHeight()  );
                      //  break;
                }
                content.addCell(cell);
                
            }
            //content.addCell("preubaaaaaaa  ");
            PdfPCell cellPar = new PdfPCell(content   );
            cellPar.setFixedHeight(section.getHeight());
           // cellPar.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
            cellPar.setBorder(Rectangle.NO_BORDER);
           // tableParent.addCell("sdfdsfsdfdsfsfs  wqeqweq");
            tableParent.addCell(cellPar);
        }
    }

   
    static class HeaderFooter extends PdfPageEventHelper {

        @Override
        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable tableHeader = new PdfPTable(1);
//            PdfPTable tableFooter = new PdfPTable(1);
            try {
                tableHeader.setWidths(new int[]{5/*, 24, 2*/});
                tableHeader.setTotalWidth(612);
                tableHeader.setLockedWidth(true);
                tableHeader.getDefaultCell().setFixedHeight(20);
                tableHeader.getDefaultCell().setBorder(Rectangle.BOTTOM);

                //Header
                //PdfPCell cellHeader = new PdfPCell(Image.getInstance("header_letter.png"));
                PdfPCell cellHeader = new PdfPCell(Image.getInstance("vector-analisis.png"));
                cellHeader.setBorder(Rectangle.NO_BORDER);
                cellHeader.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellHeader.setPaddingRight(20f);
                tableHeader.addCell(cellHeader);

//                tableFooter.setWidths(new int[]{5/*, 24, 2*/});
//                tableFooter.setTotalWidth(612);
//                tableFooter.setLockedWidth(true);
//                tableFooter.getDefaultCell().setBorder(Rectangle.BOTTOM);

                //Footer
                PdfPCell cellFooter = new PdfPCell(Image.getInstance("footer_letter.png"));
                cellFooter.setBorder(Rectangle.NO_BORDER);
                
//                tableFooter.addCell(cellFooter);

                tableHeader.writeSelectedRows(0, -1, 0, /*792*/ 1000, writer.getDirectContent());
//                tableFooter.writeSelectedRows(0, -1, 0, 60, writer.getDirectContent());
            } catch (DocumentException | IOException ex) {
                LOG.log(Level.ALL, ex.getMessage());
            }
        }
    }
}

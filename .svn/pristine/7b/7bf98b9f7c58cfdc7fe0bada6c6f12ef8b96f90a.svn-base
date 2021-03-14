package com.adinfi.seven.icm;

import com.adinfi.seven.persistence.dto.DiseñoDTO;
import com.ibm.mm.sdk.common.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class pruebaCM{

    public static void main(String argv[]) throws DKException, Exception{
    
        // Defaults for connecting to the database.
        
        DiseñoDTO d = new DiseñoDTO();
        d.setPrograma("Programa2");
        d.setGrupoZona("GpoZona2");
        d.setMecanica("Mecanica2");
        d.setSeñalamiento("Señalamiento2");
        d.setTienda("Tienda2");
        d.setZona("Zona2");
        
        CMExport cm = new CMExport();
        cm.connectToServer();
        
        
        cm.importDocument(d,extractBytes("C:\\config\\pdfs\\test.jpg"));
        cm.disconnect();
        
    }
    
    public static byte[] extractBytes (String ImageName) throws IOException {
    	 // open image
    	 File imgPath = new File(ImageName);
    	 BufferedImage bufferedImage = ImageIO.read(imgPath);

    	 // get DataBufferBytes from Raster
    	 WritableRaster raster = bufferedImage .getRaster();
    	 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

    	 return ( data.getData() );
    }
}
              
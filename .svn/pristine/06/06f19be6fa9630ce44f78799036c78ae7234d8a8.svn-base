package com.adinfi.seven.presentation.views.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

public class UploadFilesBean {
	
	private static final int BUFFER_SIZE = 6124;
	private static final int BUFFER_DIV = 1024;

	public UploadFilesBean() {
		
	}
	
	public String upload(FileUploadEvent event, String ruta){
		String retVal ="";
		try{
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyMMddHHmmssS");
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			String valorUnico = sdf.format(ts.getTime());
			
			String path=ruta + valorUnico + "-";
			
			ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
			File directorio=new File( extContext.getRealPath( ruta ) ); 
			directorio.mkdir(); 
			
			retVal = handleFileUpload(event, path, valorUnico);
		}catch(Exception e){
			Util.logger(getClass()).error(e);
		}
		return retVal;
	}

	private String handleFileUpload(FileUploadEvent event, String path, String valorUnico) {
		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		String filePath = extContext.getRealPath(path + event.getFile().getFileName());
		File result = new File(filePath);
		FileOutputStream fileOutputStream = null;
		String imagen = "";
		try {
			fileOutputStream = new FileOutputStream(result);
			byte[] buffer = new byte[BUFFER_SIZE];

			int bulk;
			InputStream inputStream = event.getFile().getInputstream();
			while (true) {
				bulk = inputStream.read(buffer);
				if (bulk < 0) {
					break;
				}
				fileOutputStream.write(buffer, 0, bulk);
				fileOutputStream.flush();
			}
			fileOutputStream.close();
			inputStream.close();
			Messages.mensajeAlerta("File Descripcion", "file name: " + event.getFile().getFileName()
					+ " file size: " + event.getFile().getSize() / BUFFER_DIV
					+ " Kb content type: "
					+ event.getFile().getContentType()
					+ "The file was uploaded.");	
			imagen = valorUnico + "-" + event.getFile().getFileName();
		} catch (IOException e) {
			Util.logger(getClass()).error(e);
			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"The files were not uploaded!", "");
			FacesContext.getCurrentInstance().addMessage(null, error);
		}finally{
			try {
				if(fileOutputStream != null)
					fileOutputStream.close();
			} catch (IOException e) {
				Util.logger(getClass()).error(e);
			}
			
		}
		return imagen;
	}
}

package br.com.caelum.casadocodigo.loja.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ManagerFile 
{
	private final String basePath = "/home/spring7635/Desktop/";
	
	public String save(MultipartFile file)
	{
		String fullPath = basePath+file.getOriginalFilename();
		
		try {
			if(file != null && !file.isEmpty() && file.getSize() > 0)
				file.transferTo(new File(fullPath));
			else
				fullPath = null;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fullPath = null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fullPath = null;
		}
		return fullPath;
	}
}

package com.cyx.util;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
@Component("FileUploads")  
public class FileUtilImpl implements IFileUtil{
	//1. ͨ���ļ����ȡ��չ��  
	  private String getFileExt(String fileName) {  
	      return FilenameUtils.getExtension(fileName);  
	  }  
	    
	  //2. ���UUID�������Ϊ�µ��ļ���  
	  private String newFileName(String fileName) {  
	      String ext = getFileExt(fileName);  
	      return UUID.randomUUID().toString() + "." + ext;  
	  }  
	    
	  //ʵ���ļ��ϴ��Ĺ��ܣ������ϴ����µ��ļ����  
	@Override
	public String uploadFile(MultipartFile file,String filePath) {
		  System.out.println("正在缓存图片");
	      String pic = newFileName(file.getOriginalFilename());
	      pic=pic.replaceAll(" ","");
	      pic=pic.replaceAll("\"","");
	      filePath=filePath.replaceAll(" ", "");
	      try {  
	           file.transferTo(new File(filePath, pic));
	          return "images/"+pic;  
	      } catch (Exception e) {  
	    	  System.out.println(e);
	          throw new RuntimeException(e);  
	      }  
	}  
}

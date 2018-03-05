package com.cyx.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



public interface IFileUtil {
	//实现文件上传的功能，返回上传后新的文件名称  
	 public abstract String uploadFile(MultipartFile file,String filePath);
}

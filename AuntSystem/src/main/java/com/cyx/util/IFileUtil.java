package com.cyx.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



public interface IFileUtil {
	//ʵ���ļ��ϴ��Ĺ��ܣ������ϴ����µ��ļ�����  
	 public abstract String uploadFile(MultipartFile file,String filePath);
}

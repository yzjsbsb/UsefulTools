package com.yzj.core.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

//上传图片到FASTDFS的工具代码
public class FileUploadUtils {
	public static String fileUpload(MultipartFile pic) throws Exception{
		//加载fdfs_client.conf配置文件
		ClassPathResource classPathResource = new ClassPathResource("fdfs_client.conf");
		//加载文件配置
		ClientGlobal.init(classPathResource.getClassLoader().getResource("fdfs_client.conf").getPath());
		//创建tracker客户端
		TrackerClient trackerClient = new TrackerClient();
		//从而得到tracker服务端
		TrackerServer trackerServer = trackerClient.getConnection();
		
		StorageServer storageServer = null;
		//目的是创建storage客户端，从而上上传文件
		StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
		
		//获得文件扩展名
		String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
		NameValuePair[] meta_list = new NameValuePair[1];
		meta_list[0] = new NameValuePair("ext", ext);//可以不要meta_list
		//上传文件，并返回文件路径
		String path = storageClient1.upload_file1(pic.getBytes(), ext, null );
		
		return path;
	}
}

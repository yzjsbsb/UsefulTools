package com.yzj.back.controller.upload;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUpload;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.yzj.core.common.Constant;
import com.yzj.core.utils.FileUploadUtils;

import net.fckeditor.response.UploadResponse;

@Controller
public class UploadPic {
	
	@RequestMapping("/uploadPic.do")
	public void uploadPic(MultipartFile pic,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//向FASTDFS服务器上传图片并获得图片路径
		String path = FileUploadUtils.fileUpload(pic);
		//allUrl为文件全路径。用于页面回显
		//Constant.IMG_URL为FASTDFS服务器IP地址
		String allUrl = Constant.IMG_URL+path;
		//设置返回值为json，并把json数据发回去。因为我们在ajax请求中设置了返回参数为json
		JSONObject jo = new JSONObject();
		jo.put("allUrl", allUrl);
		jo.put("path", path);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jo.toString());
	}
	
	@RequestMapping("/upload/uploadFck.do")
	public void uploadFck(HttpServletRequest request,HttpServletResponse response) throws Exception{
		//将request强转为MultipartRequest,目的是最终得到MultipartFile
		MultipartRequest mr = (MultipartRequest)request;
		Map<String, MultipartFile> fileMap = mr.getFileMap();
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		System.out.println(entrySet.size());
		for (Entry<String, MultipartFile> entry : entrySet) {
			MultipartFile file = entry.getValue();
			//往FASTDFS上上传文件
			String path = FileUploadUtils.fileUpload(file);
			String Allpath = Constant.IMG_URL+path;
			//返回fck的上传路径，往页面写回全路径已供显示
			UploadResponse ok = UploadResponse.getOK(Allpath);
			response.getWriter().print(ok);
		}
	}
}

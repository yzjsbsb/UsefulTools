package cn.hxx.fuck;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cn.itcast.common.fastdfs.FastDFSUtils;
import cn.itcast.core.web.Constants;

/**
 * 上传图片
 * @author hxx
 *
 */
@Controller
public class UploadController {

	//上传图片，将图片上传到项目中，而不是图片服务器。并在页面进行回显
	@RequestMapping(value = "/upload/uploadPic.do")
	public void uploadPic(@RequestParam MultipartFile pic,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		System.out.println(pic.getOriginalFilename());
		
		//日期格式化
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//当前时间
		//图片的名称
		String name = df.format(new Date());
		
		//三位随机数
		Random r = new Random();
		for (int i = 0; i < 3; i++) {
			name += r.nextInt(10);
		}
		//获取扩展名 apache  common.io.jar中已经提供获取方法
		String ext = FilenameUtils.getExtension(pic.getOriginalFilename());
		//  /upload/ + name ".jpg" 全路径
		//相对路径
		String path = "/upload/" + name + "." + ext;
		//上传路径 ：项目的绝对路径+文件全路径。
		String url = request.getSession().getServletContext().getRealPath("") + path;
		
		//上传图片的方法
		pic.transferTo(new File(url));
		
		//JSON对象
		JSONObject jo = new JSONObject();
		jo.put("path", path);
		
		//1：对响应设置类型 JSON
		response.setContentType("application/json;charset=UTF-8");
		//2:在响应中写入JSON格式的字符串
		response.getWriter().write(jo.toString());
		
		//显示图片Url
		String url = Constants.IMG_URL + path;
		
		//JSON对象
		JSONObject jo = new JSONObject();
		jo.put("url", url);
		
		//1：对响应设置类型 JSON
		response.setContentType("application/json;charset=UTF-8");
		//2:在响应中写入JSON格式的字符串
		response.getWriter().write(jo.toString());
		
		
		
	}
}

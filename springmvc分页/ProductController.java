package com.yzj.back.controller.brandcontroller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.yzj.core.bean.Brand;
import com.yzj.core.bean.BrandQuery;
import com.yzj.core.service.brandservice.BrandService;

import cn.itcast.common.page.Pagination;

@Controller
@RequestMapping("/back_page")
public class ProductController {
	
	@Autowired
	private BrandService brandService;
	
	//跳转到品牌管理界面,按条件(名称、是否可用)查询并进行分页，核心sql:
	//select * from table t1 where name like "%name%" and is_display = #{isDisplay} limit #{startRow},#{pageSize}
	//这些条件都封装在条件查询对象brandQuery中，其核心属性：当前页pageNum，每页记录数pageSize。通过其来查询到我们需要的记录。
	//这里的分页由pagination负责，其所需参数有“当前为第几页”,“每页显示几条记录”,“符合条件的记录总数”, “查询的记录集合”
	//因此我们只需要得到以上四个参数即可。
				brandDao.findBrandCount(brandQuery), brandList
	@RequestMapping("/brand/list.do")
	public String findBrandByBrandQuery(String name,Integer isDisplay,Integer pageNo,Model model
			,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws UnsupportedEncodingException{
		//sb的作用是为拼接参数：当我们点击第几页第几页是，发送的是get请求，不是post请求，因此查询条件是手动拼接的。
		StringBuilder sb = new StringBuilder();
		//创建查询对象
		BrandQuery brandQuery = new BrandQuery();
		//设置查询条件:name
		if(name != null){
			brandQuery.setName(name);
			//为了页面跳转时，查询条件回显才把name加进去
			model.addAttribute("name", name);
			//拼接get参数
			sb.append("name=").append(name);
		}
		
		//设置查询条件：是否可用
		if(isDisplay == null){
			brandQuery.setIsDisplay(1);
			//设置 是否可用 回显
			model.addAttribute("isDisplay", 1);
			//拼接get参数
			sb.append("&isDisplay=").append(1);
		}else{
			brandQuery.setIsDisplay(isDisplay);
			model.addAttribute("isDisplay", isDisplay);
			sb.append("&isDisplay=").append(isDisplay);
		}
		//设置当前页码,当页码为null或小于1时，设为1
		brandQuery.setPageNum(Pagination.cpn(pageNo));
		//进行条件查询
		List<Brand> brandList = brandService.findBrandByBrandQuery(brandQuery);
		//进行分页查询
		Pagination pagination = brandService.findBrandByPage(brandQuery,brandList);
		
		String url = "/back_page/brand/list.do";
		//设置工具条点击后的访问地址，其参数也包括name，isDisplay和pageNum，访问方式为get，并不是表单提交
		//pageView就是设置显示条，url为get的访问路径，而sb.toString()则为我们拼接的请求参数
		pagination.pageView(url , sb.toString());

		System.out.println(pagination.getPageView());
		model.addAttribute("pagination",pagination);
		return "/brand/list";
	}
}

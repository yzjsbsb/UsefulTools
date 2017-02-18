package com.yzj.core.service.brandservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yzj.core.bean.Brand;
import com.yzj.core.bean.BrandQuery;
import com.yzj.core.dao.branddao.BrandDao;

import cn.itcast.common.page.Pagination;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

	@Autowired
	BrandDao brandDao;

	// 查询所有的品牌
	@Override
	public List<Brand> findAllBrand() {
		// TODO Auto-generated method stub
		return brandDao.findAllBrand();
	}

	@Override
	public List<Brand> findBrandByBrandQuery(BrandQuery brandQuery) {
		// TODO Auto-generated method stub
		return brandDao.findBrandByBrandQuery(brandQuery);
	}

	@Override
	public Pagination findBrandByPage(BrandQuery brandQuery,List brandList) {
		Pagination pagination = new Pagination(brandQuery.getPageNum(), brandQuery.getPageSize(), 
				brandDao.findBrandCount(brandQuery), brandList);
		return pagination;
	}

}

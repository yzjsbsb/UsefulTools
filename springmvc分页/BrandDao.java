package com.yzj.core.dao.branddao;

import java.util.List;

import com.yzj.core.bean.Brand;
import com.yzj.core.bean.BrandQuery;

public interface BrandDao {
	
	//查询所有品牌
	public List<Brand> findAllBrand();

	public List<Brand> findBrandByBrandQuery(BrandQuery brandQuery);

	public int findBrandCount(BrandQuery brandQuery);
}

package com.xmg.api.service;

import java.util.List;
import java.util.Map;

import com.xmg.api.vo.SkuGenerateFormVo;


public interface ISkuService {

	/**
	 * 生成sku
	 * @param vo
	 * @return
	 */
	List<Map<String, Object>> generateSku(SkuGenerateFormVo vo);

}

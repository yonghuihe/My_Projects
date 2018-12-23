package com._520it.com.server.impl;

import com._520it.com.base.mapper.BrandMapper;
import com._520it.com.base.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dell on 2017/8/7.
 */
@Service("brandService")
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;
}

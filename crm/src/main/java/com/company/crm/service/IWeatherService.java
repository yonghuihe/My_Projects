package com.company.crm.service;

import javax.jws.WebService;

import com.company.crm.domain.Weather;

@WebService
public interface IWeatherService {
	/**
	 * 根据城市名称查询天气
	 * @param cityName
	 * @return
	 */
	public Weather queryWeatherByCityName(String cityName);
}

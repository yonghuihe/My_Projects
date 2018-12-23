package com.company.crm.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import com.company.crm.domain.Weather;
import com.company.crm.service.IWeatherService;

@WebService
public class WeatherServiceImpl implements IWeatherService {
	private static Map<String,Weather> weatherMap = new HashMap<String,Weather>();
	
	static{
		weatherMap.put("bj", new Weather(1L,"北京","20~30","小雨"));
		weatherMap.put("sh", new Weather(1L,"上海","25~35","多云"));
		weatherMap.put("gz", new Weather(1L,"广州","28~35","晴"));
		weatherMap.put("sz", new Weather(1L,"深圳","30~40","晴"));
	}

	@Override
	public Weather queryWeatherByCityName(String cityName) {
		Weather weather = weatherMap.get(cityName);
		return weather;
	}

}

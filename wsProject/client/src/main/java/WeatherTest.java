import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.webxml.weather.ArrayOfString;
import cn.com.webxml.weather.WeatherWSSoap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-cxf-client.xml")
public class WeatherTest {
	
	/*@Autowired
	private IWeatherService service;
	
	
	@Test
	public void testWeather() {
		Weather weather = service.queryWeatherByCityName("sz");
		System.out.println(weather);
	}*/
	@Autowired
	private WeatherWSSoap wsSoap;
	
	@Test
	public void testSoap() {
		ArrayOfString weather = wsSoap.getWeather("北京", "1a2ac5ebb53e4569b29ac4fe9ceb9726");
		System.out.println(weather.getString());
			
	}

	
}

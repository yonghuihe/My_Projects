package com.company.crm.domain;

public class Weather {
	private Long id;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 温度
	 */
	private String temperature;
	/**
	 * 天气情况
	 */
	private String condition;

	public Weather() {
		super();
	}

	public Weather(Long id, String city, String temperature, String condition) {
		super();
		this.id = id;
		this.city = city;
		this.temperature = temperature;
		this.condition = condition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "Weather [id=" + id + ", city=" + city + ", temperature=" + temperature + ", condition=" + condition
				+ "]";
	}

}

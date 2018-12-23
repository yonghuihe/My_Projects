package com.company.pss.domain;

public enum SaleAccountGroupBy {
	PRODUCT( "sa.product", "sa.product.name","货品名称"),
	BRAND("sa.product.brand", "sa.product.brand.name","品牌"),
	ORDERMAN("sa.saleMan", "sa.saleMan.name","销售人员"),
	CLINET("sa.client", "sa.client.name","客户"),
	YEAR("DATE_FORMAT(sa.vDate,'%Y')", "DATE_FORMAT(sa.vDate,'%Y')","销售日期（年）"),
	MONTH("DATE_FORMAT(sa.vDate,'%Y-%m')", "DATE_FORMAT(sa.vDate,'%Y-%m')","销售日期（月）"),
	DAY("DATE_FORMAT(sa.vDate,'%Y-%m-%d')", "DATE_FORMAT(sa.vDate,'%Y-%m-%d')","销售日期（日）");
	
	//private String key;
	private String groupType;
	private String groupTypeByName;
	private String value;
	
	private SaleAccountGroupBy( String groupType, String groupTypeByName,String value) {
		//this.key = key;
		this.groupType = groupType;
		this.groupTypeByName = groupTypeByName;
		this.value = value;
	}
	
	/*public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}*/
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getGroupTypeByName() {
		return groupTypeByName;
	}
	public void setGroupTypeByName(String groupTypeByName) {
		this.groupTypeByName = groupTypeByName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

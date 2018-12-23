package com.company.pss.domain;

public enum OrderBillGroupBy {
	PRODUCT( "item.product", "item.product.name","货品名称"),
	BRAND("item.product.brand", "item.product.brand.name","品牌"),
	ORDERMAN("item.orderBill.inputUser", "item.orderBill.inputUser.name","订货人员"),
	SUPPLIER("item.orderBill.supplier", "item.orderBill.supplier.name","供应商"),
	YEAR("DATE_FORMAT(item.orderBill.vdate,'%Y')", "DATE_FORMAT(item.orderBill.vdate,'%Y')","订货日期（年）"),
	MONTH("DATE_FORMAT(item.orderBill.vdate,'%Y-%m')", "DATE_FORMAT(item.orderBill.vdate,'%Y-%m')","订货日期（月）"),
	DAY("DATE_FORMAT(item.orderBill.vdate,'%Y-%m-%d')", "DATE_FORMAT(item.orderBill.vdate,'%Y-%m-%d')","订货日期（日）");
	
	//private String key;
	private String groupType;
	private String groupTypeByName;
	private String value;
	
	private OrderBillGroupBy( String groupType, String groupTypeByName,String value) {
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

<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC
	"-//Hibernate/Hibernate Mapping DTD 3.0//EN"
	"http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

<hibernate-mapping package="${basePackage}.domain">

	<class name="${className}" table="${className?lower_case}">
		<id name="id">
			<generator class="native" />
		</id>
		
		<#list propNames as prop>
		<property name="${prop}" />
		</#list>
		
	</class>

</hibernate-mapping>

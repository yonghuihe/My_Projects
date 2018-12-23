<bean id="${className?uncap_first}Action" class="${basePackage}.mvc.${className}Action" scope="prototype">
	<property name="${className?uncap_first}Service" ref="${className?uncap_first}Service" />
</bean>
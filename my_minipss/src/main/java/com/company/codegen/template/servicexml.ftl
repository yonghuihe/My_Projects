<bean id="${className?uncap_first}Service" class="${basePackage}.service.impl.${className}ServiceImpl">
	<property name="${className?uncap_first}Dao" ref="${className?uncap_first}Dao" />
</bean>
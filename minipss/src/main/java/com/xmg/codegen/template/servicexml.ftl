<bean id="${className?uncap_first}Service" class="${basePackage}.service.impl.${className}ServiceImpl">
	<property name="${className?uncap_first}DAO" ref="${className?uncap_first}DAO" />
</bean>
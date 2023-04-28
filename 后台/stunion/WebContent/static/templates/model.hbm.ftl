<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
	<class name="com.model.${Model.lm}" table="${Model.bm}">
		<comment>${Model.bms}</comment>
		<id name="id" type="java.lang.Integer">
			<column name="id" length="11">
				<comment>主键</comment>
			</column>
			<generator class="native"></generator>
		</id>
		<#list items as item>
		<#if item.lmc!='id'>
		<#if item.llx=='String'>
		<property name="${item.lmc}" type="java.lang.String">
			<column name="${item.bmc}"<#if (item.blx?? && item.blx!='' && item.blx!='varchar')> sql-type="${item.blx!''}"</#if><#if (item.cd?? && item.cd!='')> length="${item.cd!''}"</#if><#if (item.mr?? && item.mr!='')> default="${item.mr}"</#if>>
				<comment>${item.bbz}</comment>
			</column>
		</property>
		<#else>
		<property name="${item.lmc}" type="${item.llx}">
			<column name="${item.bmc}"<#if (item.blx?? && item.blx!='' && item.blx!='varchar')> sql-type="${item.blx!''}"</#if><#if (item.cd?? && item.cd!='')> length="${item.cd!''}"</#if><#if (item.mr?? && item.mr!='')> default="${item.mr}"</#if><#if (item.xsd?? && item.xsd!='')> precision="${item.xsd}"</#if>>
				<comment>${item.bbz}</comment>
			</column>
		</property>
		</#if>
		</#if>
		</#list>
	</class>
</hibernate-mapping>

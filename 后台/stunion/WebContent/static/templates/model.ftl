package com.model;

import java.io.Serializable;


// ${Model.lms}
public class ${Model.lm} implements Serializable{

	private static final long serialVersionUID = 1L;
	
	<#list items as item>
	private ${item.llx} ${item.lmc}; //${item.lbz}
	</#list>
	
	<#list items as item>
	<#if item.kjzd?? && item.kjzd!=''>
	private String ${item.lmc}Txt; //${item.lbz}
	</#if>
	</#list>
	private String searchFields = "${search}";
	
	<#list items as item>
	public ${item.llx} get${item.lmc?cap_first}() {
		return ${item.lmc};
	}
	public void set${item.lmc?cap_first}(${item.llx} ${item.lmc}) {
		this.${item.lmc} = ${item.lmc};
	}
	</#list>
	<#list items as item>
	<#if item.kjzd?? && item.kjzd!=''>
	public String get${item.lmc?cap_first}Txt() {
		return ${item.lmc}Txt;
	}
	public void set${item.lmc?cap_first}Txt(String ${item.lmc}Txt) {
		this.${item.lmc}Txt = ${item.lmc}Txt;
	}
	</#if>
	</#list>
	public String getSearchFields() {
		return searchFields;
	}
	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

}
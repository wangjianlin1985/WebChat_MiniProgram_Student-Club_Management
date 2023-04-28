<#noparse><@override name="script"></#noparse>
    <script>
        layui.use(["element", "form", "table", "layer", "laydate", "layedit"], function () {
            var element = layui.element;
            var form = layui.form;
            var table = layui.table;
            var layer = layui.layer;
            var laydate = layui.laydate;
            var layedit = layui.layedit;
            
            //切换查询条件
            form.on('select(change-query)',function (data){
            	$("#query-name").attr("name",data.value);
            });

            //搜索
            form.on('submit(search)', function (data) {
                table.reload("layuiTable", {url: "<#noparse>${rc.contextPath}</#noparse>/admin/${Model.lm?uncap_first}/getTable", method: "post", where: data.field});
                return false;
            });

            table.on('tool(opt)', function (obj) {
                //删除数据
                if (obj.event === 'del') {
                    layer.confirm("是否确认删除?", {icon: 3, title: "确认"}, function (index) {
                        jQuery.ajax({
                            url: "<#noparse>${rc.contextPath}</#noparse>/admin/${Model.lm?uncap_first}/delete",
                            type: "get",
                            dataType: "json",
                            data: {id: obj.data.id},
                            success: function (data, textStatus, jqXHR) {
                                if (data.code != "1") {
                                    layer.msg(data.msg, {icon: 2, time: 2000});
                                    return;
                                }
                                layer.msg("删除成功!", {icon: 1, time: 1000}, function () {
                                    layer.close(index);
                                    table.reload("layuiTable");
                                });
                            },
                            error: function () {
                                layer.msg("请求服务器异常", {icon: 2, time: 2000});
                            }
                        });
                    });
                }

            });
            
            
        });
    </script>
<#noparse>
</@override> 
</#noparse>

<#noparse>
<@override name="breadcrumb">
</#noparse>
    <a><cite>${Model.lms}管理</cite></a>
<#noparse>
</@override> 
</#noparse>

<#noparse>
<@override name="content">
</#noparse>
    <div class="layui-body">
        <div class="layui-content-page">
            <div class="layui-row">
                <div class="layui-col-lg8">
                    <div class="layui-btn-container">
                        <a href="<#noparse>${rc.contextPath}</#noparse>/admin/${Model.lm?uncap_first}/add.html" class="layui-btn layui-btn-normal">
                        	<i class="layui-icon layui-icon-add-1 layui-font-10"></i>添加
                        </a>
                    </div>
                </div>
                
                <div class="layui-col-lg4">
                    <form class="layui-form" action="">
                    	<div class="layui-row layui-search">
                    		<div class="layui-col-lg1">&nbsp;</div>
                        	<div class="layui-col-lg3">
                                <select class="layui-select" lay-filter="change-query">
                                	<#list schs as item>
                                	<option value="${item.lmc}">${item.lbz}</option>
                            		</#list>
                                </select>
                            </div>
                            <div class="layui-col-lg6">
                            	<#list schs as item>
                            	<#if (item_index==0)>
                                <input type="text" name="${item.lmc}" id="query-name" class="layui-input" autocomplete="off"/>
                                </#if>
                            	</#list>
                            </div>
                            <div class="layui-col-lg2">
                                <button lay-submit lay-filter="search" type="button"
                                        class="layui-btn layui-btn-normal layui-btn-fluid">搜索</button>
                            </div>
                    	</div>
                    </form>
                </div>
            </div>
            <div class="gridTable">
                <div class="layui-tables">
                    <table id="layuiTable" class="layui-table"
                           lay-data="{url:'<#noparse>${rc.contextPath}</#noparse>/admin/${Model.lm?uncap_first}/getTable',method:'post',page:true}" lay-filter="opt">
                        <thead>
                        <tr>
                            <th lay-data="{type:'numbers'}"></th>
                            <#list items as item>
                            <#if (item.lbxs?? && item.lbxs=='1')>
                            <th lay-data="{field:'<#if (item.kjzd?? && item.kjzd!='')>${item.lmc}Txt<#else>${item.lmc}</#if>'}">${item.lbz}</th>
                            </#if>
                            </#list>
                            <th lay-data="{field:'opt',width:116,toolbar:'#optbar'}">操作</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
    
	<script type="text/html" id="optbar">
		{{#  if(d.id!=0){ }}
		<a href="<#noparse>${rc.contextPath}</#noparse>/admin/${Model.lm?uncap_first}/edit/{{d.id}}.html" class="layui-btn layui-btn-xs layui-btn-normal">编辑</a>
		<a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		{{#  } }}
	</script>
<#noparse>    
</@override> 
</#noparse>

<#noparse><@extends name="/admin/layout.html"/></#noparse>
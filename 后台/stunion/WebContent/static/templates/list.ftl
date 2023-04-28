<#noparse><@override name="script"></#noparse>
    <script>
        layui.use(["element", "form", "table", "layer", "laydate", "layedit","upload"], function () {
            var element = layui.element;
            var form = layui.form;
            var table = layui.table;
            var layer = layui.layer;
            var laydate = layui.laydate;
            var layedit = layui.layedit;
            var upload = layui.upload;
            
            //切换查询条件
            form.on('select(change-query)',function (data){
            	$("#query-name").attr("name",data.value);
            });

            //搜索
            form.on('submit(search)', function (data) {
                table.reload("layuiTable", {url: "<#noparse>${rc.contextPath}</#noparse>/admin/${Model.lm?uncap_first}/getTable", method: "post", where: data.field});
                return false;
            });
            
            <#list items as item>
            <#if (item.bdxs?? && item.bdxs=='1')>
            <#if (item.kjlx?? && item.kjlx=='date')>
            laydate.render({
            	elem:"#${item.lmc}"
            });
            </#if>
            <#if (item.kjlx?? && item.kjlx=='time')>
            laydate.render({
            	elem:"#${item.lmc}",
            	type: 'time'
            });
            </#if>
            <#if (item.kjlx?? && item.kjlx=='datetime')>
            laydate.render({
            	elem:"#${item.lmc}",
            	type: 'datetime'
            });
            </#if>
            <#if (item.kjlx?? && item.kjlx=='upload')>
            var upload_${item.lmc} = upload.render({
            	elem: '#layui-upload-${item.lmc}',
                url: '<#noparse>${rc.contextPath}</#noparse>/upload/image',
                field:"imageFile",
                done: function(res){
                	<#noparse>$("#</#noparse>${item.lmc}").val(res.data);
                }
            });
            </#if>
            <#if (item.kjlx?? && item.kjlx=='layedit')>
            layedit.set({
	    		uploadImage: {
	    			url: '<#noparse>${rc.contextPath}</#noparse>/upload/layui/image',
	    		}
	    	});
	    	var ${item.lmc}Idx = layedit.build('${item.lmc}',{
	    		tool: ['strong','italic','underline','del' ,'|','left','center','right','link','unlink','image','face']
	    	});
	    	form.verify({
	    	    content: function(value) { 
	   	        	return layedit.sync(${item.lmc}Idx);
	   	        }
	    	});
            </#if>
            </#if>
            </#list>

            //添加按钮
            form.on('submit(add)', function () {
                layer.open({
                    type: 1,
                    title: "新增${Model.lms}",
                    content: $("#layui-dialog"),
                    success: function (index, layero) {
                        $("#dialogform")[0].reset();
                        layui.form.render();
                    },
                    btnAlign: 'c',
                    area: ["720px", "320px"],
                    btn: ["提交保存", "关闭"],
                    yes: function (index, layero) {
                        form.on('submit(dialogform)', function (data) {
                            jQuery.ajax({
                                url: "<#noparse>${rc.contextPath}</#noparse>/admin/${Model.lm?uncap_first}/save",
                                type: "post",
                                dataType: "json",
                                data: data.field,
                                success: function (data, textStatus, jqXHR) {
                                    if (data.code != "1") {
                                        layer.msg(data.msg, {icon: 2, time: 2000});
                                        return;
                                    }
                                    layer.msg("保存成功!", {icon: 1, time: 1000}, function () {
                                        layer.close(index);
                                        table.reload("layuiTable");
                                    });
                                },
                                error: function () {
                                    layer.msg("请求服务器异常", {icon: 2, time: 2000});
                                }
                            });
                            return false;
                        });
                        $('.submit').trigger('click');
                    }
                });
                return false;
            });

            table.on('tool(opt)', function (obj) {
                //编辑数据
                if (obj.event === 'edit') {
                    layer.open({
                        type: 1,
                        area: ["720px", "320px"],
                        title: "编辑${Model.lms}",
                        content: $("#layui-dialog"),
                        success: function (index, layero) {
                            form.val("value", obj.data);
                        },
                        btnAlign: 'c',
                        btn: ["提交保存", "关闭"],
                        yes: function (index, layero) {
                            form.on('submit(dialogform)', function (data) {
                                jQuery.ajax({
                                    url: "<#noparse>${rc.contextPath}</#noparse>/admin/${Model.lm?uncap_first}/update",
                                    type: "post",
                                    dataType: "json",
                                    data: data.field,
                                    success: function (data, textStatus, jqXHR) {
                                        if (data.code != "1") {
                                            layer.msg(data.msg, {icon: 2, time: 2000});
                                            return;
                                        }
                                        layer.msg("保存成功!", {icon: 1, time: 1000}, function () {
                                            layer.close(index);
                                            table.reload("layuiTable");
                                        });
                                    },
                                    error: function () {
                                        layer.msg("请求服务器异常", {icon: 2, time: 2000});
                                    }
                                });
                                return false;
                            });
                            $('.submit').trigger('click');
                        }
                    });

                }
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
                        <button type="button" lay-submit lay-filter="add" class="layui-btn layui-btn-normal">
                        	<i class="layui-icon layui-icon-add-1 layui-font-10"></i>添加
                        </button>
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
                                <button lay-submit lay-filter="search" type="button" class="layui-btn layui-btn-normal layui-btn-fluid">搜索</button>
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
		<a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit">编辑</a>
		<a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		{{#  } }}
	</script>
<#noparse>    
</@override> 
</#noparse>

<#noparse>
<@override name="dialog">
</#noparse>
    <div id="layui-dialog" class="layui-dialog">
        <div class="layui-dialog-body">
            <form id="dialogform" class="layui-form" lay-filter="value">
                <input type="hidden" value="0" id="id" name="id"/>
                <div class="layui-row">
                    <#list items as item>
                    <#if (item.bdxs?? && item.bdxs=='1')>
                    <div class="layui-col-lg${Model.ls}">
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">${item.lbz}</label>
                            <div class="layui-input-block">
                            	<#if (item.kjzd?? && item.kjzd!='')>
                            	<select id="${item.lmc}" name="${item.lmc}" class="layui-input">
                            		<option value="">请选择</option>
                            		<#noparse><#if </#noparse>${item.lmc}s<#noparse>??></#noparse>
                            		<#noparse><#list </#noparse>${item.lmc}<#noparse>s as item></#noparse>
                            		<#noparse><option value="${item.</#noparse>${item.kjz}!''}<#noparse>">${item.</#noparse>${item.kjxs}<#noparse>!''}</option></#noparse>
                            		<#noparse></#list></#noparse>
                            		<#noparse></#if></#noparse>
                            	</select>
                            	<#else>
                            		<#if (item.kjlx?? && item.kjlx=='layedit')>
                                <textarea id="${item.lmc}" name="${item.lmc}" class="textarea" lay-verify="content"></textarea>
                            		<#else>
                                <input type="text" id="${item.lmc}" name="${item.lmc}" autocomplete="off" class="layui-input"<#if (item.sfjy?? && item.sfjy=='1')> lay-verify="${item.jyms}" lay-reqText="${item.jyts}"</#if>/>
                            		</#if>
                            	</#if>
                            </div>
                            <#if (item.kjlx?? && item.kjlx=='upload')>
                            <div class="layui-upload-btn">
                            	<button id="layui-upload-${item.lmc}" type="button"><i class="layui-icon layui-icon-upload"></i></button>
                            </div>
                            </#if>
                        </div>
                    </div>
                    </#if>
                    </#list>
                </div>
                <button class="submit" style="display: none;" lay-submit
                        lay-filter="dialogform">提交保存
                </button>
            </form>
        </div>
    </div>
<#noparse>
</@override> 
</#noparse>

<#noparse><@extends name="/admin/layout.html"/></#noparse>
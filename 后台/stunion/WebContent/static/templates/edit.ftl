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
			
			//表单提交
            form.on('submit(save)', function (data) {
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
                        	//window.history.go(-1);
                        	location.href="<#noparse>${rc.contextPath}</#noparse>/admin/${Model.lm?uncap_first}/table.html";
                      	});
	                  },
	                error: function () {
	                      layer.msg("请求服务器异常", {icon: 2, time: 2000});
	                }
	            });
	            return false;
	        });
        });
    </script>
<#noparse>
</@override> 
</#noparse>

<#noparse>
<@override name="breadcrumb">
</#noparse>
    <a><cite>编辑${Model.lms}</cite></a>
<#noparse>
</@override> 
</#noparse>

<#noparse>
<@override name="content">
</#noparse>
    <div class="layui-body">
        <div class="layui-content-page">
            <form id="dialogform" class="layui-form" lay-filter="value">
                <input type="hidden" value="<#noparse>${model.id?c!''}</#noparse>" id="id" name="id"/>
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
                            		<#noparse><option <#if (model.</#noparse>${item.lmc}<#noparse>??&&model.</#noparse>${item.lmc}<#noparse>==item.</#noparse>${item.kjz}<#noparse>)>selected="selected"</#if> value="${item.</#noparse>${item.kjz}<#noparse>!''}">${item.</#noparse>${item.kjxs}<#noparse>!''}</option></#noparse>
                            		<#noparse></#list></#noparse>
                            		<#noparse></#if></#noparse>
                            	</select>
                            	<#else>
                            		<#if (item.kjlx?? && item.kjlx=='layedit')>
                            	<textarea id="${item.lmc}" name="${item.lmc}" class="textarea" lay-verify="content"><#noparse>${model.</#noparse>${item.lmc}<#noparse>!''}</#noparse></textarea>
                                	<#else>
                                <input type="text" id="${item.lmc}" name="${item.lmc}" value="<#noparse>${model.</#noparse>${item.lmc}<#noparse>!''}</#noparse>" autocomplete="off" class="layui-input"<#if (item.sfjy?? && item.sfjy=='1')> lay-verify="${item.jyms}" lay-reqText="${item.jyts}"</#if>/>
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
                    <div class="layui-col-lg12">
	                    <div class="layui-form-item">
	                        <div class="layui-input-block">
	                            <button class="layui-btn layui-btn-normal" lay-submit lay-filter="save">提交保存 </button>
	                            <button class="layui-btn layui-btn-primary" type="button" onclick="window.history.go(-1);">返回 </button>
	                        </div>
	                    </div>
                    </div>
                </div>
                
            </form>
        </div>
    </div>
<#noparse>    
</@override> 
</#noparse>

<#noparse><@extends name="/admin/layout.html"/></#noparse>
package com.base.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("upload")
public class UploadController extends BaseController{

	/***
	 * 上传图片
	 * @param request
	 * @param response
	 * @param fileid
	 * @throws IOException
	 */
	@RequestMapping("/image")
	public void uploadImg(HttpServletRequest request,HttpServletResponse response,MultipartFile imageFile) throws IOException{
		String day = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //服务器路径
		String realPath = request.getSession().getServletContext().getRealPath("/static/uploads/"+day);  
        String path = this.getClass().getResource("/").getPath();
		//String wokespacepath = path.toString().substring(1, path.toString().indexOf(".metadata")-1);;
        //项目路径
		//String projectpath=request.getSession().getServletContext().getContextPath();
        //projectpath  = wokespacepath+projectpath+"/WebContent/static/uploads/"+day;
        String projectpath  = "C:/EclipseIDE/apache-tomcat-8.0.47/wtpwebapps/stunion/static/uploads/"+day;
        
        
        response.setContentType("text/plain; charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        String originalFilename = null;  
        if(imageFile.isEmpty()){  
            result =  failResult("文件不能为空!");
            outJson(response, result);
            out.flush();   
        }else{  
            originalFilename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf("."));;  
            try {  
                FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(realPath, originalFilename));  
                FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(projectpath, originalFilename));  
            } catch (IOException e) {  
                e.printStackTrace();  
                result =  failResult("文件上传失败，请重试!");
                outJson(response, result);
                out.flush();  
            }  
        }
        result = successResult("/static/uploads/"+day+"/" + originalFilename);
        outJson(response, result);
        out.flush();  
	}
	
	/***
	 * 上传图片
	 * @param request
	 * @param response
	 * @param fileid
	 * @throws IOException
	 */
	@RequestMapping("/layui/image")
	public void layuiUploadImg(HttpServletRequest request,HttpServletResponse response,MultipartFile file) throws IOException{
		String day = new SimpleDateFormat("yyyyMMdd").format(new Date());
        //服务器路径
		String realPath = request.getSession().getServletContext().getRealPath("/static/uploads/"+day);  
        String path = this.getClass().getResource("/").getPath();
		String wokespacepath = path.toString().substring(1, path.toString().indexOf(".metadata")-1);;
        //项目路径
		String projectpath=request.getSession().getServletContext().getContextPath();
        projectpath  = wokespacepath+projectpath+"/WebContent/static/uploads/"+day;
        
        
        response.setContentType("text/plain; charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        String originalFilename = null;  
        if(file.isEmpty()){  
            result =  failResult("文件不能为空!");
            outJson(response, result);
            out.flush();   
        }else{  
            originalFilename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));;  
            try {  
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, originalFilename));  
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(projectpath, originalFilename));  
            } catch (IOException e) {  
                e.printStackTrace();  
                result =  failResult("文件上传失败，请重试!");
                outJson(response, result);
                out.flush();  
            }  
        }
        result = successResult("/static/uploads/"+day+"/" + originalFilename);
        result.setCode("0");
        JSONObject src  = new JSONObject();
        src.put("src", request.getContextPath()+"/static/uploads/"+day+"/" + originalFilename);
        
        result.setData(src);
        outJson(response, result);
        out.flush();  
	}
}

package com.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.util.StringUtil;
import com.base.util.field.FieldUtil;
import com.model.Activity;
import com.model.ActivityApply;
import com.model.ActivityCollect;
import com.model.ActivityComment;
import com.model.Article;
import com.model.Clazz;
import com.model.Dict;
import com.model.Scenery;
import com.model.Student;
import com.model.Study;
import com.model.Stunion;
import com.model.StunionApply;
import com.model.User;

@Controller
@RequestMapping(value = "api")
public class ApiController extends BaseController{

	// 测试
	@RequestMapping(value = "conn")
	public void conn(HttpServletRequest request, HttpServletResponse response) {
		result = successResult(" reqeust is conneted ");
		outJson(response, result);
	}
	
	//新闻top5
	@SuppressWarnings("unchecked")
	@RequestMapping("getArticlesTop5")
	public void getArticlesTop5(HttpServletRequest request, HttpServletResponse response) {
		List<Article> list = (List<Article>) baseService.findList(" from Article order by id desc ", null, 1, 5);
		result = successResult(list);
		outJson(response, result);
	}
	//新闻
	@SuppressWarnings("unchecked")
	@RequestMapping("getArticles")
	public void getArticles(HttpServletRequest request, HttpServletResponse response) {
		List<Article> list = (List<Article>) baseService.findListByHql(" from Article order by id desc ");
		result = successResult(list);
		outJson(response, result);
	}
	//新闻明细
	@RequestMapping("article")
	public void article(HttpServletRequest request, HttpServletResponse response,int id) {
		Article list = (Article) baseService.findObject(" from Article where id = '"+id+"' ");
		result = successResult(list);
		outJson(response, result);
	}
	//学习园地
	@SuppressWarnings("unchecked")
	@RequestMapping("getStudy")
	public void getStudy(HttpServletRequest request, HttpServletResponse response) {
		List<Study> list = (List<Study>) baseService.findListByHql(" from Study order by id desc ");
		result = successResult(list);
		outJson(response, result);
	}
	//学习园地明细
	@RequestMapping("study")
	public void study(HttpServletRequest request, HttpServletResponse response,int id) {
		Study list = (Study) baseService.findObject(" from Study where id = '"+id+"' ");
		result = successResult(list);
		outJson(response, result);
	}
	//社团风采
	@SuppressWarnings("unchecked")
	@RequestMapping("getScenery")
	public void getScenery(HttpServletRequest request, HttpServletResponse response) {
		List<Scenery> list = (List<Scenery>) baseService.findListByHql(" from Scenery order by id desc ");
		for (Scenery scenery : list) {
			Stunion m2 = (Stunion) baseService.findObject(" from Stunion where 1=1 and id = '"+scenery.getStunionId()+"' ");
			if(m2 != null){
				scenery.setStunionIdTxt(m2.getName());
			}
		}
		result = successResult(list);
		outJson(response, result);
	}
	//社团风采明细
	@RequestMapping("scenery")
	public void scenery(HttpServletRequest request, HttpServletResponse response,int id) {
		Scenery model = (Scenery) baseService.findObject(" from Scenery where id = '"+id+"' ");
		Stunion m2 = (Stunion) baseService.findObject(" from Stunion where 1=1 and id = '"+model.getStunionId()+"' ");
		if(m2 != null){
			model.setStunionIdTxt(m2.getName());
		}
		result = successResult(model);
		outJson(response, result);
	}
	//学生排行
	@SuppressWarnings("unchecked")
	@RequestMapping("students")
	public void students(HttpServletRequest request, HttpServletResponse response) {
		List<Student> list = (List<Student>) baseService.findListByHql(" from Student order by credit desc ");
		for (Student student : list) {
			Clazz m4 = (Clazz) baseService.findObject(" from Clazz where 1=1 and id = '"+student.getClazzId()+"' ");
			if(m4 != null){
				student.setClazzIdTxt(m4.getName());
			}
		}
		result = successResult(list);
		outJson(response, result);
	}
	//登录
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "vlogin")
	public void vlogin(HttpServletRequest request, HttpServletResponse response) {
		String sno = request.getParameter("sno");
		String password = request.getParameter("password");
		if (StringUtil.isEmpty(sno) || StringUtil.isEmpty(password)) {
			result = failResult("账号密码不能为空!");
		} else {
			String hql = " from Student where sno = '" + sno + "' and password = '" + password + "' ";
			List<Student> list = (List<Student>) baseService.findListByHql(hql);
			if (list != null && list.size() == 1) {
				Student stu = list.get(0);
				Clazz m4 = (Clazz) baseService.findObject(" from Clazz where 1=1 and id = '"+stu.getClazzId()+"' ");
				if(m4 != null){
					stu.setClazzIdTxt(m4.getName());
				}
				result = successResult(stu);
			} else {
				result = failResult("账号密码不匹配!");
			}
		}
		outJson(response, result);
	}
	
	//注册
	@RequestMapping(value = "vreg")
	public void vreg(HttpServletRequest request, HttpServletResponse response,Student stu) {
		List<?> list = baseService.findListByHql(" from Student where sno = '"+stu.getSno()+"' ");
		if(list!=null && list.size()>0) {
			result = failResult("学号【"+stu.getSno()+"】已注册");
		}else {
			FieldUtil.setCreatedAt(stu);
			boolean flag = baseService.save(stu);
			if(flag){
				result = successResult(stu);
			}else{
				result = failResult("注册失败，系统发生异常...");
			}
		}
		outJson(response, result);
	}
	//获取班级
	@RequestMapping(value = "clazz")
	public void clazz(HttpServletRequest request, HttpServletResponse response) {
		List<?> list = baseService.findListByHql(" from Clazz ");
		result = successResult(list);
		outJson(response, result);
	}
	//个人明细
	@RequestMapping(value = "student/detail")
	public void studentDetail(HttpServletRequest request, HttpServletResponse response, int id) {
		Student member = (Student) baseService.getById(new Student(), id);
		result = successResult(member);
		outJson(response, result);
	}
	//更新
	@RequestMapping(value = "student/update")
	public void studentUpdate(HttpServletRequest request, HttpServletResponse response, Student model) {
		boolean flag = baseService.merge(request,model);
		if (flag) {
			result = successResult(baseService.getById(model, model.getId()));
		} else {
			result = failResult("更新失败，系统发生异常...");
		}
		outJson(response, result);
	}
	//社团
	@SuppressWarnings("unchecked")
	@RequestMapping("stunions")
	public void stunions(HttpServletRequest request, HttpServletResponse response) {
		List<Stunion> list = (List<Stunion>) baseService.findListByHql(" from Stunion ");
		for (Stunion stunion : list) {
			User m4 = (User) baseService.findObject(" from User where 1=1 and id = '"+stunion.getUserId()+"' ");
			if(m4 != null){
				stunion.setUserIdTxt(m4.getName());
			}
		}
		result = successResult(list);
		outJson(response, result);
	}
	//社团明细
	@RequestMapping("stunion/detail")
	public void stunionDetail(HttpServletRequest request, HttpServletResponse response,int id) {
		Stunion model = (Stunion) baseService.findObject(" from Stunion where id = '"+id+"' ");
		User m4 = (User) baseService.findObject(" from User where 1=1 and id = '"+model.getUserId()+"' ");
		if(m4 != null){
			model.setUserIdTxt(m4.getName());
		}
		result = successResult(model);
		outJson(response, result);
	}
	//社团成员
	@SuppressWarnings("unchecked")
	@RequestMapping("stunion/students")
	public void stunionStudents(HttpServletRequest request, HttpServletResponse response,int id) {
		List<Student> list = (List<Student>) baseService.findListByHql(" from Student where id in ( select studentId from StunionApply where status = '1' and  stunionId = '"+id+"') ");
		for (Student student : list) {
			Clazz m4 = (Clazz) baseService.findObject(" from Clazz where 1=1 and id = '"+student.getClazzId()+"' ");
			if(m4 != null){
				student.setClazzIdTxt(m4.getName());
			}
		}
		result = successResult(list);
		outJson(response, result);
	}
	//社团申请
	@RequestMapping(value = "stunion/apply")
	public void stunionApply(HttpServletRequest request, HttpServletResponse response, StunionApply model) {
		List<?> list = baseService.findListByHql(" from StunionApply where studentId = '"+model.getStudentId()+"' and stunionId = '"+model.getStunionId()+"' and status != '-1' ");
		if(list!=null && list.size()>0) {
			result = failResult("您已申请此社团");
		}else {
			FieldUtil.setCreatedAt(model);
			boolean flag = baseService.save(model);
			if (flag) {
				result = successResult(model);
			} else {
				result = failResult("申请失败，系统发生异常...");
			}
		}
		outJson(response, result);
	}
	//我的申请
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "stunion/apply/find")
	public void stunionApplyFind(HttpServletRequest request, HttpServletResponse response, int id) {
		List<StunionApply> list = (List<StunionApply>) baseService.findListByHql(" from StunionApply where studentId = '"+id+"' ");
		for (StunionApply stunionApply : list) {
			Stunion m3 = (Stunion) baseService.findObject(" from Stunion where 1=1 and id = '"+stunionApply.getStunionId()+"' ");
			if(m3 != null){
				stunionApply.setStunionIdTxt(m3.getName());
			}
			Dict m5 = (Dict) baseService.findObject(" from Dict where code = `stunion_apply_status` and val = '"+stunionApply.getStatus()+"' ");
			if(m5 != null){
				stunionApply.setStatusTxt(m5.getTxt());
			}
		}
		result = successResult(list);
		outJson(response, result);
	}
	//删除申请
	@RequestMapping(value = "stunion/apply/del")
	public void stunionApplyDel(HttpServletRequest request, HttpServletResponse response, StunionApply model) {
		baseService.delete(model);
		result = successResult("");
		outJson(response, result);
	}
	//活动
	@SuppressWarnings("unchecked")
	@RequestMapping("activities")
	public void activities(HttpServletRequest request, HttpServletResponse response) {
		String hql = " from Activity where 1=1 ";
		//社团查询
		String stunionId = request.getParameter("stunionId");
		if(!StringUtil.isEmpty(stunionId)){
			hql += " and stunionId = '"+stunionId+"' ";
		}
		//活动名称查询
		String name = request.getParameter("name");
		if(!StringUtil.isEmpty(name)){
			hql += " and name like '%"+name+"%' ";
		}
		List<Activity> list = (List<Activity>) baseService.findListByHql(hql);
		for (Activity activity : list) {
			Stunion m2 = (Stunion) baseService.findObject(" from Stunion where 1=1 and id = '"+activity.getStunionId()+"' ");
			if(m2 != null){
				activity.setStunionIdTxt(m2.getName());
			}
		}
		result = successResult(list);
		outJson(response, result);
	}
	//活动明细
	@SuppressWarnings("unchecked")
	@RequestMapping("activity/detail")
	public void activityDetail(HttpServletRequest request, HttpServletResponse response,int id) {
		Activity model = (Activity) baseService.findObject(" from Activity where id = '"+id+"' ");
		Stunion m2 = (Stunion) baseService.findObject(" from Stunion where 1=1 and id = '"+model.getStunionId()+"' ");
		if(m2 != null){
			model.setStunionIdTxt(m2.getName());
		}
		List<ActivityComment> comment = (List<ActivityComment>) baseService.findListByHql(" from ActivityComment where activityId = '"+id+"' ");
		for (ActivityComment activityComment : comment) {
			Student m3 = (Student) baseService.findObject(" from Student where 1=1 and id = '"+activityComment.getStudentId()+"' ");
			if(m3 != null){
				activityComment.setStudentIdTxt(m3.getName());
			}
		}
		model.setComment(comment);
		result = successResult(model);
		outJson(response, result);
	}
	//活动点赞
	@RequestMapping("activity/hits")
	public void activityHits(HttpServletRequest request, HttpServletResponse response,int id) {
		baseService.execute(" update Activity set hits = hits+1 where id = '"+id+"'  ");
		result = successResult("");
		outJson(response, result);
	}
	//活动申请
	@RequestMapping(value = "activity/apply")
	public void activityApply(HttpServletRequest request, HttpServletResponse response, ActivityApply model) {
		List<?> list = baseService.findListByHql(" from ActivityApply where studentId = '"+model.getStudentId()+"' and activityId = '"+model.getActivityId()+"' and status != '-1' ");
		if(list!=null && list.size()>0) {
			result = failResult("您已申请此活动");
		}else {
			FieldUtil.setCreatedAt(model);
			boolean flag = baseService.save(model);
			if (flag) {
				result = successResult(model);
			} else {
				result = failResult("申请失败，系统发生异常...");
			}
		}
		outJson(response, result);
	}
	//我的申请
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "activity/apply/find")
	public void activityApplyFind(HttpServletRequest request, HttpServletResponse response, int id) {
		List<ActivityApply> list = (List<ActivityApply>) baseService.findListByHql(" from ActivityApply where studentId = '"+id+"' ");
		for (ActivityApply activityApply : list) {
			Activity m3 = (Activity) baseService.findObject(" from Activity where 1=1 and id = '"+activityApply.getActivityId()+"' ");
			if(m3 != null){
				activityApply.setActivityIdTxt(m3.getName());
			}
			Dict m5 = (Dict) baseService.findObject(" from Dict where code = `activity_apply_status` and val = '"+activityApply.getStatus()+"' ");
			if(m5 != null){
				activityApply.setStatusTxt(m5.getTxt());
			}
			Dict m6 = (Dict) baseService.findObject(" from Dict where code = `is_credit` and val = '"+activityApply.getIscredit()+"' ");
			if(m6 != null){
				activityApply.setIscreditTxt(m6.getTxt());
			}
		}
		result = successResult(list);
		outJson(response, result);
	}
	//删除申请
	@RequestMapping(value = "activity/apply/del")
	public void activityApplyDel(HttpServletRequest request, HttpServletResponse response, ActivityApply model) {
		baseService.delete(model);
		result = successResult("");
		outJson(response, result);
	}
	//活动评论
	@RequestMapping(value = "activity/comment")
	public void activityComment(HttpServletRequest request, HttpServletResponse response, ActivityComment model) {
		List<?> list = baseService.findListByHql(" from ActivityComment where studentId = '"+model.getStudentId()+"' and activityId = '"+model.getActivityId()+"' ");
		if(list!=null && list.size()>0) {
			result = failResult("您已评论此活动");
		}else {
			FieldUtil.setCreatedAt(model);
			boolean flag = baseService.save(model);
			if (flag) {
				baseService.execute(" update Activity set comments = comments+1 where id = '"+model.getActivityId()+"'  ");
				result = successResult(model);
			} else {
				result = failResult("评论失败，系统发生异常...");
			}
		}
		outJson(response, result);
	}
	//我的评论
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "activity/comment/find")
	public void activityCommentFind(HttpServletRequest request, HttpServletResponse response, int id) {
		List<ActivityComment> list = (List<ActivityComment>) baseService.findListByHql(" from ActivityComment where studentId = '"+id+"' ");
		for (ActivityComment activityComment : list) {
			Activity m3 = (Activity) baseService.findObject(" from Activity where 1=1 and id = '"+activityComment.getActivityId()+"' ");
			if(m3 != null){
				activityComment.setActivityIdTxt(m3.getName());
			}
		}
		result = successResult(list);
		outJson(response, result);
	}
	//删除评论
	@RequestMapping(value = "activity/comment/del")
	public void activityCommentDel(HttpServletRequest request, HttpServletResponse response, ActivityComment model) {
		baseService.delete(model);
		result = successResult("");
		outJson(response, result);
	}
	//活动收藏
	@RequestMapping(value = "activity/collect")
	public void activityCollect(HttpServletRequest request, HttpServletResponse response, ActivityCollect model) {
		List<?> list = baseService.findListByHql(" from ActivityCollect where studentId = '"+model.getStudentId()+"' and activityId = '"+model.getActivityId()+"' ");
		if(list!=null && list.size()>0) {
			result = failResult("您已收藏此活动");
		}else {
			FieldUtil.setCreatedAt(model);
			boolean flag = baseService.save(model);
			if (flag) {
				baseService.execute(" update Activity set collects = collects+1 where id = '"+model.getActivityId()+"'  ");
				result = successResult(model);
			} else {
				result = failResult("收藏失败，系统发生异常...");
			}
		}
		outJson(response, result);
	}
	//我的收藏
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "activity/collect/find")
	public void activityCollectFind(HttpServletRequest request, HttpServletResponse response, int id) {
		List<ActivityCollect> list = (List<ActivityCollect>) baseService.findListByHql(" from ActivityCollect where studentId = '"+id+"' ");
		for (ActivityCollect activityCollect : list) {
			Activity m3 = (Activity) baseService.findObject(" from Activity where 1=1 and id = '"+activityCollect.getActivityId()+"' ");
			if(m3 != null){
				activityCollect.setActivityIdTxt(m3.getName());
			}
		}
		result = successResult(list);
		outJson(response, result);
	}
	//删除收藏
	@RequestMapping(value = "activity/collect/del")
	public void activityCollectDel(HttpServletRequest request, HttpServletResponse response, ActivityCollect model) {
		baseService.delete(model);
		result = successResult("");
		outJson(response, result);
	}
}

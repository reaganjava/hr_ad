package com.dotoyo.buildjob.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.dotoyo.buildjob.common.action.BuildJobAction;
import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.BlogUserInfoDto;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.html.HtmlGeneration;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.user.service.IUserService;
import com.dotoyo.buildjob.headhunterCenter.dto.JobInfoDto;
import com.dotoyo.buildjob.headhunterCenter.service.IJobInfoService;
import com.dotoyo.buildjob.headhunterCenter.vo.JobSearchVo;


/**
 * @author tyler.qu
 * @dateCreated 2011-2-21
 * @description 招聘方管理
 *
 */
public class JobSideManagementAction extends BuildJobAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private IJobInfoService jobInfoService;
	private IUserService userService;
	private JobSearchVo jobSearchVo;
	private PageInfo pageInfo;
	private List<JobInfoDto> sysJobInforList;
	private Map<String,String> jobStatusMap;
	private String[] publishedJobs;
	private JobInfoDto jobInfo;
	private BlogUserInfoDto blogUserInfo;
	private List<BlogUserInfoDto> recruiterInformationList;
	private List<JobInfoDto> companyJobs;
	/**
	 * 管理公司(企业)、职位信息
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_RECRUITMENT_MANAGE)
	public String managementCompaniesJobInformation(){
		if(this.pageInfo == null){
			this.pageInfo = new PageInfo();
		}
		if(blogUserInfo == null)
		blogUserInfo = new BlogUserInfoDto();

		this.pageInfo.setPageSize(ApplicationConstant.NUMBER_OF_STAR_TALENT_SHOW);
		recruiterInformationList = userService.queryRecruiterInformation(pageInfo, blogUserInfo);
		return "managementCompaniesJobInformation";
	}

	/**
	 * View Jobs
	 */
	public String viewJobs(){
		if(this.pageInfo == null){
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(ApplicationConstant.SHOW_THE_NUMBER_OF_POSTS);
		if(null == jobSearchVo){
			jobSearchVo = new JobSearchVo();
			jobSearchVo.setStatus("");
		}
		companyJobs = jobInfoService.querySysJobInformation(jobSearchVo, pageInfo);
		jobStatusMap = new TreeMap<String,String>();
		jobStatusMap.put("",ApplicationConstant.WHOLE);// 全部
		jobStatusMap.put("1",ApplicationConstant.NORMAL);
		jobStatusMap.put("0",ApplicationConstant.STATUS_SUSPEND);// 暂停
		jobStatusMap.put("2",ApplicationConstant.EXPIRED);// 暂停
		return "viewJobs";
	}

//sdf
	/**
	 * 删除公司 发布中的职位
	 */
	public String delJob(){
		jobInfoService.deleteEnterprisePublishedJob(StringUtils.join(publishedJobs, ','));
		HtmlGeneration.getInstance().refreshHeadhunterCenterPage();
		return this.companiesJobManage();
	}
	/**
	 * 针对单个人删除公司 发布中的职位
	 */
	public String delJobByP(){
		jobInfoService.deleteEnterprisePublishedJob(StringUtils.join(publishedJobs, ','));
		HtmlGeneration.getInstance().refreshHeadhunterCenterPage();
		viewJobs();
		return "viewJobs";
	}
	/**
	 * 公司/企业招聘职位管理
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_RECRUITMENT_POSITION_MANAGE)
	public String companiesJobManage(){
		if(this.pageInfo == null){
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(ApplicationConstant.SHOW_THE_NUMBER_OF_POSTS);
		if(null == jobSearchVo){
			jobSearchVo = new JobSearchVo();
			jobSearchVo.setStatus("");
		}
		sysJobInforList = jobInfoService.querySysJobInformation(jobSearchVo, pageInfo);
		jobStatusMap = new TreeMap<String,String>();
		jobStatusMap.put("",ApplicationConstant.WHOLE);// 全部
		jobStatusMap.put("1",ApplicationConstant.NORMAL);
		jobStatusMap.put("0",ApplicationConstant.STATUS_SUSPEND);// 暂停
		jobStatusMap.put("2",ApplicationConstant.EXPIRED);// 过期
		return "companiesJobManage";
	}

	/**
	 * 暂停职位发布(正常-暂停)
	 */
	public String suspendJobs(){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("jobIds", StringUtils.join(publishedJobs, ','));
		paramMap.put("status", ApplicationConstant.SUSPEND);
		paramMap.put("currentDate", new Date());
		jobInfoService.updatePublishedJob(ApplicationConstant.SUSPEND_PUBLISHED_JOB,paramMap);
		HtmlGeneration.getInstance().refreshHeadhunterCenterPage();
		return this.companiesJobManage();
	}
	/**
	 * 针对单个人暂停职位发布(正常-暂停)
	 */
	public String suspendJobsByP(){
		suspendJobs();
		viewJobs();
		HtmlGeneration.getInstance().refreshHeadhunterCenterPage();
		return "viewJobs";
	}
	/**
	 * 职位详细信息
	 */
	public String jobInfoDetail(){
		jobInfo=jobInfoService.getJobInfo(jobSearchVo.getId(),jobSearchVo.getUserId());
		return "jobInfoDetail";
	}

	public void setJobInfoService(IJobInfoService jobInfoService) {
		this.jobInfoService = jobInfoService;
	}

	public IJobInfoService getJobInfoService() {
		return jobInfoService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setJobSearchVo(JobSearchVo jobSearchVo) {
		this.jobSearchVo = jobSearchVo;
	}

	public JobSearchVo getJobSearchVo() {
		return jobSearchVo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setSysJobInforList(List<JobInfoDto> sysJobInforList) {
		this.sysJobInforList = sysJobInforList;
	}

	public List<JobInfoDto> getSysJobInforList() {
		return sysJobInforList;
	}

	public void setJobStatusMap(Map<String,String> jobStatusMap) {
		this.jobStatusMap = jobStatusMap;
	}

	public Map<String,String> getJobStatusMap() {
		return jobStatusMap;
	}

	public void setPublishedJobs(String[] publishedJobs) {
		this.publishedJobs = publishedJobs;
	}

	public String[] getPublishedJobs() {
		return publishedJobs;
	}

	public void setJobInfo(JobInfoDto jobInfo) {
		this.jobInfo = jobInfo;
	}

	public JobInfoDto getJobInfo() {
		return jobInfo;
	}

	public void setBlogUserInfo(BlogUserInfoDto blogUserInfo) {
		this.blogUserInfo = blogUserInfo;
	}

	public BlogUserInfoDto getBlogUserInfo() {
		return blogUserInfo;
	}

	public void setRecruiterInformationList(List<BlogUserInfoDto> recruiterInformationList) {
		this.recruiterInformationList = recruiterInformationList;
	}

	public List<BlogUserInfoDto> getRecruiterInformationList() {
		return recruiterInformationList;
	}

	public void setCompanyJobs(List<JobInfoDto> companyJobs) {
		this.companyJobs = companyJobs;
	}

	public List<JobInfoDto> getCompanyJobs() {
		return companyJobs;
	}
}

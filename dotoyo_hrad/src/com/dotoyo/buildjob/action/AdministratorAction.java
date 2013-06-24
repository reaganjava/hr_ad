package com.dotoyo.buildjob.action;

import java.util.HashMap;
import java.util.Map;

import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.user.service.IUserService;
import com.dotoyo.buildjob.common.util.DateUtil;
import com.dotoyo.buildjob.headhunterCenter.service.IJobInfoService;
import com.dotoyo.buildjob.innovationSalon.service.ISalonSearchService;
import com.dotoyo.buildjob.sys.service.IApplyJobAdminService;
import com.dotoyo.buildjob.sys.service.ICertificateAdminService;
import com.dotoyo.buildjob.sys.service.ISysOrderService;
import com.dotoyo.buildjob.sys.service.ISysUserRoleService;
/**
 * 系统管理主页
 * @author wisdy.xiao
 *
 */
public class AdministratorAction extends SysAction{
	private IJobInfoService jobInfoService;
	private ISysOrderService sysOrderService;
	private ISysUserRoleService sysUserRoleService;
	private ICertificateAdminService certificateAdminService;
	private IUserService userService;
	private IApplyJobAdminService applyJobAdminService;
	private ISalonSearchService salonSearchService;
	private int releasedNumber;
	private int newOrders;
	private int individualMember;
	private int corporateMember;
	private int certificateRequirements;
	private int personalCertificate;
	private int newLineRecommendedPersonnels;
	private int publishedAbroad;
	private int newArticles;
	/**
	 *
	 */
	private static final long serialVersionUID = 8937122470659660048L;
	public String init(){
		releasedNumberTheOfPosts();
		getCountOfNewOrders();
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("queryType", "6");
		paramMap.put("currentDate", DateUtil.getCurrentDateString());
		certificateRequirements = certificateAdminService.queryCertNeedsCount4Admin(paramMap);
		personalCertificate = certificateAdminService.queryPersonalCertCount4Admin(paramMap);

		Map<String,String> applyJobMap = new HashMap<String,String>();
		applyJobMap.put("submitDate", DateUtil.getCurrentDateString());
		publishedAbroad = applyJobAdminService.queryApplyJobCount4Admin(applyJobMap);
		newArticles = salonSearchService.getNumberOfNewArticlesStatisticalToDay();
		newLineRecommendedPersonnels = userService.getNumberOfNewLineRecommendedPersonnel();
		individualMember = sysUserRoleService.getCountOfSysMemberToDayRegister(ApplicationConstant.USER_TYPE_PERSONAL);
		corporateMember = sysUserRoleService.getCountOfSysMemberToDayRegister(ApplicationConstant.USER_TYPE_ENTERPRISE);
		return Constants.RTN_SYS_MAIN;
	}
	public int getReleasedNumber() {
		return releasedNumber;
	}
	public void setReleasedNumber(int releasedNumber) {
		this.releasedNumber = releasedNumber;
	}
	public int getNewOrders() {
		return newOrders;
	}
	public void setNewOrders(int newOrders) {
		this.newOrders = newOrders;
	}
	public int getIndividualMember() {
		return individualMember;
	}
	public void setIndividualMember(int individualMember) {
		this.individualMember = individualMember;
	}
	public int getCorporateMember() {
		return corporateMember;
	}
	public void setCorporateMember(int corporateMember) {
		this.corporateMember = corporateMember;
	}
	public int getCertificateRequirements() {
		return certificateRequirements;
	}
	public void setCertificateRequirements(int certificateRequirements) {
		this.certificateRequirements = certificateRequirements;
	}
	public int getPersonalCertificate() {
		return personalCertificate;
	}
	public void setPersonalCertificate(int personalCertificate) {
		this.personalCertificate = personalCertificate;
	}
	public int getNewLineRecommendedPersonnels() {
		return newLineRecommendedPersonnels;
	}
	public void setNewLineRecommendedPersonnels(int newLineRecommendedPersonnels) {
		this.newLineRecommendedPersonnels = newLineRecommendedPersonnels;
	}
	public int getPublishedAbroad() {
		return publishedAbroad;
	}
	public void setPublishedAbroad(int publishedAbroad) {
		this.publishedAbroad = publishedAbroad;
	}
	public int getNewArticles() {
		return newArticles;
	}
	public void setNewArticles(int newArticles) {
		this.newArticles = newArticles;
	}

	public IJobInfoService getJobInfoService() {
		return jobInfoService;
	}
	public void setJobInfoService(IJobInfoService jobInfoService) {
		this.jobInfoService = jobInfoService;
	}
	public ISysOrderService getSysOrderService() {
		return sysOrderService;
	}
	public void setSysOrderService(ISysOrderService sysOrderService) {
		this.sysOrderService = sysOrderService;
	}
	public ISysUserRoleService getSysUserRoleService() {
		return sysUserRoleService;
	}
	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}
	public ICertificateAdminService getCertificateAdminService() {
		return certificateAdminService;
	}
	public void setCertificateAdminService(
			ICertificateAdminService certificateAdminService) {
		this.certificateAdminService = certificateAdminService;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IApplyJobAdminService getApplyJobAdminService() {
		return applyJobAdminService;
	}
	public void setApplyJobAdminService(IApplyJobAdminService applyJobAdminService) {
		this.applyJobAdminService = applyJobAdminService;
	}
	public ISalonSearchService getSalonSearchService() {
		return salonSearchService;
	}
	public void setSalonSearchService(ISalonSearchService salonSearchService) {
		this.salonSearchService = salonSearchService;
	}
	/**
	 * 新增发布招聘职位数
	 */
	public void releasedNumberTheOfPosts(){
		releasedNumber = jobInfoService.releasedNumberOfThePostsToDay();
	}

	/**
	 * 新增订单数
	 */
	public void getCountOfNewOrders(){
		newOrders = sysOrderService.getNewOrdersStatisticsToDay();
	}
}

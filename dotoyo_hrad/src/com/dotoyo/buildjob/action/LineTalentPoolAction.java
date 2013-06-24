package com.dotoyo.buildjob.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.danga.MemCached.MemCachedClient;
import com.dotoyo.buildjob.applyJobCenter.dto.RecommendationDto;
import com.dotoyo.buildjob.applyJobCenter.service.IApplyJobService;
import com.dotoyo.buildjob.applyJobCenter.vo.RecommendationVo;
import com.dotoyo.buildjob.common.action.BuildJobAction;
import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.AreaDto;
import com.dotoyo.buildjob.common.dto.BlogUserInfoDto;
import com.dotoyo.buildjob.common.dto.CityDto;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.dto.ProvinceDto;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.user.dto.LoginUserInfoDto;
import com.dotoyo.buildjob.common.user.service.IUserService;
import com.dotoyo.buildjob.common.util.CacheManager;
import com.dotoyo.buildjob.common.util.DateUtil;
import com.dotoyo.buildjob.common.util.RegionDataUtil;
import com.dotoyo.buildjob.systemManage.dto.ClassMasterDto;

/**
 * @author tyler.qu
 * @dateCreated 2011-2-22
 * @description
 * 
 */
public class LineTalentPoolAction extends BuildJobAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private IUserService userService;
	private IApplyJobService applyJobService;
	private PageInfo pageInfo;
	private String columnName = "lastEditDate";
	private List<BlogUserInfoDto> lineTalentPoolList;
	private String[] lineTalents;
	private String verifyStatus;
	private String expectedPosition;// 期望职位
	private String metier;// 专长
	private RecommendationDto recommendationDto;
	private List<ClassMasterDto> jobTypeList;// 工作类型列表
	private String actionMessage;
	private String interviewStatus;
	private String provinceCode;
	private String cityCode;
	private String areaCode;
	private String showAbstract;//页面标识，是否显示摘要
	private String isNew;

	/**
	 * 线下人才库 人才加载
	 */
	@SuppressWarnings("unchecked")
	@AuthFunction(functionCode = FunctionCodeConstant.FUN_UNLINE_TALENTS_STORAGE)
	public String loadLineTalentPool() {
		MemCachedClient memcachedClient = CacheManager
		.getInstanceMemcachedClient();
		provinceList = (List<ProvinceDto>) memcachedClient.get("provinceList");
		if(provinceCode!=null&&!"".equalsIgnoreCase(provinceCode)){
			cityList = RegionDataUtil.queryCityListByProvinceCode(provinceCode);
		}
		if(cityCode!=null&&!"".equalsIgnoreCase(cityCode)){
			areaList = RegionDataUtil.queryAreaListByCityCode(cityCode);
		}
		if (cityList == null) {
			cityList = new ArrayList<CityDto>();
		}
		if (areaList == null) {
			areaList = new ArrayList<AreaDto>();
		}
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("columnName", columnName);
		paramMap.put("expectedPosition", expectedPosition);
		paramMap.put("metier", metier);
		paramMap.put("provinceCode", provinceCode);
		paramMap.put("cityCode", cityCode);
		paramMap.put("areaCode", areaCode);
		paramMap.put("interviewStatus", interviewStatus);
		if("Y".equalsIgnoreCase(isNew)){
			paramMap.put("currentDate", DateUtil.getCurrentDateString("yyyy-MM-dd"));
		}
		
		this.pageInfo
				.setPageSize(ApplicationConstant.NUMBER_OF_STAR_TALENT_SHOW);
		lineTalentPoolList = userService
				.queryLineTalentPool(paramMap, pageInfo);
		return "lineTalentPool";
	}

	/**
	 * 审核线下人才 申请
	 */
	public String examineAndVerify() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", StringUtils.join(lineTalents, ','));
		paramMap.put("verifyStatus", verifyStatus);
		paramMap.put("lastEditDate", new Date());
		applyJobService.updateLineTalentVerifyStatus(paramMap);
		return this.loadLineTalentPool();
	}

	public String editInterviewStatus() {
		Map<String, String> recommendationMap = new HashMap<String, String>();
		recommendationMap.put("ids", StringUtils.join(lineTalents, ','));
		recommendationMap.put("interviewStatus", interviewStatus);
		applyJobService.updateRecommendationInterviewStatus(recommendationMap);
		return this.loadLineTalentPool();
	}

	/**
	 * 删除线下人才库 记录
	 */
	public String delLineTalent() {
		applyJobService
				.deleteLineTalentById(StringUtils.join(lineTalents, ','));
		return this.loadLineTalentPool();
	}

	@SuppressWarnings("unchecked")
	public String toAddRecommendation() {
		
		// 加载页面基础数据
		competencyList = (List<ClassMasterDto>) CacheManager
				.getInstanceMemcachedClient().get("competencyList");
		provinceList = (List<ProvinceDto>) CacheManager
				.getInstanceMemcachedClient().get("provinceList");
		salaryList = (List<ClassMasterDto>) CacheManager
				.getInstanceMemcachedClient().get("salaryList");
		jobTypeList = (List<ClassMasterDto>) CacheManager
				.getInstanceMemcachedClient().get("jobNatureList");
		return "toAddRecommendation";
	}

	public String saveAddRecommendation() {
		LoginUserInfoDto loginUserInfo = userService
				.getUserByUserName(recommendationDto.getUserName());

		// 检查添加的线下推荐对象是否存在
		if (loginUserInfo == null) {
			actionMessage = "用户不存在！";
			return toAddRecommendation();
		}

		// 检查添加的线下推荐对象是否具有完善的博站资料
		BlogUserInfoDto blogUserInfo = userService
				.getUserInfoDetailByUserName(loginUserInfo.getUserName());
		String userType = blogUserInfo.getUserType();
		if (userType == null || "".equalsIgnoreCase(userType)) {
			actionMessage = "用户资料不完善！";
			return toAddRecommendation();
		}

		if (ApplicationConstant.USER_TYPE_ENTERPRISE.equalsIgnoreCase(userType)) {
			actionMessage = "企业用户无法成为线下推荐对象！";
			return toAddRecommendation();
		}

		Long userId = loginUserInfo.getId();
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}

		// 价差添加的用户是否已是线下推荐对象
		pageInfo.setPageSize(10);
		RecommendationDto tempRecommendationDto = new RecommendationDto();
		tempRecommendationDto.setUserId(userId);
		List<RecommendationVo> tempList = applyJobService
				.queryRecommendationList(pageInfo, tempRecommendationDto);
		if (tempList != null && !tempList.isEmpty()) {
			actionMessage = "该用户已经是线下推荐对象！";
			return toAddRecommendation();
		}

		recommendationDto.setRecommendationType("1");

		Date currentDate = new Date();

		recommendationDto.setLastEditDate(currentDate);
		recommendationDto.setSubmitDate(currentDate);
		recommendationDto.setUserId(userId);
		recommendationDto.setVerifyStatus("2");
		recommendationDto.setInterviewStatus("1");
		applyJobService.saveRecommendation(recommendationDto, null);
		
		return this.loadLineTalentPool();
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setLineTalentPoolList(List<BlogUserInfoDto> lineTalentPoolList) {
		this.lineTalentPoolList = lineTalentPoolList;
	}

	public List<BlogUserInfoDto> getLineTalentPoolList() {
		return lineTalentPoolList;
	}

	public void setApplyJobService(IApplyJobService applyJobService) {
		this.applyJobService = applyJobService;
	}

	public IApplyJobService getApplyJobService() {
		return applyJobService;
	}

	public void setLineTalents(String[] lineTalents) {
		this.lineTalents = lineTalents;
	}

	public String[] getLineTalents() {
		return lineTalents;
	}

	public void setVerifyStatus(String verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public String getVerifyStatus() {
		return verifyStatus;
	}

	public void setExpectedPosition(String expectedPosition) {
		this.expectedPosition = expectedPosition;
	}

	public String getExpectedPosition() {
		return expectedPosition;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}

	public String getMetier() {
		return metier;
	}

	public RecommendationDto getRecommendationDto() {
		return recommendationDto;
	}

	public void setRecommendationDto(RecommendationDto recommendationDto) {
		this.recommendationDto = recommendationDto;
	}

	public List<ClassMasterDto> getJobTypeList() {
		return jobTypeList;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getShowAbstract() {
		return showAbstract;
	}

	public void setShowAbstract(String showAbstract) {
		this.showAbstract = showAbstract;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
}

package com.dotoyo.buildjob.action;

import java.util.List;

import com.dotoyo.buildjob.applyJobCenter.dto.RecommendationDto;
import com.dotoyo.buildjob.applyJobCenter.service.IApplyJobService;
import com.dotoyo.buildjob.applyJobCenter.vo.RecommendationVo;
import com.dotoyo.buildjob.common.action.BuildJobAction;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.BlogUserInfoDto;
import com.dotoyo.buildjob.common.dto.OfflineSearchResultDto;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.service.IOfflineSearchResultService;
import com.dotoyo.buildjob.headhunterCenter.dto.JobInfoDto;
import com.dotoyo.buildjob.headhunterCenter.service.IJobInfoService;
import com.dotoyo.buildjob.headhunterCenter.vo.JobSearchVo;
import com.dotoyo.buildjob.peopleExcavate.service.IPeopleExcavateService;
import com.dotoyo.buildjob.peopleExcavate.vo.PeopleExcavateVo;

/**
 * 
 * @author arthas.zou
 * @dateCreated 2011-3-1
 * @description
 */
public class TalentMatchAction extends BuildJobAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8857773012629473936L;
	private IApplyJobService applyJobService;
	private RecommendationDto recommendationDto;
	private RecommendationVo recommendationVo;
	private List<RecommendationVo> talentList;
	private PageInfo pageInfo;
	private IJobInfoService jobInfoService;
	private List<JobInfoDto> jobResultList;
	private JobSearchVo jobSearchVo;
	private OfflineSearchResultDto offlineSearchResultDto;
	private List<OfflineSearchResultDto> offlineSearchResultList;
	private IOfflineSearchResultService offlineSearchResultService;
	private List<BlogUserInfoDto> excavateResultList;
	private IPeopleExcavateService peopleExcavateService;
	private PeopleExcavateVo peopleExcavateVo;

	@AuthFunction(functionCode = FunctionCodeConstant.FUN_UNLINE_TALENTS_MATCH_PERSONAL)
	public String viewTalentList() {
		if (recommendationDto == null) {
			recommendationDto = new RecommendationDto();
			recommendationDto.setUserName("");
			recommendationDto.setExpectedPosition("");
		}
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		pageInfo.setPageCount4Paging(Constants.TALENT_LIST_SIZE);
		recommendationDto.setInterviewStatus(Constants.INTERVIEW_SUCCESS);
		talentList = applyJobService.queryRecommendationList(pageInfo,
				recommendationDto);
		return "viewTalentList";
	}

	@AuthFunction(functionCode = FunctionCodeConstant.FUN_UNLINE_TALENTS_MATCH_ENTERPRISE)
	public String viewOfflineSearchResultList() {
		if (offlineSearchResultDto == null) {
			offlineSearchResultDto = new OfflineSearchResultDto();
		}
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		pageInfo.setPageSize(Constants.OFFLINESEARCHRESULT_LIST_SIZE);
		offlineSearchResultList = offlineSearchResultService
				.queryOfflineSearchResultList(pageInfo, offlineSearchResultDto);
		return "viewOfflineSearchResultList";
	}

	/**
	 * 为个人用户匹配职位信息
	 * 
	 * @return
	 */
	public String matchPosition4Personal() {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		pageInfo.setPageSize(10);
		recommendationVo = applyJobService
				.getRecommendationById(recommendationDto.getId());
		if (null == jobSearchVo) {
			jobSearchVo = new JobSearchVo();
			jobSearchVo.setOrderBy("orderByJobIssuetDate");// 默认按用户资料更改时间降序
		} else if (null == jobSearchVo.getOrderBy()
				|| jobSearchVo.getOrderBy().equals("")) {
			jobSearchVo.setOrderBy("orderByJobIssuetDate");// 默认按用户资料更改时间降序
		}
		jobSearchVo.setJobName(recommendationVo.getExpectedPosition());
		jobSearchVo.setProvinceCode(recommendationVo.getProvince().getCode());
		jobSearchVo.setCityCode(recommendationVo.getCity().getCode());

		if (recommendationVo.getArea() != null) {
			jobSearchVo.setAreaCode(recommendationVo.getArea().getCode());
		} else {
			jobSearchVo.setAreaCode("");
		}
		jobSearchVo.setJobNature(recommendationVo.getJobType().getCode());
		String isAdvisror = recommendationVo.getIsAdvisor();
		if ("Y".equalsIgnoreCase(isAdvisror)) {
			isAdvisror = "1";
		} else {
			isAdvisror = "0";
		}
		jobSearchVo.setIsAdviser(isAdvisror);
		jobSearchVo.setSearchType("0");
		jobSearchVo.setKeyWord(recommendationVo.getExpectedPosition());
		jobResultList = jobInfoService
				.searchMatchJobList(jobSearchVo, pageInfo);
		return "matchPosition4Personal";
	}

	public String matchTalent4Enterprise() {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		pageInfo.setPageSize(10);
		offlineSearchResultDto = offlineSearchResultService
				.getOfflineSearchResultById(offlineSearchResultDto.getId());
		if (peopleExcavateVo == null) {
			peopleExcavateVo = new PeopleExcavateVo();
		}
		peopleExcavateVo.setProvinceCode(offlineSearchResultDto
				.getProvinceCode());
		peopleExcavateVo.setCityCode(offlineSearchResultDto.getCityCode());
		peopleExcavateVo.setAreaCode(offlineSearchResultDto.getAreaCode());
		peopleExcavateVo
				.setJobNature(offlineSearchResultDto.getJobNatureCode());
		peopleExcavateVo.setIsAdviser(offlineSearchResultDto.getIsAdviser());
		peopleExcavateVo.setJobKeyWord(offlineSearchResultDto.getJobKeyWord());
		excavateResultList = peopleExcavateService.mathTalent4Enterprise(pageInfo, peopleExcavateVo);
		return "matchTalent4Enterprise";
	}

	public String printPosition4Personal() {
		recommendationVo = applyJobService
				.getRecommendationById(recommendationDto.getId());
		if (null == jobSearchVo) {
			jobSearchVo = new JobSearchVo();
			jobSearchVo.setOrderBy("orderByJobIssuetDate");// 默认按用户资料更改时间降序
		} else if (null == jobSearchVo.getOrderBy()
				|| jobSearchVo.getOrderBy().equals("")) {
			jobSearchVo.setOrderBy("orderByJobIssuetDate");// 默认按用户资料更改时间降序
		}
		jobSearchVo.setJobName(recommendationVo.getExpectedPosition());
		jobSearchVo.setProvinceCode(recommendationVo.getProvince().getCode());
		jobSearchVo.setCityCode(recommendationVo.getCity().getCode());

		if (recommendationVo.getArea() != null) {
			jobSearchVo.setAreaCode(recommendationVo.getArea().getCode());
		} else {
			jobSearchVo.setAreaCode("");
		}
		jobSearchVo.setJobNature(recommendationVo.getJobType().getCode());
		String isAdvisror = recommendationVo.getIsAdvisor();
		if (isAdvisror == "Y") {
			isAdvisror = "1";
		} else {
			isAdvisror = "0";
		}
		jobSearchVo.setIsAdviser(isAdvisror);
		jobSearchVo.setSearchType("0");
		jobSearchVo.setKeyWord(recommendationVo.getExpectedPosition());
		jobResultList = jobInfoService.searchMatchJobList(jobSearchVo,null);
		return "printPosition4Personal";
	}

	public String printTalent4Enterprise() {
		offlineSearchResultDto = offlineSearchResultService
				.getOfflineSearchResultById(offlineSearchResultDto.getId());
		if (peopleExcavateVo == null) {
			peopleExcavateVo = new PeopleExcavateVo();
		}
		peopleExcavateVo.setProvinceCode(offlineSearchResultDto
				.getProvinceCode());
		peopleExcavateVo.setCityCode(offlineSearchResultDto.getCityCode());
		peopleExcavateVo.setAreaCode(offlineSearchResultDto.getAreaCode());
		peopleExcavateVo
				.setJobNature(offlineSearchResultDto.getJobNatureCode());
		peopleExcavateVo.setIsAdviser(offlineSearchResultDto.getIsAdviser());
		peopleExcavateVo.setJobKeyWord(offlineSearchResultDto.getJobKeyWord());
		excavateResultList = peopleExcavateService
				.queryBtiTalentList(peopleExcavateVo);
		return "printTalent4Enterprise";
	}

	/**
	 * @return the applyJobService
	 */
	public IApplyJobService getApplyJobService() {
		return applyJobService;
	}

	/**
	 * @param applyJobService
	 *            the applyJobService to set
	 */
	public void setApplyJobService(IApplyJobService applyJobService) {
		this.applyJobService = applyJobService;
	}

	/**
	 * @return the recommendationDto
	 */
	public RecommendationDto getRecommendationDto() {
		return recommendationDto;
	}

	/**
	 * @param recommendationDto
	 *            the recommendationDto to set
	 */
	public void setRecommendationDto(RecommendationDto recommendationDto) {
		this.recommendationDto = recommendationDto;
	}

	/**
	 * @return the talentList
	 */
	public List<RecommendationVo> getTalentList() {
		return talentList;
	}

	/**
	 * @return the pageInfo
	 */
	public PageInfo getPageInfo() {
		return pageInfo;
	}

	/**
	 * @param pageInfo
	 *            the pageInfo to set
	 */
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	/**
	 * @return the recommendationVo
	 */
	public RecommendationVo getRecommendationVo() {
		return recommendationVo;
	}

	/**
	 * @param recommendationVo
	 *            the recommendationVo to set
	 */
	public void setRecommendationVo(RecommendationVo recommendationVo) {
		this.recommendationVo = recommendationVo;
	}

	/**
	 * @return the jobInfoService
	 */
	public IJobInfoService getJobInfoService() {
		return jobInfoService;
	}

	/**
	 * @param jobInfoService
	 *            the jobInfoService to set
	 */
	public void setJobInfoService(IJobInfoService jobInfoService) {
		this.jobInfoService = jobInfoService;
	}

	/**
	 * @return the jobResultList
	 */
	public List<JobInfoDto> getJobResultList() {
		return jobResultList;
	}

	/**
	 * @return the jobSearchVo
	 */
	public JobSearchVo getJobSearchVo() {
		return jobSearchVo;
	}

	/**
	 * @param jobSearchVo
	 *            the jobSearchVo to set
	 */
	public void setJobSearchVo(JobSearchVo jobSearchVo) {
		this.jobSearchVo = jobSearchVo;
	}

	/**
	 * @return the offlineSearchResultDto
	 */
	public OfflineSearchResultDto getOfflineSearchResultDto() {
		return offlineSearchResultDto;
	}

	/**
	 * @param offlineSearchResultDto
	 *            the offlineSearchResultDto to set
	 */
	public void setOfflineSearchResultDto(
			OfflineSearchResultDto offlineSearchResultDto) {
		this.offlineSearchResultDto = offlineSearchResultDto;
	}

	/**
	 * @return the offlineSearchResultService
	 */
	public IOfflineSearchResultService getOfflineSearchResultService() {
		return offlineSearchResultService;
	}

	/**
	 * @param offlineSearchResultService
	 *            the offlineSearchResultService to set
	 */
	public void setOfflineSearchResultService(
			IOfflineSearchResultService offlineSearchResultService) {
		this.offlineSearchResultService = offlineSearchResultService;
	}

	/**
	 * @return the offlineSearchResultList
	 */
	public List<OfflineSearchResultDto> getOfflineSearchResultList() {
		return offlineSearchResultList;
	}

	/**
	 * @return the peopleExcavateService
	 */
	public IPeopleExcavateService getPeopleExcavateService() {
		return peopleExcavateService;
	}

	/**
	 * @param peopleExcavateService
	 *            the peopleExcavateService to set
	 */
	public void setPeopleExcavateService(
			IPeopleExcavateService peopleExcavateService) {
		this.peopleExcavateService = peopleExcavateService;
	}

	/**
	 * @return the excavateResultList
	 */
	public List<BlogUserInfoDto> getExcavateResultList() {
		return excavateResultList;
	}

	/**
	 * @return the peopleExcavateVo
	 */
	public PeopleExcavateVo getPeopleExcavateVo() {
		return peopleExcavateVo;
	}

	/**
	 * @param peopleExcavateVo
	 *            the peopleExcavateVo to set
	 */
	public void setPeopleExcavateVo(PeopleExcavateVo peopleExcavateVo) {
		this.peopleExcavateVo = peopleExcavateVo;
	}

}

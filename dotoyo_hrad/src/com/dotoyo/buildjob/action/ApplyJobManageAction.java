package com.dotoyo.buildjob.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.dotoyo.buildjob.applyJobCenter.dto.ApplyJobInfoDto;
import com.dotoyo.buildjob.applyJobCenter.service.IApplyJobService;
import com.dotoyo.buildjob.common.action.BuildJobAction;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.html.HtmlGeneration;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.DateUtil;
import com.dotoyo.buildjob.headhunterCenter.dto.ApplicationJobRecordDto;
import com.dotoyo.buildjob.headhunterCenter.service.IJobInfoService;
import com.dotoyo.buildjob.sys.service.IApplyJobAdminService;

/**
 * 
 * @author arthas.zou
 * @dateCreated 2011-2-25
 * @description
 */
public class ApplyJobManageAction extends BuildJobAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8926747302062343705L;
	private IApplyJobAdminService applyJobAdminService;
	private IApplyJobService applyJobService;
	private List<ApplyJobInfoDto> applyJobList;
	private Map<String, String> applyJobMap;
	private PageInfo pageInfo;
	private String[] applyJobCheckBox;
	private List<ApplicationJobRecordDto> applicationJobRecordList;
	private Map<String, String> recordMap;
	private String[] recordCheckBox;
	private IJobInfoService jobInfoService;
	private String isNew;

	/**
	 * 查看求职意向列表
	 * 
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_APPLY_JOB_INTENTION_MANAGE)
	public String viewApplyJobIntention4Admin() {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		pageInfo.setPageSize(Constants.APPLYJOB_LIST_SIZE);
		if (applyJobMap == null) {
			applyJobMap = new HashMap<String, String>();
		}
		if("Y".equalsIgnoreCase(isNew)){
			applyJobMap.put("submitDate", DateUtil.getCurrentDateString("yyyy-MM-dd"));
		}
		applyJobList = applyJobAdminService.queryApplyJobList4Admin(pageInfo,
				applyJobMap);
		return "viewApplyJobIntention4Admin";
	}
	

	/**
	 * 批量删除求职意向
	 * 
	 * @return
	 */
	public String deleteApplyJobIntention() {
		
		String applyJobInfoIds = StringUtils.join(applyJobCheckBox, ",");
		applyJobService.deleteApplyJobInfo(applyJobInfoIds);
		HtmlGeneration htmlGeneration = HtmlGeneration.getInstance();
		htmlGeneration.refreshApplyJobPage();
		return viewApplyJobIntention4Admin();
	}

	/**
	 * 查看职位申请记录列表
	 * 
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_APPLY_JOB_MANAGE)
	public String viewJobApplyRecordList4Admin() {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		pageInfo.setPageSize(Constants.APPLICATION_JOBRECORD_List_SIZE);
		if (recordMap == null) {
			recordMap = new HashMap<String, String>();
		}
		applicationJobRecordList = applyJobAdminService
				.queryApplyJobRecordList4Admin(pageInfo, recordMap);
		return "viewJobApplyRecordList4Admin";
	}

	public String deleteJobApplyRecord() {
		List<String> idList = new ArrayList<String>();
		for (String id : recordCheckBox) {
			idList.add(id);
		}
		jobInfoService.deleteUserAppliedJobByJobId(idList);
		return viewJobApplyRecordList4Admin();
	}

	/**
	 * @return the applyJobAdminService
	 */
	public IApplyJobAdminService getApplyJobAdminService() {
		return applyJobAdminService;
	}

	/**
	 * @param applyJobAdminService
	 *            the applyJobAdminService to set
	 */
	public void setApplyJobAdminService(
			IApplyJobAdminService applyJobAdminService) {
		this.applyJobAdminService = applyJobAdminService;
	}

	/**
	 * @return the applyJobList
	 */
	public List<ApplyJobInfoDto> getApplyJobList() {
		return applyJobList;
	}

	/**
	 * @return the applyJobMap
	 */
	public Map<String, String> getApplyJobMap() {
		return applyJobMap;
	}

	/**
	 * @param applyJobMap
	 *            the applyJobMap to set
	 */
	public void setApplyJobMap(Map<String, String> applyJobMap) {
		this.applyJobMap = applyJobMap;
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
	 * @return the applyJobCheckBox
	 */
	public String[] getApplyJobCheckBox() {
		return applyJobCheckBox;
	}

	/**
	 * @param applyJobCheckBox
	 *            the applyJobCheckBox to set
	 */
	public void setApplyJobCheckBox(String[] applyJobCheckBox) {
		this.applyJobCheckBox = applyJobCheckBox;
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
	 * @return the applicationJobRecordList
	 */
	public List<ApplicationJobRecordDto> getApplicationJobRecordList() {
		return applicationJobRecordList;
	}

	/**
	 * @return the recordMap
	 */
	public Map<String, String> getRecordMap() {
		return recordMap;
	}

	/**
	 * @param recordMap
	 *            the recordMap to set
	 */
	public void setRecordMap(Map<String, String> recordMap) {
		this.recordMap = recordMap;
	}

	/**
	 * @return the recordCheckBox
	 */
	public String[] getRecordCheckBox() {
		return recordCheckBox;
	}

	/**
	 * @param recordCheckBox
	 *            the recordCheckBox to set
	 */
	public void setRecordCheckBox(String[] recordCheckBox) {
		this.recordCheckBox = recordCheckBox;
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

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
}

package com.dotoyo.buildjob.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.dotoyo.buildjob.certificateCenter.dto.PersonalCertDto;
import com.dotoyo.buildjob.certificateCenter.service.ICertificateService;
import com.dotoyo.buildjob.common.action.BuildJobAction;
import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.html.HtmlGeneration;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.DateUtil;
import com.dotoyo.buildjob.common.util.EssentialDataUtil;
import com.dotoyo.buildjob.common.util.RegionDataUtil;
import com.dotoyo.buildjob.sys.service.ICertificateAdminService;
import com.dotoyo.buildjob.systemManage.dto.ClassMasterDto;

/**
 * 
 * @author arthas.zou
 * @dateCreated 2011-2-23
 * @description
 */
public class PersonalCertManageAction extends BuildJobAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8601536174012538858L;
	private ICertificateAdminService certificateAdminService;
	private ICertificateService certificateService;
	private PageInfo pageInfo;
	private Map<String, String> personalCertMap;
	private PersonalCertDto personalCertDto;
	private String[] personalCertCheckBox;
	private List<ClassMasterDto> certificateTypeList;// 证书类型列表
	private List<ClassMasterDto> specialFieldList;// 专业列表
	private List<ClassMasterDto> registerStatusList;// 注册状况列表
	private List<PersonalCertDto> personalCertList;
	private List<ClassMasterDto> certList;// 证书列表(基础数据)
	private String isNew;

	public String loadEssentialData() {
		certificateTypeList = EssentialDataUtil
				.queryEssentialDataListByParentCode(ApplicationConstant.CERT_TYPE);
		specialFieldList = EssentialDataUtil
				.querySpecializeListLikeCode(ApplicationConstant.SPECIALIZE_TYPE);
		provinceList = RegionDataUtil.queryProvinceList();
		registerStatusList = EssentialDataUtil
				.queryEssentialDataListByParentCode(ApplicationConstant.REGISTER_STATUS);
		return SUCCESS;
	}

	/**
	 * 查看个人证书信息列表
	 * 
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_CARD_MANAGE)
	public String viewPersonalCertList4Admin() {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		pageInfo.setPageSize(Constants.PERSONALCERT_LIST_SIZE);
		if (personalCertMap == null) {
			personalCertMap = new HashMap<String, String>();
		}
		if("Y".equalsIgnoreCase(isNew)){
			personalCertMap.put("currentDate", DateUtil.getCurrentDateString("yyyy-MM-dd"));
			personalCertMap.put("queryType", "6");
		}
		personalCertList = certificateAdminService.queryPersonalCertList4Admin(
				pageInfo, personalCertMap);
		return "viewPersonalCertList4Admin";
	}

	/**
	 * 批量删除个人证书信息
	 * 
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_CARD_MANAGE)
	public String deletePersonalCert() {
		String personalCertIds = StringUtils.join(personalCertCheckBox, ",");
		certificateService.deletePersonalCert(personalCertIds);
		HtmlGeneration htmlGeneration = HtmlGeneration.getInstance();
		htmlGeneration.refreshCertificateCenterPage();
		return viewPersonalCertList4Admin();
	}

	/**
	 * 查看个人证书详细信息
	 * 
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_CARD_MANAGE)
	public String viewPersonalCertDetail4Admin() {
		loadEssentialData();
		request = ServletActionContext.getRequest();
		Long personalCertId = Long.parseLong(request
				.getParameter("personalCertId"));
		personalCertDto = certificateAdminService
				.getPersonalCertById4Admin(personalCertId);
		certList = EssentialDataUtil
				.queryEssentialDataListByParentCode(personalCertDto
						.getCertTypeCode());
		cityList = RegionDataUtil.queryCityListByProvinceCode(personalCertDto
				.getProvinceCode());
		areaList = RegionDataUtil.queryAreaListByCityCode(personalCertDto
				.getCityCode());
		return "viewPersonalCertDetail4Admin";
	}

	/**
	 * @return the certificateAdminService
	 */
	public ICertificateAdminService getCertificateAdminService() {
		return certificateAdminService;
	}

	/**
	 * @param certificateAdminService
	 *            the certificateAdminService to set
	 */
	public void setCertificateAdminService(
			ICertificateAdminService certificateAdminService) {
		this.certificateAdminService = certificateAdminService;
	}

	/**
	 * @return the certificateService
	 */
	public ICertificateService getCertificateService() {
		return certificateService;
	}

	/**
	 * @param certificateService
	 *            the certificateService to set
	 */
	public void setCertificateService(ICertificateService certificateService) {
		this.certificateService = certificateService;
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
	 * @return the personalCertMap
	 */
	public Map<String, String> getPersonalCertMap() {
		return personalCertMap;
	}

	/**
	 * @param personalCertMap
	 *            the personalCertMap to set
	 */
	public void setPersonalCertMap(Map<String, String> personalCertMap) {
		this.personalCertMap = personalCertMap;
	}

	/**
	 * @return the personalCertDto
	 */
	public PersonalCertDto getPersonalCertDto() {
		return personalCertDto;
	}

	/**
	 * @param personalCertDto
	 *            the personalCertDto to set
	 */
	public void setPersonalCertDto(PersonalCertDto personalCertDto) {
		this.personalCertDto = personalCertDto;
	}

	/**
	 * @return the personalCertCheckBox
	 */
	public String[] getPersonalCertCheckBox() {
		return personalCertCheckBox;
	}

	/**
	 * @param personalCertCheckBox
	 *            the personalCertCheckBox to set
	 */
	public void setPersonalCertCheckBox(String[] personalCertCheckBox) {
		this.personalCertCheckBox = personalCertCheckBox;
	}

	/**
	 * @return the certificateTypeList
	 */
	public List<ClassMasterDto> getCertificateTypeList() {
		return certificateTypeList;
	}

	/**
	 * @return the specialFieldList
	 */
	public List<ClassMasterDto> getSpecialFieldList() {
		return specialFieldList;
	}

	/**
	 * @return the registerStatusList
	 */
	public List<ClassMasterDto> getRegisterStatusList() {
		return registerStatusList;
	}

	/**
	 * @return the personalCertList
	 */
	public List<PersonalCertDto> getPersonalCertList() {
		return personalCertList;
	}

	/**
	 * @return the certList
	 */
	public List<ClassMasterDto> getCertList() {
		return certList;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
}

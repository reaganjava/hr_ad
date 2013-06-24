package com.dotoyo.buildjob.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.dotoyo.buildjob.certificateCenter.dto.CertNeedsDto;
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
 * @dateCreated 2011-2-22
 * @description
 */
public class CertNeedsManageAction extends BuildJobAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9076759023905159768L;
	private ICertificateAdminService certificateAdminService;
	private ICertificateService certificateService;
	private List<CertNeedsDto> certNeedsList;
	private PageInfo pageInfo;
	private Map<String, String> certNeedsMap;
	private CertNeedsDto certNeedsDto;
	private List<ClassMasterDto> certList;// 证书列表(基础数据)
	private List<ClassMasterDto> certificateTypeList;// 证书类型列表
	private List<ClassMasterDto> specialFieldList;// 专业列表
	private List<ClassMasterDto> registerStatusList;// 注册状况列表
	private List<ClassMasterDto> companySizeList;// 单位规模
	private String[] certNeedsCheckBox;
	private String indeedStatus;// 是否急需证书
	private String isNew;

	public String loadEssentialData() {
		certificateTypeList = EssentialDataUtil
				.queryEssentialDataListByParentCode(ApplicationConstant.CERT_TYPE);
		specialFieldList = EssentialDataUtil
				.querySpecializeListLikeCode(ApplicationConstant.SPECIALIZE_TYPE);
		provinceList = RegionDataUtil.queryProvinceList();
		registerStatusList = EssentialDataUtil
				.queryEssentialDataListByParentCode(ApplicationConstant.REGISTER_STATUS);
		companySizeList = EssentialDataUtil
				.queryEssentialDataListByParentCode(ApplicationConstant.COMPANY_SIZE);
		return SUCCESS;
	}

	/**
	 * 查看所有证书需求列表
	 * 
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_HUNTCARD_REQUIREMENT_MANAGE)
	public String viewAllCertNeeds4Admin() {
		if (pageInfo == null) {
			pageInfo = new PageInfo();
		}
		pageInfo.setPageSize(Constants.CERTNEEDS_LIST_SIZE);
		if (certNeedsMap == null) {
			certNeedsMap = new HashMap<String, String>();
		}
		String currentDate = DateUtil.getCurrentDateString("yyyy-MM-dd");
		certNeedsMap.put("currentDate", currentDate);
		if("Y".equalsIgnoreCase(isNew)){
			certNeedsMap.put("isNew", "Y");
		}
		certNeedsList = certificateAdminService.queryCertNeedsList4Admin(
				pageInfo, certNeedsMap);
		return "viewAllCertNeeds4Admin";
	}

	/**
	 * 查看证书需求详细信息
	 * 
	 * @return
	 */
	public String viewCertNeedsDetail4Admin() {
		loadEssentialData();
		request = ServletActionContext.getRequest();
		Long certNeedsId = Long.parseLong(request.getParameter("certNeedsId"));
		certNeedsDto = certificateAdminService
				.getCertNeedsById4Admin(certNeedsId);
		certList = EssentialDataUtil
				.queryEssentialDataListByParentCode(certNeedsDto
						.getCertTypeCode());
		cityList = RegionDataUtil.queryCityListByProvinceCode(certNeedsDto
				.getProvinceCode());
		areaList = RegionDataUtil.queryAreaListByCityCode(certNeedsDto
				.getCityCode());
		return "viewCertNeedsDetail4Admin";
	}

	/**
	 * 批量删除证书需求信息
	 * 
	 * @return
	 */
	public String deleteCertNeeds() {
		String certNeedsIds = StringUtils.join(certNeedsCheckBox, ",");
		certificateService.deleteCertNeeds(certNeedsIds);
		HtmlGeneration htmlGeneration = HtmlGeneration.getInstance();
		htmlGeneration.refreshCertificateCenterPage();
		return viewAllCertNeeds4Admin();
	}

	public String editIndeedStatus() {
		String certNeedsIds = StringUtils.join(certNeedsCheckBox, ",");
		certNeedsMap.put("certNeedsIds", certNeedsIds);
		certNeedsMap.put("indeedStatus", indeedStatus);
		certificateAdminService.editIndeedStatus(certNeedsMap);
		HtmlGeneration htmlGeneration = HtmlGeneration.getInstance();
		htmlGeneration.refreshCertificateCenterPage();
		return viewAllCertNeeds4Admin();
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
	 * @return the certNeedsList
	 */
	public List<CertNeedsDto> getCertNeedsList() {
		return certNeedsList;
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
	 * @return the certNeedsMap
	 */
	public Map<String, String> getCertNeedsMap() {
		return certNeedsMap;
	}

	/**
	 * @param certNeedsMap
	 *            the certNeedsMap to set
	 */
	public void setCertNeedsMap(Map<String, String> certNeedsMap) {
		this.certNeedsMap = certNeedsMap;
	}

	/**
	 * @return the certNeedsDto
	 */
	public CertNeedsDto getCertNeedsDto() {
		return certNeedsDto;
	}

	/**
	 * @param certNeedsDto
	 *            the certNeedsDto to set
	 */
	public void setCertNeedsDto(CertNeedsDto certNeedsDto) {
		this.certNeedsDto = certNeedsDto;
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
	 * @return the certList
	 */
	public List<ClassMasterDto> getCertList() {
		return certList;
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
	 * @return the companySizeList
	 */
	public List<ClassMasterDto> getCompanySizeList() {
		return companySizeList;
	}

	/**
	 * @return the certNeedsCheckBox
	 */
	public String[] getCertNeedsCheckBox() {
		return certNeedsCheckBox;
	}

	/**
	 * @param certNeedsCheckBox
	 *            the certNeedsCheckBox to set
	 */
	public void setCertNeedsCheckBox(String[] certNeedsCheckBox) {
		this.certNeedsCheckBox = certNeedsCheckBox;
	}

	/**
	 * @return the indeedStatus
	 */
	public String getIndeedStatus() {
		return indeedStatus;
	}

	/**
	 * @param indeedStatus
	 *            the indeedStatus to set
	 */
	public void setIndeedStatus(String indeedStatus) {
		this.indeedStatus = indeedStatus;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
}

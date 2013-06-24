package com.dotoyo.buildjob.action;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.EssentialDataUtil;
import com.dotoyo.buildjob.sys.dto.SysIncrementAdvertSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementHangCardSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementHuntCardSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementRecruitSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementTalentsFoundSettingDto;
import com.dotoyo.buildjob.sys.dto.SysOrderDto;
import com.dotoyo.buildjob.sys.service.ISysIncrementAdvertSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementHangCardSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementHuntCardSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementRecruitSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementServiceNameMaintainanceService;
import com.dotoyo.buildjob.sys.service.ISysIncrementTalentsFoundSettingService;
import com.dotoyo.buildjob.sys.service.ISysOrderService;
import com.dotoyo.buildjob.systemManage.dto.ClassMasterDto;
import com.dotoyo.buildjob.vo.SysIncrementServiceVo;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-26
 * @description 增值服务管理
 */
public class SysIncrementManageAction extends SysAction {
	private Map<String,String> serviceNames; //增值服务种类
	private SysOrderDto dto;
	private SysOrderDto searchdto;
	private List<SysOrderDto> dtoList;
	private String msg;
	private ISysOrderService sysOrderService;
	private PageInfo pageInfo;
	private Map<String,String> advertisings;
	private Map<String,String> fieldsOfSearchs;

	/** 服务名称服务 **/
	private ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService;
	/** 人才挖掘服务 **/
	private ISysIncrementTalentsFoundSettingService sysIncrementTalentsFoundSettingService;
	/** 挂证服务 **/
	private ISysIncrementHangCardSettingService sysIncrementHangCardSettingService;
	/** 猎证服务 **/
	private ISysIncrementHuntCardSettingService sysIncrementHuntCardSettingService;
	/** 人才招聘服务 **/
	private ISysIncrementRecruitSettingService sysIncrementRecruitSettingService;
	/** 广告服务 **/
	private ISysIncrementAdvertSettingService sysIncrementAdvertSettingService;

	private SysIncrementServiceVo incrementServiceVo;

	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_RECRUIMENT_SERVICE_MANAGE)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysOrderDto();

		//初始化数据 - 初始化服务名称
		serviceNames = new TreeMap<String,String>();
		List<SysIncrementServiceNameMaintainanceDto> serviceList = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceNameList(null);
		if(serviceList != null){
			serviceNames.put("", "-- 选择 --");
			for(SysIncrementServiceNameMaintainanceDto tmp : serviceList){
				serviceNames.put(tmp.getServiceCode().trim(), tmp.getServiceName().trim());
			}
		}
		pageInfo = new PageInfo();
		//查询数据
		searchData();
		return Constants.RTN_SYS_MAIN;
	}

	/**
	 * On click view button
	 * @return
	 */
	public String beforeView(){
		//清空消息
		clear();
		//查询
		/** 服务信息 **/
		incrementServiceVo = new SysIncrementServiceVo();
		SysIncrementServiceNameMaintainanceDto serviceDto = new SysIncrementServiceNameMaintainanceDto();
		serviceDto.setServiceCode(dto.getServiceCode());
		serviceDto = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceName(serviceDto);
		incrementServiceVo.setServiceNameMaintainanceDto(serviceDto);
		//switch (Integer.valueOf(dto.getItemCode())) {
		//人才挖掘
		//case 1:
			/** 人才挖掘服务信息 **/
			SysIncrementTalentsFoundSettingDto talentsDto = new SysIncrementTalentsFoundSettingDto();
			talentsDto.setServiceCode(dto.getServiceCode());
			talentsDto = sysIncrementTalentsFoundSettingService.queryIncrementTalentsFound(talentsDto);
			incrementServiceVo.setTalentsFoundSettingDto(talentsDto);
			if(talentsDto != null){
				if(fieldsOfSearchs == null){
					//初始化字段设置
					fieldsOfSearchs = new TreeMap<String,String>();
					List<ClassMasterDto> mastlist = EssentialDataUtil.queryEssentialDataListByParentCode(Constants.SYS_INCREMENT_TALENTS_SEARCH_FIELD);
					if(mastlist != null){
						for(ClassMasterDto tmp : mastlist){
							if(!tmp.getCode().equals(Constants.SYS_INCREMENT_TALENTS_SEARCH_FIELD)){
								fieldsOfSearchs.put(tmp.getCode().trim(), tmp.getName().trim());
							}
						}
					}
				}
				String []fields = talentsDto.getFieldsOfSearch().split(",");
				String tmpStr = "";
				for(String field : fields){
					tmpStr += fieldsOfSearchs.get(field.trim()) + "，";
				}
				talentsDto.setFieldsOfSearchName(tmpStr.substring(0,tmpStr.length()-1));
			}
			//break;
			//挂证服务
		//case 2:
			/** 挂证服务信息 **/
			SysIncrementHangCardSettingDto hangDto = new SysIncrementHangCardSettingDto();
			hangDto.setServiceCode(dto.getServiceCode());
			hangDto = sysIncrementHangCardSettingService.queryIncrementHangCard(hangDto);
			incrementServiceVo.setHangCardSettingDto(hangDto);
			//break;
			//猎证服务
		//case 3:
			/** 猎证服务信息 **/
			SysIncrementHuntCardSettingDto huntDto = new SysIncrementHuntCardSettingDto();
			huntDto.setServiceCode(dto.getServiceCode());
			huntDto = sysIncrementHuntCardSettingService.queryIncrementHuntCard(huntDto);
			incrementServiceVo.setHuntCardSettingDto(huntDto);
			//break;
			//招聘服务
		//case 4:
			/** 招聘服务信息 **/
			SysIncrementRecruitSettingDto recruitDto = new SysIncrementRecruitSettingDto();
			recruitDto.setServiceCode(dto.getServiceCode());
			recruitDto = sysIncrementRecruitSettingService.queryIncrementRecruit(recruitDto);
			incrementServiceVo.setRecruitSettingDto(recruitDto);
			//break;
			//广告位服务
		//case 5:
			/** 广告位服务信息 **/
			SysIncrementAdvertSettingDto advertDto = new SysIncrementAdvertSettingDto();
			advertDto.setServiceCode(dto.getServiceCode());
			advertDto = sysIncrementAdvertSettingService.queryIncrementAdvert(advertDto);
			incrementServiceVo.setAdvertSettingDto(advertDto);
			if(advertDto != null){
				//初始化字段设置
				if(advertisings == null){
					advertisings = new TreeMap<String,String>();
					//List<ClassMasterDto> mastlist = EssentialDataUtil.queryEssentialDataListByParentCode(Constants.SYS_INCREMENT_ADVERSITING_SEARCH_POSITION);
					List<ClassMasterDto> mastlist = EssentialDataUtil.querySpecializeListLikeCode(Constants.SYS_INCREMENT_ADVERSITING_SEARCH_POSITION + "-");
					if(mastlist != null){
						for(ClassMasterDto tmp : mastlist){
							if(!tmp.getCode().equals(Constants.SYS_INCREMENT_ADVERSITING_SEARCH_POSITION)){
								advertisings.put(tmp.getCode().trim(), tmp.getName().trim());
							}
						}
					}
				}
				String []fields = advertDto.getAdvertising().split(",");
				String tmpStr = "";
				for(String field : fields){
					tmpStr += advertisings.get(field.trim()) + "，";
				}
				advertDto.setAdvertisingName(tmpStr.substring(0,tmpStr.length()-1));
			}
			//break;
		//default:
			//break;
		//}
		return Constants.RTN_SYS_VIEW;
	}
	/**
	 * Search records by service name
	 * @return
	 */
	public String search(){
		searchData();
		return Constants.RTN_SYS_MAIN;
	}


	/**
	 * On click cancel button
	 * @return
	 */
	public String cancel(){
		clear();
		return Constants.RTN_SYS_MAIN;
	}
	private void clear(){
		msg = "";
	}

	/**
	 * 查询数据列表
	 */
	private void searchData(){
		//查询数据
		if(this.pageInfo == null){
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		this.dtoList = sysOrderService.queryOrderList(this.pageInfo, searchdto);
		for(int i = 0;i < dtoList.size();i++){
			switch (Integer.valueOf(dtoList.get(i).getItemCode())) {
			//人才挖掘
			case 1:
				dtoList.get(i).setItemName(ApplicationConstant.SERVICE_ITEM_NAME_TALENTSFOUND);
				break;
				//挂证服务
			case 2:
				dtoList.get(i).setItemName(ApplicationConstant.SERVICE_ITEM_NAME_HANGCARD);
				break;
				//猎证服务
			case 3:
				dtoList.get(i).setItemName(ApplicationConstant.SERVICE_ITEM_NAME_HUNTCARD);
				break;
				//招聘服务
			case 4:
				dtoList.get(i).setItemName(ApplicationConstant.SERVICE_ITEM_NAME_RECRUIT);
				break;
				//广告位服务
			case 5:
				dtoList.get(i).setItemName(ApplicationConstant.SERVICE_ITEM_NAME_ADVERT);
				break;
			default:
				break;
			}
		}
	}

	public Map<String, String> getServiceNames() {
		return serviceNames;
	}

	public void setServiceNames(Map<String, String> serviceNames) {
		this.serviceNames = serviceNames;
	}

	public SysOrderDto getDto() {
		return dto;
	}

	public void setDto(SysOrderDto dto) {
		this.dto = dto;
	}

	public SysOrderDto getSearchdto() {
		return searchdto;
	}

	public void setSearchdto(SysOrderDto searchdto) {
		this.searchdto = searchdto;
	}

	public List<SysOrderDto> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<SysOrderDto> dtoList) {
		this.dtoList = dtoList;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ISysOrderService getSysOrderService() {
		return sysOrderService;
	}

	public void setSysOrderService(ISysOrderService sysOrderService) {
		this.sysOrderService = sysOrderService;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public ISysIncrementServiceNameMaintainanceService getSysIncrementServiceNameMaintainanceService() {
		return sysIncrementServiceNameMaintainanceService;
	}

	public void setSysIncrementServiceNameMaintainanceService(
			ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService) {
		this.sysIncrementServiceNameMaintainanceService = sysIncrementServiceNameMaintainanceService;
	}

	public ISysIncrementTalentsFoundSettingService getSysIncrementTalentsFoundSettingService() {
		return sysIncrementTalentsFoundSettingService;
	}

	public void setSysIncrementTalentsFoundSettingService(
			ISysIncrementTalentsFoundSettingService sysIncrementTalentsFoundSettingService) {
		this.sysIncrementTalentsFoundSettingService = sysIncrementTalentsFoundSettingService;
	}

	public ISysIncrementHangCardSettingService getSysIncrementHangCardSettingService() {
		return sysIncrementHangCardSettingService;
	}

	public void setSysIncrementHangCardSettingService(
			ISysIncrementHangCardSettingService sysIncrementHangCardSettingService) {
		this.sysIncrementHangCardSettingService = sysIncrementHangCardSettingService;
	}

	public ISysIncrementHuntCardSettingService getSysIncrementHuntCardSettingService() {
		return sysIncrementHuntCardSettingService;
	}

	public void setSysIncrementHuntCardSettingService(
			ISysIncrementHuntCardSettingService sysIncrementHuntCardSettingService) {
		this.sysIncrementHuntCardSettingService = sysIncrementHuntCardSettingService;
	}

	public ISysIncrementRecruitSettingService getSysIncrementRecruitSettingService() {
		return sysIncrementRecruitSettingService;
	}

	public void setSysIncrementRecruitSettingService(
			ISysIncrementRecruitSettingService sysIncrementRecruitSettingService) {
		this.sysIncrementRecruitSettingService = sysIncrementRecruitSettingService;
	}

	public ISysIncrementAdvertSettingService getSysIncrementAdvertSettingService() {
		return sysIncrementAdvertSettingService;
	}

	public void setSysIncrementAdvertSettingService(
			ISysIncrementAdvertSettingService sysIncrementAdvertSettingService) {
		this.sysIncrementAdvertSettingService = sysIncrementAdvertSettingService;
	}

	public SysIncrementServiceVo getIncrementServiceVo() {
		return incrementServiceVo;
	}

	public void setIncrementServiceVo(SysIncrementServiceVo incrementServiceVo) {
		this.incrementServiceVo = incrementServiceVo;
	}

	public Map<String, String> getAdvertisings() {
		return advertisings;
	}

	public void setAdvertisings(Map<String, String> advertisings) {
		this.advertisings = advertisings;
	}



}

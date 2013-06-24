package com.dotoyo.buildjob.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.EssentialDataUtil;
import com.dotoyo.buildjob.common.util.StringUtil;
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

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-11
 * @description 增值服务人才挖掘字段设置
 */
public class SysIncrementTalentsFoundSettingAction extends SysAction {
	private Map<String,String> serviceNames;
	private Map<String,String> fieldsOfSearchs;
	private SysIncrementTalentsFoundSettingDto dto;
	private SysIncrementTalentsFoundSettingDto searchdto;
	private List<SysIncrementTalentsFoundSettingDto> dtoList;
	private String serviceNameMsg;
	private String serviceSearchFiledsMsg;
	private String serviceTimesOfSearchMsg;
	private String priceMsg;
	private String msg;
	private ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService;
	private ISysIncrementAdvertSettingService sysIncrementAdvertSettingService;
	private ISysIncrementHangCardSettingService sysIncrementHangCardSettingService;
	private ISysIncrementHuntCardSettingService sysIncrementHuntCardSettingService;
	private ISysIncrementRecruitSettingService sysIncrementRecruitSettingService;
	private ISysIncrementTalentsFoundSettingService sysIncrementTalentsFoundSettingService;
	private ISysOrderService sysOrderService;
	private PageInfo pageInfo;
	private String dto_fieldsOfSearch; //人才搜索字段
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_TALENTS)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysIncrementTalentsFoundSettingDto();
		//初始化数据 - 初始化服务名称
		serviceNames = new TreeMap<String,String>();
		List<SysIncrementServiceNameMaintainanceDto> serviceList = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceNameList(null);
		if(serviceList != null){
			serviceNames.put("", "-- 选择 --");
			for(SysIncrementServiceNameMaintainanceDto tmp : serviceList){
				//check service code is existed in other service setting.
//				SysIncrementAdvertSettingDto advertSettingDto = new SysIncrementAdvertSettingDto();
//				advertSettingDto.setServiceCode(tmp.getServiceCode());
//				advertSettingDto = sysIncrementAdvertSettingService.queryIncrementAdvert(advertSettingDto);
//
//				SysIncrementHangCardSettingDto hangCardSettingDto = new SysIncrementHangCardSettingDto();
//				hangCardSettingDto.setServiceCode(tmp.getServiceCode());
//				hangCardSettingDto = sysIncrementHangCardSettingService.queryIncrementHangCard(hangCardSettingDto);
//
//				SysIncrementHuntCardSettingDto huntCardSettingDto = new SysIncrementHuntCardSettingDto();
//				huntCardSettingDto.setServiceCode(tmp.getServiceCode());
//				huntCardSettingDto = sysIncrementHuntCardSettingService.queryIncrementHuntCard(huntCardSettingDto);;
//
//				SysIncrementRecruitSettingDto recruitSettingDto = new SysIncrementRecruitSettingDto();
//				recruitSettingDto.setServiceCode(tmp.getServiceCode());
//				recruitSettingDto = sysIncrementRecruitSettingService.queryIncrementRecruit(recruitSettingDto);;
//
//				if(advertSettingDto == null && hangCardSettingDto == null && huntCardSettingDto == null && recruitSettingDto == null){
				if (ApplicationConstant.USER_TYPE_ENTERPRISE
						.equalsIgnoreCase(tmp.getServiceType())) {
					serviceNames.put(tmp.getServiceCode().trim(), tmp
							.getServiceName().trim());
				}
			//	}
			}
		}
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
		//查询数据
		pageInfo = new PageInfo();
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * On click add button
	 * @return
	 */
	public String beforeAdd(){
		//清空对象
		dto = new SysIncrementTalentsFoundSettingDto();

		clear();
		return Constants.RTN_SYS_ADD;
	}
	/**
	 * On click edit button
	 * @return
	 */
	public String beforeEdit(){
		//清空消息
		clear();
		dto_fieldsOfSearch = dto.getFieldsOfSearch();
		return Constants.RTN_SYS_EDIT;
	}
	/**
	 * Search records by service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_TALENTS)
	public String search(){
		clear();
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Add a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_TALENTS)
	public String add(){
		try{
			if(validateInput() && !checkIsExisted()){
				Date operateDate = new Date();
				dto.setLastEditDate(operateDate);
				dto.setOperator(getCurrentSessionUser().getId());
				dto.setSubmitDate(operateDate);
				
				//插入数据
				sysIncrementTalentsFoundSettingService.addIncrementTalentsFoundSetting(dto);
				//查询数据
				searchData();
				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_ADD;
			}
		}catch(Exception e){
			serviceNameMsg = "添加失败!";
			return Constants.RTN_SYS_ADD;
		}
	}
	/**
	 * Edit a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_TALENTS)
	public String edit(){
		try{
			if(validateInput()){
				dto.setLastEditDate(new Date());
				dto.setOperator(getCurrentSessionUser().getId());
				
				//修改数据
				sysIncrementTalentsFoundSettingService.updateIncrementTalentsFoundSetting(dto);
				//查询所有数据
				searchData();
				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_EDIT;
			}
		}catch(Exception e){
			serviceNameMsg = "修改失败!";
			e.printStackTrace();
			return Constants.RTN_SYS_EDIT;
		}
	}
	/**
	 * Delete a record
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_TALENTS)
	public String remove(){

		if(!StringUtil.newInstance().sNull(dto.getServiceCode()).equals("")){
			//检查订单中是否用到
			SysOrderDto orderDto = new SysOrderDto();
			orderDto.setServiceCode(dto.getServiceCode());
			orderDto.setItemCode(ApplicationConstant.SERVICE_ITEM_NAME_TALENTSFOUND_CODE);
			int counts = sysOrderService.getOrdersCount(orderDto);
			if(counts > 0){
				msg = "服务在订单中被使用,不能删除！";
				return Constants.RTN_SYS_MAIN;
			}
			//** remove records **//
			sysIncrementTalentsFoundSettingService.removeIncrementTalentsFoundSetting(dto);
			//查询数据
			searchData();
			clear();
		}
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
		serviceNameMsg = "";
		serviceSearchFiledsMsg = "";
		serviceTimesOfSearchMsg = "";
		priceMsg = "";
		msg = "";
	}
	/**
	 * 检查服务名称是否已经存在
	 * @return
	 */
	private boolean checkIsExisted(){
		SysIncrementTalentsFoundSettingDto outDTO = sysIncrementTalentsFoundSettingService.queryIncrementTalentsFound(dto);
		if(outDTO == null){
			return false;
		}else{
			serviceNameMsg = "选中的服务名称设置已经存在，请重新选择!";
			return true;
		}
	}
	/**
	 * validate page form fields.
	 * @return
	 */
	private boolean validateInput(){
		StringUtil util = StringUtil.newInstance();
		clear();
		dto.setFieldsOfSearch(dto_fieldsOfSearch);
		if(util.sNullTrim(dto.getServiceCode()).equals("")){
			serviceNameMsg = "服务名称不能为空!";
		}
		if(util.sNullTrim(dto.getFieldsOfSearch()).equals("")){
			serviceSearchFiledsMsg = "人才搜索的字段设置不能为空";
		}
		if(util.sNullTrim(dto.getTimesOfSearch()).equals("")){
			serviceTimesOfSearchMsg = "可搜索的次数不能为空";
		}
		if(util.sNullTrim(dto.getTimesOfSearch()).length() > 11){
			serviceTimesOfSearchMsg = "可搜索的次数超过限制位数[11]";
		}
		if(serviceNameMsg.equals("") && serviceTimesOfSearchMsg.equals("") && serviceSearchFiledsMsg.equals("")){
			return true;
		}
		return false;
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
		this.dtoList = sysIncrementTalentsFoundSettingService.queryIncrementTalentsFoundList(this.pageInfo, searchdto);
		//转换查询字段代码到名称
		if(fieldsOfSearchs != null && fieldsOfSearchs.size() > 0 && dtoList != null){
			for(int i = 0;i < dtoList.size();i++){
				SysIncrementTalentsFoundSettingDto tmp = dtoList.get(i);
				String []fields = tmp.getFieldsOfSearch().split(",");
				String tmpStr = "";
				for(String field : fields){
					tmpStr += fieldsOfSearchs.get(field.trim()) + "，";
				}
				dtoList.get(i).setFieldsOfSearchName(tmpStr.substring(0,tmpStr.length()-1));
			}
		}
	}
	public SysIncrementTalentsFoundSettingDto getDto() {
		return dto;
	}
	public void setDto(SysIncrementTalentsFoundSettingDto dto) {
		this.dto = dto;
	}
	public SysIncrementTalentsFoundSettingDto getSearchdto() {
		return searchdto;
	}
	public void setSearchdto(SysIncrementTalentsFoundSettingDto searchdto) {
		this.searchdto = searchdto;
	}
	public List<SysIncrementTalentsFoundSettingDto> getDtoList() {
		return dtoList;
	}
	public void setDtoList(List<SysIncrementTalentsFoundSettingDto> dtoList) {
		this.dtoList = dtoList;
	}
	public String getServiceNameMsg() {
		return serviceNameMsg;
	}
	public void setServiceNameMsg(String serviceNameMsg) {
		this.serviceNameMsg = serviceNameMsg;
	}
	public String getServiceSearchFiledsMsg() {
		return serviceSearchFiledsMsg;
	}
	public void setServiceSearchFiledsMsg(String serviceSearchFiledsMsg) {
		this.serviceSearchFiledsMsg = serviceSearchFiledsMsg;
	}
	public String getServiceTimesOfSearchMsg() {
		return serviceTimesOfSearchMsg;
	}
	public void setServiceTimesOfSearchMsg(String serviceTimesOfSearchMsg) {
		this.serviceTimesOfSearchMsg = serviceTimesOfSearchMsg;
	}
	public String getPriceMsg() {
		return priceMsg;
	}
	public void setPriceMsg(String priceMsg) {
		this.priceMsg = priceMsg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ISysIncrementTalentsFoundSettingService getSysIncrementTalentsFoundSettingService() {
		return sysIncrementTalentsFoundSettingService;
	}
	public void setSysIncrementTalentsFoundSettingService(
			ISysIncrementTalentsFoundSettingService sysIncrementTalentsFoundSettingService) {
		this.sysIncrementTalentsFoundSettingService = sysIncrementTalentsFoundSettingService;
	}
	public Map<String, String> getServiceNames() {
		return serviceNames;
	}
	public void setServiceNames(Map<String, String> serviceNames) {
		this.serviceNames = serviceNames;
	}
	public ISysIncrementServiceNameMaintainanceService getSysIncrementServiceNameMaintainanceService() {
		return sysIncrementServiceNameMaintainanceService;
	}
	public void setSysIncrementServiceNameMaintainanceService(
			ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService) {
		this.sysIncrementServiceNameMaintainanceService = sysIncrementServiceNameMaintainanceService;
	}
	public Map<String, String> getFieldsOfSearchs() {
		return fieldsOfSearchs;
	}
	public void setFieldsOfSearchs(Map<String, String> fieldsOfSearchs) {
		this.fieldsOfSearchs = fieldsOfSearchs;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public ISysIncrementAdvertSettingService getSysIncrementAdvertSettingService() {
		return sysIncrementAdvertSettingService;
	}
	public void setSysIncrementAdvertSettingService(
			ISysIncrementAdvertSettingService sysIncrementAdvertSettingService) {
		this.sysIncrementAdvertSettingService = sysIncrementAdvertSettingService;
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
	public ISysOrderService getSysOrderService() {
		return sysOrderService;
	}
	public void setSysOrderService(ISysOrderService sysOrderService) {
		this.sysOrderService = sysOrderService;
	}
	public String getDto_fieldsOfSearch() {
		return dto_fieldsOfSearch;
	}
	public void setDto_fieldsOfSearch(String dtoFieldsOfSearch) {
		dto_fieldsOfSearch = dtoFieldsOfSearch;
	}

}

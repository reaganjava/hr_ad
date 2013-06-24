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
import com.dotoyo.buildjob.sys.dto.SysIncrementAdvertSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
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
 * @description 增值服务广告位服务设置
 */
public class SysIncrementAdvertSettingAction extends SysAction {
	private Map<String,String> serviceNames;
	private Map<String,String> advertisings;
	private SysIncrementAdvertSettingDto dto;
	private SysIncrementAdvertSettingDto searchdto;
	private List<SysIncrementAdvertSettingDto> dtoList;
	private String serviceNameMsg;
	private String serviceAdvertisingMsg;
	private String priceMsg;
	private String msg;
	private ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService;
	private ISysIncrementAdvertSettingService sysIncrementAdvertSettingService;
	private ISysIncrementHangCardSettingService sysIncrementHangCardSettingService;
	private ISysIncrementHuntCardSettingService sysIncrementHuntCardSettingService;
	private ISysIncrementRecruitSettingService sysIncrementRecruitSettingService;
	private ISysIncrementTalentsFoundSettingService sysIncrementTalentsFoundSettingService;
	private ISysOrderService sysOrderService;
	private String dto_advertising;
	private PageInfo pageInfo = new PageInfo();
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_ADVERT)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysIncrementAdvertSettingDto();
		//初始化数据 - 初始化服务名称
		serviceNames = new TreeMap<String,String>();
		List<SysIncrementServiceNameMaintainanceDto> serviceList = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceNameList(null);
		if(serviceList != null){
			serviceNames.put("", "-- 选择 --");
			for(SysIncrementServiceNameMaintainanceDto tmp : serviceList){
				//check service code is existed in other service setting.
//				SysIncrementHangCardSettingDto hangCardSettingDto = new SysIncrementHangCardSettingDto();
//				hangCardSettingDto.setServiceCode(tmp.getServiceCode());
//				hangCardSettingDto = sysIncrementHangCardSettingService.queryIncrementHangCard(hangCardSettingDto);
//
//				SysIncrementHuntCardSettingDto huntCardSettingDto = new SysIncrementHuntCardSettingDto();
//				huntCardSettingDto = sysIncrementHuntCardSettingService.queryIncrementHuntCard(huntCardSettingDto);;
//
//				SysIncrementRecruitSettingDto recruitSettingDto = new SysIncrementRecruitSettingDto();
//				recruitSettingDto.setServiceCode(tmp.getServiceCode());
//				recruitSettingDto = sysIncrementRecruitSettingService.queryIncrementRecruit(recruitSettingDto);;
//
//				SysIncrementTalentsFoundSettingDto talentsSettingDto = new SysIncrementTalentsFoundSettingDto();
//				talentsSettingDto.setServiceCode(tmp.getServiceCode());
//				talentsSettingDto = sysIncrementTalentsFoundSettingService.queryIncrementTalentsFound(talentsSettingDto);;

				//if(hangCardSettingDto == null && huntCardSettingDto == null && recruitSettingDto == null && talentsSettingDto == null){
					serviceNames.put(tmp.getServiceCode().trim(), tmp.getServiceName().trim());
				//}
			}
		}
		//初始化字段设置
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
		pageInfo = new PageInfo();
		//查询数据
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * On click add button
	 * @return
	 */
	public String beforeAdd(){
		//清空对象
		dto = new SysIncrementAdvertSettingDto();

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
		dto_advertising = dto.getAdvertising();
		return Constants.RTN_SYS_EDIT;
	}
	/**
	 * Search records by service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_ADVERT)
	public String search(){
		clear();
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Add a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_ADVERT)
	public String add(){
		try{
			if(validateInput() && !checkIsExisted()){
				Date operateDate = new Date();
				dto.setLastEditDate(operateDate);
				dto.setOperator(getCurrentSessionUser().getId());
				dto.setSubmitDate(operateDate);
				
				//插入数据
				sysIncrementAdvertSettingService.addIncrementAdvertSetting(dto);
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
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_ADVERT)
	public String edit(){
		try{
			if(validateInput()){
				dto.setLastEditDate(new Date());
				dto.setOperator(getCurrentSessionUser().getId());
				
				//修改数据
				sysIncrementAdvertSettingService.updateIncrementAdvertSetting(dto);
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
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_ADVERT)
	public String remove(){
		if(!StringUtil.newInstance().sNull(dto.getServiceCode()).equals("")){
			//检查订单中是否用到
			SysOrderDto orderDto = new SysOrderDto();
			orderDto.setServiceCode(dto.getServiceCode());
			orderDto.setItemCode(ApplicationConstant.SERVICE_ITEM_NAME_ADVERT_CODE);
			int counts = sysOrderService.getOrdersCount(orderDto);
			if(counts > 0){
				msg = "服务在订单中被使用,不能删除！";
				return Constants.RTN_SYS_MAIN;
			}
				//** remove records **//
			sysIncrementAdvertSettingService.removeIncrementAdvertSetting(dto);
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
		serviceAdvertisingMsg = "";
		priceMsg = "";
		msg = "";
	}
	/**
	 * 检查服务名称是否已经存在
	 * @return
	 */
	private boolean checkIsExisted(){
		SysIncrementAdvertSettingDto outDTO = sysIncrementAdvertSettingService.queryIncrementAdvert(dto);
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
		dto.setAdvertising(dto_advertising);
		if(util.sNullTrim(dto.getServiceCode()).equals("")){
			serviceNameMsg = "服务名称不能为空!";
		}
		if(util.sNullTrim(dto.getAdvertising()).equals("")){
			serviceAdvertisingMsg = "广告位设置不能为空";
		}
		if(serviceNameMsg.equals("") && serviceAdvertisingMsg.equals("") ){
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
		this.dtoList = sysIncrementAdvertSettingService.queryIncrementAdvertList(this.pageInfo, searchdto);

		//转换查询字段代码到名称
		if(advertisings != null && advertisings.size() > 0 && dtoList != null){
			for(int i = 0;i < dtoList.size();i++){
				SysIncrementAdvertSettingDto tmp = dtoList.get(i);
				String []fields = tmp.getAdvertising().split(",");
				String tmpStr = "";
				for(String field : fields){
					tmpStr += advertisings.get(field.trim()) + "，";
				}
				dtoList.get(i).setAdvertisingName(tmpStr.substring(0,tmpStr.length()-1));
			}
		}
	}
	public Map<String, String> getServiceNames() {
		return serviceNames;
	}
	public void setServiceNames(Map<String, String> serviceNames) {
		this.serviceNames = serviceNames;
	}
	public Map<String, String> getAdvertisings() {
		return advertisings;
	}
	public void setAdvertisings(Map<String, String> advertisings) {
		this.advertisings = advertisings;
	}
	public SysIncrementAdvertSettingDto getDto() {
		return dto;
	}
	public void setDto(SysIncrementAdvertSettingDto dto) {
		this.dto = dto;
	}
	public SysIncrementAdvertSettingDto getSearchdto() {
		return searchdto;
	}
	public void setSearchdto(SysIncrementAdvertSettingDto searchdto) {
		this.searchdto = searchdto;
	}
	public List<SysIncrementAdvertSettingDto> getDtoList() {
		return dtoList;
	}
	public void setDtoList(List<SysIncrementAdvertSettingDto> dtoList) {
		this.dtoList = dtoList;
	}
	public String getServiceNameMsg() {
		return serviceNameMsg;
	}
	public void setServiceNameMsg(String serviceNameMsg) {
		this.serviceNameMsg = serviceNameMsg;
	}
	public String getServiceAdvertisingMsg() {
		return serviceAdvertisingMsg;
	}
	public void setServiceAdvertisingMsg(String serviceAdvertisingMsg) {
		this.serviceAdvertisingMsg = serviceAdvertisingMsg;
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
	public ISysIncrementAdvertSettingService getSysIncrementAdvertSettingService() {
		return sysIncrementAdvertSettingService;
	}
	public void setSysIncrementAdvertSettingService(
			ISysIncrementAdvertSettingService sysIncrementAdvertSettingService) {
		this.sysIncrementAdvertSettingService = sysIncrementAdvertSettingService;
	}
	public ISysIncrementServiceNameMaintainanceService getSysIncrementServiceNameMaintainanceService() {
		return sysIncrementServiceNameMaintainanceService;
	}
	public void setSysIncrementServiceNameMaintainanceService(
			ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService) {
		this.sysIncrementServiceNameMaintainanceService = sysIncrementServiceNameMaintainanceService;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
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
	public ISysIncrementTalentsFoundSettingService getSysIncrementTalentsFoundSettingService() {
		return sysIncrementTalentsFoundSettingService;
	}
	public void setSysIncrementTalentsFoundSettingService(
			ISysIncrementTalentsFoundSettingService sysIncrementTalentsFoundSettingService) {
		this.sysIncrementTalentsFoundSettingService = sysIncrementTalentsFoundSettingService;
	}
	public ISysOrderService getSysOrderService() {
		return sysOrderService;
	}
	public void setSysOrderService(ISysOrderService sysOrderService) {
		this.sysOrderService = sysOrderService;
	}
	public String getDto_advertising() {
		return dto_advertising;
	}
	public void setDto_advertising(String dtoAdvertising) {
		dto_advertising = dtoAdvertising;
	}


}

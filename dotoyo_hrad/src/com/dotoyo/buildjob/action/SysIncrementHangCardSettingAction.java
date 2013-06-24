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
import com.dotoyo.buildjob.common.util.StringUtil;
import com.dotoyo.buildjob.sys.dto.SysIncrementHangCardSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.dto.SysOrderDto;
import com.dotoyo.buildjob.sys.service.ISysIncrementAdvertSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementHangCardSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementHuntCardSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementRecruitSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementServiceNameMaintainanceService;
import com.dotoyo.buildjob.sys.service.ISysIncrementTalentsFoundSettingService;
import com.dotoyo.buildjob.sys.service.ISysOrderService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-11
 * @description 增值服务挂证服务设置
 */
public class SysIncrementHangCardSettingAction extends SysAction {
	private Map<String,String> serviceNames;
	private SysIncrementHangCardSettingDto dto;
	private SysIncrementHangCardSettingDto searchdto;
	private List<SysIncrementHangCardSettingDto> dtoList;
	private String serviceNameMsg;
	private String countOfSubmitMsg;
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
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_HANGCARD)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysIncrementHangCardSettingDto();
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
//				SysIncrementHuntCardSettingDto huntCardSettingDto = new SysIncrementHuntCardSettingDto();
//				huntCardSettingDto.setServiceCode(tmp.getServiceCode());
//				huntCardSettingDto = sysIncrementHuntCardSettingService.queryIncrementHuntCard(huntCardSettingDto);;
//
//				SysIncrementRecruitSettingDto recruitSettingDto = new SysIncrementRecruitSettingDto();
//				recruitSettingDto.setServiceCode(tmp.getServiceCode());
//				recruitSettingDto = sysIncrementRecruitSettingService.queryIncrementRecruit(recruitSettingDto);;
//
//				SysIncrementTalentsFoundSettingDto talentsSettingDto = new SysIncrementTalentsFoundSettingDto();
//				talentsSettingDto.setServiceCode(tmp.getServiceCode());
//				talentsSettingDto = sysIncrementTalentsFoundSettingService.queryIncrementTalentsFound(talentsSettingDto);;
//
//				if(advertSettingDto == null && huntCardSettingDto == null && recruitSettingDto == null && talentsSettingDto == null){
				if(ApplicationConstant.USER_TYPE_PERSONAL.equalsIgnoreCase(tmp.getServiceType())){
					serviceNames.put(tmp.getServiceCode().trim(), tmp.getServiceName().trim());
				}
			//	}
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
		dto = new SysIncrementHangCardSettingDto();

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
		return Constants.RTN_SYS_EDIT;
	}
	/**
	 * Search records by service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_HANGCARD)
	public String search(){
		clear();
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Add a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_HANGCARD)
	public String add(){
		try{
			if(validateInput() && !checkIsExisted()){
				Date operateDate = new Date();
				dto.setLastEditDate(operateDate);
				dto.setOperator(getCurrentSessionUser().getId());
				dto.setSubmitDate(operateDate);
				
				//插入数据
				sysIncrementHangCardSettingService.addIncrementHangCardSetting(dto);
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
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_HANGCARD)
	public String edit(){
		try{
			if(validateInput()){
				dto.setLastEditDate(new Date());
				dto.setOperator(getCurrentSessionUser().getId());
				
				//修改数据
				sysIncrementHangCardSettingService.updateIncrementHangCardSetting(dto);
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
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_HANGCARD)
	public String remove(){
		if(!StringUtil.newInstance().sNull(dto.getServiceCode()).equals("")){
			//检查订单中是否用到
			SysOrderDto orderDto = new SysOrderDto();
			orderDto.setServiceCode(dto.getServiceCode());
			orderDto.setItemCode(ApplicationConstant.SERVICE_ITEM_NAME_HANGCARD_CODE);
			int counts = sysOrderService.getOrdersCount(orderDto);
			if(counts > 0){
				msg = "服务在订单中被使用,不能删除！";
				return Constants.RTN_SYS_MAIN;
			}
				//** remove records **//
			sysIncrementHangCardSettingService.removeIncrementHangCardSetting(dto);
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
		countOfSubmitMsg = "";
		priceMsg = "";
		msg = "";
	}
	/**
	 * 检查服务名称是否已经存在
	 * @return
	 */
	private boolean checkIsExisted(){
		SysIncrementHangCardSettingDto outDTO = sysIncrementHangCardSettingService.queryIncrementHangCard(dto);
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
		if(util.sNullTrim(dto.getServiceCode()).equals("")){
			serviceNameMsg = "服务名称不能为空!";
		}
		if(util.sNullTrim(dto.getCountOfSubmit()).equals("")){
			countOfSubmitMsg = "挂证次数不能为空";
		}
		if(serviceNameMsg.equals("") && countOfSubmitMsg.equals("") ){
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
		this.dtoList = sysIncrementHangCardSettingService.queryIncrementHangCardList(this.pageInfo, searchdto);
	}
	public Map<String, String> getServiceNames() {
		return serviceNames;
	}
	public void setServiceNames(Map<String, String> serviceNames) {
		this.serviceNames = serviceNames;
	}
	public SysIncrementHangCardSettingDto getDto() {
		return dto;
	}
	public void setDto(SysIncrementHangCardSettingDto dto) {
		this.dto = dto;
	}
	public SysIncrementHangCardSettingDto getSearchdto() {
		return searchdto;
	}
	public void setSearchdto(SysIncrementHangCardSettingDto searchdto) {
		this.searchdto = searchdto;
	}
	public List<SysIncrementHangCardSettingDto> getDtoList() {
		return dtoList;
	}
	public void setDtoList(List<SysIncrementHangCardSettingDto> dtoList) {
		this.dtoList = dtoList;
	}
	public String getServiceNameMsg() {
		return serviceNameMsg;
	}
	public void setServiceNameMsg(String serviceNameMsg) {
		this.serviceNameMsg = serviceNameMsg;
	}
	public String getCountOfSubmitMsg() {
		return countOfSubmitMsg;
	}
	public void setCountOfSubmitMsg(String countOfSubmitMsg) {
		this.countOfSubmitMsg = countOfSubmitMsg;
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
	public ISysIncrementHangCardSettingService getSysIncrementHangCardSettingService() {
		return sysIncrementHangCardSettingService;
	}
	public void setSysIncrementHangCardSettingService(
			ISysIncrementHangCardSettingService sysIncrementHangCardSettingService) {
		this.sysIncrementHangCardSettingService = sysIncrementHangCardSettingService;
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
	public ISysIncrementAdvertSettingService getSysIncrementAdvertSettingService() {
		return sysIncrementAdvertSettingService;
	}
	public void setSysIncrementAdvertSettingService(
			ISysIncrementAdvertSettingService sysIncrementAdvertSettingService) {
		this.sysIncrementAdvertSettingService = sysIncrementAdvertSettingService;
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


}

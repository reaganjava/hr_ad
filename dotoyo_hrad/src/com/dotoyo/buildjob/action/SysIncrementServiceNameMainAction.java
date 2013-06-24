package com.dotoyo.buildjob.action;

import java.util.Date;
import java.util.List;

import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.StringUtil;
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

/**
*
* @author wisdy.xiao
* @dateCreated 2011-01-11
* @description 增值服务名称设置
*/
public class SysIncrementServiceNameMainAction extends SysAction{
	private SysIncrementServiceNameMaintainanceDto dto;
	private SysIncrementServiceNameMaintainanceDto searchdto;
	private List<SysIncrementServiceNameMaintainanceDto> dtoList;
	private String serviceCodeMsg;
	private String serviceNameMsg;
	private String serviceUseAbleMsg;
	private String serviceInfMsg;
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
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_NAME)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysIncrementServiceNameMaintainanceDto();
		//dtoList = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceNameList(searchdto);
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
		dto = new SysIncrementServiceNameMaintainanceDto();
		clear();
		return Constants.RTN_SYS_ADD;
	}
	/**
	 * On click edit button
	 * @return
	 */
	public String beforeEdit(){
		//清空消息
		SysIncrementServiceNameMaintainanceDto tmp = new SysIncrementServiceNameMaintainanceDto();
		tmp.setServiceCode(dto.getServiceCode());
		dto = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceName(tmp);
		clear();
		return Constants.RTN_SYS_EDIT;
	}
	/**
	 * Search records by service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_NAME)
	public String search(){
		clear();
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Add a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_NAME)
	public String add(){
		try{
			if(validateInput() && !checkIsExisted()){
				Date operateDate = new Date();
				dto.setLastEditDate(operateDate);
				dto.setOperator(getCurrentSessionUser().getId());
				dto.setSubmitDate(operateDate);
				
				// 判断价格是否面议
				if ("true".equalsIgnoreCase(dto.getPriceStatus())) {
					dto.setPriceStatus("0");
				}
				else{
					dto.setPriceStatus("1");
				}
				
				//插入数据
				sysIncrementServiceNameMaintainanceService.addIncrementServiceName(dto);
				//查询数据
				searchData();
				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_ADD;
			}
		}catch(Exception e){
			serviceCodeMsg = "添加失败!";
			return Constants.RTN_SYS_ADD;
		}
	}
	/**
	 * Edit a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_NAME)
	public String edit(){
		try{
			if(validateInput()){
				dto.setLastEditDate(new Date());
				dto.setOperator(getCurrentSessionUser().getId());
				
				// 判断价格是否面议
				if ("true".equalsIgnoreCase(dto.getPriceStatus())) {
					dto.setPriceStatus("0");
				}
				else{
					dto.setPriceStatus("1");
				}
				
				//修改数据
				sysIncrementServiceNameMaintainanceService.updateIncrementSerivceName(dto);
				//查询所有数据
				searchData();
				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_EDIT;
			}
		}catch(Exception e){
			serviceCodeMsg = "修改失败!";
			e.printStackTrace();
			return Constants.RTN_SYS_EDIT;
		}
	}
	/**
	 * Delete a record
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_INCREMENT_SERVICE_NAME)
	public String remove(){
		if(!StringUtil.newInstance().sNull(dto.getServiceCode()).equals("")){
			//检查服务代码其它增值服务表中是否存在，存在不予删除，给出提示
			/** 检查广告设置服务 **/
			SysIncrementAdvertSettingDto adverDTO = new SysIncrementAdvertSettingDto();
			adverDTO.setServiceCode(dto.getServiceCode());
			adverDTO = sysIncrementAdvertSettingService.queryIncrementAdvert(adverDTO);
			/** 检查挂证设置服务 **/
			SysIncrementHangCardSettingDto hangcardDTO = new SysIncrementHangCardSettingDto();
			hangcardDTO.setServiceCode(dto.getServiceCode());
			hangcardDTO = sysIncrementHangCardSettingService.queryIncrementHangCard(hangcardDTO);
			/** 检查猎证设置服务 **/
			SysIncrementHuntCardSettingDto huntcardDTO = new SysIncrementHuntCardSettingDto();
			huntcardDTO.setServiceCode(dto.getServiceCode());
			huntcardDTO = sysIncrementHuntCardSettingService.queryIncrementHuntCard(huntcardDTO);
			/** 检查招聘设置服务 **/
			SysIncrementRecruitSettingDto recruitDTO = new SysIncrementRecruitSettingDto();
			recruitDTO.setServiceCode(dto.getServiceCode());
			recruitDTO = sysIncrementRecruitSettingService.queryIncrementRecruit(recruitDTO);
			/** 检查人才挖掘设置服务 **/
			SysIncrementTalentsFoundSettingDto talentsDTO = new SysIncrementTalentsFoundSettingDto();
			talentsDTO.setServiceCode(dto.getServiceCode());
			talentsDTO = sysIncrementTalentsFoundSettingService.queryIncrementTalentsFound(talentsDTO);

			if(!(adverDTO == null && hangcardDTO == null && huntcardDTO == null && recruitDTO == null && talentsDTO == null)){
				msg = "服务在增值服务设置中被使用,不能删除！";
				return Constants.RTN_SYS_MAIN;
			}
			/** 检查订单表**/
			SysOrderDto orderDto = new SysOrderDto();
			orderDto.setServiceCode(dto.getServiceCode());
			int counts = sysOrderService.getOrdersCount(orderDto);
			if(counts > 0){
				msg = "服务在订单中被使用,不能删除！";
				return Constants.RTN_SYS_MAIN;
			}
			//** remove records **//
			sysIncrementServiceNameMaintainanceService.removeIncrementSerivceNameByCode(dto);
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
		serviceCodeMsg = "";
		serviceNameMsg = "";
		serviceUseAbleMsg = "";
		serviceInfMsg = "";
		msg = "";
	}
	/**
	 * 检查服务代码是否已经存在
	 * @return
	 */
	public boolean checkIsExisted(){
		SysIncrementServiceNameMaintainanceDto outDTO = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceName(dto);
		if(outDTO == null){
			return false;
		}else{
			serviceCodeMsg = "服务编码已经存在，请更换!";
			return true;
		}
	}
	private void searchData(){
		//查询数据
		if(this.pageInfo == null){
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		this.dtoList = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceNameList(this.pageInfo, searchdto);
	}
	/**
	 * validate page form fields.
	 * @return
	 */
	private boolean validateInput(){
		StringUtil util = StringUtil.newInstance();
		clear();
		if(util.sNullTrim(dto.getServiceCode()).equals("")){
			serviceCodeMsg = "服务编码不能为空";
		}
		if(util.sNullTrim(dto.getServiceCode()).length() > 20){
			serviceCodeMsg = "服务编码超过长度[20]";
		}
		if(util.sNullTrim(dto.getServiceName()).equals("")){
			serviceNameMsg = "服务名称不能为空";
		}
		if(util.sNullTrim(dto.getServiceName()).length() > 20){
			serviceNameMsg = "服务名称超过长度[20]";
		}
		if(util.sNull(dto.getUsableDays()).equals("")){
			serviceUseAbleMsg="可用天数不能为空";
		}
		if(util.sNull(dto.getUsableDays()).length() > 4){
			serviceUseAbleMsg="可用天数超过限制位数[4]";
		}
//		if(util.sNullTrim(dto.getServiceIntro()).length() > 255){
//			serviceInfMsg = "服务简介超过最大限制长度[255]";
//		}
		if(serviceCodeMsg.equals("") && serviceNameMsg.equals("") && serviceUseAbleMsg.equals("") && serviceInfMsg.equals("")){
			return true;
		}
		return false;
	}


	public SysIncrementServiceNameMaintainanceDto getDto() {
		return dto;
	}
	public void setDto(SysIncrementServiceNameMaintainanceDto dto) {
		this.dto = dto;
	}
	public ISysIncrementServiceNameMaintainanceService getSysIncrementServiceNameMaintainanceService() {
		return sysIncrementServiceNameMaintainanceService;
	}
	public void setSysIncrementServiceNameMaintainanceService(
			ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService) {
		this.sysIncrementServiceNameMaintainanceService = sysIncrementServiceNameMaintainanceService;
	}
	public String getServiceCodeMsg() {
		return serviceCodeMsg;
	}
	public void setServiceCodeMsg(String serviceCodeMsg) {
		this.serviceCodeMsg = serviceCodeMsg;
	}
	public String getServiceNameMsg() {
		return serviceNameMsg;
	}
	public void setServiceNameMsg(String serviceNameMsg) {
		this.serviceNameMsg = serviceNameMsg;
	}
	public String getServiceUseAbleMsg() {
		return serviceUseAbleMsg;
	}
	public void setServiceUseAbleMsg(String serviceUseAbleMsg) {
		this.serviceUseAbleMsg = serviceUseAbleMsg;
	}
	public String getServiceInfMsg() {
		return serviceInfMsg;
	}
	public void setServiceInfMsg(String serviceInfMsg) {
		this.serviceInfMsg = serviceInfMsg;
	}
	public List<SysIncrementServiceNameMaintainanceDto> getDtoList() {
		return dtoList;
	}
	public void setDtoList(List<SysIncrementServiceNameMaintainanceDto> dtoList) {
		this.dtoList = dtoList;
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
	public SysIncrementServiceNameMaintainanceDto getSearchdto() {
		return searchdto;
	}
	public void setSearchdto(SysIncrementServiceNameMaintainanceDto searchdto) {
		this.searchdto = searchdto;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public ISysOrderService getSysOrderService() {
		return sysOrderService;
	}
	public void setSysOrderService(ISysOrderService sysOrderService) {
		this.sysOrderService = sysOrderService;
	}
}

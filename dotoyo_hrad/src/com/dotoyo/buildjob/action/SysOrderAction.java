package com.dotoyo.buildjob.action;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.struts2.ServletActionContext;

import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.StringUtil;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.dto.SysOrderDto;
import com.dotoyo.buildjob.sys.service.ISysIncrementServiceNameMaintainanceService;
import com.dotoyo.buildjob.sys.service.ISysOrderService;
import com.dotoyo.buildjob.systemManage.dto.SysParameterDto;
import com.dotoyo.buildjob.systemManage.service.ISysParameterService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-17
 * @description 订单管理
 */
public class SysOrderAction extends SysAction {
	private Map<String,String> serviceNames;
	private Map<String,String> statuss;
	private SysOrderDto dto;
	private SysOrderDto searchdto;
	private List<SysOrderDto> dtoList;
	private String priceMsg;
	private String remarkMsg;
	private String msg;
	private ISysOrderService sysOrderService;
	private ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService;
	private PageInfo pageInfo;
	private ISysParameterService sysParameterService;
	private String payDueTime;//支付期限
	private String isNew;
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ORDER_MANAGE)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysOrderDto();

		//初始订单状态
		statuss = new TreeMap<String,String>();
		statuss.put("", "");
		statuss.put(ApplicationConstant.ORDER_STATUS_0, "未支付");
		statuss.put(ApplicationConstant.ORDER_STATUS_1, "已支付");
		pageInfo = new PageInfo();
		//查询数据
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ORDER_MANAGE)
	public String searchNewOrder(){
		//查询数据
		if(this.pageInfo == null){
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		searchdto.setIsNew("Y");
		this.dtoList = sysOrderService.queryOrderList(this.pageInfo, searchdto);
		SysParameterDto parameter = sysParameterService.getSysParameterDtoBycode(ApplicationConstant.ORDER_PAY_DUE_TIME);
		//过期时间
		Long afterTime = new Date().getTime();
		if(parameter != null){
			payDueTime = parameter.getSysValue();
			afterTime = StringUtil.newInstance().Ds(-Integer.valueOf(payDueTime)).getTime();
		}

		//转换查询字段代码到名称
		if(statuss != null && statuss.size() > 0 && dtoList != null){
			for(int i = 0;i < dtoList.size();i++){
				SysOrderDto tmp = dtoList.get(i);
				String statusName = statuss.get(tmp.getStatus());
				Long orderTime = tmp.getOrderDate().getTime();
				//超出订单支付时间
				if(orderTime < afterTime  || tmp.getStatus().equals("1")){
					dtoList.get(i).setCanEdit(false);
				}
				dtoList.get(i).setStatusName(statusName);
			}
		}
		return "viewNewOrder";
	}
	/**
	 * 设置支付期限
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ORDER_MANAGE)
	public String setDueTime(){
		if(!StringUtil.newInstance().sNull(payDueTime).equals("")){
			SysParameterDto parameter = sysParameterService.getSysParameterDtoBycode(ApplicationConstant.ORDER_PAY_DUE_TIME);
			parameter.setSysValue(payDueTime);
			sysParameterService.updateSysParameter(parameter);
			//查询数据
			searchData();
		}
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * On click edit button
	 * @return
	 */
	public String beforeEdit(){
		//清空消息
		clear();
		//查询
		dto = sysOrderService.queryOrder(dto);
		String from = ServletActionContext.getRequest().getParameter("from");
		if("newOrder".equalsIgnoreCase(from)){
			ServletActionContext.getRequest().setAttribute("from", from);
		}
		return Constants.RTN_SYS_EDIT;
	}
	/**
	 * Search records by service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ORDER_MANAGE)
	public String search(){
		searchData();
		return Constants.RTN_SYS_MAIN;
	}

	/**
	 * Edit a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ORDER_MANAGE)
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
				sysOrderService.updateOrder(dto);
				//查询所有数据
				searchData();
				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_EDIT;
			}
		}catch(Exception e){
			priceMsg = "修改失败!";
			e.printStackTrace();
			return Constants.RTN_SYS_EDIT;
		}
	}
	/**
	 * Delete a record
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ORDER_MANAGE)
	public String remove(){
		if(!StringUtil.newInstance().sNull(dto.getResId()).equals("")){
				//** remove records **//
			sysOrderService.removeOrder(dto);
			//查询数据
			searchData();
			clear();
		}
		return Constants.RTN_SYS_MAIN;
	}
	public String beforePay(){
		//清空消息
		clear();
		//查询
		dto = sysOrderService.queryOrder(dto);
		String from = ServletActionContext.getRequest().getParameter("from");
		if("newOrder".equalsIgnoreCase(from)){
			ServletActionContext.getRequest().setAttribute("from", from);
		}
		return Constants.RTN_SYS_ASSIGN;
	}
	/**
	 * Sure to pay
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ORDER_MANAGE)
	public String pay(){
		if(!StringUtil.newInstance().sNull(dto.getId()).equals("") && validateInput()){
			//** remove records **//
			Long useableDays = getUsefulDays(dto.getServiceCode());
			Date now = new Date();
			Date startDate = now;
			//续期订单支付
			if(dto.getExtensionFlag().equals(ApplicationConstant.ORDER_EXTENSION_FLAG_YES)){
				dto = sysOrderService.queryOrder(dto);
				List<SysOrderDto> orderList = sysOrderService.queryOrderByOrderIdList(dto);
				//获取续期订单服务时间
				for(SysOrderDto inDto : orderList){
					if(inDto.getExtensionFlag().equals(ApplicationConstant.ORDER_EXTENSION_FLAG_YES)){
						dto = inDto;
					}else if(inDto.getExtensionFlag().equals(ApplicationConstant.ORDER_EXTENSION_FLAG_NO)){
						if(!(inDto.getEndDate() != null && inDto.getEndDate().getTime() < now.getTime())){
							startDate = inDto.getEndDate();
						}
						/** 原订单设置为不可续期**/
						inDto.setExtensionFlag(ApplicationConstant.ORDER_EXTENSION_FLAG_DISABLED);
						sysOrderService.updateOrder(inDto);
					}

				}
				dto.setExtensionFlag(ApplicationConstant.ORDER_EXTENSION_FLAG_NO);
				toPay(dto,useableDays,startDate);
			}else{
				toPay(dto,useableDays,now);
			}

			//查询数据
			searchData();
			clear();
		}
		return Constants.RTN_SYS_MAIN;
	}
	private void toPay(SysOrderDto dto,Long useableDays,Date start){
		GregorianCalendar gc =new GregorianCalendar();
		gc.setTime(start);
		if(useableDays != null){
			dto.setStartDate(start);
			gc.add(5, +useableDays.intValue());
			dto.setEndDate(gc.getTime());
		}
		dto.setExtensionDays(useableDays);
		dto.setConfirmor(getCurrentSessionUser().getId());
		dto.setOperator(getCurrentSessionUser().getId());
		dto.setStatus(ApplicationConstant.STATUS_PASSED);
		dto.setPayDate(new Date());
		sysOrderService.updateOrder(dto);
	}
	/**
	 * 获取使用天数
	 * @param serviceCode
	 * @return
	 */
	private Long getUsefulDays(String serviceCode){
		SysIncrementServiceNameMaintainanceDto tmp = new SysIncrementServiceNameMaintainanceDto();
		tmp.setServiceCode(serviceCode);
		tmp = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceName(tmp);
		return tmp != null ? tmp.getUsableDays() : null;
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
		priceMsg = "";
		remarkMsg = "";
		msg = "";
	}

	/**
	 * validate page form fields.
	 * @return
	 */
	private boolean validateInput(){
		StringUtil util = StringUtil.newInstance();
		clear();

		if(util.sNullTrim(dto.getPrice()).equals("")){
			priceMsg = "价格不能为空";
		}else if(!util.isDouble(dto.getPrice(),true)){
			priceMsg = "价格必须是正数";
		}
		if(util.sNull(dto.getRemark()).length() > 255){
			remarkMsg = "备注超过长度[255]";
		}
		if(priceMsg.equals("") && remarkMsg.equals("")){
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
		if("Y".equalsIgnoreCase(isNew)&&searchdto!=null){
			isNew = null;
			searchdto.setIsNew("Y");
		}
		this.dtoList = sysOrderService.queryOrderList(this.pageInfo, searchdto);
		SysParameterDto parameter = sysParameterService.getSysParameterDtoBycode(ApplicationConstant.ORDER_PAY_DUE_TIME);
		//过期时间
		Long afterTime = new Date().getTime();
		if(parameter != null){
			payDueTime = parameter.getSysValue();
			afterTime = StringUtil.newInstance().Ds(-Integer.valueOf(payDueTime)).getTime();
		}

		//转换查询字段代码到名称
		if(statuss != null && statuss.size() > 0 && dtoList != null){
			for(int i = 0;i < dtoList.size();i++){
				SysOrderDto tmp = dtoList.get(i);
				String statusName = statuss.get(tmp.getStatus());
				Long orderTime = tmp.getOrderDate().getTime();
				//超出订单支付时间
				if(orderTime < afterTime  || tmp.getStatus().equals("1")){
					dtoList.get(i).setCanEdit(false);
				}
				dtoList.get(i).setStatusName(statusName);
			}
		}

	}

	public Map<String, String> getServiceNames() {
		return serviceNames;
	}

	public void setServiceNames(Map<String, String> serviceNames) {
		this.serviceNames = serviceNames;
	}

	public Map<String, String> getStatuss() {
		return statuss;
	}

	public void setStatuss(Map<String, String> statuss) {
		this.statuss = statuss;
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

	public String getRemarkMsg() {
		return remarkMsg;
	}

	public void setRemarkMsg(String remarkMsg) {
		this.remarkMsg = remarkMsg;
	}

	public ISysIncrementServiceNameMaintainanceService getSysIncrementServiceNameMaintainanceService() {
		return sysIncrementServiceNameMaintainanceService;
	}

	public void setSysIncrementServiceNameMaintainanceService(
			ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService) {
		this.sysIncrementServiceNameMaintainanceService = sysIncrementServiceNameMaintainanceService;
	}

	public String getPayDueTime() {
		return payDueTime;
	}

	public void setPayDueTime(String payDueTime) {
		this.payDueTime = payDueTime;
	}
	public ISysParameterService getSysParameterService() {
		return sysParameterService;
	}
	public void setSysParameterService(ISysParameterService sysParameterService) {
		this.sysParameterService = sysParameterService;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
}

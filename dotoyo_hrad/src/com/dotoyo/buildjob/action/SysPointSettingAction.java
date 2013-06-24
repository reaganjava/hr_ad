package com.dotoyo.buildjob.action;

import java.util.List;

import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.StringUtil;
import com.dotoyo.buildjob.sys.dto.SysPointDto;
import com.dotoyo.buildjob.sys.service.ISysPointService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-27
 * @description 积分设置
 */
public class SysPointSettingAction extends SysAction {
	private SysPointDto dto;
	private SysPointDto searchdto;
	private List<SysPointDto> dtoList;
	private String pointMsg;
	private String msg;
	private ISysPointService sysPointService;
	private PageInfo pageInfo;
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_POINT_MANAGE_SETTING)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysPointDto();
		pageInfo = new PageInfo();
		//查询数据
		searchData();
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
		dto = sysPointService.search(dto.getCode());
		return Constants.RTN_SYS_EDIT;
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
	 * Edit a system increment service name
	 * @return
	 */
	public String edit(){
		try{
			if(validateInput()){
				//修改数据
				sysPointService.update(dto);
				//查询所有数据
				searchData();
				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_EDIT;
			}
		}catch(Exception e){
			pointMsg = "修改失败!";
			e.printStackTrace();
			return Constants.RTN_SYS_EDIT;
		}
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
		pointMsg = "";
		msg = "";
	}

	/**
	 * validate page form fields.
	 * @return
	 */
	private boolean validateInput(){
		StringUtil util = StringUtil.newInstance();
		clear();

		if(util.sNullTrim(dto.getPoint()).equals("")){
			pointMsg = "积分分值不能为空";
		}
		if(pointMsg.equals("")){
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
		this.dtoList = sysPointService.searchList(this.pageInfo, searchdto.getAction());

	}

	public SysPointDto getDto() {
		return dto;
	}

	public void setDto(SysPointDto dto) {
		this.dto = dto;
	}

	public SysPointDto getSearchdto() {
		return searchdto;
	}

	public void setSearchdto(SysPointDto searchdto) {
		this.searchdto = searchdto;
	}

	public List<SysPointDto> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<SysPointDto> dtoList) {
		this.dtoList = dtoList;
	}

	public String getPointMsg() {
		return pointMsg;
	}

	public void setPointMsg(String pointMsg) {
		this.pointMsg = pointMsg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ISysPointService getSysPointService() {
		return sysPointService;
	}

	public void setSysPointService(ISysPointService sysPointService) {
		this.sysPointService = sysPointService;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}




}

package com.dotoyo.buildjob.action;

import java.util.List;

import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.StringUtil;
import com.dotoyo.buildjob.sys.dto.SysFunctionDto;
import com.dotoyo.buildjob.sys.service.ISysFunctionService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-21
 * @description 功能点维护
 */
public class SysFunctionAction extends SysAction {
	private SysFunctionDto dto;
	private SysFunctionDto searchdto;
	private List<SysFunctionDto> dtoList;
	private String nameMsg;
	private String msg;
	private ISysFunctionService sysFunctionService;
	private PageInfo pageInfo;
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_FUNCTION_MAINTAINANCE)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysFunctionDto();
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
		SysFunctionDto tmp = new SysFunctionDto();
		tmp.setId(dto.getId());
		dto = sysFunctionService.search(tmp);
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
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_FUNCTION_MAINTAINANCE)
	public String edit(){
		try{
			if(validateInput()){
				//修改数据
				sysFunctionService.update(dto);
				//查询所有数据
				searchData();
				clear();
				dto = new SysFunctionDto();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_EDIT;
			}
		}catch(Exception e){
			nameMsg = "修改失败!";
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
		nameMsg = "";
		msg = "";
	}

	/**
	 * validate page form fields.
	 * @return
	 */
	private boolean validateInput(){
		StringUtil util = StringUtil.newInstance();
		clear();

		if(util.sNullTrim(dto.getName()).equals("")){
			nameMsg = "名称不能为空";
		}
		if(nameMsg.equals("")){
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
		this.dtoList = sysFunctionService.searchList(this.pageInfo, searchdto);

	}

	public SysFunctionDto getDto() {
		return dto;
	}

	public void setDto(SysFunctionDto dto) {
		this.dto = dto;
	}

	public SysFunctionDto getSearchdto() {
		return searchdto;
	}

	public void setSearchdto(SysFunctionDto searchdto) {
		this.searchdto = searchdto;
	}

	public List<SysFunctionDto> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<SysFunctionDto> dtoList) {
		this.dtoList = dtoList;
	}

	public String getNameMsg() {
		return nameMsg;
	}

	public void setNameMsg(String nameMsg) {
		this.nameMsg = nameMsg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ISysFunctionService getSysFunctionService() {
		return sysFunctionService;
	}

	public void setSysFunctionService(ISysFunctionService sysFunctionService) {
		this.sysFunctionService = sysFunctionService;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}


}

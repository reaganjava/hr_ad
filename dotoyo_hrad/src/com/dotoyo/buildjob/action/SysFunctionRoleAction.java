package com.dotoyo.buildjob.action;

import java.util.List;

import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.sys.dto.SysFunctionDto;
import com.dotoyo.buildjob.sys.dto.SysFunctionRoleDto;
import com.dotoyo.buildjob.sys.dto.SysRoleDto;
import com.dotoyo.buildjob.sys.service.ISysFunctionRoleService;
import com.dotoyo.buildjob.sys.service.ISysRoleService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-21
 * @description 权限设置
 */
public class SysFunctionRoleAction extends SysAction {
	private SysRoleDto dto;
	private SysRoleDto searchdto;
	private List<SysRoleDto> dtoList;
	private String msg;
	private ISysRoleService sysRoleService;
	private PageInfo pageInfo;
	/** 功能分配 **/
	private ISysFunctionRoleService sysFunctionRoleService;
	private SysFunctionDto functionSearchdto;
	private List<SysFunctionDto> functionDtoList;
	private PageInfo functionPageInfo;
	private String []assignBox;
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_FUNCTION_SETTING)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysRoleDto();
		pageInfo = new PageInfo();
		//查询数据
		searchData();
		return Constants.RTN_SYS_MAIN;
	}


	/**
	 * on click 分配用户分配角色 button
	 * @return
	 */
	public String beforeAssign(){
		//查询所有数据
		functionSearchdto = new SysFunctionDto();
		functionSearchdto.setRoleid(dto.getId());
		this.functionDtoList = sysFunctionRoleService.searchListByNotPage(functionSearchdto);
		return Constants.RTN_SYS_ASSIGN;
	}
	/**
	 * Search records by 角色名
	 * @return
	 */
	public String search(){
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Search records by 用户名，状态
	 * @return
	 */
	public String functionSearch(){
		if(this.functionPageInfo == null){
			this.functionPageInfo = new PageInfo();
			this.functionPageInfo.setPageInfoName("functionPageInfo");
		}
		this.functionPageInfo.setPageSize(45);
		this.functionDtoList = sysFunctionRoleService.searchList(this.functionPageInfo, functionSearchdto);

		return Constants.RTN_SYS_ASSIGN;
	}

	/**
	 * On click cancel button
	 * @return
	 */
	public String cancel(){
		clear();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * On click sure button
	 * Assign role to user selected.
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_FUNCTION_SETTING)
	public String assign(){
		if(functionSearchdto.getRoleid() != null){
			for(SysFunctionDto functionDto : functionDtoList){
				SysFunctionRoleDto tmpDto = new SysFunctionRoleDto();
				tmpDto.setRoleid(functionSearchdto.getRoleid());
				tmpDto.setFunctionid(functionDto.getId());
				if(checkToAssin(String.valueOf(functionDto.getId()))){
					if(!checkFunctionRoleIsExisted(tmpDto)){
						sysFunctionRoleService.add(tmpDto);
					}
				}else{
					sysFunctionRoleService.remove(tmpDto);
				}
			}
		}
		//重新刷新数据
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Check user role is existed.
	 * @param userId
	 * @return
	 */
	private Boolean checkFunctionRoleIsExisted(SysFunctionRoleDto tmpDto){

		if(sysFunctionRoleService.search(tmpDto) == null){
			return false;
		}else{
			return true;
		}
	}
	/**
	 ** check user to assigned permission
	 * @param userId
	 * @return
	 */
	private Boolean checkToAssin(String userId){
		if(assignBox == null) return false;
		for(String tmpId : assignBox){
			if(userId.equals(tmpId)){
				return true;
			}
		}
		return false;
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
		this.dtoList = sysRoleService.searchList(this.pageInfo, searchdto);

	}
	/**
	 * 查询功能分配数据列表
	 */
	private void functionSearchData(){
		//查询数据
		if(this.functionPageInfo == null){
			this.functionPageInfo = new PageInfo();
			this.functionPageInfo.setPageInfoName("functionPageInfo");
		}
		this.functionPageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		this.functionDtoList = sysFunctionRoleService.searchList(this.functionPageInfo, functionSearchdto);
	}


	public SysRoleDto getDto() {
		return dto;
	}


	public void setDto(SysRoleDto dto) {
		this.dto = dto;
	}


	public SysRoleDto getSearchdto() {
		return searchdto;
	}


	public void setSearchdto(SysRoleDto searchdto) {
		this.searchdto = searchdto;
	}


	public List<SysRoleDto> getDtoList() {
		return dtoList;
	}


	public void setDtoList(List<SysRoleDto> dtoList) {
		this.dtoList = dtoList;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public ISysRoleService getSysRoleService() {
		return sysRoleService;
	}


	public void setSysRoleService(ISysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}


	public PageInfo getPageInfo() {
		return pageInfo;
	}


	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}


	public ISysFunctionRoleService getSysFunctionRoleService() {
		return sysFunctionRoleService;
	}


	public void setSysFunctionRoleService(
			ISysFunctionRoleService sysFunctionRoleService) {
		this.sysFunctionRoleService = sysFunctionRoleService;
	}


	public SysFunctionDto getFunctionSearchdto() {
		return functionSearchdto;
	}


	public void setFunctionSearchdto(SysFunctionDto functionSearchdto) {
		this.functionSearchdto = functionSearchdto;
	}


	public List<SysFunctionDto> getFunctionDtoList() {
		return functionDtoList;
	}


	public void setFunctionDtoList(List<SysFunctionDto> functionDtoList) {
		this.functionDtoList = functionDtoList;
	}


	public PageInfo getFunctionPageInfo() {
		return functionPageInfo;
	}


	public void setFunctionPageInfo(PageInfo functionPageInfo) {
		this.functionPageInfo = functionPageInfo;
	}


	public String[] getAssignBox() {
		return assignBox;
	}


	public void setAssignBox(String[] assignBox) {
		this.assignBox = assignBox;
	}

}



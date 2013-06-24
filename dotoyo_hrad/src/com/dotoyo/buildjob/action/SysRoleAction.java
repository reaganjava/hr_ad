package com.dotoyo.buildjob.action;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.StringUtil;
import com.dotoyo.buildjob.sys.dto.SysRoleDto;
import com.dotoyo.buildjob.sys.dto.SysUserDto;
import com.dotoyo.buildjob.sys.dto.SysUserRoleDto;
import com.dotoyo.buildjob.sys.service.ISysRoleService;
import com.dotoyo.buildjob.sys.service.ISysUserRoleService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-21
 * @description 角色管理
 */
public class SysRoleAction extends SysAction {
	private SysRoleDto dto;
	private SysRoleDto searchdto;
	private List<SysRoleDto> dtoList;
	private String nameMsg;
	private String codeMsg;
	private String msg;
	private ISysRoleService sysRoleService;
	private PageInfo pageInfo;
	private String []removeBox;
	/** 角色分配 **/
	private ISysUserRoleService sysUserRoleService;
	private SysUserDto userSearchdto;
	private List<SysUserDto> userDtoList;
	private PageInfo userPageInfo;
	private Map<String,String> statuss;
	private String []assignBox;
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ROLE_MANAGE)
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
	 * On click add button
	 * @return
	 */
	public String beforeAdd(){
		//清空消息
		clear();
		dto = new SysRoleDto();
		return Constants.RTN_SYS_ADD;
	}
	/**
	 * On click edit button
	 * @return
	 */
	public String beforeEdit(){
		//清空消息
		clear();
		//查询
		SysRoleDto tmp = new SysRoleDto();
		tmp.setId(dto.getId());
		dto = sysRoleService.search(tmp);
		return Constants.RTN_SYS_EDIT;
	}
	/**
	 * on click 分配用户分配角色 button
	 * @return
	 */
	public String beforeAssign(){
		//查询所有数据
		userSearchdto = new SysUserDto();
		userSearchdto.setRoleid(dto.getId());
		userPageInfo = new PageInfo();
		this.userPageInfo.setPageInfoName("userPageInfo");
		//初始化状态下拉列表
		statuss = new TreeMap<String,String>();
		statuss.put("", "");
		statuss.put(ApplicationConstant.STATUS_USER_ABLED, "正常");
		statuss.put(ApplicationConstant.STATUS_USER_UNABLED, "停用");
		//查询数据
		userSearchData();
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
	public String userSearch(){
		userSearchData();
		return Constants.RTN_SYS_ASSIGN;
	}
	/**
	 * ADD a system ROLE
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ROLE_MANAGE)
	public String add(){
		try{
			if(validateInput() && !checkIsExisted()){
				//修改数据
				sysRoleService.add(dto);
				//查询所有数据
				searchData();
				clear();
				dto = new SysRoleDto();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_ADD;
			}
		}catch(Exception e){
			nameMsg = "修改失败!";
			e.printStackTrace();
			return Constants.RTN_SYS_ADD;
		}
	}
	/**
	 * Edit a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ROLE_MANAGE)
	public String edit(){
		try{
			if(validateInput()){
				//修改数据
				sysRoleService.update(dto);
				//查询所有数据
				searchData();
				dto = new SysRoleDto();
				clear();
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
	 * 删除角色
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ROLE_MANAGE)
	public String remove(){
		SysRoleDto tmp = new SysRoleDto();
		for(String id : removeBox){
			tmp.setId(Long.valueOf(id));
			sysRoleService.remove(tmp);
		}
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * On click cancel button
	 * @return
	 */
	public String cancel(){
		dto = new SysRoleDto();
		clear();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * On click sure button
	 * Assign role to user selected.
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ROLE_MANAGE)
	public String assign(){
		if(userSearchdto.getRoleid() != null){
			for(SysUserDto userDto : userDtoList){
				SysUserRoleDto tmpDto = new SysUserRoleDto();
				tmpDto.setRoleid(userSearchdto.getRoleid());
				tmpDto.setUserid(userDto.getId());
				if(checkToAssin(String.valueOf(userDto.getId()))){
					if(!checkUserRoleIsExisted(tmpDto)){
						sysUserRoleService.add(tmpDto);
					}
				}else{
					sysUserRoleService.remove(tmpDto);
				}
			}
		}
		System.out.println("========================assign==========================");
		//重新刷新数据
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Check user role is existed.
	 * @param userId
	 * @return
	 */
	private Boolean checkUserRoleIsExisted(SysUserRoleDto tmpDto){
		if(sysUserRoleService.search(tmpDto) == null){
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
		nameMsg = "";
		codeMsg = "";
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
		if(util.sNullTrim(dto.getCode()).equals("")){
			codeMsg = "编码不能为空";
		}
		if(nameMsg.equals("") && codeMsg.equals("")){
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
		this.dtoList = sysRoleService.searchList(this.pageInfo, searchdto);

	}
	/**
	 * 查询角色分配数据列表
	 */
	private void userSearchData(){
		//查询数据
		if(this.userPageInfo == null){
			this.userPageInfo = new PageInfo();
			this.userPageInfo.setPageInfoName("userPageInfo");
		}
		this.userPageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		this.userDtoList = sysUserRoleService.searchList(this.userPageInfo, userSearchdto);
		//转换查询字段代码到名称
		if(statuss != null && statuss.size() > 0 && userDtoList != null){
			for(int i = 0;i < userDtoList.size();i++){
				SysUserDto tmp = userDtoList.get(i);
				String statusName = statuss.get(tmp.getStatus());
				userDtoList.get(i).setStatus(statusName);
			}
		}
	}
	/**
	 * 检查角色编码是否已经存在
	 * @return
	 */
	private boolean checkIsExisted(){
		dto.setCode(dto.getCode().trim());
		SysRoleDto outDTO = sysRoleService.search(dto);
		if(outDTO == null){
			return false;
		}else{
			codeMsg = "角色编码已存在，请重新输入!";
			return true;
		}
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
	public String getCodeMsg() {
		return codeMsg;
	}
	public void setCodeMsg(String codeMsg) {
		this.codeMsg = codeMsg;
	}
	public String[] getRemoveBox() {
		return removeBox;
	}
	public void setRemoveBox(String[] removeBox) {
		this.removeBox = removeBox;
	}
	public ISysUserRoleService getSysUserRoleService() {
		return sysUserRoleService;
	}
	public void setSysUserRoleService(ISysUserRoleService sysUserRoleService) {
		this.sysUserRoleService = sysUserRoleService;
	}
	public SysUserDto getUserSearchdto() {
		return userSearchdto;
	}
	public void setUserSearchdto(SysUserDto userSearchdto) {
		this.userSearchdto = userSearchdto;
	}
	public List<SysUserDto> getUserDtoList() {
		return userDtoList;
	}
	public void setUserDtoList(List<SysUserDto> userDtoList) {
		this.userDtoList = userDtoList;
	}
	public PageInfo getUserPageInfo() {
		return userPageInfo;
	}
	public void setUserPageInfo(PageInfo userPageInfo) {
		this.userPageInfo = userPageInfo;
	}
	public Map<String, String> getStatuss() {
		return statuss;
	}
	public void setStatuss(Map<String, String> statuss) {
		this.statuss = statuss;
	}
	public String[] getAssignBox() {
		return assignBox;
	}
	public void setAssignBox(String[] assignBox) {
		this.assignBox = assignBox;
	}




}

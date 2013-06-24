package com.dotoyo.buildjob.action;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.user.dto.LoginUserInfoDto;
import com.dotoyo.buildjob.common.user.service.IUserService;
import com.dotoyo.buildjob.sys.service.ISysPointService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-21
 * @description 积分分值
 */
public class SysPointAdjustAction extends SysAction {
	private LoginUserInfoDto dto;
	private LoginUserInfoDto searchdto;
	private List<LoginUserInfoDto> dtoList;
	private String pointMsg;
	private String msg;
	private ISysPointService sysPointService;
	private IUserService userService;
	private PageInfo pageInfo;
	private String [] assignBox;
	private Map<String,String> statuss;
	private Long increasePoint;
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_POINT_MANAGE_ADJUSTMENT)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new LoginUserInfoDto();
		pageInfo = new PageInfo();
		//查询数据
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	private void initStatuss(){
		//初始化状态下拉列表
		statuss = new TreeMap<String,String>();
		statuss.put("", "");
		statuss.put(ApplicationConstant.STATUS_USER_ABLED, "正常");
		statuss.put(ApplicationConstant.STATUS_USER_UNABLED, "停用");
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
	public String assign(){
		try{

			//修改数据
			if(increasePoint != null && assignBox != null && assignBox.length > 0){
				for(String id : assignBox){
					LoginUserInfoDto tmp = userService.getUserById(Long.valueOf(id));
					Long oldPoint = tmp.getPoint();
					Long respoint;
					if((oldPoint + increasePoint) > Long.valueOf("9999999999")){
						respoint = Long.valueOf("9999999999") - oldPoint;
					}else if((oldPoint + increasePoint) < Long.valueOf("-9999999999")){
						respoint = Long.valueOf("-9999999999") - oldPoint;
					}else{
						respoint = increasePoint;
					}

					sysPointService.updateUserPoint(Long.valueOf(id), respoint);
				}
			}
			//
			//查询所有数据
			searchData();
			clear();
			return Constants.RTN_SYS_MAIN;
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
	 * 查询数据列表
	 */
	private void searchData(){
		//查询数据
		if(this.pageInfo == null){
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		this.dtoList = sysPointService.searchList(pageInfo, searchdto.getUserName(), searchdto.getStatus(),"point");
		//转换查询字段代码到名称
		if(statuss == null){
			initStatuss();
		}
		if(statuss != null && statuss.size() > 0 && dtoList != null){
			for(int i = 0;i < dtoList.size();i++){
				LoginUserInfoDto tmp = dtoList.get(i);
				String statusName = statuss.get(tmp.getStatus());
				dtoList.get(i).setStatus(statusName);
			}
		}

	}

	public LoginUserInfoDto getDto() {
		return dto;
	}

	public void setDto(LoginUserInfoDto dto) {
		this.dto = dto;
	}

	public LoginUserInfoDto getSearchdto() {
		return searchdto;
	}

	public void setSearchdto(LoginUserInfoDto searchdto) {
		this.searchdto = searchdto;
	}

	public List<LoginUserInfoDto> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<LoginUserInfoDto> dtoList) {
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

	public IUserService getUserService() {
		return userService;
	}

	public String[] getAssignBox() {
		return assignBox;
	}

	public void setAssignBox(String[] assignBox) {
		this.assignBox = assignBox;
	}

	public Map<String, String> getStatuss() {
		return statuss;
	}

	public void setStatuss(Map<String, String> statuss) {
		this.statuss = statuss;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Long getIncreasePoint() {
		return increasePoint;
	}

	public void setIncreasePoint(Long increasePoint) {
		this.increasePoint = increasePoint;
	}




}

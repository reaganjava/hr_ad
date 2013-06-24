package com.dotoyo.buildjob.action;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.Md5;
import com.dotoyo.buildjob.common.util.PropertiesValue;
import com.dotoyo.buildjob.common.util.StringUtil;
import com.dotoyo.buildjob.sys.dto.SysFunctionDto;
import com.dotoyo.buildjob.sys.dto.SysFunctionRoleDto;
import com.dotoyo.buildjob.sys.dto.SysRoleDto;
import com.dotoyo.buildjob.sys.dto.SysUserDto;
import com.dotoyo.buildjob.sys.dto.SysUserRoleDto;
import com.dotoyo.buildjob.sys.service.ISysFunctionRoleAdminService;
import com.dotoyo.buildjob.sys.service.ISysRoleAdminService;
import com.dotoyo.buildjob.sys.service.ISysUserRoleAdminService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-24
 * @description 管理员管理
 */
public class SysMemberAdminAction extends SysAction {
	private static Logger logger = Logger.getLogger(SysMemberAdminAction.class);
	/** 分配角色 **/
	private SysUserDto searchdto;
	private List<SysRoleDto> dtoList;
	private String passwordMsg;
	private String userNameMsg;
	private String statusMsg;
	private String pointMsg;
	private String msg;
	private ISysRoleAdminService sysRoleAdminService;
	private PageInfo pageInfo;
	/** 管理员列表 **/
	private SysUserDto userdto;
	private ISysUserRoleAdminService sysUserRoleAdminService;
	private SysUserDto userSearchdto;
	private List<SysUserDto> userDtoList;
	private PageInfo userPageInfo;
	private static Map<String,String> statuss;
	private String []assignBox;
	/** 分配功能点**/
	private SysFunctionDto funSearchdto;
	private ISysFunctionRoleAdminService  sysFunctionRoleAdminService;
	private List<SysFunctionDto> functionDtoList;
	private PageInfo functionPageInfo;
	private String []functionsBox;
	/** 密码重置 **/
	private String emailTo;
	private String emailToMsg;
	/** 系统默认用户 **/
	private String defaultUserName;
	private String removeBox[];

	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADMIN_MANAGE)
	public String init() throws Exception{
		clear();
		//查询所有数据
		userSearchdto = new SysUserDto();
		//查询数据
		this.userPageInfo = new PageInfo();
		this.userPageInfo.setPageInfoName("userPageInfo");
		//查询数据
		//初始化状态下拉列表
		statuss = new TreeMap<String,String>();
		statuss.put("", "");
		statuss.put(ApplicationConstant.STATUS_USER_ABLED, "正常");
		statuss.put(ApplicationConstant.STATUS_USER_UNABLED, "停用");
		defaultUserName = PropertiesValue.getPropertyValue(Constants.SYSTEM_ADMINISTRATORS_DEFAULTNAME);
		userSearchData();
		return Constants.RTN_SYS_MAIN;
	}

	public String beforeAdd(){
		clear();
		userdto = new SysUserDto();
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
		userdto = sysUserRoleAdminService.search(userdto);
		return Constants.RTN_SYS_EDIT;
	}
	/**
	 * On click reset password button
	 * @return
	 */
	public String beforeReset(){
		//清空消息
		clear();
		//查询
		userdto = sysUserRoleAdminService.search(userdto);
		userdto.setStatus(statuss.get(userdto.getStatus()));
		return Constants.RTN_SYS_EDIT;
	}
	/**
	 * on click 分配角色给用户 button
	 * @return
	 */
	public String beforeAssign(){
		//查询所有数据
		searchdto = new SysUserDto();
		searchdto.setId(userdto.getId());
		pageInfo = new PageInfo();
		pageInfo.setPageInfoName("pageInfo");
		//查询数据
		searchData();
		return Constants.RTN_SYS_ASSIGN;
	}
	/**
	 * on click 分配功能点 button
	 * @return
	 */
	public String beforeAssignFunctions(){
		if(this.functionPageInfo == null){
			this.functionPageInfo = new PageInfo();
			functionPageInfo.setPageInfoName("functionPageInfo");
		}
		this.functionPageInfo.setPageSize(45);
		this.functionDtoList = sysFunctionRoleAdminService.searchList(functionPageInfo, funSearchdto);
		return Constants.RTN_SYS_ASSIGN_FUNCTIONS;
	}
	/**
	 * Search records by 角色名
	 * @return
	 */
	public String search(){
		searchData();
		return Constants.RTN_SYS_ASSIGN;
	}
	/**
	 * Search records by 用户名，状态
	 * @return
	 */
	public String userSearch(){
		userSearchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * 分配功能点分页
	 * @return
	 */
	public String searchFun(){
		searchFunData();
		return Constants.RTN_SYS_ASSIGN_FUNCTIONS;
	}
	/**
	 * 添加管理员
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADMIN_MANAGE)
	public String add(){
		try{
			clear();
			StringUtil util = StringUtil.newInstance();
			if(util.sNullTrim(userdto.getUserName()).equals("")){
				userNameMsg = "用户名不能为空";
			}
			if(util.sNullTrim(userdto.getStatus()).equals("")){
				statusMsg = "状态不能为空";
			}
			if(!userNameMsg.equals("") || !statusMsg.equals("")){
				return Constants.RTN_SYS_ADD;
			}
			//check user name is existed.
			userdto.setPassword(null);
			SysUserDto obj = sysUserRoleAdminService.getUserByUserNameAndPasswordAdmin(userdto);

			if(obj != null){
				userNameMsg = "管理员已经存在";
				return Constants.RTN_SYS_ADD;
			}
			String defaultPwd = PropertiesValue.getPropertyValue(Constants.SYSTEM_ADMINISTRATORS_ADDDEFAULT_PWD);
			userdto.setPassword(Md5.encoderByMd5(defaultPwd));
			sysUserRoleAdminService.addAdminUser(userdto);
			//查询所有数据
			userSearchData();
			return Constants.RTN_SYS_MAIN;
		}catch(Exception e){
			statusMsg = "添加失败!";
			logger.info(e + " " + statusMsg);
			return Constants.RTN_SYS_ADD;
		}
	}
	/**
	 * 删除管理员
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADMIN_MANAGE)
	public String remove(){
		SysUserRoleDto tmp = new SysUserRoleDto();
		SysFunctionRoleDto tmp2 = new SysFunctionRoleDto();
		for(String id : removeBox){
			//删除管理员角色配置
			tmp.setUserid(Long.valueOf(id));
			sysUserRoleAdminService.remove(tmp);
			//删除管理员功能点配置
			tmp2.setUserid(Long.valueOf(id));
			sysFunctionRoleAdminService.remove(tmp2);
			//删除管理员
			SysUserDto tmp3 = new SysUserDto();
			tmp3.setId(Long.valueOf(id));
			sysUserRoleAdminService.removeAdminUser(tmp3);
		}
		//查询所有数据
		userSearchData();
		clear();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Edit a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADMIN_MANAGE)
	public String edit(){
		try{
			if(validateInput()){
				//修改数据
				//userdto.setPassword(Md5.encoderByMd5(userdto.getPassword()));
				sysUserRoleAdminService.update(userdto);
				//查询所有数据
				userSearchData();
				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_EDIT;
			}
		}catch(Exception e){
			statusMsg = "修改失败!";
			logger.info(e + " " + statusMsg);
			return Constants.RTN_SYS_EDIT;
		}
	}
	/**
	 * 重设置密码
	 * @return
	 */

	public String resetPassword(){
		clear();
		if(StringUtil.newInstance().sNull(emailTo).equals("")){
			emailToMsg = "邮箱不能为空";
			return Constants.RTN_SYS_EDIT;
		}
		try{
			String pwd = StringUtil.newInstance().createRandomPassword(6);
			userdto.setPassword(Md5.encoderByMd5(pwd));
			//更新密码到数据库
			sysUserRoleAdminService.updatePassword(userdto);
			//发送密码到指定邮箱
			sendEmail(pwd);
			msg = "密码重置成功,已发送到指定邮箱";
		}catch(Exception e){
			logger.info(e);
			emailToMsg = "密码重置失败,请检查资源环境配置";
		}

		return Constants.RTN_SYS_EDIT;
	}
	private void sendEmail(String pwd) throws Exception{
		//** Get mail configurations **//
	    String host = PropertiesValue.getPropertyValue(Constants.JAVA_MAIL_HOST);
	    String username = PropertiesValue.getPropertyValue(Constants.JAVA_MAIL_NAME);
	    String password = PropertiesValue.getPropertyValue(Constants.JAVA_MAIL_PASSWORD);
	    String mail_to = emailTo;
	    String mail_from = PropertiesValue.getPropertyValue(Constants.JAVA_MAIL_FROM);

	    ///** set mail **/
	    HtmlEmail email = new HtmlEmail();
//	    SimpleEmail email = new SimpleEmail();
	    email.setHostName(host);
	    email.setAuthentication(username,password);
	    email.addTo(mail_to, "重置密码");
	    email.setFrom(mail_from, "Service");
	    email.setCharset("utf-8");
	    email.setSubject(PropertiesValue.getPropertyValue(Constants.JAVA_MAIL_TITLE));
	    //email.buildMimeMessage();
	    //** set mail content **//
	    email.setHtmlMsg(getEmailContent(pwd));
	    //send email
	    email.send();
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
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADMIN_MANAGE)
	public String assign(){
		if(searchdto.getId() != null){
			for(SysRoleDto roleDto : dtoList){
				SysUserRoleDto tmpDto = new SysUserRoleDto();
				tmpDto.setRoleid(roleDto.getId());
				tmpDto.setUserid(searchdto.getId());
				if(checkToAssin(String.valueOf(roleDto.getId()))){
					if(!checkUserRoleIsExisted(tmpDto)){
						sysUserRoleAdminService.add(tmpDto);
					}
				}else{
					sysUserRoleAdminService.remove(tmpDto);
				}
			}
		}
//		//重新刷新数据
		userSearchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * 分配功能点
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADMIN_MANAGE)
	public String assignFun(){
		if(funSearchdto.getUserid() != null){
			for(SysFunctionDto functionDto : functionDtoList){
				SysFunctionRoleDto tmpDto = new SysFunctionRoleDto();
				tmpDto.setUserid(funSearchdto.getUserid());
				tmpDto.setFunctionid(functionDto.getId());
				if(checkToAssin(String.valueOf(functionDto.getId()))){
					if(!checkFunctionUserIsExisted(tmpDto)){
						sysFunctionRoleAdminService.add(tmpDto);
					}
				}else{
					sysFunctionRoleAdminService.remove(tmpDto);
				}
			}
		}
		//重新刷新数据
		userSearchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Check user role is existed.
	 * @param userId
	 * @return
	 */
	private Boolean checkFunctionUserIsExisted(SysFunctionRoleDto tmpDto){
		if(sysFunctionRoleAdminService.search(tmpDto) == null){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * Check user role is existed.
	 * @param userId
	 * @return
	 */
	private Boolean checkUserRoleIsExisted(SysUserRoleDto tmpDto){
		if(sysUserRoleAdminService.search(tmpDto) == null){
			return false;
		}else{
			return true;
		}
	}
	/**
	 ** check role to assigned permission
	 * @param userId
	 * @return
	 */
	private Boolean checkToAssin(String roleId){
		if(assignBox == null) return false;
		for(String tmpId : assignBox){
			if(roleId.equals(tmpId)){
				return true;
			}
		}
		return false;
	}
	private void clear(){
		passwordMsg = "";
		statusMsg = "";
		pointMsg = "";
		emailToMsg = "";
		userNameMsg = "";
		msg = "";
	}

	/**
	 * validate page form fields.
	 * @return
	 */
	private boolean validateInput(){
		StringUtil util = StringUtil.newInstance();
		clear();
		if(util.sNullTrim(userdto.getPassword()).equals("")){
			passwordMsg = "密码不能为空";
		}
		if(util.sNullTrim(userdto.getPassword()).length() < 6){
			passwordMsg = "密码不能小于6位";
		}
		if(util.sNullTrim(userdto.getStatus()).equals("")){
			statusMsg = "状态不能为空";
		}
		if(util.sNullTrim(userdto.getPoint()).equals("")){
			pointMsg = "积分不能为空";
		}
		if(pointMsg.equals("") && passwordMsg.equals("") && statusMsg.equals("")){
			return true;
		}
		return false;
	}
	/**
	 * 查询数据列表
	 */
	private void searchData(){
//		//查询数据
		if(this.pageInfo == null){
			this.pageInfo = new PageInfo();
			pageInfo.setPageInfoName("pageInfo");
		}
		this.pageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		this.dtoList = sysUserRoleAdminService.searchRoleList(this.pageInfo, searchdto);

	}
	/**
	 * 查询角色分配数据列表
	 */
	private void userSearchData(){
		if(userPageInfo == null){
			userPageInfo = new PageInfo();
		}
		this.userPageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		this.userDtoList = sysUserRoleAdminService.searchMemberList(this.userPageInfo, userSearchdto,defaultUserName);
		//转换查询字段代码到名称
		if(statuss != null && statuss.size() > 0 && userDtoList != null){
			int defaultUser = -1;
			for(int i = 0;i < userDtoList.size();i++){
				SysUserDto tmp = userDtoList.get(i);
				//默认用户不显示
				if(tmp.getUserName().equals(defaultUserName)){
					defaultUser = i;
				}
				String statusName = statuss.get(tmp.getStatus());
				userDtoList.get(i).setStatus(statusName);

			}
			if(defaultUser != -1){
				userDtoList.remove(defaultUser);
			}

		}
	}
	private void searchFunData(){
		if(this.functionPageInfo == null){
			this.functionPageInfo = new PageInfo();
			functionPageInfo.setPageInfoName("functionPageInfo");
		}
		this.functionPageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		this.functionDtoList = sysFunctionRoleAdminService.searchList(functionPageInfo, funSearchdto);

	}
	/**
	 * Set mail content
	 * @param cus
	 * @return
	 */
	private String getEmailContent(String password) throws Exception{
		StringBuffer sb = new StringBuffer("");
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<div>");
		sb.append("<p>我们已审核了你的密码重置申请，验证通过。</p>");
		sb.append("<p>你的新密码已确定,密码:"+password+"(本密码为随机密码,请及时更改你的密码以防他人盗用。)</p>");
		sb.append("<p>如果您对密码重置有任何疑问，请联系我们。</p>");
		sb.append("<p>【注意】本邮件为系统自动发送的邮件，请不要回复本邮件。</p>");
		sb.append("</div>");
		sb.append("</body>");
		sb.append("<html>");
	    return sb.toString();

	}

	public SysUserDto getSearchdto() {
		return searchdto;
	}

	public void setSearchdto(SysUserDto searchdto) {
		this.searchdto = searchdto;
	}

	public List<SysRoleDto> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<SysRoleDto> dtoList) {
		this.dtoList = dtoList;
	}

	public String getPasswordMsg() {
		return passwordMsg;
	}

	public void setPasswordMsg(String passwordMsg) {
		this.passwordMsg = passwordMsg;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
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


	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}


	public SysUserDto getUserdto() {
		return userdto;
	}

	public void setUserdto(SysUserDto userdto) {
		this.userdto = userdto;
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

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailToMsg() {
		return emailToMsg;
	}

	public void setEmailToMsg(String emailToMsg) {
		this.emailToMsg = emailToMsg;
	}



	public ISysRoleAdminService getSysRoleAdminService() {
		return sysRoleAdminService;
	}

	public void setSysRoleAdminService(ISysRoleAdminService sysRoleAdminService) {
		this.sysRoleAdminService = sysRoleAdminService;
	}

	public ISysUserRoleAdminService getSysUserRoleAdminService() {
		return sysUserRoleAdminService;
	}

	public void setSysUserRoleAdminService(
			ISysUserRoleAdminService sysUserRoleAdminService) {
		this.sysUserRoleAdminService = sysUserRoleAdminService;
	}

	public ISysFunctionRoleAdminService getSysFunctionRoleAdminService() {
		return sysFunctionRoleAdminService;
	}

	public void setSysFunctionRoleAdminService(
			ISysFunctionRoleAdminService sysFunctionRoleAdminService) {
		this.sysFunctionRoleAdminService = sysFunctionRoleAdminService;
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

	public String[] getFunctionsBox() {
		return functionsBox;
	}

	public void setFunctionsBox(String[] functionsBox) {
		this.functionsBox = functionsBox;
	}

	public SysFunctionDto getFunSearchdto() {
		return funSearchdto;
	}

	public void setFunSearchdto(SysFunctionDto funSearchdto) {
		this.funSearchdto = funSearchdto;
	}

	public String getUserNameMsg() {
		return userNameMsg;
	}

	public void setUserNameMsg(String userNameMsg) {
		this.userNameMsg = userNameMsg;
	}

	public String[] getRemoveBox() {
		return removeBox;
	}

	public void setRemoveBox(String[] removeBox) {
		this.removeBox = removeBox;
	}



}

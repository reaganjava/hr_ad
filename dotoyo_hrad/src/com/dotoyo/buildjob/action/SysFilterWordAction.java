package com.dotoyo.buildjob.action;

import java.util.Date;
import java.util.List;

import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.StringUtil;
import com.dotoyo.buildjob.sys.dto.SysFilterWordDto;
import com.dotoyo.buildjob.sys.service.ISysFilterWordService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-22
 * @description 过滤词管理
 */
public class SysFilterWordAction extends SysAction {
	private SysFilterWordDto dto;
	private SysFilterWordDto searchdto;
	private List<SysFilterWordDto> dtoList;
	private String wordsMsg;
	private String notesMsg;
	private String msg;
	private ISysFilterWordService sysFilterWordService;
	private PageInfo pageInfo;
	private String []removeBox;
	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_FILTER_WORDS_MANAGE)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysFilterWordDto();
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
		dto = new SysFilterWordDto();
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
		SysFilterWordDto tmp = new SysFilterWordDto();
		tmp.setId(dto.getId());
		dto = sysFilterWordService.search(tmp);
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
	 * Edit a system filter words
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_FILTER_WORDS_MANAGE)
	public String add(){
		try{
			if(validateInput()){
				//修改数据
				//user id 未设置
				dto.setOperator(getCurrentSessionUser().getId());
				dto.setLast_edit_date(new Date());
				sysFilterWordService.add(dto);
				//查询所有数据
				searchData();
				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_ADD;
			}
		}catch(Exception e){
			wordsMsg = "修改失败!";
			e.printStackTrace();
			return Constants.RTN_SYS_ADD;
		}
	}
	/**
	 * 删除过滤词
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_FILTER_WORDS_MANAGE)
	public String remove(){
		SysFilterWordDto tmp = new SysFilterWordDto();
		for(String id : removeBox){
			tmp.setId(Long.valueOf(id));
			sysFilterWordService.remove(tmp);
		}
		searchData();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Edit a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_FILTER_WORDS_MANAGE)
	public String edit(){
		try{
			if(validateInput()){
				//修改数据
				dto.setLast_edit_date(new Date());
				sysFilterWordService.update(dto);
				//查询所有数据
				searchData();
				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_EDIT;
			}
		}catch(Exception e){
			wordsMsg = "修改失败!";
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
		wordsMsg = "";
		notesMsg = "";
		msg = "";
	}

	/**
	 * validate page form fields.
	 * @return
	 */
	private boolean validateInput(){
		StringUtil util = StringUtil.newInstance();
		clear();

		if(util.sNullTrim(dto.getWords()).equals("")){
			wordsMsg = "过滤词不能为空";
		}
		if(!util.sNullTrim(dto.getNotes()).equals("")){
			if(dto.getNotes().length() > 100){
				notesMsg = "备注超出最大长度[100]";
			}
		}
		if(wordsMsg.equals("") && notesMsg.equals("")){
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
		this.dtoList = sysFilterWordService.searchList(this.pageInfo, searchdto);
	}
	public SysFilterWordDto getDto() {
		return dto;
	}
	public void setDto(SysFilterWordDto dto) {
		this.dto = dto;
	}
	public SysFilterWordDto getSearchdto() {
		return searchdto;
	}
	public void setSearchdto(SysFilterWordDto searchdto) {
		this.searchdto = searchdto;
	}
	public List<SysFilterWordDto> getDtoList() {
		return dtoList;
	}
	public void setDtoList(List<SysFilterWordDto> dtoList) {
		this.dtoList = dtoList;
	}
	public String getWordsMsg() {
		return wordsMsg;
	}
	public void setWordsMsg(String wordsMsg) {
		this.wordsMsg = wordsMsg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ISysFilterWordService getSysFilterWordService() {
		return sysFilterWordService;
	}
	public void setSysFilterWordService(ISysFilterWordService sysFilterWordService) {
		this.sysFilterWordService = sysFilterWordService;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	public String[] getRemoveBox() {
		return removeBox;
	}
	public void setRemoveBox(String[] removeBox) {
		this.removeBox = removeBox;
	}
	public String getNotesMsg() {
		return notesMsg;
	}
	public void setNotesMsg(String notesMsg) {
		this.notesMsg = notesMsg;
	}



}

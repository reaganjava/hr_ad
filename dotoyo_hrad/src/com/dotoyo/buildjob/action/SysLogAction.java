package com.dotoyo.buildjob.action;

import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;

import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.systemManage.dto.SysLogDto;
import com.dotoyo.buildjob.systemManage.service.ISysLogService;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-30
 * @description 系统日志
 */
public class SysLogAction extends SysAction {
	private static Logger logger = Logger.getLogger(SysAction.class);
	private SysLogDto dto;
	private SysLogDto searchdto;
	private List<SysLogDto> dtoList;
	private String msg;
	private ISysLogService sysLogService;
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
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_SYSTEM_LOG)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysLogDto();
		pageInfo = new PageInfo();
		//查询数据
		searchData();
		return Constants.RTN_SYS_MAIN;
	}

	/**
	 * Search records by service name
	 * @return
	 */
	public String search(){
		searchData();
		return Constants.RTN_SYS_MAIN;
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
		try {
			this.dtoList = sysLogService.querySysLogList(this.pageInfo, searchdto);
		} catch (ParseException e) {
			logger.error(e);
		}

	}
	/**
	 * 删除日志
	 * @return
	 */
	public String remove(){
		for(String id : removeBox){
			sysLogService.removeSysLog(Long.valueOf(id));
		}
		searchData();
		return Constants.RTN_SYS_MAIN;
	}

	public SysLogDto getDto() {
		return dto;
	}

	public void setDto(SysLogDto dto) {
		this.dto = dto;
	}

	public SysLogDto getSearchdto() {
		return searchdto;
	}

	public void setSearchdto(SysLogDto searchdto) {
		this.searchdto = searchdto;
	}

	public List<SysLogDto> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<SysLogDto> dtoList) {
		this.dtoList = dtoList;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ISysLogService getSysLogService() {
		return sysLogService;
	}

	public void setSysLogService(ISysLogService sysLogService) {
		this.sysLogService = sysLogService;
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


}

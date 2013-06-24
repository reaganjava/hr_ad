package com.dotoyo.buildjob.vo;

import java.io.Serializable;

import com.dotoyo.buildjob.sys.dto.SysIncrementAdvertSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementHangCardSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementHuntCardSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementRecruitSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementTalentsFoundSettingDto;
/**
 * 增值服务信息
 * @author wisdy.xiao
 *
 */
public class SysIncrementServiceVo implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8579692221706430292L;
	/** 广告服务 **/
	private SysIncrementAdvertSettingDto advertSettingDto;
	/** 挂证服务 **/
	private SysIncrementHangCardSettingDto hangCardSettingDto;
	/** 猎证服务 **/
	private SysIncrementHuntCardSettingDto huntCardSettingDto;
	/** 招聘服务 **/
	private SysIncrementRecruitSettingDto recruitSettingDto;
	/** 服务名称 **/
	private SysIncrementServiceNameMaintainanceDto serviceNameMaintainanceDto;
	/** 人才挖掘服务 **/
	private SysIncrementTalentsFoundSettingDto talentsFoundSettingDto;
	//服务价格
	private Double servicePrice;

	public SysIncrementAdvertSettingDto getAdvertSettingDto() {
		return advertSettingDto;
	}
	public void setAdvertSettingDto(SysIncrementAdvertSettingDto advertSettingDto) {
		this.advertSettingDto = advertSettingDto;
	}
	public SysIncrementHangCardSettingDto getHangCardSettingDto() {
		return hangCardSettingDto;
	}
	public void setHangCardSettingDto(
			SysIncrementHangCardSettingDto hangCardSettingDto) {
		this.hangCardSettingDto = hangCardSettingDto;
	}
	public SysIncrementHuntCardSettingDto getHuntCardSettingDto() {
		return huntCardSettingDto;
	}
	public void setHuntCardSettingDto(
			SysIncrementHuntCardSettingDto huntCardSettingDto) {
		this.huntCardSettingDto = huntCardSettingDto;
	}
	public SysIncrementRecruitSettingDto getRecruitSettingDto() {
		return recruitSettingDto;
	}
	public void setRecruitSettingDto(SysIncrementRecruitSettingDto recruitSettingDto) {
		this.recruitSettingDto = recruitSettingDto;
	}
	public SysIncrementServiceNameMaintainanceDto getServiceNameMaintainanceDto() {
		return serviceNameMaintainanceDto;
	}
	public void setServiceNameMaintainanceDto(
			SysIncrementServiceNameMaintainanceDto serviceNameMaintainanceDto) {
		this.serviceNameMaintainanceDto = serviceNameMaintainanceDto;
	}
	public SysIncrementTalentsFoundSettingDto getTalentsFoundSettingDto() {
		return talentsFoundSettingDto;
	}
	public void setTalentsFoundSettingDto(
			SysIncrementTalentsFoundSettingDto talentsFoundSettingDto) {
		this.talentsFoundSettingDto = talentsFoundSettingDto;
	}
	public Double getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(Double servicePrice) {
		this.servicePrice = servicePrice;
	}

}

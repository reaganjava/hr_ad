package com.dotoyo.buildjob.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.Constants;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.CityDto;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.dto.ProvinceDto;
import com.dotoyo.buildjob.common.html.HtmlGeneration;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.util.CacheManager;
import com.dotoyo.buildjob.common.util.EssentialDataUtil;
import com.dotoyo.buildjob.common.util.FileUtil;
import com.dotoyo.buildjob.common.util.PropertiesValue;
import com.dotoyo.buildjob.common.util.RegionDataUtil;
import com.dotoyo.buildjob.common.util.StringUtil;
import com.dotoyo.buildjob.sys.dto.SysAdDto;
import com.dotoyo.buildjob.sys.service.ISysAdService;
import com.dotoyo.buildjob.systemManage.dto.ClassMasterDto;

/**
 *
 * @author wisdy.xiao
 * @dateCreated 2011-01-30
 * @description 广告管理
 */
public class SysAdAction extends SysAction {
	private SysAdDto dto;
	private SysAdDto searchdto;
	private List<SysAdDto> dtoList;
	private String titleMsg;     //广告标题消息
	private String companyMsg;   //公司消息
	private String startDateMsg;   //广告时段-开始消息
	private String endDateMsg;     //广告时段-结束消息
	private String typeMsg;      //类型
	private String typeContentMsg; //类型信息不能为空
	private String widthMsg;     //长
	private String heightMsg;    //宽
	private String positionMsg;  //位置
	private String advertImgMsg; //广告图片
	private String cityMsg;      //公司所在城市
	private String adindexMsg;

	private String msg;
	private ISysAdService sysAdService;
	private PageInfo pageInfo;
	private String []removeBox;
	private String []positionID;
	public String[] getPositionID() {
		return positionID;
	}
	public void setPositionID(String[] positionID) {
		this.positionID = positionID;
	}
	private String []stopBox;
	private static Logger logger = Logger.getLogger(SysAction.class);

	private Map<String,String> all_postions;  //所有位置
	private Map<String,String> types;  //广告类型
	private List<CityDto> citys;  //城市
	private List<ProvinceDto> provinces;//省份
	private SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


	/**
	 *
	 */
	private static final long serialVersionUID = -8881833076168625597L;
	/**
	 * Initialize
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADVERT_MANAGE)
	public String init(){
		clear();
		//查询所有数据
		searchdto = new SysAdDto();
		if(pageInfo == null)
			pageInfo = new PageInfo();
		//查询数据
		try {
			initData();
			searchData();
		} catch (ParseException e) {
			logger.error(e);
		}
		return Constants.RTN_SYS_MAIN;
	}
	private void initData(){
		//初始化数据
		all_postions = new TreeMap<String,String>();

		//ALL
		List<ClassMasterDto> mastlist = EssentialDataUtil.querySpecializeListLikeCode(Constants.SYS_INCREMENT_ADVERSITING_SEARCH_POSITION);
		all_postions.put("", "");
		if(mastlist != null){
			for(ClassMasterDto tmp : mastlist){
				String tmpCode = tmp.getCode();
				if(!tmpCode.equals(Constants.SYS_INCREMENT_ADVERSITING_SEARCH_POSITION)){
					if(tmpCode.length() <= 8){
						all_postions.put(tmp.getCode().trim(), "--- " + tmp.getName().trim() + " ---");
					}else{
						all_postions.put(tmp.getCode().trim(), tmp.getName().trim());
					}
				}
			}
		}
		//图片类型
//		types = new TreeMap<String,String>();
//		types.put(Constants.ADVERT_TYPE_IMAGE, Constants.ADVERT_TYPE_IMAGE_TITLE);
//		types.put(Constants.ADVERT_TYPE_WORD, Constants.ADVERT_TYPE_WORD_TITLE);
//		types.put(Constants.ADVERT_TYPE_FLASH, Constants.ADVERT_TYPE_FLASH_TITLE);
		//城市数据
		//citys = getCitys();
		citys = new ArrayList<CityDto>();
		provinces = RegionDataUtil.queryProvinceList();
	}
	/**
	 * On click add button
	 * @return
	 */
	public String beforeAdd(){
		//清空消息
		clear();
		dto = new SysAdDto();
		changeType();
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
		SysAdDto tmp = new SysAdDto();
		tmp.setId(dto.getId());
		dto = sysAdService.search(tmp);
		dto.setOldTitle(dto.getTitle());
		if(!StringUtil.newInstance().sNullTrim(dto.getProvince()).equals("")){
			citys = cityList = RegionDataUtil.queryCityListByProvinceCode(dto.getProvince());
		}
		//时间转换
		try{
			changeType();
			String serverUrl = (String) CacheManager.getInstanceMemcachedClient().get("fileServerURL");
			dto.setServerUrl(serverUrl);
			dto.setStart(simpleFormat.format(dto.getStartDate()));
			dto.setEnd(simpleFormat.format(dto.getEndDate()));
			dto.setOldPosition(dto.getPosition());
		}catch(Exception e){
			logger.error(e);
		}
		return Constants.RTN_SYS_EDIT;
	}
	private void changeType(){
		//首页---城市站点页面---城市热门企业位置 只能为文字
		if(dto == null || dto.getPosition() == null || dto.getPosition().equals("")){
			types = new TreeMap<String,String>();
			types.put(Constants.ADVERT_TYPE_IMAGE, Constants.ADVERT_TYPE_IMAGE_TITLE);
			types.put(Constants.ADVERT_TYPE_WORD, Constants.ADVERT_TYPE_WORD_TITLE);
			types.put(Constants.ADVERT_TYPE_FLASH, Constants.ADVERT_TYPE_FLASH_TITLE);
			return;
		}
		if(ApplicationConstant.SERVICE_ADVERT_HOMEPAGE_CITYPAGE_002.equals(dto.getPosition())){
			types = new TreeMap<String,String>();
			types.put(Constants.ADVERT_TYPE_WORD, Constants.ADVERT_TYPE_WORD_TITLE);
		//首页---城市站点页面---推荐企业位置 和猎头中心---推荐企业位置 可以为文字，图片，flash
		}else if(ApplicationConstant.SERVICE_ADVERT_HOMEPAGE_CITYPAGE_001.equals(dto.getPosition()) || ApplicationConstant.SERVICE_ADVERT_HUNTHEAD_001.equals(dto.getPosition())){
			types = new TreeMap<String,String>();
			types.put(Constants.ADVERT_TYPE_IMAGE, Constants.ADVERT_TYPE_IMAGE_TITLE);
			types.put(Constants.ADVERT_TYPE_WORD, Constants.ADVERT_TYPE_WORD_TITLE);
			types.put(Constants.ADVERT_TYPE_FLASH, Constants.ADVERT_TYPE_FLASH_TITLE);
		}else if(ApplicationConstant.SERVICE_ADVERT_RECRUIT_002.equals(dto.getPosition())||ApplicationConstant.SERVICE_ADVERT_SALON_001.equals(dto.getPosition())||ApplicationConstant.SERVICE_ADVERT_TALENTS_FOUND_001.equals(dto.getPosition()) ){
			types = new TreeMap<String,String>();
			types.put(Constants.ADVERT_TYPE_IMAGE, Constants.ADVERT_TYPE_IMAGE_TITLE);
		}else{
			types = new TreeMap<String,String>();
			types.put(Constants.ADVERT_TYPE_IMAGE, Constants.ADVERT_TYPE_IMAGE_TITLE);
			types.put(Constants.ADVERT_TYPE_FLASH, Constants.ADVERT_TYPE_FLASH_TITLE);
		}
		if(!StringUtil.newInstance().sNullTrim(dto.getProvince()).equals("")){
			citys = cityList = RegionDataUtil.queryCityListByProvinceCode(dto.getProvince());
		}
	}
	public String changTypeAdd(){
		changeType();
		return Constants.RTN_SYS_ADD;
	}
	public String changeTypeEdit(){
		changeType();
		return Constants.RTN_SYS_EDIT;
	}
	/**
	 * Search records
	 * @return
	 */
	public String search(){
		try {
			searchData();
		} catch (ParseException e) {
			logger.error(e);
		}
		return Constants.RTN_SYS_MAIN;
	}

	/**
	 * ADD a system ROLE
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADVERT_MANAGE)
	public String add(){
		try{
			if(validateInput(true)){
				advertImgMsg = uploadAndAddDate(true);
				if(advertImgMsg != null){
					return Constants.RTN_SYS_ADD;
				}
				//查询所有数据
				searchData();
				clear();
				if(dto.getPosition().equals(ApplicationConstant.SERVICE_ADVERT_HUNTHEAD_001)){
					HtmlGeneration.getInstance().refreshHeadhunterCenterAdvertPage();
				}else if(dto.getPosition().equals(ApplicationConstant.SERVICE_ADVERT_TALENTS_FOUND_001)){
					HtmlGeneration.getInstance().refreshPeopleExcavateAdvertPage();
				}else if(dto.getPosition().equals(ApplicationConstant.SERVICE_ADVERT_HOMEPAGE_TOP)){
					HtmlGeneration.getInstance().refreshTopAdvert();
				}else if(ApplicationConstant.SERVICE_ADVERT_SALON_001.equals(dto.getPosition())){
					HtmlGeneration.getInstance().refreshSalonAdvertPage();
				}
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_ADD;
			}
		}catch(Exception e){
			logger.error(e);
			return Constants.RTN_SYS_ADD;
		}
	}
	private String uploadAndAddDate(Boolean isAdd) throws Exception{

		int bufferSize = Constants.UPLOAD_BUFFER_SIZE;
		//String filePath = Constants.REALPATH +  "WEB-INF" + File.separator + p.getProperty(Constants.FILE_UPLOAD_PATH);
		String filePath = PropertiesValue.getPropertyValue("uploadFilePath");//上传路径
		if(!new File(filePath).exists()){
			new File(filePath).mkdir();
		}
		String fileType = null;
		String fileName = null;
		String newFilepath = null;
		if(dto.getFile() != null){
			//检查文件大小
			double limit = 0.0;
			if(Constants.ADVERT_TYPE_FLASH.equals(dto.getType())){
				limit = Double.valueOf(PropertiesValue.getPropertyValue(ApplicationConstant.UPLOADFILE_FLASH_SIZE));
			}else{
				limit = Double.valueOf(PropertiesValue.getPropertyValue(ApplicationConstant.UPLOADFILE_IMG_SIZE));
			}
			if(!FileUtil.getInstance().checkFileSizeByM(dto.getFile(), limit)){
				return "文件超过大小限制["+limit+"M]";
			}
			//验证文件类型
			fileType = StringUtil.newInstance().getFileType(dto.getFile());
			if(fileType == null && Constants.ADVERT_TYPE_FLASH.equals(dto.getType())){
				fileType = "swf";
			}else{
//				if(dto.getFile() != null && Constants.ADVERT_TYPE_IMAGE.equals(dto.getType())){
//					if(!StringUtil.newInstance().isImage(dto.getFile())){
//						advertImgMsg = "上传的文件不是图片";
//					}
//				}
				if(!StringUtil.getImgType().containsKey(fileType)){
					return "上传的文件不是图片";
				}
			}
			fileName = StringUtil.newInstance().createIdTwenty() + "." + fileType;
			newFilepath = filePath + File.separator + fileName;
			dto.setImgName(fileName);

		}
		//增加数据
		if(isAdd){
			dto.setStatus(Constants.ADVERT_STATUS_OPEN);
			sysAdService.add(dto);
		}else{
			sysAdService.update(dto);
		}
		if(dto.getFile() != null){
			//修改数据
			copy(dto.getFile(),new File(newFilepath),bufferSize);
		}
		return null;
	}
	private void copy(File src, File dst,int bufferSize) {
        try {
           InputStream in = null ;
           OutputStream out = null ;
            try {
               in = new BufferedInputStream( new FileInputStream(src), bufferSize);
               out = new BufferedOutputStream( new FileOutputStream(dst), bufferSize);
                byte [] buffer = new byte [bufferSize];
                while (in.read(buffer) > 0 ) {
                   out.write(buffer);
               }
           } finally {
                if ( null != in) {
                   in.close();
               }
                if ( null != out) {
                   out.close();
               }
           }
       } catch (Exception e) {
           logger.error(e);
       }
   }
	/**
	 * 打开
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADVERT_MANAGE)
	public String open(){

		try {
			SysAdDto tmp = new SysAdDto();
			tmp.setId(dto.getId());
			dto = sysAdService.search(tmp);
			dto.setStatus(Constants.ADVERT_STATUS_OPEN);
			//时间差计算
			Date stopDate = dto.getStopDate();
			if(stopDate != null){
				Long spea = new Date().getTime() - stopDate.getTime();
				Long stopDateL = dto.getEndDate().getTime() + spea;
				dto.setEndDate(new Date(stopDateL));
			}
			sysAdService.update(dto);
			searchData();
		} catch (ParseException e) {
			logger.error(e);
		}
		refreshPage(dto.getPosition());
		cleanRefresh();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * 暂停
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADVERT_MANAGE)
	public String stop(){
		SysAdDto tmp = new SysAdDto();
		for(String id : removeBox){
			tmp.setId(Long.valueOf(id));
			tmp.setStopDate(new Date());
			tmp.setStatus(Constants.ADVERT_STATUS_STOP);
			sysAdService.updateStatus(tmp);
		}
		try {
			searchData();
		} catch (ParseException e) {
			logger.error(e);
		}
		for(String positionid : positionID){
			refreshPage(positionid);
		}
		cleanRefresh();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * Edit a system increment service name
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADVERT_MANAGE)
	public String edit(){
		try{
			String oldPosition=dto.getOldPosition();
			if(validateInput(false)){
				advertImgMsg = uploadAndAddDate(false);
				if(advertImgMsg != null){
					return Constants.RTN_SYS_EDIT;
				}
				//查询所有数据
				searchData();
				if(!dto.getPosition().equals(oldPosition)){
					refreshPage(oldPosition);
					cleanRefresh();
					refreshPage(dto.getPosition());
				}else{
					refreshPage(dto.getPosition());
				}
				cleanRefresh();
//				if(dto.getPosition().equals(ApplicationConstant.SERVICE_ADVERT_HUNTHEAD_001)){
//					HtmlGeneration.getInstance().refreshHeadhunterCenterAdvertPage();
//				}else if(dto.getPosition().equals(ApplicationConstant.SERVICE_ADVERT_TALENTS_FOUND_001)){
//					HtmlGeneration.getInstance().refreshPeopleExcavateAdvertPage();
//				}else if(dto.getPosition().equals(ApplicationConstant.SERVICE_ADVERT_HOMEPAGE_TOP)){
//					HtmlGeneration.getInstance().refreshTopAdvert();
//				}else if(ApplicationConstant.SERVICE_ADVERT_SALON_001.equals(dto.getPosition())){
//					HtmlGeneration.getInstance().refreshSalonAdvertPage();
//				}

				clear();
				return Constants.RTN_SYS_MAIN;
			}else{
				return Constants.RTN_SYS_EDIT;
			}
		}catch(Exception e){
			logger.error(e);
			return Constants.RTN_SYS_EDIT;
		}
	}
	boolean hunthead001 = true;
	boolean talents001 = true;
	boolean top001 = true;
	boolean salon001 = true;
	private void refreshPage(String position){
		if(position.equals(ApplicationConstant.SERVICE_ADVERT_HUNTHEAD_001)&& hunthead001){
			HtmlGeneration.getInstance().refreshHeadhunterCenterAdvertPage();
			hunthead001=false;
		}
		if(position.equals(ApplicationConstant.SERVICE_ADVERT_TALENTS_FOUND_001) && talents001){
			HtmlGeneration.getInstance().refreshPeopleExcavateAdvertPage();
			talents001=false;
		}
		if(position.equals(ApplicationConstant.SERVICE_ADVERT_HOMEPAGE_TOP) && top001){
			HtmlGeneration.getInstance().refreshTopAdvert();
			top001=false;
		}
		if(position.equals(ApplicationConstant.SERVICE_ADVERT_SALON_001) && salon001){
			HtmlGeneration.getInstance().refreshSalonAdvertPage();
			salon001=false;
		}

	}
	private void cleanRefresh(){
		 hunthead001 = true;
		 talents001 = true;
		 top001 = true;
		 salon001=true;
	}
	/**
	 * 删除广告
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ADVERT_MANAGE)
	public String remove(){
		SysAdDto tmp = new SysAdDto();
		for(String id : removeBox){
			tmp.setId(Long.valueOf(id));
			sysAdService.remove(tmp);
		}
		try {
			searchData();
		} catch (ParseException e) {
			logger.error(e);
		}
		for(String positionid : positionID){

			refreshPage(positionid);

		}
		cleanRefresh();
		return Constants.RTN_SYS_MAIN;
	}
	/**
	 * On click cancel button
	 * @return
	 */
	public String cancel(){
		clear();
		try{
		}catch(Exception e){
			logger.error(e);
		}
		return Constants.RTN_SYS_MAIN;
	}

	private void clear(){
		titleMsg = "";
		companyMsg = "";
		startDateMsg = "";
		endDateMsg = "";
		typeMsg = "";
		cityMsg = "";
		widthMsg = "";
		heightMsg = "";
		positionMsg = "";
		advertImgMsg = "";
		typeContentMsg = "";
		cityMsg=  "";
		msg = "";
		adindexMsg="";
	}

	/**
	 * validate page form fields.
	 * @return
	 * @throws ParseException
	 */
	private boolean validateInput(Boolean isAdd) throws ParseException{
		StringUtil util = StringUtil.newInstance();
		clear();
		if(util.sNullTrim(dto.getTitle()).equals("")){
			titleMsg = "标题不能为空";
		}

		//检查标题是否已存在
		if(!util.sNullTrim(dto.getTitle()).equals("") &&!dto.getTitle().equals(dto.getOldTitle())){
			SysAdDto tmp = new SysAdDto();
			tmp.setTitle(dto.getTitle());
			Object obj = sysAdService.search(tmp);
			if(obj != null){
				titleMsg = "广告标题已经存在";
			}
		}
		if(util.sNullTrim(dto.getCompany()).equals("")){
			companyMsg = "公司不能为空";
		}
		if(util.sNullTrim(dto.getCity()).equals("")){
			cityMsg = "公司所在城市不能为空";
		}
		if(dto.getStartDate() == null){
			startDateMsg = "时间段开始日期不能为空";
		}
		if(dto.getEndDate() == null){
			endDateMsg = "时间段结束日期不能为空";
		}
		if(util.sNullTrim(dto.getType()).equals("")){
			typeMsg = "广告类型不能为空";
		}
		if(util.sNullTrim(dto.getTypeContent()).equals("")){
			typeContentMsg = "广告类型信息不能为空";
		}
		if(util.sNullTrim(dto.getWidth()).equals("")){
			widthMsg = "广告长度不能为空";
		}
		if(!util.isDouble(dto.getWidth(),false)){
			widthMsg = "广告长度必须是数字类型";
		}
		if(util.sNullTrim(dto.getHeight()).equals("")){
			heightMsg = "广告宽度不能为空";
		}
		if(!util.isDouble(dto.getHeight(),false)){
			heightMsg = "广告长度必须是数字类型";
		}
		if(util.sNullTrim(dto.getPosition()).length() <=8 ){
			positionMsg = "请选择具体位置";
		}
		if(!util.isDouble(dto.getAdindex(),false)){
			widthMsg = "广告索引必须是数字类型";
		}


		if(titleMsg.equals("") && companyMsg.equals("") && startDateMsg.equals("")
				&& endDateMsg.equals("") && typeMsg.equals("") && widthMsg.equals("")
				&& heightMsg.equals("") && positionMsg.equals("") && advertImgMsg.equals("") && cityMsg.equals("")){
			SysAdDto sysAdDto = new SysAdDto();

			if("017-1001-1001".equals(dto.getPosition())){
				sysAdDto.setPosition(dto.getPosition());
				PageInfo pageInfo =new PageInfo();
				pageInfo.setPageSize(10);
				List<SysAdDto> sysAdDtoList = sysAdService.searchList(pageInfo,sysAdDto);
				if(sysAdDtoList!=null && sysAdDtoList.size()>0){

					if(dto.getId()==null || dto.getId().compareTo(sysAdDtoList.get(0).getId())!=0){
						if(dto.getStartDate().before(sysAdDtoList.get(0).getEndDate())){
							startDateMsg="这个位置的广告时段已经设置，请更改时间段！";
							return false;
						}
					}
				}
			}
			return true;
		}
		if(!util.sNullTrim(dto.getProvince()).equals("")){
			citys = cityList = RegionDataUtil.queryCityListByProvinceCode(dto.getProvince());
		}
		dto.setFile(null);
		return false;
	}
	/**
	 * 查询数据列表
	 * @throws ParseException
	 */
	private void searchData() throws ParseException{
		//查询数据
		if(this.pageInfo == null){
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(Constants.SYS_INCREMENT_COMMENT_SIZE);
		this.dtoList = sysAdService.searchList(this.pageInfo, searchdto);
		//位置代码转名字
		if(this.dtoList != null){
			for(int i = 0;i < dtoList.size();i++){
				SysAdDto tmp = dtoList.get(i);
				String name = all_postions.get(tmp.getPosition());
				dtoList.get(i).setPositionName(name);
			}
		}

	}
	public SysAdDto getDto() {
		return dto;
	}
	public void setDto(SysAdDto dto) {
		this.dto = dto;
	}
	public SysAdDto getSearchdto() {
		return searchdto;
	}
	public void setSearchdto(SysAdDto searchdto) {
		this.searchdto = searchdto;
	}
	public List<SysAdDto> getDtoList() {
		return dtoList;
	}
	public void setDtoList(List<SysAdDto> dtoList) {
		this.dtoList = dtoList;
	}
	public String getTitleMsg() {
		return titleMsg;
	}
	public void setTitleMsg(String titleMsg) {
		this.titleMsg = titleMsg;
	}
	public String getCompanyMsg() {
		return companyMsg;
	}
	public void setCompanyMsg(String companyMsg) {
		this.companyMsg = companyMsg;
	}
	public String getStartDateMsg() {
		return startDateMsg;
	}
	public void setStartDateMsg(String startDateMsg) {
		this.startDateMsg = startDateMsg;
	}
	public String getEndDateMsg() {
		return endDateMsg;
	}
	public void setEndDateMsg(String endDateMsg) {
		this.endDateMsg = endDateMsg;
	}
	public String getTypeMsg() {
		return typeMsg;
	}
	public void setTypeMsg(String typeMsg) {
		this.typeMsg = typeMsg;
	}
	public String getWidthMsg() {
		return widthMsg;
	}
	public void setWidthMsg(String widthMsg) {
		this.widthMsg = widthMsg;
	}
	public String getHeightMsg() {
		return heightMsg;
	}
	public void setHeightMsg(String heightMsg) {
		this.heightMsg = heightMsg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ISysAdService getSysAdService() {
		return sysAdService;
	}
	public void setSysAdService(ISysAdService sysAdService) {
		this.sysAdService = sysAdService;
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
	public String getPositionMsg() {
		return positionMsg;
	}
	public void setPositionMsg(String positionMsg) {
		this.positionMsg = positionMsg;
	}
	public Map<String, String> getAll_postions() {
		return all_postions;
	}
	public void setAll_postions(Map<String, String> allPostions) {
		all_postions = allPostions;
	}
	public Map<String, String> getTypes() {
		return types;
	}
	public void setTypes(Map<String, String> types) {
		this.types = types;
	}
	public String[] getStopBox() {
		return stopBox;
	}
	public void setStopBox(String[] stopBox) {
		this.stopBox = stopBox;
	}
	public String getAdvertImgMsg() {
		return advertImgMsg;
	}
	public void setAdvertImgMsg(String advertImgMsg) {
		this.advertImgMsg = advertImgMsg;
	}
	public String getTypeContentMsg() {
		return typeContentMsg;
	}
	public void setTypeContentMsg(String typeContentMsg) {
		this.typeContentMsg = typeContentMsg;
	}
	public List<CityDto> getCitys() {
		return citys;
	}
	public void setCitys(List<CityDto> citys) {
		this.citys = citys;
	}
	public List<ProvinceDto> getProvinces() {
		return provinces;
	}
	public void setProvinces(List<ProvinceDto> provinces) {
		this.provinces = provinces;
	}
	public String getCityMsg() {
		return cityMsg;
	}
	public void setCityMsg(String cityMsg) {
		this.cityMsg = cityMsg;
	}
	public String getAdindexMsg() {
		return adindexMsg;
	}
	public void setAdindexMsg(String adindexMsg) {
		this.adindexMsg = adindexMsg;
	}



}

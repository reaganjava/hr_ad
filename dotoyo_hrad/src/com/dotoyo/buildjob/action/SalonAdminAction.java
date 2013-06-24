package com.dotoyo.buildjob.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.dotoyo.buildjob.common.constant.ApplicationConstant;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.html.HtmlGeneration;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.common.service.IDataOperationService;
import com.dotoyo.buildjob.common.user.dto.LoginUserInfoDto;
import com.dotoyo.buildjob.common.user.service.IUserService;
import com.dotoyo.buildjob.common.util.DateUtil;
import com.dotoyo.buildjob.common.util.FileUtil;
import com.dotoyo.buildjob.common.util.PropertiesValue;
import com.dotoyo.buildjob.common.util.StringUtil;
import com.dotoyo.buildjob.common.util.UploadFileProcessUtil;
import com.dotoyo.buildjob.innovationSalon.dto.CommentDto;
import com.dotoyo.buildjob.innovationSalon.dto.HostUnitDto;
import com.dotoyo.buildjob.innovationSalon.dto.SalonArticleDto;
import com.dotoyo.buildjob.innovationSalon.dto.SalonInfoDto;
import com.dotoyo.buildjob.innovationSalon.dto.SalonMediaDto;
import com.dotoyo.buildjob.innovationSalon.service.ISalonManageService;
import com.dotoyo.buildjob.innovationSalon.service.ISalonSearchService;
import com.dotoyo.buildjob.innovationSalon.service.ISalonService;
import com.dotoyo.buildjob.innovationSalon.vo.SalonSearchVo;
import com.dotoyo.buildjob.sys.service.ISalonAdminService;
import com.dotoyo.buildjob.sys.service.ISysPointService;
import com.dotoyo.buildjob.sys.service.impl.SalonAdminServiceImpl;
import com.dotoyo.buildjob.systemManage.dto.SysParameterDto;
import com.dotoyo.buildjob.systemManage.service.ISysParameterService;

/**
 *
 * @author Bill Xu
 * @dateCreated 2011-02-16
 * @description
 */
public class SalonAdminAction extends SysAction {
	private static Logger logger = Logger.getLogger(SalonAdminAction.class);
	private static final long serialVersionUID = 1L;
	private ISalonSearchService salonSearchService;
	private ISalonService salonService;
	private ISalonAdminService salonAdminService;
	private ISalonManageService salonManageService;
	private ISysParameterService sysParameterService;
	private IDataOperationService dataOperationService;
	private List<SalonInfoDto> salonList;// 沙龙列表
	private List<SalonInfoDto> settedSalonList;// 沙龙列表(已经设置了的沙龙)
	private List<SalonArticleDto> articleList;// 沙龙列表
	private List<SalonMediaDto> mediaList;// 图片或视频列表
	private List<CommentDto> commentList;// 评论列表
	private List<HostUnitDto>hostUnitList;// 主办单位
	private ISysPointService sysPointService;
	private IUserService userService;
	private PageInfo pageInfo;
	private SalonInfoDto salonInfoDto;
	private SalonMediaDto salonMediaDto;
	private SalonArticleDto salonArticleDto;
	private CommentDto commentDto;
	private HostUnitDto hostUnitDto;
	private SysParameterDto sysParameterDto;



	private String strStartDate;
	private String strEndDate;

	private SalonSearchVo salonSearchVo;

	private String[] arrayIds;
	private String audiFlag;
	private File uploadPicFile;// 图片
	private String uploadPicFileFileName;


	private String goFrom = "0";


	private String[] updateIds;
	private String[] updateOrders;
	private String[] addOrders;
	private String uploadFilePath;
	private String isNew;


	// to audition salon manage page
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_SALON_MANAGE_VERIFY)
	public String toAudiSalonPage() {
		int pageSize = 10;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);
		this.salonSearchVo = null;
		this.salonList = this.salonSearchService.querySalonList(pageInfo,
				this.salonSearchVo);
		return "audiSalon";
	}
	//删除沙龙
	public String deleteSalon() {
		try{
			dataOperationService.removeSalonByIds(arrayIds);
		}catch(Exception e){
			logger.error(e);
		}
		this.addActionMessage("删除成功!");
		//刷新沙龙页面
		HtmlGeneration.getInstance().refreshSalonClassicPage(null);
		HtmlGeneration.getInstance().refreshSalonOfflinePage(null);
		HtmlGeneration.getInstance().refreshSalonOnlinePage(null);
        return this.toAudiSalonPage();
	}
	//沙龙审核
	public String autiSalon() {
		String ids = StringUtils.join(this.arrayIds,",");
		this.salonAdminService.updateSalon4Audi(ids, this.audiFlag);
		//刷新沙龙页面
		HtmlGeneration.getInstance().refreshSalonClassicPage(null);
		HtmlGeneration.getInstance().refreshSalonOfflinePage(null);
		HtmlGeneration.getInstance().refreshSalonOnlinePage(null);
        return this.toAudiSalonPage();
	}


	public String searchSalon() {
		int pageSize = 10;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);

		if (this.strStartDate != null
				&& !this.strStartDate.equalsIgnoreCase("")) {
			this.salonSearchVo.setStartDate(DateUtil
					.parseToDate(this.strStartDate));
		}
		if (this.strEndDate != null && !this.strEndDate.equalsIgnoreCase("")) {
			this.salonSearchVo
					.setEndDate(DateUtil.parseToDate(this.strEndDate));
		}

		if(this.goFrom.equalsIgnoreCase("0")){
		this.salonList = this.salonSearchService.querySalonList(pageInfo,
					this.salonSearchVo);
		  return "audiSalon";
		}else if(this.goFrom.equalsIgnoreCase("1")){
			return this.toSetSalonHomePage();
		}else{
			return this.toSetSalonPage();
		}
	}

	// to set salon manage page
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_SALON_MANAGE_CLASSIC_SALON_SETTING)
	public String toSetSalonPage() {
		int pageSize = 10;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);
		if(this.salonSearchVo == null){
			this.salonSearchVo = new SalonSearchVo();
		}
		this.salonSearchVo.setStatus(ApplicationConstant.STATUS_PASSED);
		this.salonList = this.salonSearchService.querySalonList(pageInfo,
				this.salonSearchVo);
		return "setSalon";
	}
	//设置为经典沙龙
	public String setSalon() {
		String ids = StringUtils.join(this.arrayIds,",");
		this.salonAdminService.updateSalon4Excellent(ids, ApplicationConstant.SALON_IS_EXCELLENT_YES);
		HtmlGeneration.getInstance().refreshSalonClassicPage(null);
        return this.toSetSalonPage();
	}

	//取消经典沙龙
	public String cancelClassicSalon() {
		String ids = StringUtils.join(this.arrayIds,",");
		this.salonAdminService.updateSalon4Excellent(ids, ApplicationConstant.SALON_IS_EXCELLENT_NO);
		//刷新沙龙页面之经典沙龙
		HtmlGeneration.getInstance().refreshSalonClassicPage(null);
        return this.toSetSalonPage();
	}

	// to set salon article manage page
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_SALON_MANAGE_CLASSIC_ARTICLES_SETTING)
	public String toSetSalonArticlePage() {
		int pageSize = 10;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);
		this.salonSearchVo = new SalonSearchVo();
		this.salonSearchVo.setStatus(ApplicationConstant.STATUS_PASSED);
		this.articleList = this.salonSearchService.querySalonArticleList(pageInfo,
				this.salonSearchVo);
		return "setSalonArticle";
	}

	//设置为沙龙经典文章
	public String setSalonArticle() {
		String ids = StringUtils.join(this.arrayIds,",");
		this.salonAdminService.updateSalonArticle4Excellent(ids, ApplicationConstant.ARTICLE_IS_EXCELLENT_YES);
		//刷新沙龙页面之经典文章
		HtmlGeneration.getInstance().refreshSalonClassicArticlePage(null);
        return this.toSetSalonArticlePage();
	}

	//取消沙龙经典文章
	public String cancelSalonArticle() {
		String ids = StringUtils.join(this.arrayIds,",");
		this.salonAdminService.updateSalonArticle4Excellent(ids, ApplicationConstant.ARTICLE_IS_EXCELLENT_NO);
		//刷新沙龙页面之经典文章
		HtmlGeneration.getInstance().refreshSalonClassicArticlePage(null);
        return this.toSetSalonArticlePage();
	}

	//搜索文章
	public String searchArticle() {

		if (this.strStartDate != null
				&& !this.strStartDate.equalsIgnoreCase("")) {
			this.salonSearchVo.setStartDate(DateUtil
					.parseToDate(this.strStartDate));
		}
		if (this.strEndDate != null && !this.strEndDate.equalsIgnoreCase("")) {
			this.salonSearchVo
					.setEndDate(DateUtil.parseToDate(this.strEndDate));
		}

		int pageSize = 10;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);
		if("Y".equalsIgnoreCase(isNew)){
			salonSearchVo.setIsNew("Y");
		}
		this.articleList = this.salonSearchService.querySalonArticleList(
				pageInfo, salonSearchVo);
		if(this.goFrom.equalsIgnoreCase("1")){
			return "salonArticleManage";
		}else{
            return "setSalonArticle";
		}
	}



	// to host unit  manage page
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_SALON_MANAGE_HOST_UNIT_SETTING)
	public String toHostUnitManagePage() {
		int pageSize = 10;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);
		this.hostUnitList = this.salonAdminService.queryHostUnitList(pageInfo);
		this.uploadFilePath=PropertiesValue.getPropertyValueByKey(ApplicationConstant.FILE_SERVER_PATH);
		return "hostUnitManage";
	}
	// to add host unit page
	public String toAddHostUnitPage() {
		return "addHostUnit";
	}

	// to modify host unit page
	public String toModifyHostUnitPage() {
		this.hostUnitDto = this.salonService.getHostUnitById(this.hostUnitDto.getId());
		return "modifyHostUnit";
	}

	// delete host unit
	public String deleteHostUnit() {
		String ids = StringUtils.join(this.arrayIds,",");
		this.salonAdminService.deleteHostUnit(ids);
		//刷新沙龙页面之主办单位
		HtmlGeneration.getInstance().refreshSalonHostUnitPage(null);
		return this.toHostUnitManagePage();
	}
	// modify host unit
	public String modifyHostUnit() {
		this.salonAdminService.modifyHostUnit(hostUnitDto);
		//刷新沙龙页面之主办单位
		HtmlGeneration.getInstance().refreshSalonHostUnitPage(null);
		return this.toHostUnitManagePage();
	}
	// add host unit
	public String addHostUnit() {
		//检查文件大小
		String size = PropertiesValue.getPropertyValueByKey(ApplicationConstant.UPLOADFILE_IMG_SIZE);
		Boolean bl = FileUtil.getInstance().checkFileSizeByM(uploadPicFile, Double.valueOf(size));
		if(!bl){
			this.addActionMessage("图片文件超过大小限制[" + size +"M]");
			return "addHostUnit";
		}
		String newPicFileName =UploadFileProcessUtil.uploadFile(this.uploadPicFile, this.uploadPicFileFileName);
		hostUnitDto.setPictureName(newPicFileName);
		this.salonAdminService.addHostUnit(hostUnitDto);
		///刷新沙龙页面之主办单位
		HtmlGeneration.getInstance().refreshSalonHostUnitPage(null);
		return this.toHostUnitManagePage();
	}
	public String cancel(){
		return toHostUnitManagePage();
	}

	//to setting salon home page display
	public String toSetSalonHomePage() {
	int pageSize = 10;
	if (this.pageInfo == null) {
		this.pageInfo = new PageInfo();
	}
	this.pageInfo.setPageSize(pageSize);
	if(this.salonSearchVo == null){
	  SalonSearchVo tempSalonSearchVo = new SalonSearchVo();
	  tempSalonSearchVo.setStatus(ApplicationConstant.STATUS_PASSED);

//      Date date = DateUtil.parseToDate(DateUtil.getDateString(new Date()));
//
//	  tempSalonSearchVo.setEndDate(date);
//	  tempSalonSearchVo.setStartDate(date);

	  this.salonSearchVo = tempSalonSearchVo;
	}
	this.salonList = this.salonAdminService.searchSalonList4SetHomePage(pageInfo,this.salonSearchVo);
	return "setSalonHomePage";
	}

	//to setting confirm salon home page display	
	public String toSetConfirmSalonHomePage() {
	String ids = StringUtils.join(this.arrayIds,",");
	this.salonList = this.salonAdminService.querySalonListByIds();
	this.settedSalonList = this.salonAdminService.querySettedSalonList();
	return "setConfirmSalonHomePage";
	}


	// to salon article manage
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_ARTICLES_MANAGE)
	public String toSalonArticleManage() {
		int pageSize = 10;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);
		if(salonSearchVo==null){
			salonSearchVo = new SalonSearchVo();
		}
		if("Y".equalsIgnoreCase(isNew)){
			salonSearchVo.setIsNew("Y");
		}
		this.articleList = this.salonSearchService.querySalonArticleList(pageInfo,
				this.salonSearchVo);
		return "salonArticleManage";
	}

	//删除沙龙文章
	public String deleteSalonArticle() {
		try{
			dataOperationService.removeArticleByIds(arrayIds);
			//刷新沙龙页面之经典文章
			HtmlGeneration.getInstance().refreshSalonClassicArticlePage(null);

		}catch(Exception e){
			logger.error(e);
		}
		this.addActionMessage("删除成功!");
		
        return this.toSalonArticleManage();
	}

	//审核沙龙文章
	public String autiSalonArticle() {
		String ids = StringUtils.join(this.arrayIds,",");
		//更新用户积分
		Long pointArticle = this.sysPointService.getPointValueByCode(ApplicationConstant.POINT_UPLOADING_ARTICLE);
		for(String id : arrayIds){
			SalonArticleDto article =  salonService.getSalonArticleById(Long.valueOf(id));
			updateUserPoints(article.getArticleStatus(),article.getAuthor(),pointArticle);
		}
		this.salonAdminService.updateAudiSalonArticle(ids, this.audiFlag);
		this.salonAdminService.updateMedia4AudiByArticleId(ids, audiFlag);
		//刷新沙龙页面之经典文章
		HtmlGeneration.getInstance().refreshSalonClassicArticlePage(null);
        return this.toSalonArticleManage();
	}

	//设置为推荐文章
	public String setRecommentSalonArticle() {
		String ids = StringUtils.join(this.arrayIds,",");
		//判断所选的文章是否存在没有通过状态的
		if(this.salonAdminService.existNotPassedArticle(ids)){
			this.addActionMessage(ApplicationConstant.EXIST_NO_PASS_MSG);
		}else{
		  this.salonAdminService.updateSalonArticle4Recommend(ids,ApplicationConstant.ARTICLE_IS_RECOMMENT_YES);
		}
		//刷新沙龙页面之经典文章
		HtmlGeneration.getInstance().refreshSalonClassicArticlePage(null);
        return this.toSalonArticleManage();
	}
	//设置为精华文章
	public String setCreamSalonArticle() {
		String ids = StringUtils.join(this.arrayIds,",");
		//判断所选的文章是否存在没有通过状态的
		if(this.salonAdminService.existNotPassedArticle(ids)){
			this.addActionMessage(ApplicationConstant.EXIST_NO_PASS_MSG);
		}else{
		  this.salonAdminService.updateSalonArticle4Cream(ids,ApplicationConstant.ARTICLE_IS_CREAM_YES);
		}
		//刷新沙龙页面之经典文章
		HtmlGeneration.getInstance().refreshSalonClassicArticlePage(null);
        return this.toSalonArticleManage();
	}

	//设置为经典文章
	public String setClassicSalonArticle() {
		String ids = StringUtils.join(this.arrayIds,",");
		//判断所选的文章是否存在没有通过状态的
		if(this.salonAdminService.existNotPassedArticle(ids)){
			this.addActionMessage(ApplicationConstant.EXIST_NO_PASS_MSG);
		}else{
			this.salonAdminService.updateSalonArticle4Excellent(ids,ApplicationConstant.ARTICLE_IS_EXCELLENT_YES);
			//刷新沙龙页面之经典文章
			HtmlGeneration.getInstance().refreshSalonClassicArticlePage(null);
		}
        return this.toSalonArticleManage();
	}
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_COMMENT_MANAGE)
	public String toCommentManagePage(){
		int pageSize = 10;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);
		SalonSearchVo tempSalonSearchVo = new SalonSearchVo();
		this.commentList = this.salonManageService.searchAllCommentList(pageInfo, tempSalonSearchVo);
		return "commentManage";
	}
	//搜索评论
	public String searchComment() {
		int pageSize = 10;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);

		if (this.strStartDate != null
				&& !this.strStartDate.equalsIgnoreCase("")) {
			this.salonSearchVo.setStartDate(DateUtil
					.parseToDate(this.strStartDate));
		}
		if (this.strEndDate != null && !this.strEndDate.equalsIgnoreCase("")) {
			this.salonSearchVo
					.setEndDate(DateUtil.parseToDate(this.strEndDate));
		}
		this.commentList = this.salonManageService.searchAllCommentList(pageInfo, this.salonSearchVo);
		return "commentManage";
	}
	//删除评论
	public String deleteComment() {
		String ids = StringUtils.join(this.arrayIds,",");
		this.salonManageService.deleteComment(ids);
        return this.toCommentManagePage();
	}

	//审核评论
	public String autiComment() {
		String ids = StringUtils.join(this.arrayIds,",");

		Long commentpoint = this.sysPointService.getPointValueByCode(ApplicationConstant.POINT_COMMENT);
		//更新用户积分
		for(String id : arrayIds){
			CommentDto comment = salonManageService.getCommentById(Long.valueOf(id));
			updateUserPoints(comment.getStatus(),comment.getAuthor(),commentpoint);
		}
		this.salonAdminService.updateAudiComment(ids,this.audiFlag);
        return this.toCommentManagePage();
	}

	//设置沙龙首页显示
	public String setSalonHomePageDisplay() {
		if(!this.checkOrderInput(this.addOrders)||!this.checkOrderInput(this.updateOrders)){
		  this.addActionMessage(ApplicationConstant.ORDER_SEQ_NO_PASS_MSG);
		  return this.toSetConfirmSalonHomePage();
		}
		
		
		if(this.updateIds!=null){
		this.salonAdminService.deleteSettedSalonList();
			if(!this.checkOrderInput(this.updateOrders)){
				  this.addActionMessage(ApplicationConstant.ORDER_SEQ_NO_PASS_MSG);
				  return this.toSetConfirmSalonHomePage();
			}
			this.salonAdminService.addSettedSalonList(this.updateIds, this.updateOrders);
	    	//this.salonAdminService.updateSettedSalonList(this.updateIds, this.updateOrders);
		}
		//刷新沙龙页面
		HtmlGeneration.getInstance().refreshSalonClassicPage(null);
		HtmlGeneration.getInstance().refreshSalonOfflinePage(null);
		HtmlGeneration.getInstance().refreshSalonOnlinePage(null);
	    return this.toAudiSalonPage();
	}

	//to media list page(off line)
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_SALON_UNLINE_IMGS_STOREAGE)
	public String toMediaListPage(){
		int pageSize = 8;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);

		if(this.salonSearchVo == null){
			 this.salonSearchVo = new SalonSearchVo();
		}
		this.salonSearchVo.setMediaType(this.salonMediaDto.getMediaType());
		this.salonSearchVo.setSalonType(ApplicationConstant.SALON_TYPE_OFFLINE);
		this.salonList = this.salonAdminService.querySalonMediaList4Admin(pageInfo,this.salonSearchVo);

		this.uploadFilePath=PropertiesValue.getPropertyValueByKey(ApplicationConstant.FILE_SERVER_PATH);
		if (this.salonMediaDto.getMediaType()
				.equalsIgnoreCase(ApplicationConstant.MEDIA_TYPE_PICTURE)) {
			this.sysParameterDto = this.sysParameterService.getSysParameterDtoBycode(ApplicationConstant.MEDIA_AUDI_FLAG_PICTURE_CODE);
			return "salonOfflinePictureList";
		} else {
			this.sysParameterDto = this.sysParameterService.getSysParameterDtoBycode(ApplicationConstant.MEDIA_AUDI_FLAG_VIDEO_CODE);
			return "salonOfflineVideoList";
		}
	}
	/**
	 * 视频库
	 * @return
	 */
	@AuthFunction(functionCode=FunctionCodeConstant.FUN_SALON_UNLINE_VIDEOS)
	public String toMediaVideoListPage(){
		return toMediaListPage();
	}
	//设置图片或者视频上传是否需要审核
	public String setMediaAudiFlag() {
		if (this.salonMediaDto.getMediaType().equalsIgnoreCase(ApplicationConstant.MEDIA_TYPE_PICTURE)) {
			this.sysParameterService.setSysParameterValue(ApplicationConstant.MEDIA_AUDI_FLAG_PICTURE_CODE, this.sysParameterDto.getSysValue());
		}else{
			this.sysParameterService.setSysParameterValue(ApplicationConstant.MEDIA_AUDI_FLAG_VIDEO_CODE, this.sysParameterDto.getSysValue());
		}
		this.addActionMessage("设置成功!");
        this.salonSearchVo = null;
		return this.toMediaListPage();
	}

	//to media list page for salon
	public String toMediaListBySalonPage(){

		int pageSize = 8;
		if (this.pageInfo == null) {
			this.pageInfo = new PageInfo();
		}
		this.pageInfo.setPageSize(pageSize);
		this.mediaList = this.salonManageService.querySalonMediaListBySalonId(pageInfo, this.salonMediaDto.getSalonId(), this.salonMediaDto.getMediaType(), this.salonMediaDto.getStatus(),null);

		this.uploadFilePath=PropertiesValue.getPropertyValueByKey(ApplicationConstant.FILE_SERVER_PATH);
		if (this.salonMediaDto.getMediaType()
				.equalsIgnoreCase(ApplicationConstant.MEDIA_TYPE_PICTURE)) {
			return "pictureList4Salon";
		} else {
			return "videoList4Salon";
		}
	}

	//审核图片或者视频
	public String autiMedia() {
		String ids = StringUtils.join(this.arrayIds,",");
		//更新用户积分
		Long picpoint = this.sysPointService.getPointValueByCode(ApplicationConstant.POINT_UPLOADING_PICTURE);
		Long vediopoint = this.sysPointService.getPointValueByCode(ApplicationConstant.POINT_UPLOADING_VIDEO);
		for(String id : arrayIds){
			SalonMediaDto media = salonSearchService.getSalonMediaById(Long.valueOf(id));
			//图片
			if(ApplicationConstant.MEDIA_TYPE_PICTURE.equals(media.getMediaType())){
				updateUserPoints(media.getStatus(),media.getAuthor(),picpoint);
			//视频
			}else{
				updateUserPoints(media.getStatus(),media.getAuthor(),vediopoint);
			}
		}
		this.salonAdminService.updateMedia4Audi(ids,this.audiFlag);
		this.addActionMessage("审核成功!");
        return this.toMediaListBySalonPage();
	}
	//审核图片或者视频 by salon
	public String autiMediaBySalon() {
		String ids = StringUtils.join(this.arrayIds,",");
		//更新用户积分
		Long picpoint = this.sysPointService.getPointValueByCode(ApplicationConstant.POINT_UPLOADING_PICTURE);
		Long vediopoint = this.sysPointService.getPointValueByCode(ApplicationConstant.POINT_UPLOADING_VIDEO);
		for(String salonId : arrayIds){
			List<SalonMediaDto> medialist = salonSearchService.querySalonMediaListBySalonId(null, Long.valueOf(salonId), this.salonMediaDto.getMediaType());
			for(SalonMediaDto tmp : medialist){
				//图片
				if(ApplicationConstant.MEDIA_TYPE_PICTURE.equals(tmp.getMediaType())){
					updateUserPoints(tmp.getStatus(),tmp.getAuthor(),picpoint);
				//视频
				}else{
					updateUserPoints(tmp.getStatus(),tmp.getAuthor(),vediopoint);
				}
			}
		}
		this.salonAdminService.audiMediaBySalon(ids, this.salonMediaDto.getMediaType(),this.audiFlag);
		this.addActionMessage("审核成功!");
        return this.toMediaListPage();
	}


	//删除图片或者视频
	public String deleteMedia() {
		try{
			if(salonMediaDto.getMediaType().equalsIgnoreCase(ApplicationConstant.MEDIA_TYPE_PICTURE)){
				dataOperationService.removePictureMediaByIds(arrayIds);
			}else{
				dataOperationService.removeVedioMediaByIds(arrayIds);
			}

		}catch(Exception e){
			logger.error(e);
		}
		this.addActionMessage("删除成功!");
        return this.toMediaListBySalonPage();
	}

	//删除图片或者视频 by salon ids
	public String deleteMediaBySalon() {
		try{
			if(salonMediaDto.getMediaType().equalsIgnoreCase(ApplicationConstant.MEDIA_TYPE_PICTURE)){
				dataOperationService.removePicturesMediaBySalon(arrayIds);
			}else{
				dataOperationService.removeVediosMediaBySalon(arrayIds);
			}
		}catch(Exception e){
			logger.error(e);
		}
		this.addActionMessage("删除成功!");
        return this.toMediaListPage();
	}

	//设置为经典媒介(图片或者视频)
	public String setMedia() {
		//判断所选择的id里面是否都是审核通过的。
		String ids = StringUtils.join(this.arrayIds,",");
		if(this.salonAdminService.existNotPassedMedia(ids)){
			this.addActionMessage(ApplicationConstant.EXIST_NO_PASS_MSG);
		}else{
		   this.salonAdminService.updateMedia4Excellent(ids, ApplicationConstant.MEDIA_IS_EXCELLENT_YES);
		}
        return this.toMediaListBySalonPage();
	}
	/**
	 * @param userId 更新积分的用户ID
	 * @param status 1 通过
	 * @param picpoint
	 */
	synchronized private void updateUserPoints(String status,Long userId,Long point){
		//加分的
		if(this.audiFlag.equals("1") && !status.equals(ApplicationConstant.STATUS_PASSED)){
			updateUserPoint(userId,point);
		}
		//减分的
		if(!this.audiFlag.equals("1") && status.equals(ApplicationConstant.STATUS_PASSED)){
			updateUserPoint(userId,-point);
		}
	}
	/**
	 * 更新用户积分
	 * @param userId
	 * @param point
	 */
	private void updateUserPoint(Long userId,Long point){
		LoginUserInfoDto tmp = userService.getUserById(userId);
		//用户已有积分值
		Long oldPoint = tmp.getPoint();
		//用户能增加的积分值
		Long respoint = StringUtil.newInstance().getAddPoint(oldPoint, point);
		this.sysPointService.updateUserPoint(userId, respoint);
	}
	//验证输入的顺序号的合法性
	private boolean checkOrderInput(String[] orders){
	   boolean blnInputOk = false;
	   if(orders!=null){
		   for(String orderSqe:orders){
			   if(StringUtils.isNumeric(orderSqe)){
				   blnInputOk =true;
			   }else{
				   blnInputOk =false;
				   break;
			   }
		   }
	   }else{
		   blnInputOk =true;
	   }
	   return blnInputOk;
	}
	public List<SalonInfoDto> getSalonList() {
		return salonList;
	}

	public void setSalonList(List<SalonInfoDto> salonList) {
		this.salonList = salonList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public SalonInfoDto getSalonInfoDto() {
		return salonInfoDto;
	}

	public void setSalonInfoDto(SalonInfoDto salonInfoDto) {
		this.salonInfoDto = salonInfoDto;
	}

	public String getStrStartDate() {
		return strStartDate;
	}

	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

	public String getStrEndDate() {
		return strEndDate;
	}

	public void setStrEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}

	public SalonSearchVo getSalonSearchVo() {
		return salonSearchVo;
	}

	public void setSalonSearchVo(SalonSearchVo salonSearchVo) {
		this.salonSearchVo = salonSearchVo;
	}

	public String[] getArrayIds() {
		return arrayIds;
	}

	public void setArrayIds(String[] arrayIds) {
		this.arrayIds = arrayIds;
	}

	public List<SalonArticleDto> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<SalonArticleDto> articleList) {
		this.articleList = articleList;
	}

	public List<SalonMediaDto> getMediaList() {
		return mediaList;
	}

	public void setMediaList(List<SalonMediaDto> mediaList) {
		this.mediaList = mediaList;
	}

	public SalonMediaDto getSalonMediaDto() {
		return salonMediaDto;
	}

	public void setSalonMediaDto(SalonMediaDto salonMediaDto) {
		this.salonMediaDto = salonMediaDto;
	}

	public SalonArticleDto getSalonArticleDto() {
		return salonArticleDto;
	}

	public void setSalonArticleDto(SalonArticleDto salonArticleDto) {
		this.salonArticleDto = salonArticleDto;
	}

	public List<CommentDto> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentDto> commentList) {
		this.commentList = commentList;
	}

	public CommentDto getCommentDto() {
		return commentDto;
	}

	public void setCommentDto(CommentDto commentDto) {
		this.commentDto = commentDto;
	}

	public LoginUserInfoDto getLoginUserInfoDto() {
		return loginUserInfoDto;
	}

	public void setLoginUserInfoDto(LoginUserInfoDto loginUserInfoDto) {
		this.loginUserInfoDto = loginUserInfoDto;
	}

	public ISalonSearchService getSalonSearchService() {
		return salonSearchService;
	}

	public void setSalonSearchService(ISalonSearchService salonSearchService) {
		this.salonSearchService = salonSearchService;
	}
	public ISalonService getSalonService() {
		return salonService;
	}
	public void setSalonService(ISalonService salonService) {
		this.salonService = salonService;
	}
	public ISalonAdminService getSalonAdminService() {
		return salonAdminService;
	}
	public void setSalonAdminService(ISalonAdminService salonAdminService) {
		this.salonAdminService = salonAdminService;
	}
	public String getAudiFlag() {
		return audiFlag;
	}
	public void setAudiFlag(String audiFlag) {
		this.audiFlag = audiFlag;
	}
	public ISalonManageService getSalonManageService() {
		return salonManageService;
	}
	public void setSalonManageService(ISalonManageService salonManageService) {
		this.salonManageService = salonManageService;
	}
	public String getGoFrom() {
		return goFrom;
	}
	public void setGoFrom(String goFrom) {
		this.goFrom = goFrom;
	}
	public List<HostUnitDto> getHostUnitList() {
		return hostUnitList;
	}
	public void setHostUnitList(List<HostUnitDto> hostUnitList) {
		this.hostUnitList = hostUnitList;
	}
	public HostUnitDto getHostUnitDto() {
		return hostUnitDto;
	}
	public void setHostUnitDto(HostUnitDto hostUnitDto) {
		this.hostUnitDto = hostUnitDto;
	}
	public File getUploadPicFile() {
		return uploadPicFile;
	}
	public void setUploadPicFile(File uploadPicFile) {
		this.uploadPicFile = uploadPicFile;
	}
	public String getUploadPicFileFileName() {
		return uploadPicFileFileName;
	}
	public void setUploadPicFileFileName(String uploadPicFileFileName) {
		this.uploadPicFileFileName = uploadPicFileFileName;
	}
	public String[] getUpdateIds() {
		return updateIds;
	}
	public void setUpdateIds(String[] updateIds) {
		this.updateIds = updateIds;
	}
	public String[] getUpdateOrders() {
		return updateOrders;
	}
	public void setUpdateOrders(String[] updateOrders) {
		this.updateOrders = updateOrders;
	}
	public String[] getAddOrders() {
		return addOrders;
	}
	public void setAddOrders(String[] addOrders) {
		this.addOrders = addOrders;
	}
	public List<SalonInfoDto> getSettedSalonList() {
		return settedSalonList;
	}
	public void setSettedSalonList(List<SalonInfoDto> settedSalonList) {
		this.settedSalonList = settedSalonList;
	}
	public ISysParameterService getSysParameterService() {
		return sysParameterService;
	}
	public void setSysParameterService(ISysParameterService sysParameterService) {
		this.sysParameterService = sysParameterService;
	}
	public SysParameterDto getSysParameterDto() {
		return sysParameterDto;
	}
	public void setSysParameterDto(SysParameterDto sysParameterDto) {
		this.sysParameterDto = sysParameterDto;
	}
	public String getUploadFilePath() {
		return uploadFilePath;
	}
	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
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
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IDataOperationService getDataOperationService() {
		return dataOperationService;
	}
	public void setDataOperationService(IDataOperationService dataOperationService) {
		this.dataOperationService = dataOperationService;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	

}

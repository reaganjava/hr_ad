<%@page pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.dotoyo.buildjob.common.constant.ApplicationConstant"%>
<div id="leftNav">
    	<ul>
        	<li>
                <h3 id="sys_increment_h3">增值服务设置</h3>
                <ul style="display:none">
  					<li id="sys_increment_ul_sysname"><a href="<%=request.getContextPath()%>/sys/serviceNameMainAction!init.action">套餐名称维护</a></li>
                    <li id="sys_increment_ul_talents"><a href="<%=request.getContextPath()%>/sys/talentsFoundSetting!init.action">人才挖掘字段设置</a></li>
                    <li id="sys_increment_ul_talents_offline"><a href="<%=request.getContextPath()%>/sys/talentsFoundOfflineSetting!init.action">线下人才挖掘字段设置</a></li>
                	<li id="sys_increment_ul_hangcard"><a href="<%=request.getContextPath()%>/sys/hangCardSetting!init.action">挂证服务设置</a></li>
                    <li id="sys_increment_ul_huntcard"><a href="<%=request.getContextPath()%>/sys/huntCardSetting!init.action">猎证服务设置</a></li>
                    <li id="sys_increment_ul_recruit"><a href="<%=request.getContextPath()%>/sys/recruitSetting!init.action">招聘服务设置</a></li>
                	<li id="sys_increment_ul_advertising"><a href="<%=request.getContextPath()%>/sys/advertSetting!init.action">广告位服务设置</a></li>
                </ul>
            </li>
        	<li>
                <h3 id="sys_admin_h3">管理员管理</h3>
                <ul style="display:none">
                    <li id="sys_admin_manage"><a href="<%=request.getContextPath()%>/sys/sysMemberAdmin!init.action">管理员管理</a></li>
                    <li id="sys_admin_role"><a href="<%=request.getContextPath()%>/sys/sysRoleAdmin!init.action">管理员角色管理</a></li>
                    <li id="sys_admin_setting"><a href="<%=request.getContextPath()%>/sys/sysFunctionRoleAdmin!init.action">管理员权限设置</a></li>
                </ul>
            </li>
        	<li>
                <h3 id="sys_permission_h3">权限管理</h3>
                <ul style="display:none">
                    <li id="sys_permission_role"><a href="<%=request.getContextPath()%>/sys/sysRole!init.action">角色管理</a></li>
                    <li id="sys_permission_setting"><a href="<%=request.getContextPath()%>/sys/sysFunctionRole!init.action">权限设置</a></li>
                </ul>
            </li>
            <li>
                <h3 id="sys_function_h3"><a tabindex="-1" href="<%=request.getContextPath()%>/sys/sysFunction!init.action">功能点维护</a></h3>
            </li>
        	<li>
                <h3 id="sys_member_h3">会员管理</h3>
                <ul style="display:none">
                    <li id="sys_member_manage"><a href="<%=request.getContextPath()%>/sys/sysMember!init.action">会员管理</a></li>
                    <li id="sys_member_reset"><a href="<%=request.getContextPath()%>/sys/sysMemberReset!reset_init.action">密码重置</a></li>
                </ul>
            </li>
            <li>
                <h3 id="sys_dataStatistic_h3">数据统计</h3>
                <ul style="display:none">
                    <li id="sys_dataStatistic_recruit"><a href="dataStatisticAction!jobApplicantsStatistics.action">招聘应聘人数</a></li>
                    <li id="sys_dataStatistic_count"><a href="dataStatisticAction!viewCertHunterStatistic.action">猎证统计</a></li>
                </ul>
            </li>
            <li>
                <h3 id="sys_filter_words_h3"><a tabindex="-1" href="<%=request.getContextPath()%>/sys/sysFilterWord!init.action">过滤词管理</a></h3>
            </li>
            <li>
                <h3 id="sys_lineofftalents_h3"><a href="<s:url action="lineTalentPoolAction!loadLineTalentPool.action"/>">线下人才库</a></h3>
            </li>
             <li>
                <h3 id="sys_lineofftalentsmath_h3">线下人才匹配</h3>
                <ul style="display:none">
                    <li id="sys_personallineoffapply"><a href="talentMatchAction!viewTalentList.action">个人线下求职意向信息列表</a></li>
                    <li id="sys_enterpriselinefoffapply"><a href="talentMatchAction!viewOfflineSearchResultList.action">企业线下人才需求信息列表</a></li>
                </ul>
            </li>
             <li>
                <h3 id="sys_recruit_manage_h3">招聘管理</h3>
                <ul style="display:none">
                    <li id="sys_recruit_this"><a href="<s:url action="jobSideManagementAction!managementCompaniesJobInformation.action"/>">招聘方管理</a></li>
                    <li id="sys_recruit_position"><a href="<s:url action="jobSideManagementAction!companiesJobManage.action"/>">招聘职位管理</a></li>
                </ul>
            </li>
             <li>
                <h3 id="sys_applyjob_manage_h3">求职管理</h3>
                <ul style="display:none">
                    <li id="sys_applyjob_this"><a href="applyJobManageAction!viewJobApplyRecordList4Admin.action">求职方管理</a></li>
                    <li id="sys_applyjon_purpose"><a href="applyJobManageAction!viewApplyJobIntention4Admin.action">求职意向管理</a></li>
                </ul>
            </li>

            <li>
                <h3 id="sys_huntcard_requrement_h3"><a href="certNeedsManageAction!viewAllCertNeeds4Admin.action">猎证需求管理</a></h3>
            </li>

             <li>
                <h3 id="sys_card_manage_h3"><a href="personalCertManageAction!viewPersonalCertList4Admin.action">证书管理</a></h3>
            </li>

              <li>
                <h3 id="salon_manager">沙龙管理</h3>
                <ul style="display:none">
                    <li id="salon_manager_1"><a href="<%=request.getContextPath()%>/sys/salonAdminAction!toAudiSalonPage.action">沙龙审核</a></li>
                    <li id="salon_manager_2"><a href="<%=request.getContextPath()%>/sys/salonAdminAction!toSetSalonPage.action">经典沙龙设置</a></li>
                    <li id="salon_manager_3"><a href="<%=request.getContextPath()%>/sys/salonAdminAction!toSetSalonArticlePage.action">经典文章设置</a></li>
                    <li id="salon_manager_4"><a href="<%=request.getContextPath()%>/sys/salonAdminAction!toHostUnitManagePage.action">主办单位设置</a></li>
                </ul>
            </li>

            <li><h3 id="salon_picture"><a href="<%=request.getContextPath()%>/sys/salonAdminAction!toMediaListPage.action?salonMediaDto.mediaType=<%=ApplicationConstant.MEDIA_TYPE_PICTURE%>">线下沙龙图片库</a></h3></li>
            <li><h3 id="salon_video"><a href="<%=request.getContextPath()%>/sys/salonAdminAction!toMediaVideoListPage.action?salonMediaDto.mediaType=<%=ApplicationConstant.MEDIA_TYPE_VIDEO%>">线下沙龙视频库</a></h3></li>

             <li>
                <h3 id="salon_article"><a href="<%=request.getContextPath()%>/sys/salonAdminAction!toSalonArticleManage.action">文章管理</a></h3>
            </li>
             <li>
                <h3 id="salon_comment"><a href="<%=request.getContextPath()%>/sys/salonAdminAction!toCommentManagePage.action">评论管理</a></h3>
            </li>

            <li>
                <h3 id="sys_order_manager_h3"><a tabindex="-1" href="<%=request.getContextPath()%>/sys/sysOrder!init.action">订单管理</a></h3>
            </li>
            <li>
                <h3 id="sys_advert_manager_h3"><a tabindex="-1" href="<%=request.getContextPath()%>/sys/sysAd!init.action">广告管理</a></h3>
            </li>
            <li>
                <h3 id="sys_increment_manager_h3"><a tabindex="-1" href="<%=request.getContextPath()%>/sys/sysIncrementManage!init.action">增值服务管理</a></h3>
            </li>
        	<li>
                <h3 id="sys_point_setting_h3">积分管理</h3>
                <ul style="display:none">
                    <li id="sys_point_setting"><a href="<%=request.getContextPath()%>/sys/sysPointSetting!init.action">积分设置</a></li>
                    <li id="sys_point_setting_adjust"><a href="<%=request.getContextPath()%>/sys/sysPointAdjust!init.action">积分调整</a></li>
                </ul>
            </li>
            <li>
                <h3 id="sys_log_h3"><a tabindex="-1" href="<%=request.getContextPath()%>/sys/sysLog!init.action">系统日志</a></h3>
            </li>
            <li>
                <h3 id="sys_member_changepwd_h3"><a tabindex="-1" href="<%=request.getContextPath()%>/sys/sysUserChangePwd!init.action">修改密码</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="<%=request.getContextPath()%>/sys/sysUserLogin!loginout.action">退出系统</a></h3>
            </li>
            <li>
                <h3 id="sys_administrator_h3"><a tabindex="-1" href="<%=request.getContextPath()%>/sys/sysMainAction!init.action">返回后台首页</a></h3>
            </li>
        </ul>
    </div>
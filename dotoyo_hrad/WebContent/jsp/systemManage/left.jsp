<%@page pageEncoding="utf-8"%>
<script type="text/javascript">
</script>
<div id="leftNav">
    	<ul>
        	<li>
                <h3 id="sys_increment_h3">增值服务设置</h3>
                <ul>
  					<li id="sys_increment_ul_sysname"><a href="<%=request.getContextPath()%>/sysIncrement/serviceNameMainAction!init.action">服务名称维护</a></li>
                    <li id="sys_increment_ul_talents"><a href="<%=request.getContextPath()%>/sysIncrement/talentsFoundSetting!init.action">人才挖掘字段设置</a></li>
                	<li id="sys_increment_ul_hangcard"><a href="<%=request.getContextPath()%>/sysIncrement/hangCardSetting!init.action">挂证服务设置</a></li>
                    <li id="sys_increment_ul_huntcard"><a href="<%=request.getContextPath()%>/sysIncrement/huntCardSetting!init.action">猎证服务设置</a></li>
                    <li id="sys_increment_ul_recruit"><a href="<%=request.getContextPath()%>/sysIncrement/recruitSetting!init.action">招聘服务设置</a></li>
                	<li id="sys_increment_ul_advertising"><a href="<%=request.getContextPath()%>/sysIncrement/advertSetting!init.action">广告位服务设置</a></li>
                </ul>
            </li>
        	<li>
                <h3 id="sys_position_h3">职位管理</h3>
                <ul>
                    <li id="sys_position_add"><a href="#">新增职位</a></li>
                    <li id="sys_position_in_public"><a href="#">发布中的职位</a></li>
                    <li id="sys_position_suspend"><a href="#">暂停的职位</a></li>
                    <li id="sys_position_willexpire"><a href="#">即将到期的职位</a></li>
                    <li id="sys_position_ended"><a href="#">已结束的职位</a></li>
                    <li id="sys_position_model_manage"><a href="#">职位模版管理</a></li>
                </ul>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">招聘管理</a></h3>
                <div>
                      <h3><a tabindex="-1" href="#">站内信管理</a></h3>
                      <ul>
                          <li><a href="#">-&nbsp;&nbsp;收件箱</a> </li>
                          <li><a href="#">-&nbsp;&nbsp;写邮件</a> </li>
                          <li><a href="#">-&nbsp;&nbsp;收到面试邀请自动回复设置</a> </li>
                          <li><a href="#">-&nbsp;&nbsp;邀请面试设置</a></li>
                      </ul>
                      <h3><a tabindex="-1" href="#">收到的申请</a></h3><span></span>
                      <h3><a tabindex="-1" href="#">查看已发送邀请</a></h3><span></span>
                      <h3><a tabindex="-1" href="#">人才浏览记录</a></h3>
              	</div>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">线下人才推荐</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">后备人才库</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">线下人才库</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">证书管理</a></h3>
                <ul>
                    <li><a href="#">新增证书需求</a></li>
                    <li><a href="#">已发布的证书需求</a></li>
                    <li><a href="#">暂停的证书需求管理</a></li>
                    <li><a href="#">即将到期的证书需求管理</a></li>
                    <li><a href="#">已结束证书需求管理</a></li>
                    <li><a href="#">我的证书收藏</a></li>
                </ul>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">创业团队申请</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">创业项目</a></h3>
                <ul>
                    <li><a href="#">已收藏项目</a></li>
                    <li><a href="#">已承接项目</a></li>
                </ul>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">背景音乐管理</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">发起的沙龙</a></h3>
                <ul>
                    <li><a href="#">线上沙龙</a></li>
                    <li><a href="#">线下沙龙</a></li>
                </ul>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">参与的沙龙</a></h3>
                <ul>
                    <li><a href="#">线上沙龙</a></li>
                    <li><a href="#">线下沙龙</a></li>
                </ul>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">专家功能</a></h3>
                <ul>
                    <li><a href="#">收到的邀请</a></li>
                    <li><a href="#">我的点评</a></li>
                </ul>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">问答管理</a></h3>
                <ul>
                    <li><a href="#">我提出的问题</a></li>
                    <li><a href="#">我回答的问题</a></li>
                </ul>
            </li>
   			<li>
                <h3><a tabindex="-1" href="#">线下沙龙图片库</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">线下沙龙视频库</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">文章管理</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">评论管理</a></h3>
            </li>
           	<li>
                <h3><a tabindex="-1" href="#">积分管理</a></h3>
                <ul>
                    <li><a href="#">积分明细查询</a></li>
                </ul>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">猎头服务管理</a></h3>
                <ul>
                    <li><a href="#">购买服务</a></li>
                    <li><a href="#">已购买的服务</a></li>
                    <li><a href="#">已过期的服务</a></li>
                </ul>
            </li>
            <li>
                <h3 id="sys_order_manager_h3"><a tabindex="-1" href="#">订单管理</a></h3>
                <ul>
                    <li id="sys_order_manager"><a href="<%=request.getContextPath()%>/sysOrder/sysOrder!init.action">订单管理</a></li>
                </ul>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">修改密码</a></h3>
            </li>
            <li>
                <h3><a tabindex="-1" href="#">退出系统</a></h3>
            </li>
            <li>
                <h3 id="sys_administrator_h3"><a tabindex="-1" href="<%=request.getContextPath()%>/sysAdministrator/sysMainAction!init.action">返回首页</a></h3>
            </li>
        </ul>
    </div>
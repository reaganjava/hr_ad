package test.com.dotoyo.buildjob.service;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.sys.dto.SysIncrementRecruitSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.service.ISysIncrementRecruitSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementServiceNameMaintainanceService;
/**
 * 系统服务增值服务招聘服务测试
 * @author wisdy.xiao
 *
 */
public class SysIncrementRecruitSettingImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysIncrementRecruitSettingService sysIncrementRecruitSettingService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysIncrementRecruitSettingService=(ISysIncrementRecruitSettingService) tx.getBean("sysIncrementRecruitSettingService");
		init();
	}
	/**
	 * Initialize data
	 */
	public static void init(){


		ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService = (ISysIncrementServiceNameMaintainanceService) tx.getBean("sysIncrementServiceNameMaintainanceService");
		//delete sub table
		SysIncrementRecruitSettingDto indto = new SysIncrementRecruitSettingDto();
		indto.setServiceCode("1001-00-01");
		sysIncrementRecruitSettingService.removeIncrementRecruitSetting(indto);
		//delete
		SysIncrementServiceNameMaintainanceDto serviceindto = new SysIncrementServiceNameMaintainanceDto();
		serviceindto.setServiceCode("1001-00-01");
		sysIncrementServiceNameMaintainanceService.removeIncrementSerivceNameByCode(serviceindto);
		//insert
		SysIncrementServiceNameMaintainanceDto dto = new SysIncrementServiceNameMaintainanceDto();
		dto.setLastEditDate(new Date());
		dto.setOperator(Long.valueOf(1));
		dto.setServiceCode("1001-00-01");
		dto.setServiceIntro("test info.");
		dto.setServiceName("test name");
		dto.setSubmitDate(new Date());
		dto.setUsableDays(Long.valueOf(130));
		sysIncrementServiceNameMaintainanceService.addIncrementServiceName(dto);

	}
	@Test
	public void testAddIncrementRecruitSetting() {
		SysIncrementRecruitSettingDto dto = new SysIncrementRecruitSettingDto();
		dto.setLastEditDate(new Date());
		dto.setOperator(Long.valueOf(1));
		dto.setServiceCode("1001-00-01");
		dto.setTimesOfLookupBlog(Long.valueOf(50));
		dto.setTimesOfLookupInvite(Long.valueOf(50));
		dto.setTimesOfPublicPosition(Long.valueOf(50));
		dto.setSubmitDate(new Date());
		int i = sysIncrementRecruitSettingService.addIncrementRecruitSetting(dto);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testQueryIncrementRecruit() {
		SysIncrementRecruitSettingDto indto = new SysIncrementRecruitSettingDto();
		indto.setServiceCode("1001-00-01");
		SysIncrementRecruitSettingDto dto = sysIncrementRecruitSettingService.queryIncrementRecruit(indto);
		Assert.assertEquals("test name", dto.getServiceName());

	}
	@Test
	public void testQueryIncrementRecruitList() {
		SysIncrementRecruitSettingDto indto = new SysIncrementRecruitSettingDto();
		indto.setServiceName("test");
		PageInfo pageinfo = new PageInfo();
		pageinfo.setPageSize(50);
		List<SysIncrementRecruitSettingDto> dtoList = sysIncrementRecruitSettingService.queryIncrementRecruitList(pageinfo,indto);
		Assert.assertTrue(dtoList.size() > 0);
	}
	@Test
	public void testUpdateIncrementRecruitSetting() {
		SysIncrementRecruitSettingDto indto = new SysIncrementRecruitSettingDto();
		indto.setServiceCode("1001-00-01");
		indto.setTimesOfLookupBlog(Long.valueOf(150));
		indto.setTimesOfLookupInvite(Long.valueOf(150));
		indto.setTimesOfPublicPosition(Long.valueOf(150));
		indto.setLastEditDate(new Date());
		indto.setOperator(Long.valueOf(1));
		int i = sysIncrementRecruitSettingService.updateIncrementRecruitSetting(indto);
		Assert.assertTrue(i == 1);
	}
	@Test
	public void testRemoveIncrementRecruitSetting() {
		SysIncrementRecruitSettingDto indto = new SysIncrementRecruitSettingDto();
		indto.setServiceCode("1001-00-01");
		int i = sysIncrementRecruitSettingService.removeIncrementRecruitSetting(indto);
		Assert.assertTrue(i == 1);
	}
}

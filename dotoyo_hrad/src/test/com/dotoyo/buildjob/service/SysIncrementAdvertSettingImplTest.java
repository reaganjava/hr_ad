package test.com.dotoyo.buildjob.service;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.sys.dto.SysIncrementAdvertSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.service.ISysIncrementAdvertSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementServiceNameMaintainanceService;
/**
 * 系统服务增值服务广告位服务测试
 * @author wisdy.xiao
 *
 */
public class SysIncrementAdvertSettingImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysIncrementAdvertSettingService sysIncrementAdvertSettingService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysIncrementAdvertSettingService=(ISysIncrementAdvertSettingService) tx.getBean("sysIncrementAdvertSettingService");
		init();
	}
	/**
	 * Initialize data
	 */
	public static void init(){


		ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService = (ISysIncrementServiceNameMaintainanceService) tx.getBean("sysIncrementServiceNameMaintainanceService");
		//delete sub table
		SysIncrementAdvertSettingDto indto = new SysIncrementAdvertSettingDto();
		indto.setServiceCode("1001-00-01");
		sysIncrementAdvertSettingService.removeIncrementAdvertSetting(indto);
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
	public void testAddIncrementAdvertSetting() {
		SysIncrementAdvertSettingDto dto = new SysIncrementAdvertSettingDto();
		dto.setLastEditDate(new Date());
		dto.setOperator(Long.valueOf(1));
		dto.setServiceCode("1001-00-01");
		dto.setAdvertising("017-1001,017-1002");
		dto.setSubmitDate(new Date());
		int i = sysIncrementAdvertSettingService.addIncrementAdvertSetting(dto);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testQueryIncrementAdvert() {
		SysIncrementAdvertSettingDto indto = new SysIncrementAdvertSettingDto();
		indto.setServiceCode("1001-00-01");
		SysIncrementAdvertSettingDto dto = sysIncrementAdvertSettingService.queryIncrementAdvert(indto);
		Assert.assertEquals("test name", dto.getServiceName());

	}
	@Test
	public void testQueryIncrementAdvertList() {
		SysIncrementAdvertSettingDto indto = new SysIncrementAdvertSettingDto();
		indto.setServiceName("test");
		PageInfo pageinfo = new PageInfo();
		pageinfo.setPageSize(50);
		List<SysIncrementAdvertSettingDto> dtoList = sysIncrementAdvertSettingService.queryIncrementAdvertList(pageinfo,indto);
		Assert.assertTrue(dtoList.size() > 0);
	}
	@Test
	public void testUpdateIncrementAdvertSetting() {
		SysIncrementAdvertSettingDto indto = new SysIncrementAdvertSettingDto();
		indto.setServiceCode("1001-00-01");
		indto.setAdvertising("017-1003,017-1003");
		indto.setLastEditDate(new Date());
		indto.setOperator(Long.valueOf(1));
		int i = sysIncrementAdvertSettingService.updateIncrementAdvertSetting(indto);
		Assert.assertTrue(i == 1);
	}
	@Test
	public void testRemoveIncrementAdvertSetting() {
		SysIncrementAdvertSettingDto indto = new SysIncrementAdvertSettingDto();
		indto.setServiceCode("1001-00-01");
		int i = sysIncrementAdvertSettingService.removeIncrementAdvertSetting(indto);
		Assert.assertTrue(i == 1);
	}
}

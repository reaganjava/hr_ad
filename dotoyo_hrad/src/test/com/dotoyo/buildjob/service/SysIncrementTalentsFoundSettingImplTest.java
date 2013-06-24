package test.com.dotoyo.buildjob.service;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementTalentsFoundSettingDto;
import com.dotoyo.buildjob.sys.service.ISysIncrementServiceNameMaintainanceService;
import com.dotoyo.buildjob.sys.service.ISysIncrementTalentsFoundSettingService;
/**
 * 系统服务增值服务人才挖掘服务测试
 * @author wisdy.xiao
 *
 */
public class SysIncrementTalentsFoundSettingImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysIncrementTalentsFoundSettingService sysIncrementTalentsFoundSettingService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysIncrementTalentsFoundSettingService=(ISysIncrementTalentsFoundSettingService) tx.getBean("sysIncrementTalentsFoundSettingService");
		init();
	}
	/**
	 * Initialize data
	 */
	public static void init(){


		ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService = (ISysIncrementServiceNameMaintainanceService) tx.getBean("sysIncrementServiceNameMaintainanceService");
		//delete sub table
		SysIncrementTalentsFoundSettingDto indto = new SysIncrementTalentsFoundSettingDto();
		indto.setServiceCode("1001-00-01");
		sysIncrementTalentsFoundSettingService.removeIncrementTalentsFoundSetting(indto);
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
	public void testAddIncrementTalentsFoundSetting() {
		SysIncrementTalentsFoundSettingDto dto = new SysIncrementTalentsFoundSettingDto();
		dto.setLastEditDate(new Date());
		dto.setOperator(Long.valueOf(1));
		dto.setServiceCode("1001-00-01");
		dto.setFieldsOfSearch("017-1001");
		dto.setTimesOfSearch(Long.valueOf(150));
		dto.setSubmitDate(new Date());
		int i = sysIncrementTalentsFoundSettingService.addIncrementTalentsFoundSetting(dto);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testQueryIncrementTalentsFound() {
		SysIncrementTalentsFoundSettingDto indto = new SysIncrementTalentsFoundSettingDto();
		indto.setServiceCode("1001-00-01");
		SysIncrementTalentsFoundSettingDto dto = sysIncrementTalentsFoundSettingService.queryIncrementTalentsFound(indto);
		Assert.assertEquals("test name", dto.getServiceName());

	}
	@Test
	public void testQueryIncrementTalentsFoundList() {
		SysIncrementTalentsFoundSettingDto indto = new SysIncrementTalentsFoundSettingDto();
		indto.setServiceName("test");
		PageInfo pageinfo = new PageInfo();
		pageinfo.setPageSize(50);
		List<SysIncrementTalentsFoundSettingDto> dtoList = sysIncrementTalentsFoundSettingService.queryIncrementTalentsFoundList(pageinfo,indto);
		Assert.assertTrue(dtoList.size() > 0);
	}
	@Test
	public void testUpdateIncrementTalentsFoundSetting() {
		SysIncrementTalentsFoundSettingDto indto = new SysIncrementTalentsFoundSettingDto();
		indto.setServiceCode("1001-00-01");
		indto.setFieldsOfSearch("017-1002");
		indto.setTimesOfSearch(Long.valueOf(140));
		indto.setLastEditDate(new Date());
		indto.setOperator(Long.valueOf(1));
		int i = sysIncrementTalentsFoundSettingService.updateIncrementTalentsFoundSetting(indto);
		Assert.assertTrue(i == 1);
	}
	@Test
	public void testRemoveIncrementTalentsFoundSetting() {
		SysIncrementTalentsFoundSettingDto indto = new SysIncrementTalentsFoundSettingDto();
		indto.setServiceCode("1001-00-01");
		int i = sysIncrementTalentsFoundSettingService.removeIncrementTalentsFoundSetting(indto);
		Assert.assertTrue(i == 1);
	}
}

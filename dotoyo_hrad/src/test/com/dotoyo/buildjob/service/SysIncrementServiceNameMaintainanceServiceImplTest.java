package test.com.dotoyo.buildjob.service;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.service.ISysIncrementServiceNameMaintainanceService;
/**
 * 系统服务增值服务名称维护测试
 * @author wisdy.xiao
 *
 */
public class SysIncrementServiceNameMaintainanceServiceImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysIncrementServiceNameMaintainanceService=(ISysIncrementServiceNameMaintainanceService) tx.getBean("sysIncrementServiceNameMaintainanceService");
		//delete
		SysIncrementServiceNameMaintainanceDto indto = new SysIncrementServiceNameMaintainanceDto();
		indto.setServiceCode("1001-00-01");
		sysIncrementServiceNameMaintainanceService.removeIncrementSerivceNameByCode(indto);
	}

	@Test
	public void testAddIncrementServiceName() {
		//insert
		SysIncrementServiceNameMaintainanceDto dto = new SysIncrementServiceNameMaintainanceDto();
		dto.setLastEditDate(new Date());
		dto.setOperator(Long.valueOf(1));
		dto.setServiceCode("1001-00-01");
		dto.setServiceIntro("test info.");
		dto.setServiceName("test name");
		dto.setSubmitDate(new Date());
		dto.setUsableDays(Long.valueOf(130));
		int i = sysIncrementServiceNameMaintainanceService.addIncrementServiceName(dto);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testQueryIncrementSerivceName() {
		SysIncrementServiceNameMaintainanceDto indto = new SysIncrementServiceNameMaintainanceDto();
		indto.setServiceCode("1001-00-01");
		SysIncrementServiceNameMaintainanceDto dto = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceName(indto);
		Assert.assertEquals("test name", dto.getServiceName());

	}
	@Test
	public void testQueryIncrementSerivceNameList() {
		SysIncrementServiceNameMaintainanceDto indto = new SysIncrementServiceNameMaintainanceDto();
		indto.setServiceName("test");
		List<SysIncrementServiceNameMaintainanceDto> dtoList = sysIncrementServiceNameMaintainanceService.queryIncrementSerivceNameList(indto);
		Assert.assertTrue(dtoList.size() > 0);
	}
	@Test
	public void testUpdateIncrementSerivceName() {
		SysIncrementServiceNameMaintainanceDto indto = new SysIncrementServiceNameMaintainanceDto();
		indto.setServiceCode("1001-00-01");
		indto.setServiceName("update test name");
		indto.setUsableDays(Long.valueOf(150));
		indto.setServiceIntro("update test info.");
		indto.setLastEditDate(new Date());
		indto.setOperator(Long.valueOf(1));
		int i = sysIncrementServiceNameMaintainanceService.updateIncrementSerivceName(indto);
		Assert.assertTrue(i == 1);
	}
	@Test
	public void testRemoveIncrementSerivceNameByCode() {
		SysIncrementServiceNameMaintainanceDto indto = new SysIncrementServiceNameMaintainanceDto();
		indto.setServiceCode("1001-00-01");
		int i = sysIncrementServiceNameMaintainanceService.removeIncrementSerivceNameByCode(indto);
		Assert.assertTrue(i == 1);
	}
}

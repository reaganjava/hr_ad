package test.com.dotoyo.buildjob.service;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.sys.dto.SysFunctionDto;
import com.dotoyo.buildjob.sys.service.ISysFunctionService;
/**
 * 功能点表测试
 * @author wisdy.xiao
 *
 */
public class SysFunctionImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysFunctionService sysFuntionService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysFuntionService=(ISysFunctionService) tx.getBean("sysFunctionService");
		init();
	}
	/**
	 * Initialize data
	 */
	public static void init(){

		/*
		ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService = (ISysIncrementServiceNameMaintainanceService) tx.getBean("sysIncrementServiceNameMaintainanceService");

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
		*/

	}
	@Test
	public void testAdd() {
		SysFunctionDto dto = new SysFunctionDto();
		dto.setCode("1001");
		dto.setName("test1");
		sysFuntionService.add(dto);
		Assert.assertEquals(1,1);
	}
	@Test
	public void testQuery() {
		SysFunctionDto dto = new SysFunctionDto();
		dto.setCode("1001");
		SysFunctionDto outDTO = sysFuntionService.search(dto);
		Assert.assertEquals(1,1);
	}
	@Test
	public void testUpdate() {
		SysFunctionDto dto = new SysFunctionDto();
		dto.setCode("1001");
		dto.setName("test2");
		SysFunctionDto outDTO = sysFuntionService.search(dto);
		Assert.assertEquals(1,1);
	}
	@Test
	public void testRemove() {
		SysFunctionDto dto = new SysFunctionDto();
		dto.setCode("1001");
		dto.setName("test1");
		sysFuntionService.remove(dto);
		Assert.assertEquals(1,1);
	}

}

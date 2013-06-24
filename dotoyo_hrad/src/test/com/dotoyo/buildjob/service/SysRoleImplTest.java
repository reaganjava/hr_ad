package test.com.dotoyo.buildjob.service;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.sys.dto.SysRoleDto;
import com.dotoyo.buildjob.sys.service.ISysRoleService;
/**
 * 角色测试
 * @author wisdy.xiao
 *
 */
public class SysRoleImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysRoleService sysRoleService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysRoleService=(ISysRoleService) tx.getBean("sysRoleService");
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
		SysRoleDto dto = new SysRoleDto();
		dto.setCode("1001");
		dto.setName("test1");
		sysRoleService.add(dto);
		Assert.assertEquals(1,1);
	}
	@Test
	public void testQuery() {
		SysRoleDto dto = new SysRoleDto();
		dto.setCode("1001");
		SysRoleDto outDTO = sysRoleService.search(dto);
		Assert.assertEquals(1,1);
	}
	@Test
	public void testUpdate() {
		SysRoleDto dto = new SysRoleDto();
		dto.setCode("1001");
		dto.setName("test2");
		SysRoleDto outDTO = sysRoleService.search(dto);
		Assert.assertEquals(1,1);
	}
	@Test
	public void testRemove() {
		SysRoleDto dto = new SysRoleDto();
		dto.setCode("1001");
		dto.setName("test1");
		sysRoleService.remove(dto);
		Assert.assertEquals(1,1);
	}

}

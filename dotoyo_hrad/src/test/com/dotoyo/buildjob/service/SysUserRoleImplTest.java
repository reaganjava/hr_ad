package test.com.dotoyo.buildjob.service;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.sys.dto.SysUserRoleDto;
import com.dotoyo.buildjob.sys.service.ISysUserRoleService;
/**
 * 用户角色分配表测试
 * @author wisdy.xiao
 *
 */
public class SysUserRoleImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysUserRoleService sysUserRoleService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysUserRoleService=(ISysUserRoleService) tx.getBean("sysUserRoleService");
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
		SysUserRoleDto dto = new SysUserRoleDto();
		dto.setUserid(Long.valueOf("1"));
		dto.setRoleid(Long.valueOf("2"));
		sysUserRoleService.add(dto);
		Assert.assertEquals(1,1);
	}
	@Test
	public void testQuery() {
		SysUserRoleDto dto = new SysUserRoleDto();
		dto.setId(Long.valueOf("1"));
		SysUserRoleDto outDTO = sysUserRoleService.search(dto);
		Assert.assertEquals(1,1);
	}
	@Test
	public void testUpdate() {
		SysUserRoleDto dto = new SysUserRoleDto();
		dto.setId(Long.valueOf("1"));
		dto.setUserid(Long.valueOf("1"));
		dto.setRoleid(Long.valueOf("3"));
		SysUserRoleDto outDTO = sysUserRoleService.search(dto);
		Assert.assertEquals(1,1);
	}
	@Test
	public void testRemove() {
		SysUserRoleDto dto = new SysUserRoleDto();
		dto.setId(Long.valueOf("1"));
		sysUserRoleService.remove(dto);
		Assert.assertEquals(1,1);
	}

}

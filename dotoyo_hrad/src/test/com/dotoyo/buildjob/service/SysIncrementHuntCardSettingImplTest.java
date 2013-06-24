package test.com.dotoyo.buildjob.service;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.sys.dto.SysIncrementHuntCardSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.service.ISysIncrementHuntCardSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementServiceNameMaintainanceService;
/**
 * 系统服务增值服务猎证服务测试
 * @author wisdy.xiao
 *
 */
public class SysIncrementHuntCardSettingImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysIncrementHuntCardSettingService sysIncrementHuntCardSettingService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysIncrementHuntCardSettingService=(ISysIncrementHuntCardSettingService) tx.getBean("sysIncrementHuntCardSettingService");
		init();
	}
	/**
	 * Initialize data
	 */
	public static void init(){


		ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService = (ISysIncrementServiceNameMaintainanceService) tx.getBean("sysIncrementServiceNameMaintainanceService");
		//delete sub table
		SysIncrementHuntCardSettingDto indto = new SysIncrementHuntCardSettingDto();
		indto.setServiceCode("1001-00-01");
		sysIncrementHuntCardSettingService.removeIncrementHuntCardSetting(indto);
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
	public void testAddIncrementHuntCardSetting() {
		SysIncrementHuntCardSettingDto dto = new SysIncrementHuntCardSettingDto();
		dto.setLastEditDate(new Date());
		dto.setOperator(Long.valueOf(1));
		dto.setServiceCode("1001-00-01");
		dto.setTimesOfLookup(Long.valueOf(140));
		dto.setSubmitDate(new Date());
		int i = sysIncrementHuntCardSettingService.addIncrementHuntCardSetting(dto);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testQueryIncrementHuntCard() {
		SysIncrementHuntCardSettingDto indto = new SysIncrementHuntCardSettingDto();
		indto.setServiceCode("1001-00-01");
		SysIncrementHuntCardSettingDto dto = sysIncrementHuntCardSettingService.queryIncrementHuntCard(indto);
		Assert.assertEquals("test name", dto.getServiceName());

	}
	@Test
	public void testQueryIncrementHuntCardList() {
		SysIncrementHuntCardSettingDto indto = new SysIncrementHuntCardSettingDto();
		indto.setServiceName("test");
		PageInfo pageinfo = new PageInfo();
		pageinfo.setPageSize(50);
		List<SysIncrementHuntCardSettingDto> dtoList = sysIncrementHuntCardSettingService.queryIncrementHuntCardList(pageinfo,indto);
		Assert.assertTrue(dtoList.size() > 0);
	}
	@Test
	public void testUpdateIncrementHuntCardSetting() {
		SysIncrementHuntCardSettingDto indto = new SysIncrementHuntCardSettingDto();
		indto.setServiceCode("1001-00-01");
		indto.setTimesOfLookup(Long.valueOf(150));
		indto.setLastEditDate(new Date());
		indto.setOperator(Long.valueOf(1));
		int i = sysIncrementHuntCardSettingService.updateIncrementHuntCardSetting(indto);
		Assert.assertTrue(i == 1);
	}
	@Test
	public void testRemoveIncrementHuntCardSetting() {
		SysIncrementHuntCardSettingDto indto = new SysIncrementHuntCardSettingDto();
		indto.setServiceCode("1001-00-01");
		int i = sysIncrementHuntCardSettingService.removeIncrementHuntCardSetting(indto);
		Assert.assertTrue(i == 1);
	}
}

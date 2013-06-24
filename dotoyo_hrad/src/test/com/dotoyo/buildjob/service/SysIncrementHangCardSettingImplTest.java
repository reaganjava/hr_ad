package test.com.dotoyo.buildjob.service;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.sys.dto.SysIncrementHangCardSettingDto;
import com.dotoyo.buildjob.sys.dto.SysIncrementServiceNameMaintainanceDto;
import com.dotoyo.buildjob.sys.service.ISysIncrementHangCardSettingService;
import com.dotoyo.buildjob.sys.service.ISysIncrementServiceNameMaintainanceService;
/**
 * 系统服务增值服务挂证服务测试
 * @author wisdy.xiao
 *
 */
public class SysIncrementHangCardSettingImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysIncrementHangCardSettingService sysIncrementHangCardSettingService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysIncrementHangCardSettingService=(ISysIncrementHangCardSettingService) tx.getBean("sysIncrementHangCardSettingService");
		init();
	}
	/**
	 * Initialize data
	 */
	public static void init(){


		ISysIncrementServiceNameMaintainanceService sysIncrementServiceNameMaintainanceService = (ISysIncrementServiceNameMaintainanceService) tx.getBean("sysIncrementServiceNameMaintainanceService");
		//delete sub table
		SysIncrementHangCardSettingDto indto = new SysIncrementHangCardSettingDto();
		indto.setServiceCode("1001-00-01");
		sysIncrementHangCardSettingService.removeIncrementHangCardSetting(indto);
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
	public void testAddIncrementHangCardSetting() {
		SysIncrementHangCardSettingDto dto = new SysIncrementHangCardSettingDto();
		dto.setLastEditDate(new Date());
		dto.setOperator(Long.valueOf(1));
		dto.setServiceCode("1001-00-01");
		dto.setCountOfSubmit(Long.valueOf(140));
		dto.setSubmitDate(new Date());
		int i = sysIncrementHangCardSettingService.addIncrementHangCardSetting(dto);
		Assert.assertEquals(1, i);
	}

	@Test
	public void testQueryIncrementHangCard() {
		SysIncrementHangCardSettingDto indto = new SysIncrementHangCardSettingDto();
		indto.setServiceCode("1001-00-01");
		SysIncrementHangCardSettingDto dto = sysIncrementHangCardSettingService.queryIncrementHangCard(indto);
		Assert.assertEquals("test name", dto.getServiceName());

	}
	@Test
	public void testQueryIncrementHangCardList() {
		SysIncrementHangCardSettingDto indto = new SysIncrementHangCardSettingDto();
		indto.setServiceName("test");
		PageInfo pageinfo = new PageInfo();
		pageinfo.setPageSize(50);
		List<SysIncrementHangCardSettingDto> dtoList = sysIncrementHangCardSettingService.queryIncrementHangCardList(pageinfo,indto);
		Assert.assertTrue(dtoList.size() > 0);
	}
	@Test
	public void testUpdateIncrementHangCardSetting() {
		SysIncrementHangCardSettingDto indto = new SysIncrementHangCardSettingDto();
		indto.setServiceCode("1001-00-01");
		indto.setCountOfSubmit(Long.valueOf(150));
		indto.setLastEditDate(new Date());
		indto.setOperator(Long.valueOf(1));
		int i = sysIncrementHangCardSettingService.updateIncrementHangCardSetting(indto);
		Assert.assertTrue(i == 1);
	}
	@Test
	public void testRemoveIncrementHangCardSetting() {
		SysIncrementHangCardSettingDto indto = new SysIncrementHangCardSettingDto();
		indto.setServiceCode("1001-00-01");
		int i = sysIncrementHangCardSettingService.removeIncrementHangCardSetting(indto);
		Assert.assertTrue(i == 1);
	}
}

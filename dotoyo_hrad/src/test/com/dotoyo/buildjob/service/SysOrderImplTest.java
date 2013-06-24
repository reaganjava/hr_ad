package test.com.dotoyo.buildjob.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.sys.dto.SysOrderDto;
import com.dotoyo.buildjob.sys.service.ISysOrderService;
/**
 * 系统服务订单管理测试
 * @author wisdy.xiao
 *
 */
public class SysOrderImplTest {
	private static AbstractApplicationContext tx = null;
	private static ISysOrderService sysOrderService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String[] app = {"applicationContext*.xml"};
		tx = new ClassPathXmlApplicationContext(app);

		sysOrderService=(ISysOrderService) tx.getBean("sysOrderService");
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
	//@Test
	public void testAddIncrementAdvertSetting() {
		SysOrderDto dto = new SysOrderDto();
		dto.setLastEditDate(new Date());
		dto.setOperator(Long.valueOf(1));
		dto.setServiceCode("1001-00-01");
		dto.setConfirmor(Long.valueOf(1));
		dto.setInOrder(Long.valueOf(1));
		dto.setOperator(Long.valueOf(1));
		dto.setOrderDate(new Date());
		dto.setPayDate(new Date());
		dto.setStatus("019-1001");
		dto.setPrice("12.5");
		List<SysOrderDto> paramList = new ArrayList<SysOrderDto>();
		paramList.add(dto);
		int i = sysOrderService.addOrder(paramList);
		Assert.assertEquals(1, i);
	}
	@Test
	public void testQueryOrderList(){
		SysOrderDto dto = new SysOrderDto();
		//dto.setStatus("019-1001");
		PageInfo pageInfo = new PageInfo();
		pageInfo.setHtmlPagingInfo("");
		pageInfo.setHtmlListTableFootInfoNoAllCheck("");
		pageInfo.setPageSize(50);
		List<SysOrderDto> dtolist = sysOrderService.queryOrderList(pageInfo, dto);

		System.out.println("size:" + dtolist.size());
	}

}

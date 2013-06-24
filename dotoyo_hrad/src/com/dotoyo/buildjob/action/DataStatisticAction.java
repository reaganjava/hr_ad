package com.dotoyo.buildjob.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.dotoyo.buildjob.common.action.BuildJobAction;
import com.dotoyo.buildjob.common.constant.FunctionCodeConstant;
import com.dotoyo.buildjob.common.dto.PageInfo;
import com.dotoyo.buildjob.common.interceptor.AuthFunction;
import com.dotoyo.buildjob.headhunterCenter.service.IJobInfoService;
import com.dotoyo.buildjob.headhunterCenter.vo.JobSearchVo;
import com.dotoyo.buildjob.sys.service.ICertificateAdminService;

/**
 * 
 * @author arthas.zou
 * @dateCreated 2011-2-24
 * @description 数据统计
 */
public class DataStatisticAction extends BuildJobAction {

	/**
	 *
	 */
	private static final long serialVersionUID = -5979535136473451226L;
	private ICertificateAdminService certificateAdminService;
	private IJobInfoService jobInfoService;
	private Integer certNeedsAmount;// 证书需求数量
	private Integer personalCertAmount;// 个人证书数量
	private Date jobIssuetDate;
	private Date jobExpiryDate;
	private List<JobSearchVo> jobApplicantsList;
	private int sumEmploy = 0;
	private int sumRecruitingNumber = 0;
	private PageInfo pageInfo;

	/**
	 * 查看猎证统计
	 * 
	 * @return
	 */
	@AuthFunction(functionCode = FunctionCodeConstant.FUN_STATISTICS_HUNTCARD_TOTAL)
	public String viewCertHunterStatistic() {
		certNeedsAmount = certificateAdminService.getCertNeedsCount();
		personalCertAmount = certificateAdminService.getPersonalCertCount();
		return "viewCertHunterStatistic";
	}

	/**
	 * 数据统计 招聘应聘人数统计
	 */
	@AuthFunction(functionCode = FunctionCodeConstant.FUN_STATISTICS_RECRUIT_TOTAL)
	public String jobApplicantsStatistics() {
		if (getPageInfo() == null) {
			setPageInfo(new PageInfo());
		}
		getPageInfo().setPageSize(4);// Constants.APPLYJOB_LIST_SIZE
		jobApplicantsList = jobInfoService.queryJobApplicantsStatistics(
				jobIssuetDate, jobExpiryDate, getPageInfo());
		for (JobSearchVo jobSearchVo : jobApplicantsList) {
			sumEmploy = getSumEmploy() + jobSearchVo.getEmploy();
			sumRecruitingNumber = getSumRecruitingNumber()
					+ jobSearchVo.getRecruitingNumber();
		}
		return "jobApplicantsStatistics";
	}

	public InputStream getJobApplicantsStatisticsAsExcel() {
		jobApplicantsList = jobInfoService.queryJobApplicantsStatistics(
				jobIssuetDate, jobExpiryDate, null);

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell1 = row.createCell(0);
		cell1.setCellValue("省份");

		HSSFCell cell2 = row.createCell(1);
		cell2.setCellValue("行业");

		HSSFCell cell3 = row.createCell(2);
		cell3.setCellValue("职能");
		
		HSSFCell cell4 = row.createCell(3);
		cell4.setCellValue("职位");

		HSSFCell cell5 = row.createCell(4);
		cell5.setCellValue("招聘人数");

		HSSFCell cell6 = row.createCell(5);
		cell6.setCellValue("应聘人数");

		for (int i = 1; i <= jobApplicantsList.size(); i++) {
			row = sheet.createRow(i);
			cell1 = row.createCell(0);
			cell1.setCellValue(jobApplicantsList.get(i - 1).getProvinceName());

			cell2 = row.createCell(1);
			cell2.setCellValue(jobApplicantsList.get(i - 1).getIndustryName());

			cell3 = row.createCell(2);
			cell3.setCellValue(jobApplicantsList.get(i - 1).getCompetencyName());
			
			cell4 = row.createCell(3);
			cell4.setCellValue(jobApplicantsList.get(i - 1).getJobName());

			cell5 = row.createCell(4);
			cell5.setCellValue(jobApplicantsList.get(i - 1)
					.getRecruitingNumber());

			cell6 = row.createCell(5);
			cell6.setCellValue(jobApplicantsList.get(i - 1).getEmploy());

			sumEmploy = getSumEmploy()
					+ jobApplicantsList.get(i - 1).getEmploy();
			sumRecruitingNumber = getSumRecruitingNumber()
					+ jobApplicantsList.get(i - 1).getRecruitingNumber();
		}

		row = sheet.createRow(jobApplicantsList.size() + 1);
		cell1 = row.createCell(0);
		cell1.setCellValue("小计");

		cell2 = row.createCell(4);
		cell2.setCellValue(sumRecruitingNumber);

		cell2 = row.createCell(5);
		cell2.setCellValue(sumEmploy);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		return is;
	}

	/**
	 * 导出猎证统计数据为Excel文件
	 * 
	 * @return
	 */
	public String exportHunterStatisticAsExcel() {
		return "exportHunterStatisticAsExcel";
	}

	public String exportJobApplicantsStatisticsAsExcel() {
		return "exportJobApplicantsStatisticsAsExcel";
	}

	public String printCertHunterStatistic() {
		return "printCertHunterStatistic";
	}

	public InputStream getHunterStatisticAsExcel() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("sheet1");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell1 = row.createCell(0);
		cell1.setCellValue("统计项");

		HSSFCell cell2 = row.createCell(1);
		cell2.setCellValue("数量");

		row = sheet.createRow(1);
		cell1 = row.createCell(0);
		cell1.setCellValue("个人提交证书数量");

		cell2 = row.createCell(1);
		cell2.setCellValue(personalCertAmount);

		row = sheet.createRow(2);
		cell1 = row.createCell(0);
		cell1.setCellValue("企业证书需求数量");

		cell2 = row.createCell(1);
		cell2.setCellValue(certNeedsAmount);

		row = sheet.createRow(3);
		cell1 = row.createCell(0);
		cell1.setCellValue("总计");

		cell2 = row.createCell(1);
		cell2.setCellValue(personalCertAmount + certNeedsAmount);
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		return is;
	}

	/**
	 * @return the certificateAdminService
	 */
	public ICertificateAdminService getCertificateAdminService() {
		return certificateAdminService;
	}

	/**
	 * @param certificateAdminService
	 *            the certificateAdminService to set
	 */
	public void setCertificateAdminService(
			ICertificateAdminService certificateAdminService) {
		this.certificateAdminService = certificateAdminService;
	}

	/**
	 * @return the certNeedsAmount
	 */
	public Integer getCertNeedsAmount() {
		return certNeedsAmount;
	}

	/**
	 * @param certNeedsAmount
	 *            the certNeedsAmount to set
	 */
	public void setCertNeedsAmount(Integer certNeedsAmount) {
		this.certNeedsAmount = certNeedsAmount;
	}

	/**
	 * @return the personalCertAmount
	 */
	public Integer getPersonalCertAmount() {
		return personalCertAmount;
	}

	/**
	 * @param personalCertAmount
	 *            the personalCertAmount to set
	 */
	public void setPersonalCertAmount(Integer personalCertAmount) {
		this.personalCertAmount = personalCertAmount;
	}

	public void setJobInfoService(IJobInfoService jobInfoService) {
		this.jobInfoService = jobInfoService;
	}

	public IJobInfoService getJobInfoService() {
		return jobInfoService;
	}

	public void setJobIssuetDate(Date jobIssuetDate) {
		this.jobIssuetDate = jobIssuetDate;
	}

	public Date getJobIssuetDate() {
		return jobIssuetDate;
	}

	public void setJobExpiryDate(Date jobExpiryDate) {
		this.jobExpiryDate = jobExpiryDate;
	}

	public Date getJobExpiryDate() {
		return jobExpiryDate;
	}

	public void setJobApplicantsList(List<JobSearchVo> jobApplicantsList) {
		this.jobApplicantsList = jobApplicantsList;
	}

	public List<JobSearchVo> getJobApplicantsList() {
		return jobApplicantsList;
	}

	public void setSumEmploy(int sumEmploy) {
		this.sumEmploy = sumEmploy;
	}

	public int getSumEmploy() {
		return sumEmploy;
	}

	public void setSumRecruitingNumber(int sumRecruitingNumber) {
		this.sumRecruitingNumber = sumRecruitingNumber;
	}

	public int getSumRecruitingNumber() {
		return sumRecruitingNumber;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

}

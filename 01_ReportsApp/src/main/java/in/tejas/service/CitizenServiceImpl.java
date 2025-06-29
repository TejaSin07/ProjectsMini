package in.tejas.service;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.tejas.entity.CitizenPlan;
import in.tejas.repo.CitizenPlanRepo;
import in.tejas.request.SearchRequest;
import in.tejas.utils.EmailUtils;
import in.tejas.utils.ExcelGenerator;
import in.tejas.utils.PdfGenerator;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CitizenServiceImpl implements CitizenService {

	@Autowired
	private CitizenPlanRepo planRepo;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public List<String> getPlanName() {
		// TODO Auto-generated method stub
		 List<String> planNames = planRepo.getPlanNames();
		 return planNames;
		
	}

	@Override
	public List<String> getPlanStatuses() {
		// TODO Auto-generated method stub
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		// TODO Auto-generated method stub
		CitizenPlan entity = new CitizenPlan();
//		BeanUtils.copyProperties(request, entity); dont copy directly like this data will came empty instead of null
		
		if(null != request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
//		 If you skip the null check, it can cause a NullPointerException. as request.get will have null value
		
		if(null != request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		
		if(null != request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		
		if(null != request.getStartDate() && !"".equals(request.getStartDate())) {
			String startDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate =  LocalDate.parse(startDate,formatter);
			entity.setPlanStartDate(localDate);	
		}
		
		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response, SearchRequest request) throws Exception {
	    File f = new File("plans.xls");

	    List<CitizenPlan> filteredPlans = search(request); // ✅ Use existing search logic

	    excelGenerator.generate(response, filteredPlans, f); // Pass only filtered data

	    // Optional: send email or log it
	    emailUtils.sendEmail("Filtered Data", "<h1>Filtered Excel</h1>", "tejassinkar24@gmail.com", f);
	    f.delete();

	    return true;
	}


	@Override
	public boolean exportdf(HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		File f = new File("Plans.pdf");
		List<CitizenPlan> plans = planRepo.findAll();
		
		pdfGenerator.generate(response,plans,f);
		String Subject ="Test mail subject";
		String Body = "<h1>Test Mail Body</h1>";
		String to = "tejassinkar24@gmail.com";
		
		emailUtils.sendEmail(Subject, Body, to,f);
		
		f.delete();
		return true;	
	}

}

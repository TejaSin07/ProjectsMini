package in.tejas.service;

import java.util.List;


import in.tejas.binding.DashBoardResponse;
import in.tejas.binding.EnquiryForm;
import in.tejas.binding.EnquirySearchFilter;
import in.tejas.entity.StudentEnqEntity;

public interface EnquiryService {
	public List<String> getCourseName();
	
	public List<String> getEnqStatus();
	
	public DashBoardResponse getDashboardData(Integer userId);
	
	public String upsertEnquiry(EnquiryForm form);
	
//	List<StudentEnqEntity> getFilteredEnquiries(EnquirySearchFilter filter);
	public List<StudentEnqEntity> getFilteredEnquiries(EnquirySearchFilter filter,Integer userId);
	
	public EnquiryForm getEnquiry(Integer enqId);
	
	public List<StudentEnqEntity> getEnquries();
}
package in.tejas.service;

import java.util.List;

import in.tejas.binding.DashBoardResponse;
import in.tejas.binding.EnquiryForm;
import in.tejas.binding.EnquirySearchFilter;

public interface EnquiryService {
	public List<String> getCourseName();
	
	public List<String> getEnqStatus();
	
	public DashBoardResponse getDashboardData(Integer userId);
	
	public String upsertEnquiry(EnquiryForm form);
	
	public List<EnquiryForm> getEnquries(Integer userId,EnquirySearchFilter criteria );
	
	public EnquiryForm getEnquiry(Integer enqId);
	
}

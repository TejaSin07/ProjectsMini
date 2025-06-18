package in.tejas.service;

import java.util.List;

import in.tejas.entity.CitizenPlan;
import in.tejas.request.SearchRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CitizenService {
	
	
	public List<String> getPlanName();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportdf(HttpServletResponse response) throws Exception;
	
		
}

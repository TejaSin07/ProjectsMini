package in.tejas.service; 

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tejas.binding.DashBoardResponse;
import in.tejas.binding.EnquiryForm;
import in.tejas.binding.EnquirySearchFilter;
import in.tejas.entity.StudentEnqEntity;
import in.tejas.entity.UserDtlsEntity;
import in.tejas.repo.StudentEnqRepo;
import in.tejas.repo.UserDtlsRepo;
import jakarta.servlet.http.HttpSession;

@Service
public class EnquiryServiceImpl implements EnquiryService{
	
	@Autowired
	private UserDtlsRepo userDtlsRepo;
	@Autowired
	private HttpSession session;
	
	@Override
	public List<StudentEnqEntity> getEnquries(){
		Integer userId =  (Integer)session.getAttribute("userId");
		Optional<UserDtlsEntity> findbyId = userDtlsRepo.findById(userId);
		if(findbyId.isPresent()) {
			UserDtlsEntity userDtlsEntity =  findbyId.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			return enquiries;
		}
		return null;
	}

	@Override
	public List<String> getCourseName() {
	    return List.of("Java Fullstack", "DevOps", "React JS");
	}

	@Override
	public List<String> getEnqStatus() {
	    return List.of("New", "Enrolled", "Lost");
	}


	@Override
	public DashBoardResponse getDashboardData(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Autowired
	private StudentEnqRepo studentEnqRepo;

	@Override
	public String upsertEnquiry(EnquiryForm form) {
		Integer userId = (Integer) session.getAttribute("userId");
		Optional<UserDtlsEntity> userOpt = userDtlsRepo.findById(userId);

		if (userOpt.isPresent()) {
			StudentEnqEntity enq = new StudentEnqEntity();
			enq.setStudentName(form.getStudentName());
			enq.setStudentPhno(form.getStudentPhno());
			enq.setCourseName(form.getCourseName());
			enq.setClassMode(form.getMode());
			enq.setEnqStatus(form.getEnqStatus());
			enq.setUser(userOpt.get());

			studentEnqRepo.save(enq); // ✅ Persist enquiry

			return "success";
		}

		return "fail";
	}
	@Override
	public List<StudentEnqEntity> getFilteredEnquiries(EnquirySearchFilter filter, Integer userId) {
	    Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
	    if (findById.isPresent()) {
	        UserDtlsEntity user = findById.get();
	        List<StudentEnqEntity> enquiries = user.getEnquiries();

	        if (filter.getCourseName() != null && !filter.getCourseName().isEmpty()) {
	            enquiries = enquiries.stream()
	                .filter(e -> e.getCourseName().equals(filter.getCourseName()))
	                .collect(Collectors.toList());
	        }

	        if (filter.getEnqStatus() != null && !filter.getEnqStatus().isEmpty()) {
	            enquiries = enquiries.stream()
	                .filter(e -> e.getEnqStatus().equals(filter.getEnqStatus()))
	                .collect(Collectors.toList());
	        }

	        if (filter.getClassMode() != null && !filter.getClassMode().isEmpty()) {
	            enquiries = enquiries.stream()
	                .filter(e -> e.getClassMode().equals(filter.getClassMode()))
	                .collect(Collectors.toList());
	        }

	        return enquiries;
	    }

	    return List.of(); // return empty list if user not found
	}

	
//	@Override
//	public List<StudentEnqEntity> getFilteredEnquiries(EnquirySearchFilter filter) {
//	    Integer userId = (Integer) session.getAttribute("userId");
//	    Optional<UserDtlsEntity> userOpt = userDtlsRepo.findById(userId);
//
//	    if (userOpt.isPresent()) {
//	        List<StudentEnqEntity> enquiries = userOpt.get().getEnquiries();
//
//	        // In-memory filtering (for simplicity — use JPA Query for efficiency in real projects)
//	        return enquiries.stream()
//	                .filter(e -> filter.getCourseName() == null || filter.getCourseName().isEmpty() || e.getCourseName().equals(filter.getCourseName()))
//	                .filter(e -> filter.getEnqStatus() == null || filter.getEnqStatus().isEmpty() || e.getEnqStatus().equals(filter.getEnqStatus()))
//	                .filter(e -> filter.getClassMode() == null || filter.getClassMode().isEmpty() || e.getClassMode().equals(filter.getClassMode()))
//	                .toList();
//	    }
//
//	    return List.of(); // empty list
//	}




	@Override
	public EnquiryForm getEnquiry(Integer enqId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

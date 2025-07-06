package in.tejas.binding;

import lombok.Data;

@Data
public class EnquiryForm {
    private String studentName;
    private Long studentPhno;
    private String studentEmail;
    
    private String mode;        // Online / Offline
    private String courseName;  // Java, DevOps, etc.
    private String enqStatus;   // New, Enrolled, Lost
}

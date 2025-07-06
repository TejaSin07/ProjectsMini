package in.tejas.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "student_enquiries") 
@Data
public class StudentEnqEntity {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enqId;

    // other fields, getters, setters...
    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key column in student_enquiries table
    private UserDtlsEntity user;

    private String classMode;
    private String courseName;
   
    @CreationTimestamp
    private Date dateCreated;
    private String enqStatus;
    
    @UpdateTimestamp
    private Date lastUpdated;
    private String studentName;
    private long studentPhno;
    
}

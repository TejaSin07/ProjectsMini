package in.tejas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "student_enquiries") // optional: matches your DB table name
public class StudentEnqEntity {
  
    @Id
    private Integer id;

    // other fields, getters, setters...
    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key column in student_enquiries table
    private UserDtlsEntity user;

}

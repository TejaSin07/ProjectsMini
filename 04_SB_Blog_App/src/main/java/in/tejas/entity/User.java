package in.tejas.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER_TBL")
@Setter
@Getter   //dont write @Data as it will call toString and it will give stack overflow
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String fname;
	private String lname;
	private String email;
	private String pwd;
	
//	@OneToMany(mappedBy="user")
//	private List<Post> posts;
}

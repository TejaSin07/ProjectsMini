package in.tejas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.tejas.entity.StudentEnqEntity;

public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer>{

}

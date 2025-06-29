package in.tejas.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.tejas.entity.UserDtlsEntity;

public interface UserDtlsRepo extends JpaRepository<UserDtlsEntity,Integer> {
	public UserDtlsEntity findByEmail(String email);
}

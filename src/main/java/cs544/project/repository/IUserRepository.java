package cs544.project.repository;
import cs544.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
	 public User findByName(String name);
}

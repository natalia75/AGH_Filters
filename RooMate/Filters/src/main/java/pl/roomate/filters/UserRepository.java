package pl.roomate.filters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import roomate.filters.model.*;

public interface UserRepository extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile>{

}

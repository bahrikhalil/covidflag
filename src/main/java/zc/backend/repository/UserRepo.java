package zc.backend.repository;


import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zc.backend.modles.Users;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
    Users findByUsername(String username);
  @Query("SELECT u.id FROM Users u JOIN u.events")
   List<Integer> getAttendants();

}

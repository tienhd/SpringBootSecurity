package us.yoloz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import us.yoloz.model.UserAccount;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: tienhd
 * Date: 9/28/15
 * Time: 4:43 PM
 */
@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, UUID>
{
    public UserAccount findByUsername(String username);
}

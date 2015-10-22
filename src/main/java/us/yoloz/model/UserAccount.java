package us.yoloz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: tienhd
 * Date: 9/20/15
 * Time: 12:16 PM
 */
@Entity
@Table (name = "user_account")
public class UserAccount extends BaseEntity
{
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}

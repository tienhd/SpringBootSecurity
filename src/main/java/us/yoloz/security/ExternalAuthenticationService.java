package us.yoloz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import us.yoloz.model.UserAccount;
import us.yoloz.repository.UserAccountRepository;
import us.yoloz.security.AuthenticationWithToken;
import us.yoloz.security.ExternalServiceAuthenticator;

/**
 * Created with IntelliJ IDEA.
 * User: tienhd
 * Date: 9/29/15
 * Time: 10:12 AM
 */
public class ExternalAuthenticationService implements ExternalServiceAuthenticator
{
    private static final String INVALID_USER_PASSWORD = "Wrong password";
    private static final String INVALID_USER = "User not found";
    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public AuthenticationWithToken authenticate(String username, String password)
    {
        UserAccount userAccount = userAccountRepository.findByUsername(username);
        if (userAccount == null)
        {
            throw new BadCredentialsException(INVALID_USER);
        }

        if (!userAccount.getPassword().equals(password))
        {
            throw new BadCredentialsException(INVALID_USER_PASSWORD);
        }

        AuthenticationWithToken authenticationWithToken = new AuthenticationWithToken(userAccount, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        authenticationWithToken.setAuthenticated(true);
        return authenticationWithToken;
    }
}

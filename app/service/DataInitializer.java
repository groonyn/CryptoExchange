package service;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.providers.password.UsernamePasswordAuthProvider;
import com.feth.play.module.pa.user.AuthUser;
import models.SecurityRole;
import models.User;
import providers.MyLoginUsernamePasswordAuthUser;

import java.util.Arrays;

/**
 * Data initializer class.
 */
public class DataInitializer {
    public DataInitializer() {
        if (SecurityRole.find.findCount() == 0) {
            for (final String roleName : Arrays
                    .asList(controllers.Application.USER_ROLE)) {
                final SecurityRole role = new SecurityRole();
                role.roleName = roleName;
                role.save();
            }
        }

    }

    private void createAdminAccounts() {
        AuthUser authUser = new MyLoginUsernamePasswordAuthUser("ilarion.golovin@gmail.com");
        User user = User.create(authUser);
    }
}

package codeswarm.io.perun.utility;

import codeswarm.io.perun.dto.UserDTO;
import codeswarm.io.perun.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements DataMapper<UserDTO, User> {

    @Override
    public User convert(UserDTO userDTO) {

        User user = new User();

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setToken(userDTO.getToken());
        //user.setRoles(userDTO.getRoles());
        user.setEnabled(userDTO.getEnabled());
        user.setConfirmed(userDTO.getConfirmed());
        //user.setVariants(userDTO.getVariants());

        return user;
    }
}

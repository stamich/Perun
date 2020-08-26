package codeswarm.io.perun.utility;

import codeswarm.io.perun.dto.UserDTO;
import codeswarm.io.perun.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements DataMapper<User, UserDTO> {

    @Override
    public UserDTO convert(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setToken(user.getToken());
        //userDTO.setRoles(user.getRoles());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setConfirmed(user.getConfirmed());
        //userDTO.setVariants(user.getVariants());

        return userDTO;
    }
}

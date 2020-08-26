package codeswarm.io.perun.service;

import codeswarm.io.perun.dto.UserDTO;
import codeswarm.io.perun.dto.UserDTOs;
import codeswarm.io.perun.exception.*;
import codeswarm.io.perun.helper.PasswordHelper;
import codeswarm.io.perun.helper.TokenHelper;
import codeswarm.io.perun.model.User;
import codeswarm.io.perun.repository.UserRepository;
import codeswarm.io.perun.utility.DataMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static codeswarm.io.perun.utility.Constants.*;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
@SuppressWarnings("rawtypes")
public class UserServiceImpl {

    private UserRepository userRepository;
    private PasswordHelper passwordHelper;
    private TokenHelper tokenHelper;
    private DataMapper<User, UserDTO> dataMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordHelper passwordHelper,
                           TokenHelper tokenHelper, DataMapper<User, UserDTO> dataMapper) {
        this.userRepository = userRepository;
        this.passwordHelper = passwordHelper;
        this.tokenHelper = tokenHelper;
        this.dataMapper = dataMapper;
    }

    // READ
    public ResponseEntity findOne(Long id) {

        if (StringUtils.isEmpty(id.toString())) {
            throw new ServiceException(ERROR_THERE_IS_NO_USER_YET);
        }

        User user = userRepository.findOne(id);
        UserDTO userDTO = dataMapper.convert(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity findAll() {

        if (userRepository.findAll().isEmpty()) {
            throw new ServiceException(ERROR_THERE_IS_NO_USER_YET);
        }

        List<User> users = userRepository.findAll();
        List<UserDTO> userDTO = new LinkedList<>();
        UserDTOs userDTOs = new UserDTOs();

        users.forEach(user -> {
            UserDTO item = dataMapper.convert(user);
            userDTO.add(item);
        });
        userDTOs.setUserDTOList(userDTO);

        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    // CREATE
    public ResponseEntity createUser(UserDTO userDTO) {

        if (StringUtils.isEmpty(userDTO.getEmail())) {
            throw new EmailNotFoundException(ERROR_EMAIL_REQURIED);
        }
        if (!userDTO.getEmail().isEmpty()) {
            throw new DuplicateEmailException(ERROR_EMAIL_ALREADY_EXISTS);
        }
        if (StringUtils.isEmpty(userDTO.getPassword())) {
            throw new PasswordException(ERROR_PASSWORD_REQURIED);
        }
        if (PasswordHelper.isPasswordStrong(userDTO.getPassword())) {
            throw new PasswordException(ERROR_PASSWORD_POLICY);
        }
        if (StringUtils.isEmpty(userDTO.getFirstName())) {
            throw new NameException(ERROR_FIRST_NAME_REQURIED);
        }
        if (StringUtils.isEmpty(userDTO.getLastName())) {
            throw new NameException(ERROR_LAST_NAME_REQURIED);
        }

        User user = new User();

        String firstToken = "007";

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setEnabled(userDTO.getEnabled());
        //user.setRoles(userDTO.getRoles());
        user.setToken(firstToken);
        userRepository.saveAndFlush(user);
        userDTO.setPassword(null);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public ResponseEntity login(UserDTO userDTO) {

        if (StringUtils.isEmpty(userDTO.getEmail())){
            throw new EmailNotFoundException(ERROR_EMAIL_REQURIED);
        }
        if (StringUtils.isEmpty(userDTO.getPassword())) {
            throw new PasswordException(ERROR_PASSWORD_REQURIED);
        }

        User user = userRepository.findByEmail(userDTO.getEmail());

        String generatedToken = tokenHelper.generateToken(user.getId());
        user.setToken(generatedToken);
        userRepository.updateUserToken(generatedToken, user.getId());
        userDTO.setToken(generatedToken);
        userDTO = dataMapper.convert(user);
        userDTO.setPassword(null);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // UPDATE
    public ResponseEntity updateUser(UserDTO userDTO) {

        if (StringUtils.isEmpty(userDTO.getEmail())) {
            throw new EmailNotFoundException(ERROR_EMAIL_REQURIED);
        }

        User user = userRepository.findByEmail(userDTO.getEmail());

        if (user != null) {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());

            if (!userDTO.getPassword().equals(user.getPassword())) {
                user.setPassword(passwordHelper.hashPassword(userDTO.getPassword()));
            }

            user.setPassword(userDTO.getPassword());
            //user.setRoles(userDTO.getRoles());
            user.setEnabled(userDTO.getEnabled());
            user.setUpdatedAt(LocalDateTime.now());
            user.setUpdatedBy(user.getEmail());
        }
        Optional.ofNullable(userRepository.saveAndFlush(user));
        userDTO = dataMapper.convert(user);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // DELETE
    public ResponseEntity deleteById(Long id) {

        if (id == null) {
            throw new ServiceException(ERROR_THERE_IS_NO_USER_YET);
        }

        User user = userRepository.findOne(id);
        userRepository.deleteById(user.getId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity deleteAll() {

        List<User> users = userRepository.findAll();
        userRepository.deleteAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

package com.lvb.challenge.picpay.PicpayBackendChallenge.service.user;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user.CreateUserDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user.UserDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.User;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.UserRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.keycloak.KeyCloakService;
import com.lvb.challenge.picpay.PicpayBackendChallenge.validations.UserValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KeyCloakService keyCloakService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserValidation userValidation;

    public Long createUser(final CreateUserDto createUserDto) {

        // Business Validation
        userValidation.newAccountValidation(createUserDto);

        // Create a new User in Keycloak
        final String keycloakUserId = keyCloakService.createUser(createUserDto);

        //Create a new User in DB
        var mappedUser = modelMapper.map(createUserDto, User.class);
        mappedUser.setUserIdKeycloak(keycloakUserId);
        var user = userRepository.save(mappedUser);

        return user.getId();
    }

    public UserDto getUserById(final Long id) {

        //TODO verificar se vale a pena colocar um throw para usuario não encontrado

        var user = findUserById(id);

        if (user == null) {
            return null;
        }

        return  modelMapper.map(user, UserDto.class);
    }

    private User findUserById(final Long id) {

        var user = userRepository.findById(id);


        //TODO verificar se vale a pena colocar um throw para usuario não encontrado

        return user.orElse(null);
    }

    public void updateUser(final UserDto userDto, final Long id) {

        var oldUser = findUserById(id);

        if (oldUser == null) {
            return ;
        }

        //TODO validar se vale a pena separar as o codigo abaixo para outra funcao
        var newUser = modelMapper.map(userDto, User.class);
        newUser.setId(oldUser.getId());
        newUser.setUserIdKeycloak(oldUser.getUserIdKeycloak());
        newUser.setCreatedAt(oldUser.getCreatedAt());

        userRepository.save(newUser);

        keyCloakService.updateUser(userDto, newUser.getUserIdKeycloak());
    }

    public void deleteUser(final Long id) {

        var oldUser = findUserById(id);

        if (oldUser == null) {
            return ;
        }

        userRepository.delete(oldUser);
        keyCloakService.removeUser(oldUser.getUserIdKeycloak());
    }
}

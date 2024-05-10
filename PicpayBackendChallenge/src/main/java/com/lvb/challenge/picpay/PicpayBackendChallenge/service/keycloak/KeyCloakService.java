package com.lvb.challenge.picpay.PicpayBackendChallenge.service.keycloak;

import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.account.AccountDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user.CreateUserDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.dto.user.UserDto;
import com.lvb.challenge.picpay.PicpayBackendChallenge.security.Credentials;
import com.lvb.challenge.picpay.PicpayBackendChallenge.util.Utils;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class KeyCloakService {

    @Autowired
    private UsersResource usersResource;

    public String createAccount(final AccountDto accountDto, final String password) {

        final UserRepresentation user = buildUserKeycloak(accountDto, password);

        final Response response = usersResource.create(user);

        System.out.printf("Response: %s %s%n", response.getStatus(), response.getStatusInfo());
        return CreatedResponseUtil.getCreatedId(response);
    }

    public void updateAccount(final AccountDto accountDto, final String accountId) {

        final UserResource userResource = usersResource.get(accountId);

        final UserRepresentation userRepresentation = buildUserKeycloak(accountDto, null);

        userResource.update(userRepresentation);

        //TODO add error treatment here
    }

    public void removeAccount(final String accountId) {
        final UserResource userResource = usersResource.get(accountId);
        userResource.remove();
        //TODO add error treatment here
    }

    public void changeUserPassword(final String keycloakUserId, final String newPassword) {
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(newPassword);

        final UserResource userResource = usersResource.get(keycloakUserId);

        // Set password credential
        userResource.resetPassword(passwordCred);

        //TODO add error treatment here
    }

    private UserRepresentation buildUserKeycloak(final AccountDto accountDto, final String password) {
        final UserRepresentation user = new UserRepresentation();

        if (password != null) {
            final CredentialRepresentation credential = Credentials
                    .createPasswordCredentials(password);

            user.setCredentials(Collections.singletonList(credential));
        }

        user.setUsername(Utils.getUsernameFromEmail(accountDto.getEmail()));
        user.setFirstName(accountDto.getFirstname());
        user.setLastName(accountDto.getLastName());
        user.setEmail(accountDto.getEmail());

        user.setRequiredActions(Collections.emptyList());
        //TODO remove the line below
        user.setAttributes(Collections.singletonMap("qualquer atributo", List.of("qualquer valor")));
        user.setEnabled(true);

        return user;
    }

}

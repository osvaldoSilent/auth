package com.osvaldevops.auth.infrastructure.adapter.in.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.osvaldevops.auth.application.port.in.CreateUser;
import com.osvaldevops.auth.domain.model.User;
import com.osvaldevops.auth.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import com.osvaldevops.auth.infrastructure.adapter.in.rest.dto.UserResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private CreateUser createUser;

    @InjectMocks
    private UserController userController;

    @Test
    void should_ReturnCreatedResponse_When_ValidRequestProvided() {
        // Given
        CreateUserRequest request = new CreateUserRequest("johndoe", "john@example.com", "password123", "ACTIVE");
        User savedUser = new User("johndoe", "john@example.com", "password123", "ACTIVE");
        savedUser.setId(1L);

        when(createUser.createUser(any(User.class))).thenReturn(savedUser);

        // When
        ResponseEntity<UserResponse> response = userController.createUser(request);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().id());
        assertEquals("johndoe", response.getBody().username());
        assertEquals("john@example.com", response.getBody().email());
        assertEquals("ACTIVE", response.getBody().status());
        verify(createUser, times(1)).createUser(any(User.class));
    }

    @Test
    void should_PassMappedDomainUserToUseCase_When_ValidRequestProvided() {
        // Given
        CreateUserRequest request = new CreateUserRequest("johndoe", "john@example.com", "password123", "ACTIVE");
        User savedUser = new User("johndoe", "john@example.com", "password123", "ACTIVE");
        savedUser.setId(1L);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        when(createUser.createUser(userCaptor.capture())).thenReturn(savedUser);

        // When
        userController.createUser(request);

        // Then
        User capturedUser = userCaptor.getValue();
        assertNotNull(capturedUser);
        assertEquals("johndoe", capturedUser.getUsername());
        assertEquals("john@example.com", capturedUser.getEmail());
        assertEquals("password123", capturedUser.getPassword());
        assertEquals("ACTIVE", capturedUser.getStatus());
        verify(createUser, times(1)).createUser(any(User.class));
    }

    @Test
    void should_ThrowNullPointerException_When_RequestIsNull() {
        // Given
        CreateUserRequest request = null;

        // When
        Executable executable = () -> userController.createUser(request);

        // Then
        assertThrows(NullPointerException.class, executable);
        verifyNoInteractions(createUser);
    }

    @Test
    void should_PropagateIllegalArgumentException_When_UseCaseThrowsIllegalArgumentException() {
        // Given
        CreateUserRequest request = new CreateUserRequest("johndoe", "invalid-email", "password123", "ACTIVE");
        when(createUser.createUser(any(User.class)))
                .thenThrow(new IllegalArgumentException("Invalid email format"));

        // When
        Executable executable = () -> userController.createUser(request);

        // Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Invalid email format", exception.getMessage());
        verify(createUser, times(1)).createUser(any(User.class));
    }

    @Test
    void should_PropagateIllegalStateException_When_UserAlreadyExists() {
        // Given
        CreateUserRequest request = new CreateUserRequest("johndoe", "john@example.com", "password123", "ACTIVE");
        when(createUser.createUser(any(User.class)))
                .thenThrow(new IllegalStateException("User already exists"));

        // When
        Executable executable = () -> userController.createUser(request);

        // Then
        IllegalStateException exception = assertThrows(IllegalStateException.class, executable);
        assertEquals("User already exists", exception.getMessage());
        verify(createUser, times(1)).createUser(any(User.class));
    }
}


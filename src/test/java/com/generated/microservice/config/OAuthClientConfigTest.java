package com.generated.microservice.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.core.Authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
        "spring.security.oauth2.client.registration.external.client-id=clientId",
        "spring.security.oauth2.client.registration.external.client-secret=clientSecret",
        "spring.security.oauth2.client.registration.external.provider=provider",
        "spring.security.oauth2.client.provider.external.token-uri=http://localhost:8080/oauth/token",
        "spring.security.oauth2.client.registration.external.authorization-grant-type=client_credentials",
        "spring.security.oauth2.client.registration.external.redirect-uri=http://localhost:8080/redirect"
})
class OAuthClientConfigTest {

    private OAuthClientConfig config;

    @BeforeEach
    void setUp() {
        config = new OAuthClientConfig();
        ReflectionTestUtils.setField(config, "clientId", "clientId");
        ReflectionTestUtils.setField(config, "clientSecret", "clientSecret");
        ReflectionTestUtils.setField(config, "provider", "provider");
        ReflectionTestUtils.setField(config, "tokenUri", "http://localhost:8080/oauth/token");
        ReflectionTestUtils.setField(config, "authorizationGrantType", "client_credentials");
        ReflectionTestUtils.setField(config, "redirectUri", "http://localhost:8080/redirect");

    }


    @Test
    void clientRegistrationRepository_shouldCreateRepository() {
        ClientRegistrationRepository repository = config.clientRegistrationRepository();
        assertNotNull(repository);
    }

    @Test
    void authorizedClientManager_shouldCreateManager() {
        ClientRegistrationRepository clientRegistrationRepository = config.clientRegistrationRepository();
        OAuth2AuthorizedClientRepository authorizedClientRepository = new OAuth2AuthorizedClientRepository() {
            @Override
            public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
                //Do nothing
            }

            @Override
            public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, Authentication authentication, HttpServletRequest request) {
                return null;
            }


            @Override
            public void removeAuthorizedClient(String clientRegistrationId, Authentication principal, HttpServletRequest request, HttpServletResponse response) {
                //Do nothing
            }
        };

        OAuth2AuthorizedClientManager manager = config.authorizedClientManager(clientRegistrationRepository, authorizedClientRepository);

        assertNotNull(manager);
    }
}
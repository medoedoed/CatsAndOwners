import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import ru.medoedoed.Application;
import ru.medoedoed.models.authEntities.SignUpRequest;
import ru.medoedoed.services.authServices.AuthenticationService;
import ru.medoedoed.utils.Role;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@SqlGroup({
  @Sql(value = "classpath:init/init.sql", executionPhase = BEFORE_TEST_METHOD),
})
public class OwnerTests {
  @Autowired private MockMvc mockMvc;
  @Autowired private AuthenticationService authenticationService;

  private String token;

  private String signIn(String username, String password, Long ownerId, Role role)
      throws Exception {
    if (role.equals(Role.ADMIN_ROLE)) {
      authenticationService.signUpAdmin(
          SignUpRequest.builder().username(username).password(password).build());
    } else {
      authenticationService.signUp(
          SignUpRequest.builder().username(username).password(password).ownerId(ownerId).build());
    }

    var result =
        mockMvc
            .perform(
                post("/auth/sign-in")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\"username\":\""
                            + username
                            + "\","
                            + "\"password\":\""
                            + password
                            + "\"}"))
            .andExpect(status().isOk())
            .andReturn();

    return result.getResponse().getContentAsString();
  }

  public String signInAdmin() throws Exception {
    if (token == null) {
      token = signIn("admin", "admin1", null, Role.ADMIN_ROLE);
    }
    return token;
  }

  @Test
  void addOwnerTest() throws Exception {
    mockMvc
        .perform(
            post("/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + signInAdmin())
                .content(
                    "{\"name\":\"name12\"," + "\"birthDate\":\"2007-01-01\"}"))
        .andExpect(status().isOk())
            .andExpect(jsonPath("$", equalTo(1)));
  }

  @Test
  void getAllOwnersTest() throws Exception {
    mockMvc
        .perform(get("/owners").header("Authorization", "Bearer " + signInAdmin()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2));
  }

  @Test
  void getOwnerByIdTest() throws Exception {
    mockMvc
        .perform(get("/owners/6").header("Authorization", "Bearer " + signInAdmin()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("owner1"));
  }

  @Test
  void updateOwnerTest() throws Exception {
    mockMvc
        .perform(
            put("/owners")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + signInAdmin())
                .content(
                    "{\"id\":11,"
                        + "\"name\":\"John Doe\","
                        + "\"birthDate\":\"1990-01-01\","
                        + "\"catsId\":[]}"))
        .andExpect(status().isOk());
  }

  @Test
  void deleteOwnerTest() throws Exception {
    mockMvc
        .perform(delete("/owners/12").header("Authorization", "Bearer " + signInAdmin()))
        .andExpect(status().isOk());
  }
}

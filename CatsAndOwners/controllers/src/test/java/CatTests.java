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
public class CatTests {
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

  public String signInUser(Long ownerId) throws Exception {
    if (token == null) {
      token = signIn("user1", "user123", ownerId, Role.USER_ROLE);
    }
    return token;
  }

  @Test
  void addCatAdminTest() throws Exception {
    mockMvc
        .perform(
            post("/cats")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"name\":\"name3\","
                        + "\"birthDate\":\"2011-01-11\","
                        + "\"breed\":\"breeeed\","
                        + "\"colorId\":6,"
                        + "\"ownerId\":6,"
                        + "\"friendsId\":[]}")
                .header("Authorization", "Bearer " + signInAdmin()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", equalTo(2)));
  }

  @Test
  void addCatUserTest() throws Exception {
    mockMvc
        .perform(
            post("/cats")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"name\":\"name3\","
                        + "\"birthDate\":\"2011-01-11\","
                        + "\"breed\":\"breed23\","
                        + "\"colorId\":6,"
                        + "\"ownerId\":7,"
                        + "\"friendsId\":[]}")
                .header("Authorization", "Bearer " + signInUser(7L)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", equalTo(3)));
  }

  @Test
  void getAllCatsTest() throws Exception {
    mockMvc
        .perform(get("/cats/all").header("Authorization", "Bearer " + signInAdmin()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2));
  }

  @Test
  void getAllCatsUserTest() throws Exception {
    mockMvc
        .perform(get("/cats/all").header("Authorization", "Bearer " + signInUser(6L)))
        .andExpect(status().isForbidden());
  }

  @Test
  void getCatByIdTest() throws Exception {
    mockMvc
        .perform(get("/cats/6").header("Authorization", "Bearer " + signInAdmin()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("cat1"));
  }

  @Test
  void getCatByIdUserTest() throws Exception {
    mockMvc
        .perform(get("/cats/6").header("Authorization", "Bearer " + signInUser(7L)))
        .andExpect(status().isForbidden());
  }

  @Test
  void updateCatTest() throws Exception {
    mockMvc
        .perform(
            put("/cats")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"id\":11,"
                        + "\"name\":\"Tom\","
                        + "\"birthDate\":\"2018-05-15\","
                        + "\"breed\":\"Siamese\","
                        + "\"colorId\":6,"
                        + "\"ownerId\":7,"
                        + "\"friendsId\":[]}")
                .header("Authorization", "Bearer " + signInAdmin()))
        .andExpect(status().isOk());
  }

  @Test
  void deleteCatTest() throws Exception {
    mockMvc
        .perform(delete("/cats/6").header("Authorization", "Bearer " + signInAdmin()))
        .andExpect(status().isOk());
  }
}

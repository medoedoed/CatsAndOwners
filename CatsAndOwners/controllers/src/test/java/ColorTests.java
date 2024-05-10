import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
public class ColorTests {
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
          SignUpRequest.builder().username(username).password(password).build());
    }

    var result =
        mockMvc
            .perform(
                MockMvcRequestBuilders.get("/auth/sign-in")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\"username\":\"" + username + "\"," + "\"password\":\"" + password + "\"}"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.token").isString())
            .andReturn();

    return JsonPath.read(result.getResponse().getContentAsString(), "$.token");
  }

  public String signInAdmin() throws Exception {
    if (token == null) {
      token = signIn("admin", "admin1", null, Role.ADMIN_ROLE);
    }
    return token;
  }

  public String signInUser(Long ownerId) throws Exception {
    if (token == null) {
      token = signIn("user", "user", ownerId, Role.USER_ROLE);
    }
    return token;
  }

  @Test
  public void addCatWithoutToken() throws Exception {

    mockMvc.perform(post("/cats")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"name\":\"Tom\"," +
                            "\"birthDate\":\"2018-05-15\"," +
                            "\"breed\":\"Siamese\"," +
                            "\"colorId\":11," +
                            "\"ownerId\":11," +
                            "\"friendsId\":[]}"))
            .andExpect(status().isForbidden());
  }


}

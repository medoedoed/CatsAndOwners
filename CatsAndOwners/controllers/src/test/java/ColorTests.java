import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                MockMvcRequestBuilders.post("/auth/sign-in")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        "{\"username\":\"" + username + "\"," + "\"password\":\"" + password + "\"}"))
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

//  @Test
//  public void addCatWithoutToken() throws Exception {
//
//    mockMvc.perform(post("/cats")
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content("{\"name\":\"cat1\"," +
//                            "\"birthDate\":\"2007-01-01\"," +
//                            "\"breed\":\"breed1\"," +
//                            "\"colorId\":2," +
//                            "\"ownerId\":1," +
//                            "\"friendsId\":[]}"))
//            .andExpect(status().isForbidden());
//  }

  @Test
  void addColor1Test() throws Exception {
    mockMvc.perform(post("/colors").contentType(MediaType.APPLICATION_JSON)
                    .content("{\"colorName\":\"color3\"}")
                    .header("Authorization", "Bearer " + signInAdmin()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", equalTo(1)));
  }

  @Test
  void getAllColorsTest() throws Exception {
    mockMvc.perform(get("/colors")
                    .header("Authorization", "Bearer " + signInAdmin()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2));
  }

  @Test
  void getAllColorsUserTest() throws Exception {
    mockMvc.perform(get("/colors")
                    .header("Authorization", "Bearer " + signInUser(6L)))
            .andExpect(status().isOk());
  }

  @Test
  void getColorByIdTest() throws Exception {
    mockMvc.perform(get("/colors/6")
                    .header("Authorization", "Bearer " + signInAdmin()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.colorName").value("white"));
  }

  @Test
  void updateColorTest() throws Exception {
    mockMvc.perform(put("/colors").contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":7," +
                            "\"colorName\":\"green\"}")
                    .header("Authorization", "Bearer " + signInAdmin()))
            .andExpect(status().isOk());
  }

  @Test
  void deleteColorTest() throws Exception {
    mockMvc.perform(delete("/colors/7")
                    .header("Authorization", "Bearer " + signInAdmin()))
            .andExpect(status().isOk());

  }
}

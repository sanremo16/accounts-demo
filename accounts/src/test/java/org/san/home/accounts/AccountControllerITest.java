package org.san.home.accounts;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.SneakyThrows;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.san.home.accounts.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class AccountControllerITest {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public WireMockRule wiremock = new WireMockRule(8083);

    @Autowired
    private AccountService accountService;


    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void getAll() {
        this.mockMvc.perform(get("http://localhost:"+ port + "/accounts")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.accountDtoList", hasSize(3)))
                .andExpect(jsonPath("$._embedded.accountDtoList[*].id", containsInAnyOrder(100, 200, 300)))
                .andExpect(jsonPath("$._embedded.accountDtoList[*]._links.self.href", hasSize(3)));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void getByAccNum() {
        this.mockMvc.perform(get("http://localhost:"+ port + "/accounts/11111111111111111111")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(100)));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void delete() {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:"+ port + "/accounts/11111111111111111111")).andDo(print())
                .andExpect(status().isOk());
        assertEquals(2, accountService.findAll().size());
    }

    @Test
    @SneakyThrows
    public void add() {
        String s = "{\"num\":\"555\",\"currencyType\":\"USD\", \"personId\":\"555\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:"+ port + "/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(s))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void update() {
        String s = "{\"num\":\"11111111111111111111\",\"currencyType\":\"USD\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:"+ port + "/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(s))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyType", is("USD")));
     }

    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void topUp() {
        this.mockMvc.perform(post("http://localhost:"+ port + "/accounts/topUp")
                .param("accountNumber", "11111111111111111111")
                .param("moneyMajor", "10")
                .param("moneyMinor", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance.major", is(20)))
                .andExpect(jsonPath("$.balance.minor", is(10)));

        this.mockMvc.perform(post("http://localhost:"+ port + "/accounts/topUp")
                .param("accountNumber", "22222222222222222222")
                .param("moneyMajor", "10")
                .param("moneyMinor", "110"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance.major", is(31)))
                .andExpect(jsonPath("$.balance.minor", is(10)));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void withdraw() {
        this.mockMvc.perform(post("http://localhost:"+ port + "/accounts/withdraw")
                .param("accountNumber", "11111111111111111111")
                .param("moneyMajor", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance.major", is(5)));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void withdraw_noMoney() {
        this.mockMvc.perform(post("http://localhost:"+ port + "/accounts/withdraw")
                .param("accountNumber", "11111111111111111111")
                .param("moneyMajor", "15"))
                .andDo(print())
                .andExpect(status().is5xxServerError());
    }

    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void transfer() {
        this.mockMvc.perform(post("http://localhost:"+ port + "/accounts/transfer")
                .param("srcAccountNumber", "22222222222222222222")
                .param("dstAccountNumber", "11111111111111111111")
                .param("moneyMajor", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance.major", is(15)));
    }


    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void validationTest() {
        String s = "{\"num\":\"-11111111111111111111\",\"currencyType\":\"USD\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:"+ port + "/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(s))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode", is(14)));
    }

    @Test
    @SneakyThrows
    @DatabaseSetup({"/dataset/account.xml"})
    public void findByPerson() {
        String person = "[{\"id\": 100,\"globalId\": 100,\"firstName\": \"Alex\",\"secondName\": \"Alexeev\",\"thirdName\": \"Alexeevich\"}]";
        stubFor(WireMock.get(urlPathMatching("/persons.*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(person)));

        String s = "{\"globalId\": 100}";
        this.mockMvc.perform(post("http://localhost:"+ port + "/accounts/filtered/byPerson/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(s))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.accountDtoList", hasSize(2)))
                .andExpect(jsonPath("$._embedded.accountDtoList[*].id", containsInAnyOrder(100, 300)));
    }

}

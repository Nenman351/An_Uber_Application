package africa.nems.uberdeluxe.service;

import africa.nems.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import africa.nems.uberdeluxe.data.dto.response.RegisterResponse;
import africa.nems.uberdeluxe.data.models.Detail;
import africa.nems.uberdeluxe.data.models.Passenger;
import africa.nems.uberdeluxe.exceptions.BusinessLogicException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.ReplaceOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PassengerServiceImplTest {

    @Autowired
    private PassengerService passengerService;
    private RegisterPassengerRequest request;

    @BeforeEach
    public void setUp(){
        request = new RegisterPassengerRequest();
        request.setEmail("test@gmail.com");
        request.setPassword("password");
        request.setName("Amirah");

    }

    @Test
    void registerTest() {
        RegisterResponse registerResponse = passengerService.register(request);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getCode())
                .isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void getUserByIdTest(){
        var registerResponse =passengerService.register(request);
        Passenger foundPassenger = passengerService.getPassengerById(registerResponse.getId());
        assertThat(foundPassenger).isNotNull();
        Detail userDetails=foundPassenger.getUserDetails();
        assertThat(userDetails.getName()).isEqualTo(request.getName());
    }

    @Test
    public void updatePassengerTest() throws JsonPointerException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree("2349099876543");
        JsonPatch updatePayload = new JsonPatch(List.of(
                new ReplaceOperation(new JsonPointer("/phoneNumber"),
                        node)
        ));
        var registerResponse = passengerService.register(request);
        var updatedPassenger =passengerService.updatePassenger(registerResponse.getId(), updatePayload);
        assertThat(updatedPassenger).isNotNull();
        assertThat(updatedPassenger.getPhoneNumber()).isNotNull();
    }

    @Test
    public void deletePassengerTest() {
        var response = passengerService.register(request);
        passengerService.deletePassenger(response.getId());
        assertThrows(BusinessLogicException.class, ()->passengerService.getPassengerById(response.getId()));

    }

}
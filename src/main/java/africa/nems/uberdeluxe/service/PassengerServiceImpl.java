package africa.nems.uberdeluxe.service;

import africa.nems.uberdeluxe.cloud.CloudService;
import africa.nems.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import africa.nems.uberdeluxe.data.dto.response.RegisterResponse;
import africa.nems.uberdeluxe.data.dto.response.UpdatePassengerResponse;
import africa.nems.uberdeluxe.data.models.Detail;
import africa.nems.uberdeluxe.data.models.Passenger;
import africa.nems.uberdeluxe.data.repositories.PassengerRepository;
import africa.nems.uberdeluxe.exceptions.BusinessLogicException;
import africa.nems.uberdeluxe.mapper.ParaMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static africa.nems.uberdeluxe.util.AppUtilities.NUMBER_OF_ITEMS_PER_PAGE;

@AllArgsConstructor
@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService{


    private final PassengerRepository passengerRepository;
    private final CloudService cloudService;

    @Override
    public RegisterResponse register(RegisterPassengerRequest registerRequest) {
        Detail appUser = ParaMapper.map(registerRequest);
        appUser.setCreatedAt(LocalDateTime.now().toString());
        Passenger passenger = new Passenger();
        passenger.setUserDetails(appUser);
        Passenger savedPassenger = passengerRepository.save(passenger);
        RegisterResponse registerResponse = getRegisterResponse(savedPassenger);
        return registerResponse;
    }

    @Override
    public Passenger getPassengerById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElseThrow(()->
                new BusinessLogicException(
                        String.format("Passenger with id %d not found", passengerId)));
    }

    @Override
    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public Optional<Passenger> getPassengerBy(Long passengerId) {
        return passengerRepository.findById(passengerId);
    }

    @Override
    public Passenger updatePassenger(Long passengerId, JsonPatch updatePayload) {
        ObjectMapper mapper = new ObjectMapper();
        Passenger foundPassenger = getPassengerById(passengerId);
        Detail passengerDetails = foundPassenger.getUserDetails();
        //Passenger Object to node
        JsonNode node = mapper.convertValue(foundPassenger, JsonNode.class);
        try {
            //apply patch
            JsonNode updatedNode = updatePayload.apply(node);
            //node to Passenger Object
            var updatedPassenger = mapper.convertValue(updatedNode, Passenger.class);
            updatedPassenger = passengerRepository.save(updatedPassenger);
            return updatedPassenger;

        } catch (JsonPatchException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Page<Passenger> getAllPassenger(int pageNumber) {
        if (pageNumber<1) pageNumber = 0;
        else pageNumber=pageNumber-1;
        Pageable pageable = PageRequest.of(pageNumber, NUMBER_OF_ITEMS_PER_PAGE);
        return passengerRepository.findAll(pageable);
    }

    @Override
    public void deletePassenger(Long id) {

        passengerRepository.deleteById(id);
    }


    private static RegisterResponse getRegisterResponse(Passenger savedPassenger) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setId(savedPassenger.getId());
        registerResponse.setCode(HttpStatus.CREATED.value());
        registerResponse.setSuccess(true);
        registerResponse.setMessage("User Registration Successful");
        return registerResponse;
    }
}

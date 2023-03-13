package africa.nems.uberdeluxe.service;

import africa.nems.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import africa.nems.uberdeluxe.data.dto.response.RegisterResponse;
import africa.nems.uberdeluxe.data.dto.response.UpdatePassengerResponse;
import africa.nems.uberdeluxe.data.models.Passenger;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PassengerService {
        RegisterResponse register(RegisterPassengerRequest registerRequest);
        Passenger getPassengerById(Long passengerId);

        void savePassenger(Passenger passenger);
        Optional<Passenger> getPassengerBy(Long passengerId);
        Passenger updatePassenger(Long passengerId, JsonPatch updatePayload);

        Page<Passenger> getAllPassenger(int pageNumber);

        void deletePassenger(Long id);

}

package africa.nems.uberdeluxe.mapper;

import africa.nems.uberdeluxe.data.dto.request.RegisterPassengerRequest;
import africa.nems.uberdeluxe.data.models.Detail;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParaMapper {

    public static Detail map(RegisterPassengerRequest request){
        Detail user = new Detail();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        return user;
    }
}

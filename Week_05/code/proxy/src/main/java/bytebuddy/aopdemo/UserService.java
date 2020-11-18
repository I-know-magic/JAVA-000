package bytebuddy.aopdemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class UserService {
    private User user;
    public User queryUserById(long id){
        log.info("queryUsers-->"+id);
        user=new User(1,"lvlvl");
        return user;
    }
}

package bytebuddy.aopdemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceV2 {

    public User printA(long id){
       User user=new User(id,"00000");
       log.info(user.toString());
       return user;
    }

}

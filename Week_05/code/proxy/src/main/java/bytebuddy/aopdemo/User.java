package bytebuddy.aopdemo;

import lombok.Data;

@Data
public class User {
    String name;
    long id;

    public User(long id,String name ) {
        this.name = name;
        this.id = id;
    }
}

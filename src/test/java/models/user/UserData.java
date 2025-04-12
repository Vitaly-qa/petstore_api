package models.user;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)  // Добавляем поддержку цепочки вызовов для всех сеттеров
public class UserData {
    private long id;  // Используем long для ID
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}

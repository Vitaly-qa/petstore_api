package lombok.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Генерирует геттеры, сеттеры, toString, equals, hashCode
@NoArgsConstructor // Генерирует конструктор без параметров
public class UserLogin {
    private String username;
    private String password;
}
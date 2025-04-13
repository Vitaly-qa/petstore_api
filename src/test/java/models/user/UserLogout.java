package models.user;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)  // Добавляем поддержку цепочки вызовов для всех сеттеров
public class UserLogout {
    private String username;
}

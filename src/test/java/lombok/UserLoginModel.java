package lombok;

@Data // Генерирует геттеры, сеттеры, toString, equals, hashCode
@NoArgsConstructor // Генерирует конструктор без параметров
public class UserLoginModel {
    private String username;
    private String password;
}
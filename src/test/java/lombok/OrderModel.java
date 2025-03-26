package lombok;
@Data
@Builder
public class OrderModel {
    private Integer id;
    private Integer petId;
    private Integer quantity;
    private Integer shipDate;
    private String status;
    private Boolean complete;
}

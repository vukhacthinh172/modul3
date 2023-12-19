package business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
    private Long id ;
    private  String fullName ;
    private  String address ;
    private  String status ;
    private  int rollId ;
}

package pattern.gateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class MyEntity_1 {//step 2
    private final String request1;

    private final String request2;
    //step 5. create multiple type of constroct , that will create diff type of ICreator,
    // these contstructor will call from ICreator IMPL class
//    public MyEntity() {
//    }

//    public MyEntity_1(String request1) {
//        this.request1 = request1;
//    }
//    public MyEntity_1(String request1, String request2) {
//        this.request1 = request1;
//        this.request2 = request2;
//    }
//
//    public MyEntity_1(String request2) {
//        super(null);
//        this.request2 = request2;
//    }
}

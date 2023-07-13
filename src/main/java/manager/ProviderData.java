package manager;

import com.sun.org.apache.xpath.internal.operations.String;

import models.User;
import org.testng.annotations.*;

import java.util.*;


public class ProviderData{


    @DataProvider
    public Iterator<Object[]> userDto(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User()
                .withEmail("domes7@mail.com")
                .withPassword("123456Aa$")
            }
        );
        list.add(new Object[]{new User()
                .withEmail("domes7@mail.com")
                .withPassword("123456Aa$")
            }
        );
        return list.iterator();
    }

//@DataProvider
//public Iterator<Object[]> carSearchLocation(){
//
//}

@DataProvider
    public Iterator<Object[]> userDtoNegEmail(){
        List<Object[]> list = new ArrayList<>();
            list.add(new Object[]{new User()
                            .withEmail("domesmail.com")
                            .withPassword("123456Aa$")
                    }
            );
            list.add(new Object[]{new User()
                            .withEmail("domes7@mailcom")
                            .withPassword("123456Aa$")
                    }
            );
        return list.iterator();
    }
//    @DataProvider
//    public Iterator<Object[]> userDtoNeg(){
//        List<Object[]> list = new ArrayList<>();
//        if(testType.equals("email")) {
//            list.add(new Object[]{new User()
//                            .withEmail("domesmail.com")
//                            .withPassword("123456Aa$")
//                    }
//            );
//            list.add(new Object[]{new User()
//                            .withEmail("domes7@mailcom")
//                            .withPassword("123456Aa$")
//                    }
//            );
//        }
//        else if(testType.equals("password")) {
//            list.add(new Object[]{new User()
//                            .withEmail("domes7@mail.com")
//                            .withPassword("123456Aa")
//                    }
//            );
//            list.add(new Object[]{new User()
//                            .withEmail("domes7@mail.com")
//                            .withPassword("123Aa$")
//                    }
//            );
//            list.add(new Object[]{new User()
//                            .withEmail("domes7@mail.com")
//                            .withPassword("1234567a$")
//                    }
//            );
//            list.add(new Object[]{new User()
//                            .withEmail("domes7@mail.com")
//                            .withPassword("01234567890123456789AaBb$")
//                    }
//            );
//        }
//  //     else if(testType.equals("notexist")){
//        else  {
//            testType="notexist";
//            list.add(new Object[]{new User()
//                            .withEmail("domes@mail.com")
//                            .withPassword("123456789Aa$")
//                    }
//            );
//        }
//
//        return list.iterator();
//
//    }


}

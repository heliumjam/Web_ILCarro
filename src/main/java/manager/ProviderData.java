package manager;

import models.User;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;


public class ProviderData{


    @DataProvider
    public Iterator<Object[]> userDtoLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User()
                .withEmail("domes7@mail.com")
                .withPassword("123456Aa$")
            }
        );
        list.add(new Object[]{new User()
                .withEmail("dan1h9433@mail.com")
                .withPassword("123456789$Aa")
            }
        );        
		list.add(new Object[]{new User()
                .withEmail("dan1094455@mail.com")
                .withPassword("123456790$Aa")
            }
        );
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userRegDtoCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new FileReader(new File("C:\\Users\\heliu\\Documents\\IT\\GitHub\\ILCarro\\src\\test\\resources\\reg_dataList.csv")));
       String line = reader.readLine();
       while (line != null){
           String [] split = line.split(",");
           list.add(new Object[]{new User()
                   .withName(split[0])
                   .withLastName(split[1])
                   .withEmail(split[2])
                   .withPassword(split[3])
       });

        line = reader.readLine();
       }
        return list.iterator();
    }


@DataProvider
    public Iterator<Object[]> userDtoNeg(){
        List<Object[]> list = new ArrayList<>();
            list.add(new Object[]{new User()
                            .withEmail("domesmail.com")
                            .withPassword("123456Aa$")
                    }
            );
            list.add(new Object[]{new User()
                            .withEmail("domes77777@mailcom")
                            .withPassword("123456Aa$")
                    }
            );
			list.add(new Object[]{new User()
                            .withEmail("domes77777@mailcom")
                            .withPassword("1234561a$")
                    }
            );
			list.add(new Object[]{new User()
                            .withEmail("domes77777@mail.com")
                            .withPassword("12345678Aa")
                    }
            );
			list.add(new Object[]{new User()
                            .withEmail("domes77777@mail.com")
                            .withPassword("12345678A$")
                    }
            );
			list.add(new Object[]{new User()
                            .withEmail("domes77777@mail.com")
                            .withPassword("1Aa$")
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

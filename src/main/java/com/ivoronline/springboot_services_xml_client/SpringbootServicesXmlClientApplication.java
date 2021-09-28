package com.ivoronline.springboot_services_xml_client;

import com.ivoronline.soap.GetPersonResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootServicesXmlClientApplication {

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SpringbootServicesXmlClientApplication.class, args);

    //CALL SERVER
    GetPersonResponse getPersonResponse = PersonClient.getPerson();
    System.out.println(getPersonResponse.getName());

  }

}

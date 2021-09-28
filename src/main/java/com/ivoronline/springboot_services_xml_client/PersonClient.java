package com.ivoronline.springboot_services_xml_client;

import com.ivoronline.soap.GetPersonRequest;
import com.ivoronline.soap.GetPersonResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;

import java.net.URI;

public class PersonClient {

  //PROPERTIES
  static String serverURL = "http://localhost:8080/GetPerson";

  //============================================================================================
  // MAIN
  //============================================================================================
  public static GetPersonResponse getPerson() throws Exception {

    //CREATE REQUEST OBJECT
    GetPersonRequest    getPersonRequest  = new GetPersonRequest();
                        getPersonRequest.setId(1);

    //MARSHAL REQUEST OBJECT
    Document            requestDocument   = UtilXML.marshal(getPersonRequest, GetPersonRequest.class);
    String              requestString     = UtilXML.documentToString(requestDocument);

    //SEND  REQUEST
    URI                 serverURI         = new URI(serverURL);
    RestTemplate        restTemplate      = new RestTemplate();
    HttpHeaders         headers           = new HttpHeaders();
                        headers.set("Content-Type", "text/xml;charset=UTF-8");
    HttpEntity<String>  entity            = new HttpEntity<>(requestString, headers);
    String              responseString    = restTemplate.postForObject(serverURI, entity, String.class);

    //UNMARSHAL RESPONSE OBJECT
    Document            responseDocument  = UtilXML.stringToDocument(responseString);
    GetPersonResponse   getPersonResponse = (GetPersonResponse) UtilXML.unmarshal(responseDocument, GetPersonResponse.class);

    //DISPLAY REQUEST & RESPONSE
    System.out.println(requestString);
    System.out.println(responseString);

    //RETURN RESPONSE OBJECT
    return getPersonResponse;

  }

}

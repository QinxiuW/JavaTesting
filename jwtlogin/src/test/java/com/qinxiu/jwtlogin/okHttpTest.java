package com.qinxiu.jwtlogin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class okHttpTest {

    @Test
    public void getTest() {

        // request url
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // make an HTTP GET request
        String json = restTemplate.getForObject(url, String.class);

        // print json
        System.out.println(json);

    }

    @Test
    public void getWithBody() {
        String code = "0.AQwAgXtGaMli6E-AOQqygPmSiuM2ULBCm_NBqEgtyNSagF4BAFk.AQABAAIAAAD--DLA3VO7QrddgJg7WevrH3Im4eEMWuRSJXfi33P0o-Xzxf6DvZWba3YAjKRvaaC9StAQEcbEHhqjK-x3Fu8y0aM8WvYyEySmSVbCQRxB5iJ7aJBKsML_05-W3Y8qMObw3Gmn1V9sJVuWmp7jnJiTS5lRh7v2cWeDiHGW5Em_KddxQXgiPkOmLQOSvTwH5Riu9j1vzzN0axdLrb4Ofvc_y6UViGm0n0RBrxfHZQbyJJF0c8sox37-8yyHYlEYs9H0aCVKZakPhGMX-ibdNsDyPB2g_mZg3WemvuCT99M7-1ghhpm5EjyeGXxSIgcnn925R6lIpopVsgZPt8Xo3dCeElloSD8jUr1KQj8YubtMFXqJEqjMEdmNmwooIWnV08LbbhCBKJjflFyrRba42_Jah-lHCs8dLppKuPZ0rNGx4APNXkI1J2BbZpAUa9gNmM8B9IGEKCXvE3N6dij_8b5LMoS6DoLJUNqNxszL2FOKdkQ4Wnezjud1n-d5tYhzDtXc3a_pgg2vsLv_Vfk12HOY_7NedisndFH2jelY7xSOUQhqg3KVa8bgjtx3P337-iwIeDaSgUrZEKUEDl0AbeV7Yw0eDYiBIg6QG58Sb-pKEQH0SzvQlSGW__kOm7LmChw9febGx5sE7rhg7fu42Mq1Bf94fWV1mVzqESYkrjXE_Q1OJMtXuILE55XgUk8GFFHS46GkEveZX0y0lji7UwVQac99rtyxqr-7P4vQ1Bt1_XJaj_GbRi_LgbueScHQXPLuBYkO1dHc9LzcFe9VG92wOPTXCw9cTwbk7Mt_BOQxgk3HI6x2MejVOz7yW2SEVuHENnYqeibJzwqlMwNiWm6RxH_2lzgAqk9jTmOnxBDWa9UutS4lFmH0UACnG-JMzAhUYe88qFwk63fD0WN7P64s_Wu-QT5PqkX5E8pStGqmG6aVhq4tojJpP0WiSDHim83jHBu7ND79LnUc4EenvVbCi4-W54ax-vpZ_h4tqn1U3F7EP_GVlySJ7H0lk6vJ9r_6fIULlWp3VtKoQdwDzyjK6sV-SMJuFXsvQgrvyK8HXxBIPlvpqAq_b1yP2LLJksgveY5YbHSHRDUx8pmasUYD8EMF6E8Ol79QuWui4Y5VfVROm7sRWTnAZTF7fW_IP8xxKJrI7EIkemmtxGBhdDpkIAA";
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://login.microsoftonline.com/68467b81-62c9-4fe8-8039-0ab280f9928a/oauth2/v2.0/token";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        //body
        HashMap<String, String> body = new HashMap<>();
        body.put("client_id", "b05036e3-9b42-41f3-a848-2dc8d49a805e");
        body.put("client_secret", "xVk7Q~~-kgflD6zJMvNp3FBf46ILA0a9Fulx4");
        body.put("code", code);
        body.put("scope", "https://graph.microsoft.com/User.Read");
        body.put("grant_type", "authorization_code");
        body.put("redirect_uri", "https://localhost:5555/azuredemo");


        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

        System.out.println(result.getBody());
    }


    @Test
    public void post() throws Exception {

        String code = "0.AQwAgXtGaMli6E-AOQqygPmSiuM2ULBCm_NBqEgtyNSagF4BAFk.AQABAAIAAAD--DLA3VO7QrddgJg7WevryV-hGKaR8LJmrC38GWGtvDbn6RLyabX0OrNDK41vLFNBG-BkYVtR073oIAxvHygH3cpUTnea4UckPg9ih0hCHfYDSqVt0spN0xtCobiWmTtwWPw0I28-dpn-Rr13uJvpbIxrnYW6GsPDWVeWsomlAwbp_wTr8tW9_AQq8OrGWqBLVviAXQ0cXEOOOyWMxOEEbZU0ieytP9iW2gh8XoFTFtnox7sqKBiHOvusD4fMBpZDTqjk4B9vB7KDe_dIvlqrnJxjFNEKlmXyeFDcpVwmLcyz-8IqTJ-xGCAMrXaU6Rcx9IWrhYxR5YguasxwG22FB05BTBNwz6Ht74cPkibpuc_uGcji53bKsmyM3bfa0Xgo1RMTHFIPVYLkmBuar64cGh5kOnK9vrvaQbkx9KYgY9W1YxQjMJ2SHIhz4xyussVhdMxz1JTuPWbOzgFpAJfVpqNlijqNZf48KsV32_hXMd73p4md5n33hyPmeb9Lk_fq629DnBbOhteDcUvTKzf5rpF-cz2nnxbUG3n9ETBp7fb6KtLGlzImwUxPcWznFsTouAT4z8JcVT5sgMNRh5G_5bfmHUz9aRywuXz4R-6mD9xA55vewTutjwW8743VAgzwVn_Tyk1eM7zRBPduXR9-aErluser2rhvsXoLqImiG7ptCHsgmx4f_m0_r6kBF0yri6_dHJWkNo1UnhbmsSQGDyKvVC-mBJM9Vselh4BzS5hXE8m4ERFavx9eTjGbZHesaY82GssQXHNr8kbusFWktgC9PhZhVNMTKzwksqsSXQYWKgEiwMnVOmDQdNzau5ehgoEcr7KLuGN7gcG2gJKFfds9oNDdInBWcmVWQuwUylunbeAEipPU58hsLU8EitRuSS5X0T6wd0BnbAKztOE1A38GCkzi_ijIZy1xDr9BJEG_AIOcFdvlEoEpJkmCww_kLggFHZ7yaGrD0tEZ33ccSiIfyqjDFK9HLfzAQuNQ_Nj85GUtgpdsE9R5p39JJfxWB9YnOV6y5qqZLYpuFIOritI1VWwLVuGuJLTrIpT-TgbwKt1msQ3CSBrffh6XqdBC3UVAAiH8s0suyFR2bPSTB6DSB0aRoxrmTKIfPvBgzCVkDpbpI9PKQ62QXwdt3BqsLIqzeSny7ppLMrlgXNITIAA";
        String uri = "https://login.microsoftonline.com/68467b81-62c9-4fe8-8039-0ab280f9928a/oauth2/v2.0/token";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", "b05036e3-9b42-41f3-a848-2dc8d49a805e");
        body.add("client_secret", "xVk7Q~~-kgflD6zJMvNp3FBf46ILA0a9Fulx4");
        body.add("code", code);
        body.add("scope", "https://graph.microsoft.com/User.Read");
        body.add("grant_type", "authorization_code");
        body.add("redirect_uri", "https://localhost:5555/azuredemo");


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);
        System.out.println(response.getBody());

        Map<String, String> jsonMap = json2map(response.getBody());
        String accessToken =  String.valueOf(jsonMap.get("access_token"));
        System.out.println("accessToken: " + accessToken);
    }

    @Test
    public void getUserInfo() throws Exception {
        String uri = "https://graph.microsoft.com/v1.0/me";
        String token = "eyJ0eXAiOiJKV1QiLCJub25jZSI6IkpMLUh5bnAzZXVFQmZFSlhyelZaYkpENEs5YU1rVHhNaExXTzZxd3pkWjAiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik1yNS1BVWliZkJpaTdOZDFqQmViYXhib1hXMCIsImtpZCI6Ik1yNS1BVWliZkJpaTdOZDFqQmViYXhib1hXMCJ9.eyJhdWQiOiJodHRwczovL2dyYXBoLm1pY3Jvc29mdC5jb20iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC82ODQ2N2I4MS02MmM5LTRmZTgtODAzOS0wYWIyODBmOTkyOGEvIiwiaWF0IjoxNjQzMTQ2Njk5LCJuYmYiOjE2NDMxNDY2OTksImV4cCI6MTY0MzE1MTI5MCwiYWNjdCI6MCwiYWNyIjoiMSIsImFpbyI6IkFWUUFxLzhUQUFBQWd4RTY2WlBNZnFVb1RWV21PUTRtY1IvZGYrZnVhZCtpcm1Oek8zeWQ1dEp5anFaemE0blVEM1E2b3dxU2xOQXFpNGhJMzdoc1dza2dKbWJ6dEE3VmppRUo5TEtFSk53YjFIUEdBWDQyNk5FPSIsImFsdHNlY2lkIjoiMTpsaXZlLmNvbTowMDAzNDAwMUNGNERDQ0Q3IiwiYW1yIjpbInB3ZCIsIm1mYSJdLCJhcHBfZGlzcGxheW5hbWUiOiJBenVyZU9hdXRoMiIsImFwcGlkIjoiYjA1MDM2ZTMtOWI0Mi00MWYzLWE4NDgtMmRjOGQ0OWE4MDVlIiwiYXBwaWRhY3IiOiIxIiwiZW1haWwiOiJXcWlueGl1MDFAb3V0bG9vay5jb20iLCJmYW1pbHlfbmFtZSI6IldhbmciLCJnaXZlbl9uYW1lIjoiUWlueGl1IiwiaWRwIjoibGl2ZS5jb20iLCJpZHR5cCI6InVzZXIiLCJpcGFkZHIiOiI4MS40MC4yMjAuMTY0IiwibmFtZSI6IlFpbnhpdSBXYW5nIiwib2lkIjoiYTBhMjhjZTctMGRmMS00OTcxLWI5MDYtYTZkZGY2MzBhMTJkIiwicGxhdGYiOiIzIiwicHVpZCI6IjEwMDMyMDAxNjQxMDJGQUEiLCJyaCI6IjAuQVF3QWdYdEdhTWxpNkUtQU9RcXlnUG1TaXVNMlVMQkNtX05CcUVndHlOU2FnRjRNQUJBLiIsInNjcCI6IlVzZXIuUmVhZCBwcm9maWxlIG9wZW5pZCBlbWFpbCIsInNpZ25pbl9zdGF0ZSI6WyJrbXNpIl0sInN1YiI6IkU3dEF2S1RkZERKd25NdVNjV3ZVQ3p3am1zUVNOczh5V0JfTTVGN0pQY2ciLCJ0ZW5hbnRfcmVnaW9uX3Njb3BlIjoiRVUiLCJ0aWQiOiI2ODQ2N2I4MS02MmM5LTRmZTgtODAzOS0wYWIyODBmOTkyOGEiLCJ1bmlxdWVfbmFtZSI6ImxpdmUuY29tI1dxaW54aXUwMUBvdXRsb29rLmNvbSIsInV0aSI6InFMVFlBUkEySmstUlRReC1wX3N4QUEiLCJ2ZXIiOiIxLjAiLCJ3aWRzIjpbIjYyZTkwMzk0LTY5ZjUtNDIzNy05MTkwLTAxMjE3NzE0NWUxMCIsImI3OWZiZjRkLTNlZjktNDY4OS04MTQzLTc2YjE5NGU4NTUwOSJdLCJ4bXNfc3QiOnsic3ViIjoiSHF1WGdSYUNXeEFJam56YXF4eldnNUJUUW5uWkp3WTdMcktPcllnajZDQSJ9LCJ4bXNfdGNkdCI6MTYyNzMxMzI1NH0.GnpORdpXLWBXv3kNZO_sC9ijmfEUAOKgjI8DhipX5Col5DL1LleLvJcIeA1Wk66_fU9GDUwympIP0U5BYy6IfPd4DjD0AuOmkp4iDjhfCDvf7pfZndMn0ynlhfjOEqAHABPrnPRylBwDCY5wACdDcs7wgHDZo4toEEGy-r4bLt_3DwkqhQXMQManZ82QCv6bdoj8JfWRHv4v3cpu0iyWYXtx1dQ-G5edXbqmy63u95L-kjjdVzYFVVriCYjassqz0c_I2-gCkOC6yBpbKMCRO-FNMPD6HIgORotrrMGB6AXQHuQgKIbiSFiuWRyyt0BECpyjFsi1nHgZYMtC8NWmdw";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(
                uri, HttpMethod.GET, requestEntity, String.class);

        Map<String, String> jsonMap = json2map(response.getBody());
        System.out.println(jsonMap);
    }



    public static Map<String, String> json2map(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.readValue(jsonString, Map.class);
    }


}

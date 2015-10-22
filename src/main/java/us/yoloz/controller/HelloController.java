package us.yoloz.controller;

/**
 * Created with IntelliJ IDEA.
 * User: tienhd
 * Date: 9/29/15
 * Time: 8:14 AM
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController extends ApiController {

    @RequestMapping(value = API_V1_ENDPOINT + "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HelloweenResponse> hello(Principal principal) {

        return new ResponseEntity<HelloweenResponse>(
                new HelloweenResponse("Happy Halloween, " + principal.getName() + "!"), HttpStatus.OK);
    }

    public static class HelloweenResponse {
        private String message;
        public HelloweenResponse(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
    }
}

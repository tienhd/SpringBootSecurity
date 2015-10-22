package us.yoloz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 * User: tienhd
 * Date: 9/29/15
 * Time: 10:39 PM
 */
@RestController
public class MetricController extends ApiController
{
    @RequestMapping(value = METRIC_ENDPOINT + "/hello", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> hello(Principal principal) {
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}

package Server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/justrun")
public class DataController {

/*
* obtiene los datos por medio de un request en formato json
*/
	@ResponseBody
    @RequestMapping(value = "/data", method={GET})
    public ResponseEntity<String> dataFetching(@PathVariable Data data ) {
    	if(data!=null)
    		return new ResponseEntity<String>(HttpStatus.OK);
    	else
    		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
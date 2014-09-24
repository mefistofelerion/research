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
    @RequestMapping(value = "/data", method={RequestMethod.GET})
    public ResponseEntity<String> dataFetching(@PathVariable Data data ) {
    	if(data!=null)
    		return new ResponseEntity<String>(HttpStatus.OK);
    	else
    		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);

/*
* esta verifica si la conexion se encuentra bien
*/

	@ResponseBody
	@RequestMapping(value="/data/works", method={RequestMethod.GET})
	public ResponseEntity<Boolean> isWorking(){
		if()//datos se pueden guardar o accesar
			return true;
		else{
			return false;
			}
		}

/*
*puede regresar informacion mediante el id solicitado
*/
	@ResponseBody
	@RequestMapping(value="/data/info/{id}", method={RequestMethod.GET}){
		public ResponseEntity<Data> dataObtaining(@PathVariable String id){
		//checar si hay conexion al database o archivo
		//hacer un query con el id
			Data info;//aqui pondre la información proveniente del query
			return ResponseEntity<Data> info;
		}
	}
}
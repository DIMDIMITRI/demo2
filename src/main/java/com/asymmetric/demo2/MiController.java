package com.asymmetric.demo2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asymmetric.demo2.model.Estudiante;
import com.asymmetric.demo2.repositorios.EstudianteRepository;

//Anotamos con RestController para que Spring sepa que esto entrega REST
//en pom.xml agregamos Jasper... con esto este controlador opera JSON nativo
@RestController
public class MiController {

	//acá le decimos a Spring que debe instanciar un repositorio para Estudiantes. c
	//con este repositorio tenemos la funcionalidad de búsqueda, creación, borrado de registros en la base de datos
	@Autowired
	EstudianteRepository repo;
	
	//acá indicamos que se reciben las solicitudes tipo POST como http://localhost:8080/agregar con parámetros nombre=Dimitri
	//donde "nombre" es como llamamos el parámetro y "Dimitri" es el dato que pasamos de parámetro
	//"Dimitri" entra a nn como parámetro del método
	@PostMapping ("agregar")
	public Estudiante agregar (@RequestParam ("nombre") String nn) {
		
		//acá creamos un nuevo POJO estudiante
		Estudiante estudiante = new Estudiante();
		
		//acá fijamos su nombre. Recuerden que id lo marcamos como generado automático 
		//por lo tanto no hay que darle un valor. se va incrementando solo
		estudiante.setNombre(nn);
		
		//acá repositorio nos ayuda a guardar en la base de datos al nuevo estudiante
		repo.save(estudiante);
	
		//este return básicamente muestra al usuario final lo que se guardó
		return estudiante;
	}
	
	//GetMapping atiende una solitud GET de listado de todos los estudiantes de la tabla
	//desde el browser se usa como: http://localhost:8080/listar
	//el método regresa JSON
	@GetMapping ("listar")
	public List<Estudiante> listar (){
		
		//así el repositorio nos encuentra y manda todos los registros
		return repo.findAll();
		
	}
	
	//este encuentra a un solo estudiante por ID. 
	//checa que el valor retornado es Optional
	@GetMapping("estudiante/{id}")
	public Optional <Estudiante> getEstudiante(@PathVariable ("id") int id){
		return repo.findById(id);
	}
	
	
}

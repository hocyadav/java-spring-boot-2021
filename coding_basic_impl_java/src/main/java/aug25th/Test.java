package aug25th;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//https://www.baeldung.com/jackson-object-mapper-tutorial

class Car {
    private String color;
    private String type;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Car [color=" + color + ", type=" + type + "]";
	}
	
	
}

public class Test {
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		Set<String> set = new HashSet<String>();
		set.add("hari");
		set.add("om");
		
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		Car car = new Car();
		car.setColor("green");
		car.setType("SUV");
		
		//serialize : obj -> json
		ObjectMapper objectMapper = new ObjectMapper();//1 object mapper object
		objectMapper.writeValue(new File("car.json"), car);//2. serialize : java obj -> json : call writeValue api
		
		//desrialize : json -> obj
		String jsonDesrializeValue = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
		Car carObj = objectMapper.readValue(jsonDesrializeValue, Car.class);
		System.out.println(carObj);
		
		//json file -> java object
		Car readValue = objectMapper.readValue(new File("car.json"), Car.class);
		System.out.println(readValue);
		
		
	}

}
/**
Car [color=Black, type=BMW]
Car [color=green, type=SUV]

 * 
 */

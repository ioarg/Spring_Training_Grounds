package components.controllers.rest_proj1;

import model.common.TestSeeder;
import model.rest.students.Student;
import model.rest.students.StudentErrorResponse;
import model.rest.students.StudentNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/rest_proj1")
public class StudentRestController {
    @Autowired
    private TestSeeder testSeeder;
    private List<Student> students;
    private final Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @PostConstruct
    public void seedData(){
        students = testSeeder.seedStudents();
    }

    @GetMapping("/hello")
    public String getHelloMessage(){
        return "Hello REST";
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        Student student = null;
        if((studentId <0) || (studentId >= students.size())){
            throw new StudentNotFoundException("Student id "+studentId+" invalid");
        }
        student = students.get(studentId);
        return student;
    }

    /* Handle the out of bounds exception with a handler*/
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleStudentException(StudentNotFoundException exc){
        //Create the error response object
        StudentErrorResponse sterr = new StudentErrorResponse();
        sterr.setStatus(HttpStatus.NOT_FOUND.value());
        sterr.setMessage(exc.getMessage());
        sterr.setTimestamp(System.currentTimeMillis());
        //Create the response entity
        ResponseEntity<StudentErrorResponse> response = new ResponseEntity<>(sterr, HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleGeneralStudentException(Exception exc){
        //Create the error response object
        StudentErrorResponse sterr = new StudentErrorResponse();
        sterr.setStatus(HttpStatus.BAD_REQUEST.value());
        sterr.setMessage(exc.getMessage());
        sterr.setTimestamp(System.currentTimeMillis());
        //Create the response entity
        ResponseEntity<StudentErrorResponse> response = new ResponseEntity<>(sterr, HttpStatus.BAD_REQUEST);
        return response;
    }
}

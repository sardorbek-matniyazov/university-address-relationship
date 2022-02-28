package university.controller.university;

import org.springframework.web.bind.annotation.*;
import university.dto.university.StudentDto;
import university.entity.university.Student;
import university.service.university.StudentService;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Student> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student get(@PathVariable(value = "id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable(value = "id") Long id, @RequestBody Student student){
        return service.edit(id, student);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody StudentDto dto){
        return service.add(dto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        return service.delete(id);
    }
}

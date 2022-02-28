package university.controller.university;

import org.springframework.web.bind.annotation.*;
import university.dto.university.TeacherDto;
import university.entity.university.Teacher;
import university.service.university.TeacherService;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Teacher> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Teacher get(@PathVariable(value = "id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable(value = "id") Long id, @RequestBody Teacher teacher){
        return service.edit(id, teacher);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody TeacherDto dto){
        return service.add(dto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        return service.delete(id);
    }

}

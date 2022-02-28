package university.controller.university;


import org.springframework.web.bind.annotation.*;
import university.dto.university.FacultyDto;
import university.entity.university.Faculty;
import university.service.university.FacultyService;

import java.util.List;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {
    private final FacultyService service;

    public FacultyController(FacultyService service) {
        this.service = service;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Faculty> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Faculty get(@PathVariable(value = "id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable(value = "id") Long id, @RequestBody Faculty faculty){
        return service.edit(id, faculty);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody FacultyDto dto){
        return service.add(dto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        return service.delete(id);
    }

}

package university.controller.university;

import org.springframework.web.bind.annotation.*;
import university.dto.university.UniversityDto;
import university.entity.university.University;
import university.service.university.UniversityService;

import java.util.List;

@RestController
@RequestMapping(value = "/university")
public class UniversityController {
    private final UniversityService service;

    public UniversityController(UniversityService service) {
        this.service = service;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<University> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public University get(@PathVariable(value = "id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable(value = "id") Long id, @RequestBody University university){
        return service.edit(id, university);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody UniversityDto dto){
        return service.add(dto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        return service.delete(id);
    }

}

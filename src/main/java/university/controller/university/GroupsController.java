package university.controller.university;

import org.springframework.web.bind.annotation.*;
import university.dto.university.GroupsDto;
import university.entity.university.Groups;
import university.service.university.GroupsService;

import java.util.List;

@RestController
@RequestMapping(value = "/groups")
public class GroupsController {
    public GroupsController(GroupsService service) {
        this.service = service;
    }

    private final GroupsService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Groups> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Groups get(@PathVariable(value = "id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable(value = "id") Long id, @RequestBody Groups groups){
        return service.edit(id, groups);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody GroupsDto dto){
        return service.add(dto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id){
        return service.delete(id);
    }

}

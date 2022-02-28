package university.controller.address;

import org.springframework.web.bind.annotation.*;
import university.entity.address.Continent;
import university.service.address.ContinentService;

import java.util.List;

@RestController
@RequestMapping("/continent")
public class ContinentController {

    private final ContinentService service;

    public ContinentController(ContinentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Continent> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Continent get(@PathVariable("id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody Continent continent){
        return service.add(continent);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable("id") Long id, @RequestBody Continent continent){
        return service.edit(id, continent);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String edit(@PathVariable("id") Long id){
        return service.delete(id);
    }

}

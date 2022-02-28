package university.controller.address;

import org.springframework.web.bind.annotation.*;
import university.dto.address.CountryDto;
import university.entity.address.Country;
import university.service.address.CountryService;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @RequestMapping("/all")
    public List<Country> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Country get(@PathVariable("id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody CountryDto dto){
        return service.add(dto);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable("id") Long id, @RequestBody Country country){
        return service.edit(id, country);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        return service.delete(id);
    }
}

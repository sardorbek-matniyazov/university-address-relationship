package university.controller.address;

import org.springframework.web.bind.annotation.*;
import university.dto.address.RegionDto;
import university.entity.address.Region;
import university.service.address.RegionService;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    private final RegionService service;

    public RegionController(RegionService service) {
        this.service = service;
    }

    @RequestMapping("/all")
    public List<Region> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Region get(@PathVariable("id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody RegionDto dto){
        return service.add(dto);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable("id") Long id, @RequestBody Region region){
        return service.edit(id, region);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        return service.delete(id);
    }
}

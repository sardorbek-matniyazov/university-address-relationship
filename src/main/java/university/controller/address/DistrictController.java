package university.controller.address;


import org.springframework.web.bind.annotation.*;
import university.dto.address.DistrictDto;
import university.entity.address.District;
import university.service.address.DistrictService;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {
    private final DistrictService service;

    public DistrictController(DistrictService service) {
        this.service = service;
    }

    @RequestMapping("/all")
    public List<District> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public District get(@PathVariable("id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody DistrictDto dto){
        return service.add(dto);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable("id") Long id, @RequestBody District district){
        return service.edit(id, district);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        return service.delete(id);
    }
}

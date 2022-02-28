package university.controller.address;

import org.springframework.web.bind.annotation.*;
import university.dto.address.AddressDto;
import university.entity.address.Address;
import university.service.address.AddressService;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @RequestMapping("/all")
    public List<Address> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Address get(@PathVariable("id") Long id){
        return service.get(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestBody AddressDto dto){
        return service.add(dto);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public String edit(@PathVariable("id") Long id, @RequestBody Address address){
        return service.edit(id, address);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id){
        return service.delete(id);
    }
}

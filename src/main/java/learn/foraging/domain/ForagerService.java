package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
@Service
public class
ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public Result<Forager> add(Forager forager) throws DataException {
        Result <Forager> result= validate(forager);
        if(!result.isSuccess()){
            return result;
        }
        result.setPayload(repository.add(forager));

        return result;
    }
    public Result validate(Forager forager){
        Result<Forager> result = new Result<>();
        if(forager == null){
            result.addErrorMessage("Nothing to save");
        }
        if(forager.getFirstName()==null || forager.getFirstName().isBlank()){
            result.addErrorMessage("Firstname is required");
        }
        if(forager.getLastName()==null || forager.getLastName().isBlank()){
            result.addErrorMessage("Lastname is required");
        }
        if(forager.getState()==null || forager.getState().isBlank()){
            result.addErrorMessage("State is required");
        }


        return result;
    }
}

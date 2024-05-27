package learn.foraging.data;

import learn.foraging.models.Forage;
import learn.foraging.models.Forager;

import java.util.List;

public interface ForagerRepository {
    Forager findById(String id);

    List<Forager> findAll();
    Forager add(Forager forager) throws DataException;

    List<Forager> findByState(String stateAbbr);
}

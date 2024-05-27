package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForagerServiceTest {
    ForagerService service = new ForagerService(new ForagerRepositoryDouble());
    @Test
    void shouldAdd() throws DataException{
        Forager forager = ForagerRepositoryDouble.FORAGER;
        Result<Forager> resultq=service.add(forager);
        assertTrue(resultq.isSuccess());
    }


}

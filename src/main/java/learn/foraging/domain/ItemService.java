package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ItemRepository;
import learn.foraging.models.Category;
import learn.foraging.models.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import static learn.foraging.models.Category.INEDIBLE;
import static learn.foraging.models.Category.POISONOUS;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public List<Item> findByCategory(Category category) {
        return repository.findAll().stream()
                .filter(i -> i.getCategory() == category)
                .collect(Collectors.toList());
    }

    public Result<Item> add(Item item) throws DataException {

        Result<Item> result = new Result<>();
        if (item == null) {
            result.addErrorMessage("Item must not be null.");
            return result;
        }

        if (item.getName() == null || item.getName().isBlank()) {
            result.addErrorMessage("Item name is required.");
        } else if (repository.findAll().stream()
                .anyMatch(i -> i.getName().equalsIgnoreCase(item.getName()))) {
            result.addErrorMessage(String.format("Item '%s' is a duplicate.", item.getName()));
        }

        if (item.getDollarPerKilogram() == null) {
            result.addErrorMessage("$/Kg is required.");
        } else if (item.getDollarPerKilogram().compareTo(BigDecimal.ZERO) < 0
                || item.getDollarPerKilogram().compareTo(new BigDecimal("7500.00")) > 0) {
            result.addErrorMessage("%/Kg must be between 0.00 and 7500.00.");
        }
        if ((Objects.equals(item.getCategory(), INEDIBLE) && item.getDollarPerKilogram().compareTo(BigDecimal.ZERO) > 0)
                || (Objects.equals(item.getCategory(), POISONOUS) && item.getDollarPerKilogram().compareTo(BigDecimal.ZERO) > 0)) {
            result.addErrorMessage("Inedible & Poisonous items cannot have a value > 0");
        }




        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(item));

        return result;
    }
}

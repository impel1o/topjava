package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.NamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    //boolean ?
    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        if (repository.containsKey(id))
        {
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew())
        {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(),user);
        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        if (!repository.containsKey(id))
        {
            return null;
        }
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        Comparator<User> userComparator = (User o1, User o2) -> {
            if (o1.getName().equalsIgnoreCase(o2.getName()))
            {
                return o1.getEmail().compareTo(o2.getEmail());
            } else
                return o1.getName().compareTo(o2.getName());
        };

        if (repository.isEmpty())   return Collections.emptyList();
        else
        {
            return repository.values().stream()
                    .sorted(userComparator)
                    .collect(Collectors.toList());
        }

    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return repository.values().stream().filter(x->email.equals(x)).findFirst().orElse(null);
    }
}

package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";


    @Autowired
    public MealRestController(MealService service) {
        super(service);
    }

    @Override
    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll()
    {
        return super.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable("id") int id) {
        //id = это ид еды , ид пользователя берется из AuthorizedUser.id()
        return super.get(id);
    }

    //обычнй запрос через браузер это Get
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") int id)
    {
        super.delete(id);
    }

    @GetMapping(value = "/filter/{startDate}/{startTime}/{endDate}/{endTime}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> filter(
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("startDate") LocalDate startDate,
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @PathVariable("startTime") LocalTime startTime,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("endDate") LocalDate endDate,
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)@PathVariable("endTime") LocalTime endTime)
    {
        return super.getBetween(startDate,startTime,endDate,endTime);
    }

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createM(@RequestBody Meal meal)
    {
        Meal created=super.create(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable("id") int id)
    {
        super.update(meal,id);
    }

}


//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public void update(@RequestBody User user, @PathVariable("id") int id) {
//        super.update(user, id);
//    }



//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
//        User created = super.create(user);
//
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setLocation(uriOfNewResource);
//
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{id}")
//                .buildAndExpand(created.getId()).toUri();
//
//        return ResponseEntity.created(uriOfNewResource).body(created);
//    }



//    static final String REST_URL = "/rest/meals";

//TODO сделать getAll/get/delete/update

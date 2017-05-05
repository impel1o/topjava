package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class RootController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:meals";
    }

    //@RequestMapping(value ="/meals",method = RequestMethod.GET)





    //todo update / delete / create

//    @RequestMapping("/remove/{id}")
//    public String removeUser(@PathVariable("id")int id)
//    {
//        this.userService.removeUser(id);
//        return  "redirect:/users/1";
//    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//
//        switch (action == null ? "all" : action) {
//            case "delete":
//                int id = getId(request);
//                mealController.delete(id);
//                response.sendRedirect("meals");
//                break;
//            case "create":
//            case "update":
//                final Meal meal = "create".equals(action) ?
//                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
//                        mealController.get(getId(request));
//                request.setAttribute("meal", meal);
//                request.getRequestDispatcher("/meal.jsp").forward(request, response);
//                break;
//            case "all":
//            default:
//                request.setAttribute("meals", mealController.getAll());
//                request.getRequestDispatcher("/meals.jsp").forward(request, response);
//                break;
//        }

}

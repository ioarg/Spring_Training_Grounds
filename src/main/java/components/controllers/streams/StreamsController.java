package components.controllers.streams;

import model.common.ListUtils;
import model.common.TestSeeder;
import model.streams.Dish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("streams_training")
public class StreamsController {
    private static final Logger console = LoggerFactory.getLogger(StreamsController.class);
    @Autowired
    private TestSeeder testSeeder;
    private List<Dish> dishes;
    //private int visits;

    /******************************************************
     *  Initialization
     * ****************************************************/
    @PostConstruct
    public void init(){
        dishes = testSeeder.seedDishes();
        console.info("****** Init Executed ******");
        ListUtils.printList(dishes, console);
        //visits = 0;
    }

    /******************************************************
     *  Mappings
     * ****************************************************/

    @GetMapping("")
    public String getStreamTrainingHome(Model model){
        /*visits ++;
        console.info(String.valueOf(visits));*/
        model.addAttribute("menu", dishes);
        return "streams";
    }

    @GetMapping(path = "/async", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Dish> getFilteredMeals(@RequestParam String filter){
        List<Dish> filteredMeals = null;
        switch (filter){
            case "highCalories" : filteredMeals = getHighCaloricMeals(); break;
            default: break;
        }
        return filteredMeals;
    }

    /******************************************************
     *  Internal Functionality
     * ****************************************************/

    private List<Dish> getHighCaloricMeals(){
        List<Dish> highCaloricMeals = dishes.stream()
                .filter((d)->d.getCalories() > 300)
                .collect(Collectors.toList());
        return highCaloricMeals;
    }
}

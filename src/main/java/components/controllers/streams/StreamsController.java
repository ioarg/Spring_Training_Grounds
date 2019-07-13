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

    @PostConstruct
    public void init(){
        dishes = testSeeder.seedDishes();
        console.info("****** Init Executed ******");
        ListUtils.printList(dishes, console);
    }

    @GetMapping("")
    public String getStreamTrainingHome(Model model){
        List<String> highCaloricMeals = dishes.stream()
                .filter((d)->d.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
        model.addAttribute("menu", dishes);
        model.addAttribute("high_caloric_meals", highCaloricMeals);
        return "streams";
    }

}

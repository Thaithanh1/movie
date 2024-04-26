package movie.controller;

import movie.payload.dto.FoodDTO;
import movie.payload.request.FoodRequest;
import movie.payload.response.ResponseObject;
import movie.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/food/")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @RequestMapping(value = "addfood", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<FoodDTO> addFood(@RequestBody FoodRequest request) {
        return foodService.addFood(request);
    }

    @RequestMapping(value = "editfood", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<FoodDTO> editFood(@RequestParam int foodID, @RequestBody FoodRequest request) {
        return foodService.editFood(foodID, request);
    }

    @RequestMapping(value = "deletefood", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<FoodDTO> deleteFood(@RequestParam int foodID) {
        return foodService.deleteFood(foodID);
    }
}

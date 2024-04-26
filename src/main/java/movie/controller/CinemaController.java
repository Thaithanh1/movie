package movie.controller;

import movie.payload.dto.CinemaDTO;
import movie.payload.request.CinemaRequest;
import movie.payload.response.ResponseObject;
import movie.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cinema/")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;
    @RequestMapping(value = "addcinema", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<CinemaDTO> addCinema(@RequestBody CinemaRequest request) {
        return cinemaService.addCinema(request);
    }
    @RequestMapping(value = "editcinema", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<CinemaDTO> editCinema(@RequestParam int cinemaID, @RequestBody CinemaRequest request) {
        return cinemaService.editCinema(cinemaID, request);
    }
    @RequestMapping(value = "deletecinema", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<CinemaDTO> deleteCinema(@RequestParam int cinemaID) {
        return cinemaService.deleteCinema(cinemaID);
    }
}


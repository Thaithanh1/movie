package movie.controller;

import movie.entity.Bill;
import movie.payload.request.OrderRequest;
import movie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order/")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "cinema", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object[]>> getSalesStatisticsByCinemaAndTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

        List<Object[]> salesStatistics = orderService.calculateSalesStatisticsByCinemaAndTimeRange(startTime, endTime);
        return ResponseEntity.ok(salesStatistics);
    }
    @PostMapping("add")
    public ResponseEntity<?> addBill(@RequestBody OrderRequest request) {
        orderService.addBill(request);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Thêm bill thành công");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

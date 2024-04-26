package movie.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import movie.entity.Bill;
import movie.entity.BillFood;
import movie.entity.BillTicket;
import movie.payload.request.OrderRequest;
import movie.repository.BillFoodRepository;
import movie.repository.BillRepository;
import movie.repository.BillTicketRepository;
import movie.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillFoodRepository billFoodRepository;
    @Autowired
    private BillTicketRepository billTicketRepository;
    @Autowired
    private FoodRepository foodRepository;

    public List<Object[]> calculateSalesStatisticsByCinemaAndTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return billRepository.calculateSalesStatisticsByCinemaAndTimeRange(startTime, endTime);
    }
}

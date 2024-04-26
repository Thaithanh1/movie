package movie.repository;

import movie.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    @Query("SELECT c.nameOfCinema, SUM(t.priceTicket) " +
            "FROM Cinema c " +
            "JOIN c.rooms r " +
            "JOIN r.schedules s " +
            "JOIN s.tickets t " +
            "JOIN t.billTickets bt " +
            "JOIN bt.bill b " +
            "WHERE b.createTime BETWEEN :startTime AND :endTime " +
            "GROUP BY c.nameOfCinema")
    List<Object[]> calculateSalesStatisticsByCinemaAndTimeRange(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}

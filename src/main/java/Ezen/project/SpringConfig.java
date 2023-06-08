package Ezen.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Ezen.project.repository.JpaPaymentRepository;
import Ezen.project.repository.JpaRoomRepository;
import Ezen.project.repository.JpaSampleRepository;
import Ezen.project.repository.PaymentRepository;
import Ezen.project.repository.RoomRepository;
import Ezen.project.repository.SampleRepository;
import Ezen.project.service.RoomService;
import Ezen.project.service.PaymentService;
import Ezen.project.service.SampleService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    @Autowired
    private EntityManager em;

    @Bean
    public SampleService sampleService() {
        return new SampleService(sampleRepository());
    }

    @Bean
    public SampleRepository sampleRepository() {
        return new JpaSampleRepository(em);
    }

    @Bean
    public RoomService roomService() {
        return new RoomService(roomRepository());
    }

    @Bean
    public RoomRepository roomRepository() {
        return new JpaRoomRepository(em);
    }

    @Bean
    public PaymentService paymentsService() {
        return new PaymentService(paymentRepository());
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new JpaPaymentRepository(em);
    }
}

package com.github.tomaszplonski.mes_project.controller;


import com.github.tomaszplonski.mes_project.model.ExpansionJoint;
import com.github.tomaszplonski.mes_project.model.Order;
import com.github.tomaszplonski.mes_project.repository.ExpansionJointRepository;
import com.github.tomaszplonski.mes_project.repository.SupervisorRepository;
import com.github.tomaszplonski.mes_project.repository.OrderRepository;
import com.github.tomaszplonski.mes_project.repository.PurchaserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final SupervisorRepository supervisorRepository;
    private final PurchaserRepository purchaserRepository;
    private final OrderRepository orderRepository;
    private final ExpansionJointRepository expansionJointRepository;


    @GetMapping("/test")
    public void test(){

        List<ExpansionJoint> expansionJointList = new ArrayList<>();

        ExpansionJoint e1 = new ExpansionJoint();
        expansionJointList.add(e1);

        e1.setLength(12.50);
        e1.setBridgeName("WD-1");
        e1.setType("80N");

        e1.setStarOfProduction(LocalDate.now());
        e1.setEstimatedTimes();

        Optional<Order> order = orderRepository.findById(1L);
        order.ifPresent(e1::setOrder);

     //=======================================================//

        ExpansionJoint e2 = new ExpansionJoint();
        expansionJointList.add(e2);

        e2.setLength(20.2);
        e2.setBridgeName("WD-2");
        e2.setType("80N");

        e2.setStarOfProduction(LocalDate.now().minusDays(25));
        e2.setEstimatedTimes();

        e2.setActualEndOfAnchoringWelding(e2.getStarOfProduction().plusDays(13));
        e2.setActualEndOfAnchoringWelding(e2.getStarOfProduction().plusDays(16));
        e2.setActualEndOfMakingOfAntiCorrosionProtection(e2.getStarOfProduction().plusDays(20));
        e2.setActualEndOfProduction(e2.getStarOfProduction().plusDays(24));

        Optional<Order> order2 = orderRepository.findById(1L);
        order2.ifPresent(e2::setOrder);

        //=======================================================//

        ExpansionJoint e3 = new ExpansionJoint();
        expansionJointList.add(e3);

        e3.setLength(27);
        e3.setBridgeName("MS-1");
        e3.setType("80N");

        e3.setStarOfProduction(LocalDate.now().minusDays(10));
        e3.setEstimatedTimes();

        e3.setActualEndOfAnchoringWelding(e3.getStarOfProduction().plusDays(10));

        Optional<Order> order3 = orderRepository.findById(1L);
        order3.ifPresent(e3::setOrder);

        //=======================================================//

        ExpansionJoint e4 = new ExpansionJoint();
        expansionJointList.add(e4);

        e4.setLength(9.51);
        e4.setBridgeName("MS-2");
        e4.setType("100N");

        e4.setStarOfProduction(LocalDate.now().minusDays(4));
        e4.setEstimatedTimes();


        Optional<Order> order4 = orderRepository.findById(2L);
        order4.ifPresent(e4::setOrder);

        //=======================================================//

        ExpansionJoint e5 = new ExpansionJoint();
        expansionJointList.add(e5);

        e5.setLength(22.02);
        e5.setBridgeName("WD-1");
        e5.setType("100S");

        e5.setStarOfProduction(LocalDate.now().minusDays(14));
        e5.setEstimatedTimes();

        e5.setActualEndOfAnchoringWelding(e5.getStarOfProduction().plusDays(13));

        Optional<Order> order5 = orderRepository.findById(3L);
        order5.ifPresent(e5::setOrder);

        //=======================================================//

        ExpansionJoint e6 = new ExpansionJoint();
        expansionJointList.add(e6);

        e6.setLength(19.98);
        e6.setBridgeName("WK-25");
        e6.setType("80S");

        e6.setStarOfProduction(LocalDate.now().minusDays(35));
        e6.setEstimatedTimes();

        e6.setActualEndOfAnchoringWelding(e6.getStarOfProduction().plusDays(10));
        e6.setActualEndOfAnchoringWelding(e6.getStarOfProduction().plusDays(13));
        e6.setActualEndOfMakingOfAntiCorrosionProtection(e6.getStarOfProduction().plusDays(16));
        e6.setActualEndOfProduction(e6.getStarOfProduction().plusDays(20));

        Optional<Order> order6 = orderRepository.findById(3L);
        order6.ifPresent(e6::setOrder);

        //=======================================================//

        ExpansionJoint e7 = new ExpansionJoint();
        expansionJointList.add(e7);

        e7.setLength(31.51);
        e7.setBridgeName("PDZG-1");
        e7.setType("80S");

        e7.setStarOfProduction(LocalDate.now().minusDays(30));
        e7.setEstimatedTimes();

        e7.setActualEndOfAnchoringWelding(e7.getStarOfProduction().plusDays(12));
        e7.setActualEndOfAnchoringWelding(e7.getStarOfProduction().plusDays(15));
        e7.setActualEndOfMakingOfAntiCorrosionProtection(e7.getStarOfProduction().plusDays(19));
        e7.setActualEndOfProduction(e7.getStarOfProduction().plusDays(22));

        Optional<Order> order7 = orderRepository.findById(4L);
        order7.ifPresent(e7::setOrder);

        //=======================================================//

        ExpansionJoint e8 = new ExpansionJoint();
        expansionJointList.add(e8);

        e8.setLength(11.05);
        e8.setBridgeName("Kładka-5");
        e8.setType("100N");

        e8.setStarOfProduction(LocalDate.now().minusDays(14));
        e8.setEstimatedTimes();

        e8.setActualEndOfAnchoringWelding(e8.getStarOfProduction().plusDays(12));
        e8.setActualEndOfAnchoringWelding(e8.getStarOfProduction().plusDays(13));

        Optional<Order> order8 = orderRepository.findById(4L);
        order8.ifPresent(e8::setOrder);

       // =======================================================//

        ExpansionJoint e9 = new ExpansionJoint();
        expansionJointList.add(e9);

        e9.setLength(13.27);
        e9.setBridgeName("PZD_G-9");
        e9.setType("100N");

        e9.setStarOfProduction(LocalDate.now().minusDays(20));
        e9.setEstimatedTimes();

        e9.setActualEndOfAnchoringWelding(e9.getStarOfProduction().plusDays(10));
        e9.setActualEndOfAnchoringWelding(e9.getStarOfProduction().plusDays(13));
        e9.setActualEndOfMakingOfAntiCorrosionProtection(e9.getStarOfProduction().plusDays(16));
        e9.setActualEndOfProduction(e9.getStarOfProduction().plusDays(20));

        Optional<Order> order9 = orderRepository.findById(5L);
        order9.ifPresent(e9::setOrder);

        //=======================================================//

        ExpansionJoint e10 = new ExpansionJoint();
        expansionJointList.add(e10);

        e10.setLength(17.83);
        e10.setBridgeName("OPD");
        e10.setType("100S");

        e10.setStarOfProduction(LocalDate.now().minusDays(13));
        e10.setEstimatedTimes();

        e10.setActualEndOfAnchoringWelding(e10.getStarOfProduction().plusDays(9));
        e10.setActualEndOfAnchoringWelding(e10.getStarOfProduction().plusDays(12));

        Optional<Order> order10 = orderRepository.findById(4L);
        order10.ifPresent(e10::setOrder);

        expansionJointRepository.saveAll(expansionJointList);

    }
}

package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.entity.Payment;
import com.main.entity.PaymentOrderResponseDTO;
import com.main.entity.PaymentRequestDTO;
import com.main.service.PaymentInterface;

@RestController
@RequestMapping("/buyer")
@CrossOrigin(origins = "http://localhost:5173")
public class Payment_Controller {

    @Autowired
    PaymentInterface s;

    @PostMapping("/car/payment/add/{user_id}/{car_id}")
    public ResponseEntity<PaymentOrderResponseDTO> insertpayment(
            @PathVariable int user_id,
            @PathVariable int car_id,
            @RequestBody PaymentRequestDTO dto) {

        PaymentOrderResponseDTO response =
                s.insertdeta(user_id, car_id, dto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.efecto.bar_efecto_backend.controller;

import com.efecto.bar_efecto_backend.dto.PaymentMethodDTO;
import com.efecto.bar_efecto_backend.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @GetMapping
    public List<PaymentMethodDTO> getPaymentMethods () {
        return paymentMethodService.getPaymentMethod();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<PaymentMethodDTO> getPaymentMethodById (@PathVariable long id) {
        return new ResponseEntity<>(paymentMethodService.getPaymentMethodById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod (@RequestBody PaymentMethodDTO paymentMethodDTO) {
        System.out.println(paymentMethodDTO); // Verifica qué llega realmente
        return new ResponseEntity<>(paymentMethodService.createPaymentMethod(paymentMethodDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodDTO> updatePaymentMethod (@PathVariable long id, @RequestBody PaymentMethodDTO paymentMethodDTO) {
        return new ResponseEntity<>(paymentMethodService.updatePaymentMethod(paymentMethodDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaymentMethod (@PathVariable long id) {
        paymentMethodService.deletePaymentMethod(id);
        return new ResponseEntity<>("Medio de Pago Eliminado", HttpStatus.OK);
    }
}

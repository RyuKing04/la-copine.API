package com.la_copine.api.controller;


import com.la_copine.api.model.PaymentMethod;
import com.la_copine.api.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-method")
public class PaymentMethodController {
    @Autowired
    PaymentMethodService paymentMethodService;

    @RequestMapping()
    public List<PaymentMethod> getAll() {
        return paymentMethodService.getAll();
    }

    @RequestMapping("/{id}")
    public PaymentMethod getById(int id) {
        return paymentMethodService.getById(id);
    }

    @PostMapping()
    public PaymentMethod save(PaymentMethod paymentMethod) {

        return paymentMethodService.save(paymentMethod);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        paymentMethodService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> update(@RequestBody PaymentMethod paymentMethod) {

        return ResponseEntity.ok(paymentMethodService.update(paymentMethod));
    }

}

package com.la_copine.api.service;

import com.la_copine.api.model.PaymentMethod;
import com.la_copine.api.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {
     @Autowired
     PaymentMethodRepository paymentMethodRepository;

        public List<PaymentMethod> getAll() {
            return paymentMethodRepository.findAll();
        }

        public PaymentMethod getById(int id) {
            return paymentMethodRepository.findById(id).orElse(null);
        }

        public PaymentMethod save(PaymentMethod paymentMethod) {
            return paymentMethodRepository.save(paymentMethod);
        }

        public void delete(int id) {
            paymentMethodRepository.deleteById(id);
        }

        public PaymentMethod update(PaymentMethod paymentMethod) {
            return paymentMethodRepository.save(paymentMethod);
        }


}


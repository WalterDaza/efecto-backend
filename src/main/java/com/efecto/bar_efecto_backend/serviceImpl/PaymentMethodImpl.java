package com.efecto.bar_efecto_backend.serviceImpl;

import com.efecto.bar_efecto_backend.dto.PaymentMethodDTO;
import com.efecto.bar_efecto_backend.exceptions.ResourceNotFoundException;
import com.efecto.bar_efecto_backend.mapper.PaymentMethodMapper;
import com.efecto.bar_efecto_backend.model.PaymentMethod;
import com.efecto.bar_efecto_backend.repository.PaymentMethodRepository;
import com.efecto.bar_efecto_backend.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodImpl implements PaymentMethodService {

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Override
    public List<PaymentMethodDTO> getPaymentMethod() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        return paymentMethods.stream().map(paymentMethod -> paymentMethodMapper.toDTO(paymentMethod)).collect(Collectors.toList());
    }

    @Override
    public PaymentMethodDTO getPaymentMethodById(long id) {

        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Medio de Pago", "id", id));

        return paymentMethodMapper.toDTO(paymentMethod);
    }

    @Override
    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO paymentMethodDTO) {

        PaymentMethod paymentMethod = paymentMethodMapper.toEntity(paymentMethodDTO);
        PaymentMethod newPaymentMethod = paymentMethodRepository.save(paymentMethod);

        return paymentMethodMapper.toDTO(newPaymentMethod);
    }

    @Override
    public PaymentMethodDTO updatePaymentMethod(PaymentMethodDTO paymentMethodDTO, long id) {

        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Medio de Pago", "id", id));

        paymentMethod.setNameMethodPayment(paymentMethodDTO.getNameMethodPayment());

        PaymentMethod newPaymentMethod = paymentMethodRepository.save(paymentMethod);

        return paymentMethodMapper.toDTO(newPaymentMethod);
    }

    @Override
    public void deletePaymentMethod(long id) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Medio de Pago", "id", id));
        paymentMethodRepository.delete(paymentMethod);
    }
}

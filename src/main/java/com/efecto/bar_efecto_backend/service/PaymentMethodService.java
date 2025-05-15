package com.efecto.bar_efecto_backend.service;

import com.efecto.bar_efecto_backend.dto.PaymentMethodDTO;

import java.util.List;

public interface PaymentMethodService {

    List<PaymentMethodDTO> getPaymentMethod ();

    PaymentMethodDTO getPaymentMethodById (long id);

    PaymentMethodDTO createPaymentMethod (PaymentMethodDTO paymentMethodDTO);

    PaymentMethodDTO updatePaymentMethod (PaymentMethodDTO paymentMethodDTO, long id);

    void deletePaymentMethod (long id);

}

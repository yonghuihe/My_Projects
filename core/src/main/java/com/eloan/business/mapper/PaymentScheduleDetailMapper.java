package com.eloan.business.mapper;

import com.eloan.business.domain.PaymentScheduleDetail;
import java.util.List;

public interface PaymentScheduleDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PaymentScheduleDetail record);

    PaymentScheduleDetail selectByPaymentScheduleId(Long id);

    List<PaymentScheduleDetail> selectAll();

    int updateByPrimaryKey(PaymentScheduleDetail record);
}
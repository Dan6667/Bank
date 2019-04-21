package bank.service;

import bank.entity.bills.Bill;

import java.util.List;

public interface IBillService {
    Bill findBill(final long id);

    List<Bill> findAllBills();

    void createBill(final Bill bill);

    void updateBill(final Bill bill);

    void deleteBill(final Bill bill);

    void deleteBillById(final long billId);
}
